package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.vo.file.FileAddVo;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * @author Ding
 * @version V1.0
 * @Description: about file upload and download
 * @create 2019-03-06 14:04
 **/
public interface IFileService {

    Long uploadFile(String token, InputStream inputStream, FileAddVo fileAddVo);

    void downloadFile(Boolean downloadAsFile, Long fileId, HttpServletResponse response) throws Exception;

}
