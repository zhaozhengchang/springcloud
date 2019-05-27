package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.services.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Ding
 * @version V1.0
 * @Description: file down load controller
 * @create 2019-03-27 17:46
 **/
@RestController
@RequestMapping("/downloadFile")
public class FileDownloadController {

    @Autowired
    private IFileService fileService;

    /*************************************************************************************************************************************
     ** @Description download file
     ** @param: token
     * @param: file
     * @param: fileName
     * @param: fileType
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/3/6 15:02
     **
     ************************************************************************************************************************************/
    @GetMapping("/{downloadAsFile}/{fileId}")
    public void fileDownload(@PathVariable Boolean downloadAsFile, @PathVariable Long fileId, HttpServletResponse response) throws Exception {

        fileService.downloadFile(downloadAsFile, fileId, response);
    }
}
