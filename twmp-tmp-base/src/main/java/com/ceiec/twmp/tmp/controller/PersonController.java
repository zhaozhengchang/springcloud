package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.services.IPersonService;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.vo.person.add.PersonDataVO;
import com.ceiec.twmp.tmp.vo.person.query.PersonQueryVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonDataResultVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonInfoImportResultVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonInfoListResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CreateDate：2019/3/1 15:07 </br>
 * Author：shihsh  </br>
 * Description: 人员信息Controller </br>
 **/

@RestController
@RequestMapping("/information")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
    *
    * @description:  添加、编辑人员信息
    * @param:token
    * @param: personDataVO
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/3/11
    */
    @PostMapping("/personData")
    public ResponseContent addOrEditPerson(@RequestHeader String token, @RequestBody PersonDataVO personDataVO) {
        PersonDataResultVO personDataResultVO = personService.addOrEditPerson(token, personDataVO);
        return new ResponseContent(ResponseType.SUCCESS, personDataResultVO);
    }


    /**
    *
    * @description:  根据被监控人员Id删除被监控人员
    * @param:token
    * @param: personId
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/3/11
    */
    @PostMapping("/personDel/{personId}")
    public ResponseContent delPerson(@RequestHeader String token, @PathVariable Long personId) {
        personService.deletePerson(token, personId);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /**
    *
    * @description: 查询被监控人员列表
    * @param: token
    * @param: personQueryVO
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/3/11
    */
    @PostMapping("/personList")
    public ResponseContent queryPersonList(@RequestHeader String token, @RequestBody PersonQueryVO personQueryVO) {
        PagedItemsVO<PersonInfoListResultVO> personInfos = personService.personInfoQueryByPage(token, personQueryVO);
        return new ResponseContent(ResponseType.SUCCESS, personInfos);
    }


    /**
    *
    * @description:  通过excel批量导入被监控人员信息
    * @param: token
    * @param: file excel
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/3/25
    */
    @PostMapping("/personImport")
    public ResponseContent importPersonInfo(@RequestHeader String token, @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        PersonInfoImportResultVO personInfoImportResultVO;
        personInfoImportResultVO = personService.importPersonInfoExcel(token ,fileName, file);
        return new ResponseContent(ResponseType.SUCCESS, personInfoImportResultVO);
    }


    @PostMapping("/personImportTemplate")
    public ResponseContent downloadTemplate(@RequestHeader String token, HttpServletResponse response) throws IOException {
        personService.getPersonImportTemplate(token, response);
        return new ResponseContent(ResponseType.SUCCESS);
    }

    /**
    *
    * @description:  导出被监控人员基本信息
    * @param: token
    * @param: response
    * @param: personQueryVO
    * @return: com.ceiec.twmp.tmp.utils.response.ResponseContent
    * @author: shihsh
    * @date: 2019/4/15
    */

    @PostMapping("/personExport")
    public ResponseContent exportPersonInfo(@RequestHeader String token, HttpServletResponse response,@RequestBody PersonQueryVO personQueryVO) throws IOException {
        personService.exportPersonInfo(token, response, personQueryVO);
        return new ResponseContent(ResponseType.SUCCESS);
    }
}
