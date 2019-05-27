package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.vo.gps.GpsResultVO;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description: monitor service
 * @create 2019-03-28 10:23
 **/
public interface IMonitorService {

    /*************************************************************************************************************************************
     ** @Description  get gps info
     ** @param: deviceNumber
     ** @param: date
     ** @Return java.util.List<com.ceiec.twmp.tmp.vo.gps.GpsInfo>
     ** @Author Ding
     ** @Date 2019/3/28 10:36
     **
     ************************************************************************************************************************************/
    GpsResultVO queryGpsInfo(String deviceNumber, String taskCode, String date) throws DocumentException, IOException;

    /*************************************************************************************************************************************
     ** @Description export gps
     ** @param: httpServletRequest
     ** @param: deviceNumber
     ** @param: date
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/28 10:37
     **
     ************************************************************************************************************************************/
    void exportGpsInfo(HttpServletResponse response, String deviceNumber, String taskCode, String date) throws TransformerException, IOException;

    /*************************************************************************************************************************************
     ** @Description get gps info and generate xml document
     ** @param: deviceNumber
     ** @param: taskCode
     ** @param: date
     ** @Return org.dom4j.Document
     ** @Author Ding
     ** @Date 2019/3/28 12:03
     **
     ************************************************************************************************************************************/
    Document getGpsXML(String deviceNumber, String taskCode, Date date);

    /*************************************************************************************************************************************
     ** @Description import gps result
     ** @param: file
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/28 14:08
     **
     ************************************************************************************************************************************/
    GpsResultVO importGpsResult(MultipartFile file) throws IOException, DocumentException;

    /*************************************************************************************************************************************
     ** @Description save gps as a file
     ** @param: date
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/29 9:36
     **
     ************************************************************************************************************************************/
    void saveGpsFile(Date date) throws UnsupportedEncodingException;
}
