package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.vo.dict.result.DictResultVo;
import com.ceiec.twmp.tmp.vo.dict.save.DictSaveVo;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: interface for sys dict service
 * @create 2019-02-28 14:57
 **/
public interface IDictService {

    /*************************************************************************************************************************************
     ** @Description  Add or Update sys dict
     * @param dictSaveVo  the dict save vo
     * @result the save result dicId
     ** @Author Ding
     ** @Date 2019/2/28 15:30
     **
     ************************************************************************************************************************************/

    Long addOrUpdateDict(String token, DictSaveVo dictSaveVo);


    /*************************************************************************************************************************************
     ** @Description save dict into redis including system dict and database dict
     ** @param:
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/1 16:40
     **
     ************************************************************************************************************************************/
    void saveDictRedis();

    /*************************************************************************************************************************************
     ** @Description get all dict from database
     ** @param:
     ** @Return java.util.List<com.ceiec.twmp.tmp.vo.dict.result.DictResultVo>
     ** @Author Ding
     ** @Date 2019/3/4 11:15
     **
     ************************************************************************************************************************************/
    List<DictResultVo> getAllDict();

    /*************************************************************************************************************************************
     ** @Description delete dict
     ** @param: dictId
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/6 11:34
     **
     ************************************************************************************************************************************/
    void deleteDict(String token, Long dictId);
}
