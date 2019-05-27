package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.common.dict.Deleted;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.common.exception.ParameterException;
import com.ceiec.twmp.tmp.entity.TwmpSysDict;
import com.ceiec.twmp.tmp.mapper.TwmpSysDictMapper;
import com.ceiec.twmp.tmp.cache.redis.SysDictRedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.services.IDictService;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.tools.ClassUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.dict.result.DictResultVo;
import com.ceiec.twmp.tmp.vo.dict.save.DictSaveVo;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Ding
 * @version V1.0
 * @Description: sys dict service
 * @create 2019-02-28 14:57
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class DictServiceImpl implements IDictService {

    private static final Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;


    @Autowired
    private TwmpSysDictMapper twmpSysDictMapper;

    
    @Override
    public Long addOrUpdateDict(String token, DictSaveVo dicSaveVo){
        TwmpSysDict twmpSysDict = new TwmpSysDict();

        RedisUserInfoVO userInfoVo = UserInfoRedis.getUser(token);

        ObjectUtils.copy(dicSaveVo, twmpSysDict);

        if(twmpSysDict.getDictId() == null ){//update dict

            long num = twmpSysDictMapper.getDictNum(dicSaveVo);
            if(num>0){
                throw new BusinessException(localeMessageSourceService.getMessageLocal("error.dict.illegal",userInfoVo.getLanguage()));
            }

            twmpSysDict.setDictId(SnowflakeIdWorkerUtil.generateLongId());
            twmpSysDict.setCreator(userInfoVo.getUserName());
            twmpSysDict.setCreatorId(userInfoVo.getUserId());
            twmpSysDict.setCreateTime(new Date());
            twmpSysDictMapper.insertSelective(twmpSysDict);

        }else{//update dict
            twmpSysDict.setUpdater(userInfoVo.getUserName());
            twmpSysDict.setUpdaterId(userInfoVo.getUserId());
            twmpSysDict.setUpdateTime(new Date());
            twmpSysDictMapper.updateByPrimaryKeySelective(twmpSysDict);
        }


        saveDictRedis();//after update, save in redis

        return twmpSysDict.getDictId();

    }



    @Override
    public void saveDictRedis() {
        Map<String, List<DictResultVo>> map = getSysDictFromPackage();

        List<DictResultVo> dictList = getAllDict();
        if(dictList!=null && dictList.size()>0){
            for(DictResultVo dictResultVo: dictList){

                if(map.get(dictResultVo.getDictType()) == null){
                    List<DictResultVo> list = new ArrayList<>();
                    list.add(dictResultVo);
                    map.put(dictResultVo.getDictType(), list);
                }else{
                    List<DictResultVo> list = map.get(dictResultVo.getDictType());
                    list.add(dictResultVo);
                    map.put(dictResultVo.getDictType(), list);
                }

            }
        }


        SysDictRedis.saveSysDict(map);
    }

    @Override
    public List<DictResultVo> getAllDict() {
        return twmpSysDictMapper.getAllDict();
    }

    @Override
    public void deleteDict(String token, Long dictId) {
        RedisUserInfoVO userInfoVo = UserInfoRedis.getUser(token);

        TwmpSysDict twmpSysDict = new TwmpSysDict();
        twmpSysDict.setDictId(dictId);
        twmpSysDict.setDeleted(Deleted.deleted.value);
        twmpSysDict.setUpdater(userInfoVo.getUserName());
        twmpSysDict.setUpdaterId(userInfoVo.getUserId());
        twmpSysDict.setUpdateTime(new Date());

        twmpSysDictMapper.updateByPrimaryKeySelective(twmpSysDict);
    }


    /*************************************************************************************************************************************
     ** @Description get sys dict from package
     ** @param:
     ** @Return java.util.Map<java.lang.String,java.util.List<com.ceiec.twmp.tmp.vo.dict.result.DictResultVo>>
     ** @Author Ding
     ** @Date 2019/3/4 11:16
     **
     ************************************************************************************************************************************/
    private Map<String, List<DictResultVo>> getSysDictFromPackage(){
        String packageName = "com.ceiec.twmp.tmp.common.dict";

        Set<Class<?>> classes = ClassUtils.getClasses(packageName);

        Map<String, List<DictResultVo>> map = new HashMap<>();
        if(classes!=null && classes.size()>0){
            for(Class<?> cls: classes){
                for(Object o: cls.getEnumConstants()){

                    DictResultVo dictResultVo = new DictResultVo();

                    try {
                        String type=null, name=null, nameCode=null, comment=null;
                        byte value = 0;
                        if(o.getClass().getDeclaredField("type").get(o)!=null){
                            type = o.getClass().getDeclaredField("type").get(o).toString();
                        }
                        if(o.getClass().getDeclaredField("value").get(o)!=null){
                            value = (byte)Integer.parseInt(o.getClass().getDeclaredField("value").get(o).toString());
                        }
                        if(o.getClass().getDeclaredField("name").get(o)!=null){
                            name = o.getClass().getDeclaredField("name").get(o).toString();
                        }
                        if(o.getClass().getDeclaredField("nameCode").get(o)!=null){
                            nameCode = o.getClass().getDeclaredField("nameCode").get(o).toString();
                        }
                        if(o.getClass().getDeclaredField("comment").get(o)!=null){
                            comment = o.getClass().getDeclaredField("comment").get(o).toString();
                        }

                        dictResultVo.setDictType(type);
                        dictResultVo.setDictValue(value);
                        dictResultVo.setDictName(name);
                        dictResultVo.setDictNameCode(nameCode);
                        dictResultVo.setComment(comment);
                    } catch (IllegalAccessException e) {
                        logger.error("have no this column", e);
                    } catch (NoSuchFieldException e) {
                        logger.error("have no this column, this dict is wrong", e);
                    }

                    try {
                        String parentType = o.getClass().getDeclaredField("parentType").get(o).toString();
                        byte parentValue = (byte)Integer.parseInt(o.getClass().getDeclaredField("parentValue").get(o).toString());

                        dictResultVo.setParentType(parentType);
                        dictResultVo.setParentValue(parentValue);
                    } catch (IllegalAccessException e) {

                    } catch (NoSuchFieldException e) {

                    }


                    if(map.get(dictResultVo.getDictType()) == null){
                        List<DictResultVo> list = new ArrayList<>();
                        list.add(dictResultVo);
                        map.put(dictResultVo.getDictType(), list);
                    }else{
                        List<DictResultVo> list = map.get(dictResultVo.getDictType());
                        list.add(dictResultVo);
                        map.put(dictResultVo.getDictType(), list);
                    }

                }
            }
        }

        return map;

    }


}
