package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.cache.redis.DepartmentRedis;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.entity.TwmpSysDepartment;
import com.ceiec.twmp.tmp.entity.TwmpSysUser;
import com.ceiec.twmp.tmp.common.dict.OperateObjectType;
import com.ceiec.twmp.tmp.common.dict.OperateType;
import com.ceiec.twmp.tmp.mapper.TwmpDevDeviceMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSysDepartmentMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSysUserMapper;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.services.IDepartmentService;
import com.ceiec.twmp.tmp.services.ILogService;
import com.ceiec.twmp.tmp.utils.ObjectUtils;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.department.add.DepartmentAddVO;
import com.ceiec.twmp.tmp.vo.department.result.*;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigInteger;
import java.util.*;

/**
 * CreateDate：2019/1/17 <br/>
 * Author：wenliang <br/>
 * Description: department service
 */
@Service
@Transactional(rollbackFor = Exception.class) //事务控制
public class DepartmentServiceImpl implements IDepartmentService {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * system department mapper
     */
    @Autowired
    private TwmpSysDepartmentMapper twmpSysDepartmentMapper;

    @Autowired
    private TwmpSysUserMapper twmpSysUserMapper;

    @Autowired
    private TwmpDevDeviceMapper twmpDevDeviceMapper;
    @Autowired
    private ILogService logService;

    @Autowired
    private IDepartmentService departmentService;

    /**
     * Department query list paged items vo.
     *
     * @param token          the token
     * @param departmentName the department name
     * @return the paged items vo
     */
    @Override
    public List<DepartmentListResultVO> departmentQueryList(String token, String departmentName) {
        return twmpSysDepartmentMapper.getDepartmentList(departmentName);
    }

    /**
     * Add department department save result vo.
     *
     * @param token           the token
     * @param departmentAddVO the department save vo
     * @return the department save result vo
     */
    @Override
    public DepartmentAddResultVO addDepartment(String token, DepartmentAddVO departmentAddVO) {
        DepartmentAddResultVO departmentAddResultVO = new DepartmentAddResultVO();
        TwmpSysDepartment twmpSysDepartment = new TwmpSysDepartment();
        BigInteger departmentId = SnowflakeIdWorkerUtil.generateId();
        ObjectUtils.copy(departmentAddVO, twmpSysDepartment);

        twmpSysDepartment.setDepartmentId(departmentId.longValue());
        twmpSysDepartment.setDeleted((byte) TmpBaseConstants.FLAG_DELETE_FALSE);
        twmpSysDepartment.setCreator(TokenUtils.getUserName(token));
        twmpSysDepartment.setCreatorId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpSysDepartment.setCreateTime(new Date());
        twmpSysDepartment.setUpdater(TokenUtils.getUserName(token));
        twmpSysDepartment.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpSysDepartment.setUpdateTime(new Date());

        twmpSysDepartmentMapper.insertSelective(twmpSysDepartment);
        departmentAddResultVO.setDepartmentId(departmentId.longValue());


        RedisDepartmentVO redisDepartmentVO = new RedisDepartmentVO();
        ObjectUtils.copy(twmpSysDepartment, redisDepartmentVO);
        DepartmentRedis.saveDepartmentInfo(redisDepartmentVO);

        return departmentAddResultVO;
    }


    /**
     * Edit department.
     *
     * @param token           the token
     * @param departmentAddVO the department update vo
     */
    @Override
    public void editDepartment(String token, DepartmentAddVO departmentAddVO) {
        TwmpSysDepartment twmpSysDepartment = new TwmpSysDepartment();
        ObjectUtils.copy(departmentAddVO, twmpSysDepartment);
        twmpSysDepartment.setDeleted((byte) TmpBaseConstants.FLAG_DELETE_FALSE);
        twmpSysDepartment.setUpdater(TokenUtils.getUserName(token));
        twmpSysDepartment.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpSysDepartment.setUpdateTime(new Date());

        twmpSysDepartmentMapper.updateByPrimaryKeySelective(twmpSysDepartment);

        RedisDepartmentVO redisDepartmentVO = new RedisDepartmentVO();
        ObjectUtils.copy(twmpSysDepartment, redisDepartmentVO);
        DepartmentRedis.saveDepartmentInfo(redisDepartmentVO);
    }


