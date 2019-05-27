package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.common.dict.*;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.entity.TwmpBsPersonEf;
import com.ceiec.twmp.tmp.entity.TwmpSysDepartment;
import com.ceiec.twmp.tmp.mapper.TwmpBsPersonCriminalEfMapper;
import com.ceiec.twmp.tmp.mapper.TwmpBsPersonEfMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSysDepartmentMapper;
import com.ceiec.twmp.tmp.services.ICriminalService;
import com.ceiec.twmp.tmp.services.ILogService;
import com.ceiec.twmp.tmp.services.IPersonService;
import com.ceiec.twmp.tmp.utils.DepartmentStrToList;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.ExcelUtils;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.person.add.PersonCriminalVO;
import com.ceiec.twmp.tmp.vo.person.add.PersonDataVO;
import com.ceiec.twmp.tmp.vo.person.query.PersonQueryVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonAndDeviceVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonDataResultVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonInfoImportResultVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonInfoListResultVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * CreateDate：2019/3/1 16:17 </br>
 * Author：shihsh  </br>
 * Description: 被监控人员Service </br>
 **/

@Service
@Transactional(rollbackFor = Exception.class) //事务控制
public class PersonServiceImpl implements IPersonService {

    private static final String PERSON_INFO_TEMPLATE = "Person-Information-Template";

    private static final String PERSON_INFO_EXPORT = "Person-Information";

    private static final String[] PERSON_INFO_TEMPLATE_TITLE = {"export.person.template.personName", "export.person.template.personDepartment",
            "export.person.template.personIdCard", "export.person.template.phone", "export.person.template.career", "export.person.template.gender",
            "export.person.template.maritalStatus", "export.person.template.birthDate", "export.person.template.formerName", "export.person.template.address",
            "export.person.template.email", "export.person.template.comment"};

    private static final String[] PERSON_EXPORT_TITLE = {"export.person.personNumber", "export.person.personName", "export.perosn.gender",
            "export.person.birthDate", "export.person.personIdCard", "export.person.departmentName", "export.person.phone"};

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TwmpBsPersonCriminalEfMapper twmpBsPersonCriminalEfMapper;

    @Autowired
    private TwmpBsPersonEfMapper twmpBsPersonEfMapper;

    @Autowired
    private ICriminalService criminalService;

    @Autowired
    private TwmpSysDepartmentMapper twmpSysDepartmentMapper;

    @Autowired
    private ILogService logService;

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    /**
    *
    * @description: 新增或编辑被监控人员
    * @param: token
    * @param: personDataVO
    * @return: com.ceiec.twmp.tmp.vo.person.result.PersonDataResultVO
    * @author: shihsh
    * @date: 2019/3/11
    */
    @Override
    public PersonDataResultVO addOrEditPerson(String token, PersonDataVO personDataVO) {
        //插入被监控人员表
        //ObjectUtils.copy() 名字相同的字段直接copy
        PersonDataResultVO personDataResultVO;
        if (personDataVO.getPersonId() == null) {
            personDataResultVO = addPerson(token, personDataVO);
        } else {
            personDataResultVO = editPerson(token, personDataVO);
        }

        //刪除犯罪记录
        String criminalIds = personDataVO.getCriminalId();
        if (criminalIds != null && !"".equals(criminalIds.trim())) {
            String[] criminalIdList = criminalIds.split(",");
            for (String criminalIdStr : criminalIdList) {
                Long criminalId = Long.valueOf(criminalIdStr.trim());
                criminalService.deleteCriminal(token, criminalId, personDataVO.getPersonName());
            }
        }

        //处理犯罪记录，可能是新增，也可能是编辑
        List<PersonCriminalVO> criminalList = personDataVO.getCriminalList();
        if (criminalList != null && criminalList.size() != 0) {
            for (PersonCriminalVO personCriminalVO : criminalList) {
                personCriminalVO.setPersonId(personDataVO.getPersonId());
                criminalService.addOrEditCiminal(token, personCriminalVO, personDataVO.getPersonName());
            }
        }
        return personDataResultVO;
    }

