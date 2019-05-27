package com.ceiec.twmp.tmp.services.impl;

import com.alibaba.fastjson.JSON;
import com.ceiec.twmp.tmp.EConfig;
import com.ceiec.twmp.tmp.cache.redis.DepartmentRedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.common.dict.*;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.entity.TwmpDevDevice;
import com.ceiec.twmp.tmp.entity.TwmpDevLifecycle;
import com.ceiec.twmp.tmp.entity.TwmpDevType;
import com.ceiec.twmp.tmp.mapper.TwmpDevDeviceMapper;
import com.ceiec.twmp.tmp.mapper.TwmpDevLifecycleMapper;
import com.ceiec.twmp.tmp.mapper.TwmpDevTypeMapper;
import com.ceiec.twmp.tmp.services.IDeviceService;
import com.ceiec.twmp.tmp.services.ILogService;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.ExcelUtils;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.TemplateConvertUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.department.result.RedisDepartmentVO;
import com.ceiec.twmp.tmp.vo.device.query.DevicePageQueryVO;
import com.ceiec.twmp.tmp.vo.device.query.DeviceQueryVO;
import com.ceiec.twmp.tmp.vo.device.result.DeviceAttributeExtend;
import com.ceiec.twmp.tmp.vo.device.result.DeviceListVO;
import com.ceiec.twmp.tmp.vo.device.update.DeviceStatusUpdateVO;
import com.ceiec.twmp.tmp.vo.device.update.DeviceUpdateVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Ding
 * @version V1.0
 * @Description: device service
 * @create 2019-04-02 9:21
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private TwmpDevTypeMapper twmpDevTypeMapper;

    @Autowired
    private TwmpDevDeviceMapper twmpDevDeviceMapper;

    @Autowired
    private TwmpDevLifecycleMapper twmpDevLifecycleMapper;

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    @Autowired
    private ILogService logService;

    @Autowired
    private Environment env;


    private static final String DEVICE_TEMPLATE = "Device-Template";


    @Override
    public List<TwmpDevType> getDeviceType() {

        return  twmpDevTypeMapper.selectAll();
    }

    @Override
    public List<DeviceAttributeExtend> getDeviceTableColumn(Long typeId) {
        Map<String, DeviceAttributeExtend> map = new HashMap<>();

        TwmpDevType twmpDevType = new TwmpDevType();
        twmpDevType.setTypeId(typeId);
        twmpDevType = twmpDevTypeMapper.selectByPrimaryKey(twmpDevType);
        if(twmpDevType!=null){
            if(!StringUtils.isNullOrEmpty(twmpDevType.getAttributeExtend())){
                List<DeviceAttributeExtend> attributeExtendList = JSON.parseArray(twmpDevType.getAttributeExtend(), DeviceAttributeExtend.class);
                if(attributeExtendList!=null && attributeExtendList.size()>0){
                    for(DeviceAttributeExtend deviceAttributeExtend: attributeExtendList){
                        if(deviceAttributeExtend.getShow() == DeviceColumn.show.value){
                            map.put(deviceAttributeExtend.getAttributeKey(), deviceAttributeExtend);
                        }
                    }
                }
            }
        }



        if(map!=null && map.size()>0){
            List<DeviceAttributeExtend> list = new ArrayList<>();
            for(String key: map.keySet()){
                list.add(map.get(key));
            }
            return list;
        }

        return null;
    }

    @Override
    public PagedItemsVO<DeviceListVO> getDeviceByPage(String token, DevicePageQueryVO deviceQueryVO) {
        List<DeviceListVO> deviceListVOList = twmpDevDeviceMapper.getDeviceByPage(deviceQueryVO);
        Long total = twmpDevDeviceMapper.countDevice(deviceQueryVO);

        return new PagedItemsVO<>(total, deviceListVOList);
    }

    @Override
    public TwmpDevDevice getDeviceById(Long deviceId) {
        TwmpDevDevice twmpDevDevice = new TwmpDevDevice();
        twmpDevDevice.setDeviceId(deviceId);
        return twmpDevDeviceMapper.selectByPrimaryKey(twmpDevDevice);
    }

    @Override
    public void updateDevice(String token, DeviceUpdateVO deviceUpdateVO){
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);

        TwmpDevDevice twmpDevDevice = new TwmpDevDevice();
        ObjectUtils.copy(deviceUpdateVO, twmpDevDevice);

        Long num = twmpDevDeviceMapper.countDeviceByDeviceNumber(deviceUpdateVO.getDeviceNumber(), deviceUpdateVO.getDeviceId());
        if(num >0){
            throw new BusinessException(localeMessageSourceService.getMessageLocal("device.repeat.deviceNumber", redisUserInfoVO.getLanguage()));
        }

        //update
        if(twmpDevDevice.getDeviceId() !=null){
            twmpDevDevice.setUpdaterId(redisUserInfoVO.getUserId());
            twmpDevDevice.setUpdater(redisUserInfoVO.getUserName());
            twmpDevDevice.setUpdateTime(new Date());
            twmpDevDeviceMapper.updateByPrimaryKeySelective(twmpDevDevice);

            logService.saveOperateLog(token, OperateType.update.value, OperateObjectType.device.value, deviceUpdateVO.getDeviceNumber(), null);

        }else{
            //insert
            twmpDevDevice.setDeviceId(SnowflakeIdWorkerUtil.generateLongId());
            twmpDevDevice.setCreatorId(redisUserInfoVO.getUserId());
            twmpDevDevice.setCreator(redisUserInfoVO.getUserName());
            twmpDevDevice.setCreateTime(new Date());
            twmpDevDevice.setOpeStatus(OpeStatus.initial.value);
            twmpDevDevice.setOnlineStatus(OnlineStatus.offline.value);
            twmpDevDeviceMapper.insertSelective(twmpDevDevice);

            TwmpDevLifecycle twmpDevLifecycle = new TwmpDevLifecycle();
            twmpDevLifecycle.setLifecycleId(SnowflakeIdWorkerUtil.generateLongId());
            twmpDevLifecycle.setDeviceId(twmpDevDevice.getDeviceId());
            twmpDevLifecycle.setLifecycleEventType(LifecycleEventType.insert.value);

            String template = localeMessageSourceService.getMessageLocal(LifecycleEventType.insert.comment, null);
            twmpDevLifecycle.setEvent(TemplateConvertUtils.getValueJson(template, twmpDevDevice));
            twmpDevLifecycle.setCreateTime(twmpDevDevice.getCreateTime());
            twmpDevLifecycle.setCreator(twmpDevDevice.getCreator());
            twmpDevLifecycle.setCreatorId(twmpDevDevice.getCreatorId());
            twmpDevLifecycleMapper.insertSelective(twmpDevLifecycle);

            logService.saveOperateLog(token, OperateType.insert.value, OperateObjectType.device.value, deviceUpdateVO.getDeviceNumber(), null);
        }


    }

    @Override
    public void deleteDevice(String token, Long deviceId) {
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);

        TwmpDevDevice twmpDevDevice = new TwmpDevDevice();
        twmpDevDevice.setDeviceId(deviceId);
        twmpDevDevice.setUpdaterId(redisUserInfoVO.getUserId());
        twmpDevDevice.setUpdater(redisUserInfoVO.getUserName());
        twmpDevDevice.setUpdateTime(new Date());
        twmpDevDeviceMapper.updateByPrimaryKeySelective(twmpDevDevice);
    }

    @Override
    public List<TwmpDevDevice> queryDeviceByDeviceNumber(String deviceNumber) {
        return twmpDevDeviceMapper.queryDeviceByDeviceNumber(deviceNumber);
    }

    @Override
    public void updateDeviceStatus(String token, DeviceStatusUpdateVO deviceStatusUpdateVO) {
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);

        TwmpDevDevice twmpDevDevice = new TwmpDevDevice();
        twmpDevDevice.setDeviceId(deviceStatusUpdateVO.getDeviceId());
        twmpDevDevice.setOpeStatus(deviceStatusUpdateVO.getOpeStatus());
        twmpDevDevice.setComment(deviceStatusUpdateVO.getComment());

        twmpDevDevice.setUpdaterId(redisUserInfoVO.getUserId());
        twmpDevDevice.setUpdater(redisUserInfoVO.getUserName());
        twmpDevDevice.setUpdateTime(new Date());
        twmpDevDeviceMapper.updateByPrimaryKeySelective(twmpDevDevice);

        logService.saveOperateLog(token, OperateType.changeDeviceStatus.value, OperateObjectType.device.value, null, null);

    }

    @Override
    public List<TwmpDevLifecycle> getDeviceLifecycle(String token, Long deviceId) {
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);

        Example example =new Example(TwmpDevLifecycle.class);
        example.createCriteria().andEqualTo("deviceId",deviceId);
        List<TwmpDevLifecycle> list = twmpDevLifecycleMapper.selectByExample(example);
        if(list!=null && list.size()>0){
            for(TwmpDevLifecycle twmpDevLifecycle: list){

                String template = localeMessageSourceService.getMessageLocal(
                        LifecycleEventType.get(twmpDevLifecycle.getLifecycleEventType()).comment, redisUserInfoVO.getLanguage());

                String event = TemplateConvertUtils.template2Result(template, twmpDevLifecycle.getEvent());

                twmpDevLifecycle.setEvent(event);

            }

        }
        return list;
    }

    @Override
    public void downloadDeviceTemplate(String token, Long deviceTypeId, HttpServletResponse response) throws IOException {
        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);

        List<String> titleFields = createTemplateTitle(deviceTypeId, redisUserInfoVO.getLanguage());
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet(DEVICE_TEMPLATE);

        ExcelUtils.buildTitle(worksheet, titleFields);

        ExcelUtils.writeExcel(worksheet, DEVICE_TEMPLATE, response);
    }

    @Override
    public void exportDevice(String token, DeviceQueryVO deviceQueryVO, HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet(DEVICE_TEMPLATE);

        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);
        String language = redisUserInfoVO.getLanguage();

        /**
         * get excel title
         */
        List<String> titleFields = createExportTitle(deviceQueryVO.getTypeId(), language);
        ExcelUtils.buildTitle(worksheet, titleFields);
        List<String> extendList = getExtendList(deviceQueryVO.getTypeId());

        /**
         * get excel content
         */
        List<DeviceListVO> twmpDevDevices = twmpDevDeviceMapper.getDevice(deviceQueryVO);
        if(twmpDevDevices!=null && twmpDevDevices.size()>0 && titleFields!=null && titleFields.size()>0){
            if(twmpDevDevices.size()>Integer.parseInt(env.getProperty(EConfig.EXCEL_MAX_IMPORT))){
                throw new BusinessException(localeMessageSourceService.getMessageLocal("prompt.excel.max", language));
            }

           String[][] dateString = new String[twmpDevDevices.size()][titleFields.size()];

           for(int j=0; j<twmpDevDevices.size(); j++){
               for(int i=0; i<titleFields.size(); i++){
                   if(i ==0){
                       dateString[j][i] = twmpDevDevices.get(j).getDeviceNumber();
                   }
                   else if(i ==1){
                       dateString[j][i] = twmpDevDevices.get(j).getDepartmentName();
                   }
                   else if(i ==2){
                       dateString[j][i] = localeMessageSourceService.getMessageLocal(OpeStatus.get(twmpDevDevices.get(j).getOpeStatus()).nameCode, language);
                   }
                   else{
                       List<DeviceAttributeExtend> attributeExtendList = JSON.parseArray(twmpDevDevices.get(j).getAttributeExtend(), DeviceAttributeExtend.class);
                       if(attributeExtendList!=null && attributeExtendList.size()>0){
                           for(DeviceAttributeExtend deviceAttributeExtend: attributeExtendList){
                               if(deviceAttributeExtend.getAttributeKey().equals(extendList.get(i-3))){
                                   dateString[j][i] = deviceAttributeExtend.getAttributeValue();
                               }
                           }
                       }
                   }
               }
           }
            ExcelUtils.buildContent(worksheet, dateString);
        }



        ExcelUtils.writeExcel(worksheet, DEVICE_TEMPLATE, response);

    }

    @Override
    public String importDevice(String token, Long typeId, MultipartFile file) throws IOException {
        StringBuilder necessaryErrorColumns = new StringBuilder(), repeatColumns = new StringBuilder(), existColumns = new StringBuilder(), typeErrorColumns = new StringBuilder();
        int successNum = 0;

        RedisUserInfoVO redisUserInfoVO = UserInfoRedis.getUser(token);
        String language = redisUserInfoVO.getLanguage();
        if(file.isEmpty()){
            throw new BusinessException(localeMessageSourceService.getMessageLocal("prompt.excel.isNotExist", language));
        }

        if (!file.getOriginalFilename().matches("^.+\\.(?i)(xls)$") && !file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$")) {
            throw new BusinessException(localeMessageSourceService.getMessageLocal("importFile.error.illegal", language));
        }


        //the exist device number
        List<TwmpDevDevice> deviceList = twmpDevDeviceMapper.getAllDevice();
        Map<String, TwmpDevDevice> devDeviceMap = new HashMap<>();
        if(deviceList !=null && deviceList.size()>0){
            for(TwmpDevDevice twmpDevDevice: deviceList){
                devDeviceMap.put(twmpDevDevice.getDeviceNumber(), twmpDevDevice);
            }
        }
        //the repeat device number
        Set<String> deviceNumberSet = new HashSet<>();

        //the result of add list
        List<TwmpDevDevice> addList = new ArrayList<>();

        //get info from excel
        List<List<String>> listObject = ExcelUtils.getListByExcel(file.getOriginalFilename(), file.getInputStream());
        if(listObject!=null && listObject.size()>0){
            //the excel begins with deviceNumber, departmentId
            int rowNum = 2;
            for(List<String> attributeList : listObject){
                //if the column has no data
                if(attributeList ==null || attributeList.size()==0){
                    typeErrorColumns.append(","+rowNum);
                    rowNum++;
                    continue;
                }
                //check necessary attribute
                if(checkNecessary(attributeList)){
                    necessaryErrorColumns.append(","+rowNum);
                    rowNum++;
                    continue;
                }
                //get device info
                TwmpDevDevice twmpDevDevice = convertColumnsToDevice(redisUserInfoVO, typeId, attributeList);
                if(twmpDevDevice == null){
                    typeErrorColumns.append(","+rowNum);
                    rowNum++;
                    continue;
                }
                //check exist
                if(devDeviceMap.get(twmpDevDevice.getDeviceNumber())!=null){
                    existColumns.append(","+rowNum);
                    rowNum++;
                    continue;
                }
                //check repeat
                if(deviceNumberSet.contains(twmpDevDevice)){
                    repeatColumns.append(","+rowNum);
                    rowNum++;
                    continue;
                }


                deviceNumberSet.add(twmpDevDevice.getDeviceNumber());
                addList.add(twmpDevDevice);
                successNum ++;
            }
        }


        twmpDevDeviceMapper.addBatch(addList);

        if(successNum>0){
            logService.saveOperateLog(token, OperateType.importData.value, OperateObjectType.device.value, null, null);
        }

        StringBuilder message = new StringBuilder();
        if(successNum>0){
            message.append(";"+localeMessageSourceService.getMessageLocal("importFile.success", language).replace("{0}", String.valueOf(successNum)));
        }
        if(necessaryErrorColumns.length()>0){
            message.append(";"+localeMessageSourceService.getMessageLocal("importFile.error.necessary", language).replace("{0}", necessaryErrorColumns.substring(1)));
        }
        if(typeErrorColumns.length()>0){
            message.append(";"+localeMessageSourceService.getMessageLocal("importFile.error.typeError", language).replace("{0}", typeErrorColumns.substring(1)));
        }
        if(existColumns.length()>0){
            message.append(";"+localeMessageSourceService.getMessageLocal("importFile.error.existedDeviceNumber", language).replace("{0}", existColumns.substring(1)));
        }
        if(repeatColumns.length()>0){
            message.append(";"+localeMessageSourceService.getMessageLocal("importFile.error.repeatedDeviceNumber", language).replace("{0}", repeatColumns.substring(1)));
        }

        return message.substring(1);

    }

    @Override
    public void insertDeviceLifecycle(String token, TwmpDevDevice device, LifecycleEventType lifecycleEventType) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);

        TwmpDevLifecycle twmpDevLifecycle = new TwmpDevLifecycle();
        twmpDevLifecycle.setLifecycleId(SnowflakeIdWorkerUtil.generateLongId());
        twmpDevLifecycle.setDeviceId(device.getDeviceId());
        twmpDevLifecycle.setLifecycleEventType(lifecycleEventType.value);

        String template = localeMessageSourceService.getMessageLocal(lifecycleEventType.comment, user.getLanguage());
        twmpDevLifecycle.setEvent(TemplateConvertUtils.getValueJson(template, device));
        twmpDevLifecycle.setCreateTime(new Date());
        twmpDevLifecycle.setCreator(user.getUserName());
        twmpDevLifecycle.setCreatorId(user.getUserId());
        twmpDevLifecycleMapper.insertSelective(twmpDevLifecycle);
    }

    /*************************************************************************************************************************************
     ** @Description check some necessary attribute
     ** @param: attributeList
     ** @Return boolean
     ** @Author Ding
     ** @Date 2019/4/10 11:33
     **
     ************************************************************************************************************************************/
    private boolean checkNecessary(List<String> attributeList){
        if(StringUtils.isNullOrEmpty(attributeList.get(0)) || StringUtils.isNullOrEmpty(attributeList.get(1))){
            return false;
        }
        return true;
    }

    /*************************************************************************************************************************************
     ** @Description convert row data to device
     ** @param: redisUserInfoVO
     ** @param: typeId
     ** @param: attributeList
     ** @Return com.ceiec.twmp.tmp.entity.TwmpDevDevice
     ** @Author Ding
     ** @Date 2019/4/10 11:33
     **
     ************************************************************************************************************************************/
    private TwmpDevDevice convertColumnsToDevice(RedisUserInfoVO redisUserInfoVO, Long typeId,  List<String> attributeList){
        TwmpDevDevice twmpDevDevice = new TwmpDevDevice();
        twmpDevDevice.setDepartmentId(SnowflakeIdWorkerUtil.generateLongId());

        if(attributeList.get(0).length()>64){
            return null;
        }
        twmpDevDevice.setDeviceNumber(attributeList.get(0));


        //department is no exist
        RedisDepartmentVO redisDepartmentVO = DepartmentRedis.getDepartmentInfoByName(attributeList.get(1));
        if(redisDepartmentVO==null){
            return null;
        }
        //user don't have the department authority
        if(redisUserInfoVO.getOwnDepartmentId().indexOf(redisDepartmentVO.getDepartmentId().toString())<0){
            return null;
        }
        twmpDevDevice.setDepartmentId(redisDepartmentVO.getDepartmentId());

        List<DeviceAttributeExtend> attributeExtendList = getDeviceTypeAttributeExtend(typeId);
        List<DeviceAttributeExtend> devExtend = new ArrayList<>();
        if(attributeExtendList!=null && attributeExtendList.size()>0){

            for(int i=0; i<attributeExtendList.size(); i++){
                DeviceAttributeExtend attributeExtend = new DeviceAttributeExtend();
                attributeExtend.setAttributeKey(attributeExtendList.get(i).getAttributeKey());
                attributeExtend.setAttributeValue(attributeList.get(i+2));
                devExtend.add(attributeExtend);
            }
        }

        twmpDevDevice.setAttributeExtend(JSON.toJSONString(devExtend));

        twmpDevDevice.setTypeId(typeId);
        twmpDevDevice.setOpeStatus(OpeStatus.initial.value);
        twmpDevDevice.setOnlineStatus(OnlineStatus.offline.value);
        twmpDevDevice.setCreateTime(new Date());
        twmpDevDevice.setCreator(redisUserInfoVO.getUserName());
        twmpDevDevice.setCreatorId(redisUserInfoVO.getUserId());
        twmpDevDevice.setDeleted((byte)1);

        return twmpDevDevice;

    }











    /*************************************************************************************************************************************
     ** @Description get device type extend attribute
     ** @param: deviceTypeId
     ** @Return java.util.List<com.ceiec.twmp.tmp.vo.device.result.DeviceAttributeExtend>
     ** @Author Ding
     ** @Date 2019/4/10 11:11
     **
     ************************************************************************************************************************************/
    private List<DeviceAttributeExtend> getDeviceTypeAttributeExtend(Long deviceTypeId){
        TwmpDevType twmpDevType = new TwmpDevType();
        twmpDevType.setTypeId(deviceTypeId);
        twmpDevType = twmpDevTypeMapper.selectByPrimaryKey(twmpDevType);

        List<DeviceAttributeExtend> attributeExtendList =  JSON.parseArray(twmpDevType.getAttributeExtend(), DeviceAttributeExtend.class);

        //sort DeviceAttributeExtend
        attributeExtendList = sortDeviceAttributeExtend(attributeExtendList);

        return attributeExtendList;
    }



    /*************************************************************************************************************************************
     ** @Description create device template title , the default device type is 1
     ** @param: typeId
     ** @Return java.util.List<java.lang.String>
     ** @Author Ding
     ** @Date 2019/4/4 14:53
     **
     ************************************************************************************************************************************/
    private List<String> createTemplateTitle(Long deviceTypeId, String language){
        List<DeviceAttributeExtend> attributeExtendList = getDeviceTypeAttributeExtend(deviceTypeId);
        int titleLength = 2;
        if(attributeExtendList!=null && attributeExtendList.size()>0){
            titleLength = titleLength + attributeExtendList.size();

        }

        String titleName[] = new String[titleLength];
        titleName[0]=localeMessageSourceService.getMessageLocal("deviceNumber", language);
        titleName[1]=localeMessageSourceService.getMessageLocal("departmentId", language);
        if(attributeExtendList!=null && attributeExtendList.size()>0){
            for(int i=0; i<attributeExtendList.size(); i++){
                titleName[i+2]=localeMessageSourceService.getMessageLocal(attributeExtendList.get(i).getAttributeName(), language);
            }
        }

        Integer necessaryCondition[] = new Integer[titleLength];
        necessaryCondition[0]=1;
        necessaryCondition[1]=1;

        return ExcelUtils.setTitle(titleName, necessaryCondition, null);
    }

    /*************************************************************************************************************************************
     ** @Description  sort DeviceAttributeExtend
     ** @param: attributeExtendList
     ** @Return java.util.List<com.ceiec.twmp.tmp.vo.device.result.DeviceAttributeExtend>
     ** @Author Ding
     ** @Date 2019/4/8 9:24
     **
     ************************************************************************************************************************************/
    private List<DeviceAttributeExtend> sortDeviceAttributeExtend(List<DeviceAttributeExtend> attributeExtendList){
        if(attributeExtendList!=null && attributeExtendList.size()>0){
            for(int i=0; i<attributeExtendList.size()-1; i++){
                for(int j=0; j<attributeExtendList.size()-1-i; j++){
                    if(attributeExtendList.get(j).getOrder()!=null && attributeExtendList.get(j+1).getOrder()!=null){
                        if(attributeExtendList.get(j).getOrder()>attributeExtendList.get(j+1).getOrder()){
                            DeviceAttributeExtend temp = attributeExtendList.get(j);
                            attributeExtendList.set(j, attributeExtendList.get(j+1));
                            attributeExtendList.set(j+1, temp);
                        }
                    }
                }
            }
        }

        return attributeExtendList;
    }

    /*************************************************************************************************************************************
     ** @Description create device export result excel title
     ** @param: deviceTypeId
     ** @param: language
     ** @Return java.util.List<java.lang.String>
     ** @Author Ding
     ** @Date 2019/4/4 16:59
     **
     ************************************************************************************************************************************/
    private List<String> createExportTitle(Long deviceTypeId, String language){
        List<DeviceAttributeExtend> attributeExtendList = getDeviceTypeAttributeExtend(deviceTypeId);

        int titleLength = 3;
        if(attributeExtendList!=null && attributeExtendList.size()>0){
            titleLength = titleLength + attributeExtendList.size();
        }


        String titleName[] = new String[titleLength];
        titleName[0]=localeMessageSourceService.getMessageLocal("deviceNumber", language);
        titleName[1]=localeMessageSourceService.getMessageLocal("departmentId", language);
        titleName[2]=localeMessageSourceService.getMessageLocal("opeStatus", language);
        if(attributeExtendList!=null && attributeExtendList.size()>0){
            for(int i=0; i<attributeExtendList.size(); i++){
                titleName[i+3]=localeMessageSourceService.getMessageLocal(attributeExtendList.get(i).getAttributeName(), language);
            }
        }


        return ExcelUtils.setTitle(titleName, null, null);
    }


    private List<String> getExtendList(Long deviceTypeId){
        List<String> list = new ArrayList<>();

        TwmpDevType twmpDevType = new TwmpDevType();
        twmpDevType.setTypeId(deviceTypeId);
        twmpDevType = twmpDevTypeMapper.selectByPrimaryKey(twmpDevType);

        List<DeviceAttributeExtend> attributeExtendList =  JSON.parseArray(twmpDevType.getAttributeExtend(), DeviceAttributeExtend.class);



        if(attributeExtendList!=null && attributeExtendList.size()>0){
            for(int i=0; i<attributeExtendList.size(); i++){
                list.add(attributeExtendList.get(i).getAttributeKey());
            }
        }

        return list;
    }


}