    /**
     * Name exist flag boolean.
     *
     * @param token          the token
     * @param departmentName the department name
     * @return the boolean
     */
    @Override
    public boolean nameExistFlag(String token, String departmentName) {
        boolean existFlag = false;
        Example example = new Example(TwmpSysDepartment.class);
        example.createCriteria()
                .andEqualTo("departmentName", departmentName);
        List<TwmpSysDepartment> exitEntity = twmpSysDepartmentMapper.selectByExample(example);
        if (exitEntity.size() > 0) {
            existFlag = true;
        }
        return existFlag;
    }


    /**
     * Delete department.
     *
     * @param token        the token
     * @param departmentId the department id
     */
    @Override
    public void deleteDepartment(String token, BigInteger departmentId) {
        TwmpSysDepartment twmpSysDepartment = new TwmpSysDepartment();
        twmpSysDepartment.setDepartmentId(departmentId.longValue());
        twmpSysDepartment.setDeleted((byte) TmpBaseConstants.FLAG_DELETE_TRUE);
        twmpSysDepartmentMapper.updateByPrimaryKeySelective(twmpSysDepartment);

        RedisDepartmentVO redisDepartmentVO = new RedisDepartmentVO();
        redisDepartmentVO.setDepartmentId(departmentId.longValue());
        DepartmentRedis.deleteDepartmentInfo(redisDepartmentVO);

    }

    @Override
    public List<DepartmentListResultVO> getAllDepartment() {
        return twmpSysDepartmentMapper.getAllDepartment();
    }

    @Override
    public DepartmentTreeVO getDepartmentTree(Long fatherDepartmentId) {
        List<DepartmentListResultVO> departmentList = twmpSysDepartmentMapper.getAllDepartment();
        if (departmentList != null && departmentList.size() > 0) {
            List<DepartmentTreeVO> departmentTreeVOS = new ArrayList<>();
            Map<Long, DepartmentTreeVO> departmentTreeVOMap = new HashMap<>();

            for (DepartmentListResultVO departmentListResultVO : departmentList) {
                DepartmentTreeVO departmentTreeVO = new DepartmentTreeVO();
                ObjectUtils.copy(departmentListResultVO, departmentTreeVO);
                departmentTreeVOS.add(departmentTreeVO);
                departmentTreeVOMap.put(departmentTreeVO.getDepartmentId(), departmentTreeVO);
            }

            for (DepartmentTreeVO departmentListResultVO : departmentTreeVOS) {
                if (departmentListResultVO.getParentId() != null && departmentTreeVOMap.get(departmentListResultVO.getParentId()) != null) {
                    DepartmentTreeVO departmentTreeVO = departmentTreeVOMap.get(departmentListResultVO.getParentId());

                    List<DepartmentTreeVO> list = new ArrayList<>();
                    if (departmentTreeVO.getChildrenList() != null) {
                        list = departmentTreeVO.getChildrenList();
                    }
                    list.add(departmentListResultVO);
                    departmentTreeVO.setChildrenList(list);
                }
            }

            return departmentTreeVOMap.get(fatherDepartmentId);

        }

        return null;
    }

    @Override
    public String getOwnDepartmentIds(Long fatherDepartmentId) {

        DepartmentTreeVO departmentTreeVO = getDepartmentTree(fatherDepartmentId);
        String ids = departmentTreeVO.getDepartmentId().toString();

        ids = ids + "," + getChildDepartmentId(departmentTreeVO);

        ids = ids.replaceAll(",//", "");

        return ids;
    }


    /*************************************************************************************************************************************
     ** @Description get all children node id from father department
     ** @param: departmentTreeVO
     ** @Return java.lang.String
     ** @Author Ding
     ** @Date 2019/3/5 16:53
     **
     ************************************************************************************************************************************/
    private String getChildDepartmentId(DepartmentTreeVO departmentTreeVO) {
        String ids = "";
        if (departmentTreeVO.getChildrenList() != null && departmentTreeVO.getChildrenList().size() > 0) {
            List<DepartmentTreeVO> childrenList = departmentTreeVO.getChildrenList();
            for (DepartmentTreeVO childDepartment : childrenList) {
                ids = ids + "," + childDepartment.getDepartmentId().toString();
                ids = ids + "," + getChildDepartmentId(childDepartment);
            }
        }

        if (ids.startsWith(",")) {
            ids = ids.substring(1);
        }

        if (StringUtils.isNullOrEmpty(ids)) {
            return "//";
        }
        return ids;
    }