    /**
    *
    * @description: 编辑被监控人员
    * @param: token
    * @param: personDataVO
    * @return: com.ceiec.twmp.tmp.vo.person.result.PersonDataResultVO
    * @author: shihsh
    * @date: 2019/3/11
    */
    @Override
    public PersonDataResultVO editPerson(String token, PersonDataVO personDataVO) {
        TwmpBsPersonEf twmpBsPersonEf = new TwmpBsPersonEf();
        ObjectUtils.copy(personDataVO, twmpBsPersonEf);
        //曾用名前端参数formernName，数据库为person_former_name,故copy失败，单独设置
        twmpBsPersonEf.setPersonFormerName(personDataVO.getFormerName());
        twmpBsPersonEf.setUpdater(TokenUtils.getUserName(token));
        twmpBsPersonEf.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpBsPersonEf.setUpdateTime(new Date());

        twmpBsPersonEfMapper.updateByPrimaryKeySelective(twmpBsPersonEf);
        logService.saveOperateLog(token, OperateType.update.value, OperateObjectType.person.value, twmpBsPersonEf.getPersonName(), null);

        PersonDataResultVO personDataResultVO = new PersonDataResultVO();
        personDataResultVO.setPersonId(twmpBsPersonEf.getPersonId());
        return personDataResultVO;
    }

    /**
    *
    * @description: 新增被监控人员
    * @param: token
    * @param: personDataVO
    * @return: com.ceiec.twmp.tmp.vo.person.result.PersonDataResultVO
    * @author: shihsh
    * @date: 2019/3/11
    */
    @Override
    public PersonDataResultVO addPerson(String token, PersonDataVO personDataVO) {
        // 判断是否有身份证号相同的人存在，有则抛异常
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        TwmpBsPersonEf samePerson = new TwmpBsPersonEf();
        samePerson.setPersonIdCard(personDataVO.getPersonIdCard());
        List<TwmpBsPersonEf> testPersons = twmpBsPersonEfMapper.select(samePerson);
        if (testPersons != null && testPersons.size() != 0) {
            throw new BusinessException(localeMessageSourceService.getMessageLocal("importFile.error.illegalIdCard", userInfo.getLanguage()));
        }

        TwmpBsPersonEf twmpBsPersonEf = new TwmpBsPersonEf();
        ObjectUtils.copy(personDataVO, twmpBsPersonEf);
        //曾用名前端参数formernName，数据库为person_former_name,故copy失败，单独设置
        twmpBsPersonEf.setPersonFormerName(personDataVO.getFormerName());
        if (personDataVO.getPersonId() == null) {
            twmpBsPersonEf.setPersonId(SnowflakeIdWorkerUtil.generateLongId());
            personDataVO.setPersonId(twmpBsPersonEf.getPersonId());
        }
        TwmpSysDepartment twmpSysDepartment = twmpSysDepartmentMapper.selectByPrimaryKey(twmpBsPersonEf.getDepartmentId());
        String personNumber = twmpSysDepartment.getDepartmentCode() + "P"
                                + DateFormatUtils.dateToString(new Date(), DateFormatUtils.NUMBER_DATE_FORMATE) + (twmpSysDepartment.getPersonNum() + 1);
        twmpBsPersonEf.setPersonNumber(personNumber);
        twmpBsPersonEf.setCreator(TokenUtils.getUserName(token));
        twmpBsPersonEf.setCreatorId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpBsPersonEf.setCreateTime(new Date());
        twmpBsPersonEf.setUpdater(TokenUtils.getUserName(token));
        twmpBsPersonEf.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpBsPersonEf.setUpdateTime(new Date());

        twmpBsPersonEfMapper.insertSelective(twmpBsPersonEf);
        // 组织机构下增加一个新的被监控人员后，该组织机构的被监控人员计数 +1
        twmpSysDepartment.setPersonNum(twmpSysDepartment.getPersonNum() + 1);
        twmpSysDepartment.setUpdater(userInfo.getUserName());
        twmpSysDepartment.setUpdaterId(userInfo.getUserId());
        twmpSysDepartment.setUpdateTime(new Date());
        twmpSysDepartmentMapper.updateByPrimaryKeySelective(twmpSysDepartment);
        logService.saveOperateLog(token, OperateType.insert.value, OperateObjectType.person.value, twmpBsPersonEf.getPersonName(), null);
        logService.saveOperateLog(token, OperateType.update.value, OperateObjectType.department.value, twmpSysDepartment.getDepartmentName(), null);
        PersonDataResultVO personDataResultVO = new PersonDataResultVO();
        personDataResultVO.setPersonId(twmpBsPersonEf.getPersonId());
        personDataResultVO.setPersonName(twmpBsPersonEf.getPersonName());
        return personDataResultVO;
    }

