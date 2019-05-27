package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.cache.mongodb.GpsMongodb;
import com.ceiec.twmp.tmp.cache.redis.GpsFileRedis;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.entity.TwmpMonitorTaskEf;
import com.ceiec.twmp.tmp.EConfig;
import com.ceiec.twmp.tmp.services.IMonitorService;
import com.ceiec.twmp.tmp.services.IMonitorTaskService;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.utils.tools.ftp.FTPClientUtils;
import com.ceiec.twmp.tmp.vo.gps.GpsInfo;
import com.ceiec.twmp.tmp.vo.gps.GpsResultVO;
import com.ceiec.twmp.tmp.vo.gps.RedisGpsFileInfo;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: monitor service
 * @create 2019-03-28 10:23
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class MonitorServiceImpl implements IMonitorService {

    private final static String DOCUMENT_DATE_NAME = "date";
    private final static String DOCUMENT_GPS_NAME = "gps";
    private final static String DOCUMENT_ROOT_NAME = "INFO";
    private final static String DOCUMENT_FILE_FORMAT = "{deviceNumber}--{taskCode}----{date}.xml";
    private final static String GPS_FILE_NAME = "/gps";


    @Autowired
    private Environment env;

    @Autowired
    private IMonitorTaskService monitorTaskService;

    private static Logger logger = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Override
    public GpsResultVO queryGpsInfo(String deviceNumber, String taskCode, String date) throws DocumentException, IOException {
        int interval = Integer.parseInt(env.getProperty(EConfig.GPS_SAVE_INTERVAL));

        Date start = DateFormatUtils.stringToDate(date);
        Date end = DateFormatUtils.stringToDate(date);



        GpsResultVO result = new GpsResultVO();
        result.setDate(date);

        /**
         * if the time is in the interval time , will get data from mongodb
         */
        if((System.currentTimeMillis() - start.getTime()) /1000<interval*60*60*24L){
            List<GpsInfo> gpsInfoList = GpsMongodb.queryGps(deviceNumber, taskCode, start, end);
            result.setGpsInfoList(gpsInfoList);
            result = computeNoDataTime(result);
            return result;
        }else{
            /**
             * else will get the data from ftp
             */
            String fileName = DOCUMENT_FILE_FORMAT.replace("{deviceNumber}",deviceNumber)
                    .replace("{taskCode}",taskCode).replace("{date}",DateFormatUtils.dateToString(start,DateFormatUtils.DATETIME_FORMAT_SYSTEM));
            RedisGpsFileInfo gpsFileInfo = GpsFileRedis.getGpsFile(fileName);
            if(gpsFileInfo == null){
                return null;
            }else{

                File file = new File("/home/tempGps");
                if(!file.exists()){
                    file.createNewFile();
                }
                OutputStream os = new FileOutputStream(file);
                FTPClientUtils.downloadFile(gpsFileInfo.getPath(), os);

                return gpsXML2GpsResult(new FileInputStream(file));
            }
        }
    }

    @Override
    public void exportGpsInfo(HttpServletResponse response, String deviceNumber, String taskCode, String dateStr) throws IOException {
        Date date = DateFormatUtils.stringToDate(dateStr);
        Document document = getGpsXML(deviceNumber, taskCode, date);

        InputStream is = new ByteArrayInputStream(document.getName().getBytes("UTF-8"));

        String fileName = DOCUMENT_FILE_FORMAT.replace("{deviceNumber}",deviceNumber)
                .replace("{taskCode}",taskCode).replace("{date}",DateFormatUtils.dateToString(date, DateFormatUtils.DATETIME_FORMAT_SYSTEM));

        OutputStream os = response.getOutputStream();
        response.reset();
        response.setHeader("Content-type","multipart/form-data;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,  "UTF-8"));

        byte[] b = new byte[512];
        int len;
        while ((len = is.read(b)) > 0){
            os.write(b, 0, len);
        }
        os.flush();
        os.close();


    }


    @Override
    public Document getGpsXML(String deviceNumber, String taskCode, Date date){
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement(DOCUMENT_ROOT_NAME);
        rootElement.addAttribute(GpsInfo.MONGO_DEVICE_NUMBER, deviceNumber);
        rootElement.addAttribute(GpsInfo.MONGO_TASK_CODE, taskCode);
        rootElement.addAttribute(DOCUMENT_DATE_NAME, DateFormatUtils.dateToString(date));

        Date start = date;
        Date end = date;
        List<GpsInfo> list = GpsMongodb.queryGps(deviceNumber, taskCode, start, end);

        if(list!=null && list.size()>0){
            for(GpsInfo gpsInfo: list){
                Element element = rootElement.addElement(DOCUMENT_GPS_NAME);
                element.addElement(GpsInfo.MONGO_LONGITUDE).setText(gpsInfo.getLongitude());
                element.addElement(GpsInfo.MONGO_LATITUDE).setText(gpsInfo.getLatitude());
                element.addElement(GpsInfo.MONGO_SPEED).setText(gpsInfo.getSpeed());
                element.addElement(GpsInfo.MONGO_DIRECTION).setText(gpsInfo.getDirection());
                element.addElement(GpsInfo.MONGO_GPS_TIME).setText(gpsInfo.getGpsTime());
            }
        }

        return document;
    }

    @Override
    public GpsResultVO importGpsResult(MultipartFile file) throws IOException, DocumentException {

       return gpsXML2GpsResult(file.getInputStream());

    }

    @Override
    public void saveGpsFile(Date date){

        if(GpsFileRedis.getGpsFileJob(DateFormatUtils.dateToString(date, DateFormatUtils.DATE_FORMAT_SYSTEM))){
           return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)-1);
        Date startTime = calendar.getTime();

        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR));
        Date endTime = calendar.getTime();

        List<TwmpMonitorTaskEf> taskList = monitorTaskService.getTaskByTime(startTime, endTime);
        if(taskList!=null && taskList.size()>0){

            for(TwmpMonitorTaskEf task : taskList){
                try{
                    Document document = getGpsXML(task.getDeviceNumber(), task.getTaskCode(), date);
                    Calendar c = Calendar.getInstance();
                    c.setTime(startTime);
                    String fileName = DOCUMENT_FILE_FORMAT.replace("{deviceNumber}",task.getDeviceNumber())
                            .replace("{taskCode}",task.getTaskCode()).replace("{date}",DateFormatUtils.dateToString(date, DateFormatUtils.DATETIME_FORMAT_SYSTEM));
                    String path = GPS_FILE_NAME + "/" + c.get(Calendar.YEAR)+
                            "/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH)+"/"+ fileName;

                    try{
                        FTPClientUtils.uploadFile(new ByteArrayInputStream(document.asXML().getBytes("utf-8")), path);
                    }catch (Exception e){
                        //if ftp server is error, wait 5 minutes, try again
                        Thread.sleep(5*60*1000);
                        FTPClientUtils.uploadFile(new ByteArrayInputStream(document.asXML().getBytes("utf-8")), path);
                    }

                    RedisGpsFileInfo gpsFileInfo = new RedisGpsFileInfo();
                    gpsFileInfo.setTaskCode(task.getTaskCode());
                    gpsFileInfo.setDeviceNumber(task.getDeviceNumber());
                    gpsFileInfo.setDate(DateFormatUtils.dateToString(date, DateFormatUtils.DATETIME_FORMAT));
                    gpsFileInfo.setFileName(fileName);
                    gpsFileInfo.setPath(path);

                    GpsFileRedis.saveGpsFile(gpsFileInfo);
                }catch (Exception e){
                    logger.error("save gps file is error ", e);
                    throw new BusinessException("save gps file is error");
                }
            }
        }

        GpsFileRedis.saveGpsFileJob(DateFormatUtils.dateToString(date, DateFormatUtils.DATE_FORMAT_SYSTEM));
        logger.info("save gps file is ok, the date is "+ DateFormatUtils.dateToString(date, DateFormatUtils.DATE_FORMAT_SYSTEM));
    }

    /*************************************************************************************************************************************
     ** @Description  convert input stream(gps xml) to gps result
     ** @param: inputStream
     ** @Return com.ceiec.twmp.tmp.vo.gps.GpsResultVO
     ** @Author Ding
     ** @Date 2019/3/28 14:39
     **
     ************************************************************************************************************************************/
    private GpsResultVO gpsXML2GpsResult (InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element rootElement = document.getRootElement();
        String deviceNumber = rootElement.attributeValue(GpsInfo.MONGO_DEVICE_NUMBER);
        String taskCode = rootElement.attributeValue(GpsInfo.MONGO_TASK_CODE);
        String date = rootElement.attributeValue(DOCUMENT_DATE_NAME);

        GpsResultVO result = new GpsResultVO();
        result.setDate(date);

        List<Element> elementList = new ArrayList<>();
        elementList = rootElement.elements(DOCUMENT_GPS_NAME);

        List<GpsInfo> gpsInfoList = new ArrayList<>();
        if(elementList!=null && elementList.size()>0){
            for(Element element : elementList){
                GpsInfo gpsInfo = new GpsInfo();
                gpsInfo.setDeviceNumber(deviceNumber);
                gpsInfo.setTaskCode(taskCode);
                gpsInfo.setLongitude(element.elementText(GpsInfo.MONGO_LONGITUDE));
                gpsInfo.setLatitude(element.elementText(GpsInfo.MONGO_LATITUDE));
                gpsInfo.setSpeed(element.elementText(GpsInfo.MONGO_SPEED));
                gpsInfo.setDirection(element.elementText(GpsInfo.MONGO_DIRECTION));
                gpsInfo.setGpsTime(element.elementText(GpsInfo.MONGO_GPS_TIME));
                gpsInfoList.add(gpsInfo);
            }
        }
        result.setGpsInfoList(gpsInfoList);

        result = computeNoDataTime(result);

        return result;
    }


    /*************************************************************************************************************************************
     ** @Description compute the gps list which period has no data
     ** @param: gpsResultVO
     ** @Return com.ceiec.twmp.tmp.vo.gps.GpsResultVO
     ** @Author Ding
     ** @Date 2019/3/28 14:41
     **
     ************************************************************************************************************************************/
    private GpsResultVO computeNoDataTime(GpsResultVO gpsResultVO){
        List<GpsInfo> gpsInfoList = new ArrayList<>();
        //假设gps点正常每1分钟上传一次,如果两个点之间距离时间>=12分钟，那么认为不正常
        if(gpsInfoList!=null && gpsInfoList.size()>0){
            String[] noDateTime = new String[gpsInfoList.size()];

            Date tempDate = null;
            for(int i=0; i<gpsInfoList.size(); i++){
                Date gpsTime = DateFormatUtils.stringToDate(gpsInfoList.get(i).getGpsTime(), DateFormatUtils.DATETIME_FORMAT_SYSTEM);
                if(i == 0){
                    tempDate = gpsTime;
                    continue;
                }

                if((gpsTime.getTime() - tempDate.getTime())>=12*60*1000L){
                    Calendar calendar = Calendar.getInstance();
                    String period = "";
                    calendar.setTime(tempDate);
                    String time1 = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
                    calendar.setTime(gpsTime);
                    String time2 = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
                    period = time1 +" "+time2;
                    noDateTime[i] = period;
                }
                tempDate = gpsTime;
            }
            gpsResultVO.setNoDataTime(noDateTime);
        }
        return gpsResultVO;
    }


}
