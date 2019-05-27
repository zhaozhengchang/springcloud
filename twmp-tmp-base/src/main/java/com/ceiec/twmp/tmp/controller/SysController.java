package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.cache.redis.SysDictRedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.services.IFileService;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.dict.result.DictResultVo;
import com.ceiec.twmp.tmp.vo.file.FileAddVo;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: some general method in system
 * @create 2019-03-04 11:54
 **/
@RestController
@RequestMapping("/system")
public class SysController {

    @Autowired
    private IFileService fileService;


    /*************************************************************************************************************************************
     ** @Description get all system dict
     ** @param:
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/3/4 11:58
     **
     ************************************************************************************************************************************/
    @PostMapping("/getAllDict")
    public ResponseContent getAllDict(@RequestHeader String token) {
         Map<String, List<DictResultVo>> map = SysDictRedis.getAllSysDict();
         return new ResponseContent(ResponseType.SUCCESS, map);
    }

    /*************************************************************************************************************************************
     ** @Description
     ** @param: token
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/3/5 18:02
     **
     ************************************************************************************************************************************/
    @PostMapping("/getUserInfo")
    public ResponseContent getUserInfo(@RequestHeader String token) {
        RedisUserInfoVO redisUserInfoVo = UserInfoRedis.getUser(token);
        return new ResponseContent(ResponseType.SUCCESS, redisUserInfoVo);
    }


    /*************************************************************************************************************************************
     ** @Description upload file
     ** @param: token
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/3/6 14:28
     **
     ************************************************************************************************************************************/
    @PostMapping("/fileUpload")
    public ResponseContent fileUpload(@RequestHeader String token, @RequestParam("file") MultipartFile file, @RequestParam String fileName,
                                      @RequestParam Byte fileType) throws IOException {
        FileAddVo fileAddVo = new FileAddVo();
        fileAddVo.setFileName(fileName);
        fileAddVo.setFileType(fileType);

        Long fileId = fileService.uploadFile(token, file.getInputStream(),fileAddVo);
        return new ResponseContent(ResponseType.SUCCESS, fileId);
    }



}
