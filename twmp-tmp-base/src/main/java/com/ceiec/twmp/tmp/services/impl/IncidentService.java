package com.ceiec.twmp.tmp.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ceiec.twmp.tmp.EConfig;
import com.ceiec.twmp.tmp.services.IIncidentService;
import com.ceiec.twmp.tmp.utils.HttpUtil;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.vo.CADHttpResultVO;
import com.ceiec.twmp.tmp.vo.incident.push.PushAlarmVO;
import com.ceiec.twmp.tmp.vo.incident.result.IncidentTypeVO;
import com.ceiec.twmp.tmp.vo.incident.result.PushAlarmResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @title: IncidentService </br>
 * @createDate: 2019/4/18 16:02 </br>
 * @author: shihsh  </br>
 * @description: 警情处理 </br>
 * @version: V1.0
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class IncidentService implements IIncidentService {
    @Autowired
    private Environment env;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String getUrl(String url) {

        String ip = env.getProperty(EConfig.CAD_IP);
        String port = env.getProperty(EConfig.CAD_PORT);
        String project = env.getProperty(EConfig.CAD_PROJECT);
        String result = "http://" + ip + ":" + port + "/" +project + url;
        return result;
    }

    @Override
    public List<IncidentTypeVO> getIncidentTypes() {
        List<IncidentTypeVO> incidentTypeVOList = null;
        String url = "GetAllIncidentTypeList.do";
        url = getUrl(url);
        logger.info("-----警情类型字典查询------，url is:" + url);
        try {
            String result = HttpUtil.sendPost(url, "");
            if (!StringUtils.isNullOrEmpty(result)) {
                JSONObject jsonObject = (JSONObject) JSON.parse(result);
                if (!"0000".equals(jsonObject.get("code"))) {
                    logger.error("---------调用接处警接口GetAllIncidentTypeList.do失败---------返回结果为：" + result);
                } else {
                    logger.info("------------调用接处警接口GetAllIncidentTypeList.do成功---------返回结果为：" + result);
                    CADHttpResultVO cadHttpResultVO = JSONObject.parseObject(result, CADHttpResultVO.class);
                    jsonObject = (JSONObject)JSON.toJSON(cadHttpResultVO.getMess());
                    incidentTypeVOList = JSONArray.parseArray(JSON.toJSONString(jsonObject.get("incidentTypeList")), IncidentTypeVO.class);
                }
            }
        } catch (IOException e) {
            logger.error("警情类型字典查询调用失败!", e);
        }
        return incidentTypeVOList;
    }

    @Override
    public PushAlarmResultVO pushAlarmToCAD(String token, PushAlarmVO pushAlarmVO) {
        PushAlarmResultVO resultVO = new PushAlarmResultVO();
        resultVO.setMessage("fail to push incident to CAD!");
        resultVO.setSuccess(true);
        String url = "ElectronicFootRingAlarm.do";
        url = getUrl(url);
        String paramString = JSON.toJSONString(pushAlarmVO);
        logger.info("---------推送警情到接处警系统-----------。 url is:" + url + ",param is " + paramString);
        try {
            String result = HttpUtil.sendPost(url, paramString);
            if (!StringUtils.isNullOrEmpty(result)) {
                JSONObject jsonObject = (JSONObject) JSON.parse(result);
                if (!"0000".equals(jsonObject.get("code"))) {
                    logger.error("-----------推送警情失败----------返回结果为：" + result);
                } else {
                    logger.info("------------推送警情成功----------返回结果为：" + result);
                    resultVO.setMessage("succeed");
                    resultVO.setSuccess(true);
                }
            }

        } catch (IOException e) {
            logger.error("--------推送警情到接处警系统失败--------", e);
        }
        return resultVO;
    }
}
