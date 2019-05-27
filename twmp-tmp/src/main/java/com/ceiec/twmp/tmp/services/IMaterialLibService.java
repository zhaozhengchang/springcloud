package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.vo.MaterialAddVO;

/**
 * CreateDate：2018/9/18 <br/>
 * Author：WangHao <br/>
 * Description: material service interface
 **/
public interface IMaterialLibService {

    /**
     * save new material
     *
     * @param token login user token
     * @param materialAddVO the VO for adding new material
     * @return material id
     */
    String addMaterial(String token, MaterialAddVO materialAddVO);


}