    /**
    *
    * @description: 查询被监控人员列表
    * @param: token
    * @param: personQueryVO
    * @return: com.ceiec.twmp.tmp.utils.page.PagedItemsVO<com.ceiec.twmp.tmp.vo.person.result.PersonInfoListResultVO>
    * @author: shihsh
    * @date: 2019/3/11
    */
    @Override
    public PagedItemsVO<PersonInfoListResultVO> personInfoQueryByPage(String token, PersonQueryVO personQueryVO) {
        // 组织机构权限
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        personQueryVO.setDepartmentIds(DepartmentStrToList.getDepartmentIdList(token));
        dealTaskStatus(personQueryVO);
        Long total = twmpBsPersonEfMapper.countPersonList(personQueryVO);
        PageHelper.startPage(personQueryVO.getPageNo(), personQueryVO.getPageSize(), "create_time desc");
        List<PersonInfoListResultVO> personInfoListResultVOS = twmpBsPersonEfMapper.getPersonListByPage(personQueryVO);
        for (PersonInfoListResultVO personInfo : personInfoListResultVOS) {
            if (personInfo.getPersonStatus() == null || personInfo.getPersonStatus() < TaskStatus.monitoring.value) {
                // 没有监控任务时，默认为未监控状态
                personInfo.setPersonStatus(PersonStatus.unmonitored.value);
                personInfo.setPersonStatusName(localeMessageSourceService.getMessageLocal(PersonStatus.unmonitored.nameCode, userInfo.getLanguage()));
            } else if (personInfo.getPersonStatus() == TaskStatus.monitoring.value) {
                // 监控中
                personInfo.setPersonStatus(PersonStatus.monitoring.value);
                personInfo.setPersonStatusName(localeMessageSourceService.getMessageLocal(PersonStatus.monitoring.nameCode, userInfo.getLanguage()));
            } else {
                // 监控结束
                personInfo.setPersonStatus(PersonStatus.over.value);
                personInfo.setPersonStatusName(localeMessageSourceService.getMessageLocal(PersonStatus.over.nameCode, userInfo.getLanguage()));
            }
        }

        return new PagedItemsVO<>(total, personInfoListResultVOS);
    }

    /**
    *
    * @description: 删除被监控人员
    * @param: token
    * @param: personId
    * @return: void
    * @author: shihsh
    * @date: 2019/3/11
    */
    @Override
    public void deletePerson(String token, Long personId) {
        TwmpBsPersonEf twmpBsPersonEf = new TwmpBsPersonEf();
        twmpBsPersonEf.setPersonId(personId);
        twmpBsPersonEf.setDeleted(TmpBaseConstants.FLAG_DELETE_TRUE);
        twmpBsPersonEf.setUpdater(TokenUtils.getUserName(token));
        twmpBsPersonEf.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpBsPersonEf.setUpdateTime(new Date());

        twmpBsPersonEfMapper.updateByPrimaryKeySelective(twmpBsPersonEf);
        twmpBsPersonEf = twmpBsPersonEfMapper.selectByPrimaryKey(personId);
        //记录操作日志
        logService.saveOperateLog(token , OperateType.delete.value, OperateObjectType.person.value, twmpBsPersonEf.getPersonName(), null);

    }