    @Override
    public DepartmentAddResultVO addOrEditDepartment(String token, DepartmentAddVO departmentAddVO) throws BusinessException{
        if (null == departmentAddVO.getDepartmentId()) {
            //校验组织机构名是否已存在
            if (existDepartmentName(departmentAddVO.getDepartmentName())) {
//                throw new BusinessException("department name existed !");
                return new DepartmentAddResultVO(ResponseType.DEPARTMENT_NAME_EXISTED);
            }
            //校验组织机构编码是否已存在
            if (existDepartmentCode(departmentAddVO.getDepartmentCode())) {
                return new DepartmentAddResultVO(ResponseType.DEPARTMENT_CODE_EXISTED);
//                throw new BusinessException("department code existed !");
            }
            //新增组织机构
            addDepartment(token, departmentAddVO);
            //记录操作日志
            logService.saveOperateLog(token, OperateType.insert.value, OperateObjectType.department.value,
                    departmentAddVO.getDepartmentName(), null);
        } else {
            //组织机构有更新时，要把相同组织机构的所有用户更新一遍
            updateUsers(token, departmentAddVO);
            //编辑组织机构
            editDepartment(token, departmentAddVO);
            //记录操作日志
            logService.saveOperateLog(token, OperateType.update.value, OperateObjectType.department.value,
                    departmentAddVO.getDepartmentName(), null);
        }
        //重新缓存用户组织机构信息
        updateDepartmentTree(token);
        return new DepartmentAddResultVO();
    }

    /**
     * 组织机构的map_center修改了之后，把组织机构相同的所有用户的map_center全部更新
     *
     * @param token
     * @param departmentAddVO
     */
    private void updateUsers(String token, DepartmentAddVO departmentAddVO) {
        TwmpSysDepartment twmpSysDepartment = twmpSysDepartmentMapper.selectByPrimaryKey(departmentAddVO.getDepartmentId());
        TwmpSysUser twmpSysUser = new TwmpSysUser();
        twmpSysUser.setDepartmentId(departmentAddVO.getDepartmentId());
        List<TwmpSysUser> users = twmpSysUserMapper.select(twmpSysUser);
        if (twmpSysDepartment.getMapCenter() != null && (!twmpSysDepartment.getMapCenter().equals(departmentAddVO.getMapCenter()))) {
            for (TwmpSysUser user : users) {
                user.setMapCenter(departmentAddVO.getMapCenter());
                user.setUpdater(TokenUtils.getUserName(token));
                user.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
                user.setUpdateTime(new Date());
                twmpSysUserMapper.updateByPrimaryKey(user);
                //记录操作日志
                logService.saveOperateLog(token, OperateType.update.value, OperateObjectType.user.value,
                        user.getUserName(), null);
            }

        }

    }

    @Override
    public DepartmentDeleteResultVO deleteDepartments(String token, Long departmentId)throws BusinessException {

        //查询当前组织机构及其下级组织机构id
        List<Long> departmentIdlist = listAllDepartmentIds(departmentId);

        //校验是否有组织机构绑定了用户或者设备
        if (!check(departmentIdlist)) {
            throw new BusinessException(" department Bound user or device");
        }


        List<Long> list = new ArrayList();
        //删除当前组织机构及其下属组织机构
        for (long id : departmentIdlist) {
            list.add(id);
        }
        Map map = new HashMap(8);
        map.put("updater", TokenUtils.getUserName(token));
        map.put("updaterId", Long.valueOf(TokenUtils.getUserID(token)));
        map.put("departmentIdList", list);
        twmpSysDepartmentMapper.deleteDepartmentsById(map);
        for (Long id : list) {
            //记录操作日志
            TwmpSysDepartment twmpSysDepartment = twmpSysDepartmentMapper.selectByPrimaryKey(id);

            logService.saveOperateLog(token, OperateType.delete.value, OperateObjectType.department.value,
                    twmpSysDepartment.getDepartmentName(), null);
        }
        //重新缓存用户组织机构信息
        updateDepartmentTree(token);
        return new DepartmentDeleteResultVO();
    }

