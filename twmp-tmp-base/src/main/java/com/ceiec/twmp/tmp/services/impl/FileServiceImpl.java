package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.common.dict.FileType;
import com.ceiec.twmp.tmp.entity.TwmpSysFile;
import com.ceiec.twmp.tmp.mapper.TwmpSysFileMapper;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.services.IFileService;
import com.ceiec.twmp.tmp.utils.tools.ftp.FTPClientUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.file.FileAddVo;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description: file service
 * @create 2019-03-06 14:05
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements IFileService {

    @Autowired
    private TwmpSysFileMapper twmpSysFileMapper;

    @Override
    public Long uploadFile(String token, InputStream inputStream, FileAddVo fileAddVo){
        RedisUserInfoVO redisUserInfoVo = UserInfoRedis.getUser(token);

        TwmpSysFile twmpSysFile = new TwmpSysFile();
        twmpSysFile.setFileId(SnowflakeIdWorkerUtil.generateLongId());
        twmpSysFile.setCreateTime(new Date());
        twmpSysFile.setCreator(redisUserInfoVo.getUserName());
        twmpSysFile.setCreatorId(redisUserInfoVo.getUserId());
        twmpSysFile.setFileType(fileAddVo.getFileType());
        twmpSysFile.setFileName(fileAddVo.getFileName());
        Calendar cal = Calendar.getInstance();
        int year =cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int date = cal.get(Calendar.DATE);
        twmpSysFile.setFilePath("/"+year+"/"+month+"/"+date+"/"+ FileType.get(twmpSysFile.getFileType()).toString()+"/"+twmpSysFile.getFileName());

        twmpSysFileMapper.insertSelective(twmpSysFile);


        FTPClientUtils.uploadFile(inputStream, twmpSysFile.getFilePath());

        return twmpSysFile.getFileId();
    }

    @Override
    public void downloadFile(Boolean downloadAsFile, Long fileId, HttpServletResponse response) throws Exception {
        TwmpSysFile twmpSysFile = twmpSysFileMapper.selectByPrimaryKey(fileId);

        response.reset();

        if(downloadAsFile){
            response.setHeader("Content-type","multipart/form-data;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(twmpSysFile.getFileName(),  "UTF-8"));
        }

        FTPClientUtils.downloadFile(twmpSysFile.getFilePath(), response.getOutputStream());
    }

}