    /**
    *
    * @description:  excel表头，一行：| *personName | *departmentId | *personIdCard | *phone | *career | *gender	|
    *                              *maritalStatus | *bitthDate（yyyy-mm-dd) | formerName | *address | *email | comment |
    * @param: token
    * @param: fileName
    * @param: in
    * @return: void
    * @author: shihsh
    * @date: 2019/3/26
    */
    @Override
    public PersonInfoImportResultVO importPersonInfoExcel(String token, String fileName, MultipartFile file) {
        RedisUserInfoVO userInfoVO = UserInfoRedis.getUser(token);
        String language = userInfoVO.getLanguage();
        StringBuffer necessaryStringBuffer = new StringBuffer();
        StringBuffer typeErrorStringBuffer = new StringBuffer();
        StringBuffer idCardExistStringBuffer = new StringBuffer();
        StringBuffer wrongDepartmentStringBuffer = new StringBuffer();
        PersonInfoImportResultVO resultVO =  new PersonInfoImportResultVO();
        String failed = localeMessageSourceService.getMessageLocal("importFile.failed.excel", language);
        if(file.isEmpty()){
            throw new BusinessException(localeMessageSourceService.getMessageLocal("prompt.excel.isNotExist", language));
        }
        if (!file.getOriginalFilename().matches("^.+\\.(?i)(xls)$") && !file.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$")) {
            throw new BusinessException(localeMessageSourceService.getMessageLocal("importFile.error.illegal", language));
        }

        List<List<String>> list;
        try {
            list = ExcelUtils.getListByExcel(fileName, file.getInputStream());
        } catch (Exception e) {
            logger.error(failed, e);
            throw new BusinessException(failed);
        }
        TwmpBsPersonEf twmpBsPersonEf;
        List<TwmpBsPersonEf> personList = new ArrayList<>();
        int line = 1;
        boolean legalData = true;
        Map<String, Integer> personIdCardAndLineMap = new HashMap<>();
        Map<String, String> idCardRepeatedLineMap = new HashMap<>();
        List<TwmpSysDepartment> departments = twmpSysDepartmentMapper.selectAll();
        Map<Long, Integer> departmentCountMap = new HashMap<>();
        Map<Long, TwmpSysDepartment> idToDepartmentMap = new HashMap<>();
        Map<String, TwmpSysDepartment> nameToDepartmentMap = new HashMap<>();
        List<String> personNames = new ArrayList<>();
        departments.forEach(department->{
            departmentCountMap.put(department.getDepartmentId(), 0);
            nameToDepartmentMap.put(department.getDepartmentName(), department);
            idToDepartmentMap.put(department.getDepartmentId(), department);
        });
        for (List<String> strList : list) {

            //检查导入信息格式是否有误
            if (strList == null || strList.size() == 0) {
                // 空行
                line++;
                continue;
            } else if (necessaryInfoIsEmpty(strList)) {
                // 检查必填项是否为空
                necessaryStringBuffer.append(",").append(line);
                legalData = false;
            } else {
                // 判断Excel表格中被监控人员身份证号是否重复
                String personIdCard = strList.get(2);
                if (personIdCardAndLineMap.containsKey(personIdCard)) {
                    legalData = false;
                    if (idCardRepeatedLineMap.containsKey(personIdCard)) {
                        idCardRepeatedLineMap.put(personIdCard, idCardRepeatedLineMap.get(personIdCard) + "," + line);
                    } else {
                        // 首次加入重复的personIdCard,需要记录当前的行号和上一个该Id的行号
                        idCardRepeatedLineMap.put(personIdCard, personIdCardAndLineMap.get(personIdCard) + "," + line);
                    }
                } else {
                    // 记录首次出现该personIdCard时的行号
                    personIdCardAndLineMap.put(personIdCard, line);
                }
                try {
                    //检查数据格式是否有误
                    // 数字n对应excel里的第n+1列
                    if (!nameToDepartmentMap.containsKey(strList.get(1))) {
                        //组织机构名填写错误，系统中不存在该组织机构
                        legalData = false;
                        wrongDepartmentStringBuffer.append(",").append(line);

                    }
                    Byte gender = Byte.valueOf(strList.get(5));
                    Byte maritalStatus = Byte.valueOf(strList.get(6));
                    // 日期格式系统中统一使用yyyy-MM-dd，excel中为yyyy/MM/dd
                    Date birDate = DateFormatUtils.stringToDate(strList.get(7).replaceAll("/", "-"));
                    if (birDate == null) {
                        typeErrorStringBuffer.append(",").append(line);
                        legalData = false;
                    } else {
                        strList.set(7, DateFormatUtils.dateToString(birDate));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    typeErrorStringBuffer.append(",").append(line);
                    legalData = false;
                }
                if (legalData) {
                    TwmpSysDepartment department = nameToDepartmentMap.get(strList.get(1));
                    Integer count = departmentCountMap.get(department.getDepartmentId());
                    twmpBsPersonEf = converStrListToTwmpBsPersonEf(strList, userInfoVO, department, count+1);
                    departmentCountMap.put(department.getDepartmentId(), count+1);
                    personList.add(twmpBsPersonEf);
                    personNames.add(twmpBsPersonEf.getPersonName());
                }
            }
            line++;
        }
        //数据没有错误, 还需要检查身份证号是否有重复
        if (legalData) {
            List<TwmpBsPersonEf> repeatedList = twmpBsPersonEfMapper.repeatedPerson(personList);
            if (repeatedList != null && repeatedList.size() != 0) {
                //有重复的身份证号  "行号：身份证号"
                repeatedList.forEach(person->{idCardExistStringBuffer.append(",").append(personIdCardAndLineMap.get(person.getPersonIdCard())).append(":").append(person.getPersonIdCard());});
                String idCardExist = localeMessageSourceService.getMessageLocal("importFile.error.idCardExist", language);
                idCardExist = idCardExist.replace("{0}", idCardExistStringBuffer.substring(1));
                resultVO.setIdCardExist(idCardExist);
                resultVO.setFailed(failed);
            } else {
                // 没有重复身份证号才能进行插入
                twmpBsPersonEfMapper.insertPersonInfoList(personList);
                List<TwmpSysDepartment> changeDepartmentList = new ArrayList<>();
                List<String> departmentNameList = new ArrayList<>();
                departmentCountMap.forEach((k,v) -> {
                    if (!v.equals(0)) {
                        TwmpSysDepartment upateDepartment = idToDepartmentMap.get(k);
                        upateDepartment.setPersonNum(upateDepartment.getPersonNum()+v);
                        upateDepartment.setUpdateTime(new Date());
                        upateDepartment.setUpdater(userInfoVO.getUserName());
                        upateDepartment.setUpdaterId(userInfoVO.getUserId());
                        changeDepartmentList.add(upateDepartment);
                        departmentNameList.add(upateDepartment.getDepartmentName());
                    }
                });
                twmpSysDepartmentMapper.updateBatchPersonNum(changeDepartmentList);
                // 批量插入，记录日志
                logService.saveBatchOperateLog(token, OperateType.insert.value, OperateObjectType.person.value, personNames);
                logService.saveBatchOperateLog(token, OperateType.update.value, OperateObjectType.department.value, departmentNameList);
                // 数据插入成功
                String success = localeMessageSourceService.getMessageLocal("importFile.success.excel", language);
                resultVO.setSuccess(success);
            }
        } else {
            String necessary = null;
            if (necessaryStringBuffer.length() > 0) {
                necessary = localeMessageSourceService.getMessageLocal("importFile.error.necessary", language).replace("{0}", necessaryStringBuffer.substring(1));
            }
            String typeError = null;
            if (typeErrorStringBuffer.length() > 0) {
                typeError = localeMessageSourceService.getMessageLocal("importFile.error.typeError", language).replace("{0}", typeErrorStringBuffer.substring(1));
            }
            String wrongDepartment = null;
            if (wrongDepartmentStringBuffer.length() > 0) {
                wrongDepartment = localeMessageSourceService.getMessageLocal("importFile.error.wrongDepartmentName", language).replace("{0}", wrongDepartmentStringBuffer.substring(1));
            }
            StringBuffer repeatedIdCardBuffer = new StringBuffer();
            String repeatedIdCard = null;
            if (idCardRepeatedLineMap.size() != 0) {
                idCardRepeatedLineMap.forEach((k, v) -> {
                     repeatedIdCardBuffer.append(",").append(k).append(":").append(v) ;
                });
                repeatedIdCard = localeMessageSourceService.getMessageLocal("importFile.error.repeatedIdCard", language).replace("{0}", repeatedIdCardBuffer.substring(1));
            }
            resultVO.setNecessaryMissing(necessary);
            resultVO.setTypeError(typeError);
            resultVO.setWrongDepartment(wrongDepartment);
            resultVO.setIdRepeated(repeatedIdCard);
        }
        return resultVO;
    }

    @Override
    public PersonAndDeviceVO getPersonAndDeviceInfo(String token, Long personId) {
        return twmpBsPersonEfMapper.getPersonAndDeviceInfo(personId);
    }

    @Override
    public void getPersonImportTemplate(String token, HttpServletResponse response) throws IOException {
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        List<String> titleFields = createTitle(userInfo.getLanguage(), PERSON_INFO_TEMPLATE_TITLE);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet(PERSON_INFO_TEMPLATE);
        ExcelUtils.buildTitle(worksheet, titleFields);
        ExcelUtils.writeExcel(worksheet, PERSON_INFO_TEMPLATE, response);
    }

    @Override
    public void exportPersonInfo(String token, HttpServletResponse response, PersonQueryVO personQueryVO) throws IOException {
        RedisUserInfoVO userInfo = UserInfoRedis.getUser(token);
        personQueryVO.setDepartmentIds(DepartmentStrToList.getDepartmentIdList(token));
        dealTaskStatus(personQueryVO);
        //  获取导出列表
        List<PersonInfoListResultVO> list = twmpBsPersonEfMapper.getPersonList(personQueryVO);
        List<String> titleFields = createTitle(userInfo.getLanguage(), PERSON_EXPORT_TITLE);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet worksheet = workbook.createSheet(PERSON_INFO_EXPORT);
        ExcelUtils.buildTitle(worksheet, titleFields);
        String[][] dataString = new String[list.size()+1][titleFields.size()];
        for (int i = 0; i < list.size(); i++) {
            PersonInfoListResultVO personInfo = list.get(i);
            dataString[i][0] = personInfo.getPersonNumber();
            dataString[i][1] = personInfo.getPersonName();
            dataString[i][2] = localeMessageSourceService.getMessageLocal("", userInfo.getLanguage());
            dataString[i][3] = personInfo.getBirthDate();
            dataString[i][4] = personInfo.getPersonIdCard();
            dataString[i][5] = personInfo.getDepartmentName();
            dataString[i][6] = personInfo.getPhone();

        }
        ExcelUtils.buildContent(worksheet, dataString);
        ExcelUtils.writeExcel(worksheet, PERSON_INFO_EXPORT, response);
    }

    private void  dealTaskStatus(PersonQueryVO personQueryVO) {
        if (personQueryVO.getPersonStatus() != null) {
            List<Byte> tasks = personQueryVO.getTaskStatusList();
            if (personQueryVO.getPersonStatus().equals(PersonStatus.total.value)) {
                // 查询全部
                personQueryVO.setTaskStatusList(null);
            } else if (personQueryVO.getPersonStatus().equals(PersonStatus.unmonitored.value) ) {
                // 前端显示未监控，对应监控任务:初始化、审批中、安装中、安装完成
                tasks.add(TaskStatus.initial.value);
                tasks.add(TaskStatus.initial_approval.value);
                tasks.add(TaskStatus.initial_wait_to_install.value);
                tasks.add(TaskStatus.initial_installed.value);
            } else if (personQueryVO.getPersonStatus().equals(PersonStatus.monitoring.value)) {
                // 监控中对应监控任务:监控中
                tasks.add(TaskStatus.monitoring.value);
            } else {
                // 监控结束对应监控任务: 监控结束、待拆机、已结束
                tasks.add(TaskStatus.completed.value);
                tasks.add(TaskStatus.completed_wait_to_dismantle.value);
                tasks.add(TaskStatus.over.value);
            }
        }
        return;
    }


    private boolean necessaryInfoIsEmpty(List<String> strList) {
        if ("".equals(strList.get(0)) || "".equals(strList.get(1)) || "".equals(strList.get(2)) || "".equals(strList.get(3))
                || "".equals(strList.get(4)) || "".equals(strList.get(5)) || "".equals(strList.get(6))|| "".equals(strList.get(7))
                || "".equals(strList.get(9)) || "".equals(strList.get(10))) {
            return true;
        }
        return false;
    }

    private TwmpBsPersonEf converStrListToTwmpBsPersonEf(List<String> strList, RedisUserInfoVO userInfoVO, TwmpSysDepartment department, Integer count) {
        TwmpBsPersonEf twmpBsPersonEf = new TwmpBsPersonEf();
        twmpBsPersonEf.setPersonId(SnowflakeIdWorkerUtil.generateLongId());
        twmpBsPersonEf.setPersonName(strList.get(0));
        String personNumber = department.getDepartmentCode() + "P"
                + DateFormatUtils.dateToString(new Date(), DateFormatUtils.NUMBER_DATE_FORMATE) + (department.getPersonNum() + count);
        // 组织机构下增加一个新的被监控人员后，该组织机构的被监控人员计数 +1
        twmpBsPersonEf.setPersonNumber(personNumber);
        twmpBsPersonEf.setDepartmentId(department.getDepartmentId());
        twmpBsPersonEf.setPersonIdCard(strList.get(2));
        twmpBsPersonEf.setPhone(strList.get(3));
        twmpBsPersonEf.setCareer(strList.get(4));
        twmpBsPersonEf.setGender(Byte.valueOf(strList.get(5)));
        twmpBsPersonEf.setMaritalStatus(Byte.valueOf(strList.get(6)));
        twmpBsPersonEf.setBirthDate(strList.get(7));
        twmpBsPersonEf.setPersonFormerName(strList.get(8));
        twmpBsPersonEf.setAddress(strList.get(9));
        twmpBsPersonEf.setEmail(strList.get(10));
        twmpBsPersonEf.setComment(strList.get(11));
        twmpBsPersonEf.setCreator(userInfoVO.getUserName());
        twmpBsPersonEf.setCreatorId(userInfoVO.getUserId());
        twmpBsPersonEf.setCreateTime(new Date());
        twmpBsPersonEf.setDeleted(Deleted.noDeleted.value);
        return twmpBsPersonEf;
    }

    private List<String> createTitle(String language, String[] messageLocals) {
        List<String> titleList = new ArrayList<>();
        for (String messageLocal : messageLocals) {
            titleList.add(localeMessageSourceService.getMessageLocal(messageLocal, language));
        }
        return titleList;
    }
}