    /**
     * false : 校验不通过（已经有用户或设备绑定了组织机构了）
     * true  ：校验成功
     *
     * @param departmentIdlist
     * @return
     */
    private boolean check(List<Long> departmentIdlist) {
        int userCount = twmpSysUserMapper.userCountByDepartmentId(departmentIdlist);
        int departmentIdCount = twmpDevDeviceMapper.deviceCountByDepartmentId(departmentIdlist);
        boolean userCheckPass = userCount > 0 ? false : true;
        boolean departmentCheckPass = departmentIdCount > 0 ? false : true;
        return userCheckPass && departmentCheckPass;
    }

    /**
     * 查询当前组织机构及其下级组织机构id
     *
     * @param departmentId
     * @return
     */
    private List<Long> listAllDepartmentIds(Long departmentId) {
        List tempList = new ArrayList();
        List resultList = new ArrayList();
        tempList.add(departmentId);
        resultList.add(departmentId);
        List<Long> list = null;
        do {
            list = twmpSysDepartmentMapper.listDepartmentIdsByParentIds(tempList);
            resultList.addAll(list);
            tempList = list;
        } while (list != null && list.size() > 0);

        return resultList;
    }

    /**
     * 校验组织机构名
     * true : 此组织机构已存在
     * false：此组织机构不存在
     *
     * @param departmentName
     * @return
     */
    private boolean existDepartmentName(String departmentName) {
        TwmpSysDepartment twmpSysDepartment = new TwmpSysDepartment();
        twmpSysDepartment.setDepartmentName(departmentName);
        int count = twmpSysDepartmentMapper.selectCount(twmpSysDepartment);
        return count >= 1 ? true : false;
    }
    /**
     * 校验组织机构编码
     * true : 此组织机构已存在
     * false：此组织机构不存在
     *
     * @param departmentCode
     * @return
     */
    private boolean existDepartmentCode(String departmentCode) {
        TwmpSysDepartment twmpSysDepartment = new TwmpSysDepartment();
        twmpSysDepartment.setDepartmentCode(departmentCode);
        int count = twmpSysDepartmentMapper.selectCount(twmpSysDepartment);
        return count >= 1 ? true : false;
    }

    /**
     * 组织机构修改后，重新缓存组织机构信息
     * @param token
     * @return
     */
    public void updateDepartmentTree(String token) {
        RedisUserInfoVO redisUserInfoVo = UserInfoRedis.getUser(token);
        redisUserInfoVo.setDepartmentTree(departmentService.getDepartmentTree(redisUserInfoVo.getDepartmentId()));
        //重新缓存组织机构信息
        UserInfoRedis.saveUser(redisUserInfoVo);
    }


    /**
     * 递归设置子级组织机构
     *
     * @param sameLevelDepartmentsList 当前用户的所有同级别组织机构列表
     * @return
     */
    public List<DepartmentTreeVO> setChildensList(List<DepartmentTreeVO> sameLevelDepartmentsList ) {
        if (sameLevelDepartmentsList == null || sameLevelDepartmentsList.size() == 0) {
            return null;
        }
        //所有下一级组织机构列表
        List<DepartmentTreeVO> sameLevelDepartmentsListNew = new ArrayList<>();
        //遍历当前组织机构
        for (DepartmentTreeVO departmentTreeVO : sameLevelDepartmentsList) {
            //查找当前组织机构的下一级组织机构
            List<DepartmentTreeVO> twmpSysDepartmentList = twmpSysDepartmentMapper.listDepartmentsByParentId(departmentTreeVO.getDepartmentId());
            if (twmpSysDepartmentList == null || twmpSysDepartmentList.size() == 0) {
                continue;
            }
            //放置所有当前循环列表的所有下一级组织机构，用于下次遍历
            sameLevelDepartmentsListNew.addAll(twmpSysDepartmentList);
            //设置当前组织机构的childrenList属性
            departmentTreeVO.setChildrenList(twmpSysDepartmentList);
        }
        return setChildensList(sameLevelDepartmentsListNew );
    }


}
