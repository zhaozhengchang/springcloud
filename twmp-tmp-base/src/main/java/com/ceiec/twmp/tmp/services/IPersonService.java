package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.person.add.PersonDataVO;
import com.ceiec.twmp.tmp.vo.person.query.PersonQueryVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonAndDeviceVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonDataResultVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonInfoImportResultVO;
import com.ceiec.twmp.tmp.vo.person.result.PersonInfoListResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CreateDate：2019/3/1 15:20 </br>
 * Author：shihsh  </br>
 * Description: 被监控人员Service </br>
 **/


public interface IPersonService {


    /**
    * @description:  新增被监控人员
    * @param: token the token
    * @param: personAddVO
    * @return: void
    * @author: shihsh
    * @date: 2019/3/1
    */
    PersonDataResultVO addPerson(String token, PersonDataVO personDataVO);

    /**
    *
    * @description: 新增或编辑被监控人员
    * @param: token
    * @param: personDataResultVO
    * @return: com.ceiec.twmp.tmp.vo.person.result.PersonDataResultVO
    * @author: shihsh
    * @date: 2019/3/11
    */
    PersonDataResultVO addOrEditPerson(String token, PersonDataVO personDataResultVO);

    /**
    *
    * @description: 编辑被监控人员
    * @param: token
    * @param: personDataVO
    * @return: com.ceiec.twmp.tmp.vo.person.result.PersonDataResultVO
    * @author: shihsh
    * @date: 2019/3/11
    */
    PersonDataResultVO editPerson(String token, PersonDataVO personDataVO);


    /**
    *
    * @description: 被监控人员列表查询
    * @param: token
    * @param: personQueryVO
    * @return: com.ceiec.twmp.tmp.utils.page.PagedItemsVO<com.ceiec.twmp.tmp.vo.person.result.PersonInfoListResultVO>
    * @author: shihsh
    * @date: 2019/3/11
    */
    PagedItemsVO<PersonInfoListResultVO> personInfoQueryByPage(String token, PersonQueryVO personQueryVO);

    /**
    *
    * @description: 删除被监控人员
    * @param: token
    * @param: personId
    * @return: void
    * @author: shihsh
    * @date: 2019/3/11
    */
    void deletePerson(String token, Long personId);


    PersonInfoImportResultVO importPersonInfoExcel(String token, String fileName, MultipartFile file);

    PersonAndDeviceVO getPersonAndDeviceInfo(String token, Long personId);

    void getPersonImportTemplate(String token, HttpServletResponse response) throws IOException;

    void exportPersonInfo(String token, HttpServletResponse response, PersonQueryVO personQueryVO) throws IOException;
}
