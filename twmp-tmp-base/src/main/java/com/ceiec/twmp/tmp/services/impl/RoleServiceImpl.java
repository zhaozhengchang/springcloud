package com.ceiec.twmp.tmp.services.impl;

import com.ceiec.twmp.tmp.common.dict.MessageSubType;
import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.entity.TwmpSysRole;
import com.ceiec.twmp.tmp.entity.TwmpSysRoleAuthority;
import com.ceiec.twmp.tmp.mapper.TwmpSysRoleAuthorityMapper;
import com.ceiec.twmp.tmp.mapper.TwmpSysRoleMapper;
import com.ceiec.twmp.tmp.cache.redis.SysDictRedis;
import com.ceiec.twmp.tmp.services.IRoleService;
import com.ceiec.twmp.tmp.utils.TmpBaseConstants;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.TokenUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.dict.result.DictResultVo;
import com.ceiec.twmp.tmp.vo.role.addoredit.RoleAddOrEditVO;
import com.ceiec.twmp.tmp.vo.role.query.RoleQueryVO;
import com.ceiec.twmp.tmp.vo.role.result.TwmpSysRoleAddResultVO;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: RoleServiceImpl
 * @Package: com.ceiec.twmp.tmp.services.impl
 * @Description: role service 实现类
 * @Author: tangquanbin
 * @Data: 2019/1/22 11:18
 * @Version: V1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {
    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * role Mapper
     */
    @Autowired
    private TwmpSysRoleMapper twmpSysRoleMapper;
    /**
     * 角色权限mapper
     */
    @Autowired
    private TwmpSysRoleAuthorityMapper twmpSysRoleAuthorityMapper;

    /**
     * 添加角色
     * @param token
     * @param roleAddOrEditVO
     * @return
     */
    public TwmpSysRoleAddResultVO addRole(String token,RoleAddOrEditVO roleAddOrEditVO) {
        //插入角色表
        TwmpSysRole twmpSysRole = new TwmpSysRole();
        Long roleId = SnowflakeIdWorkerUtil.generateLongId();

        twmpSysRole.setRoleId(roleId);
        twmpSysRole.setRoleName(roleAddOrEditVO.getRoleName());
        twmpSysRole.setCreateTime(new Date());
        twmpSysRole.setUpdateTime(new Date());
        twmpSysRole.setUpdater(TokenUtils.getUserName(token));
        twmpSysRole.setCreator(TokenUtils.getUserName(token));
        twmpSysRole.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpSysRole.setCreatorId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpSysRole.setDeleted((byte) TmpBaseConstants.FLAG_DELETE_FALSE);
        twmpSysRoleMapper.insertSelective(twmpSysRole);

        //插入角色权限表
        roleAddOrEditVO.setRoleId(roleId);
        insertRoleAuthorityBatch(roleAddOrEditVO);

        TwmpSysRoleAddResultVO roleAddResultVO = new TwmpSysRoleAddResultVO();
        roleAddResultVO.setRoleId(twmpSysRole.getRoleId());
        return roleAddResultVO;
    }

    /**
     * 批量插入角色权限表
     * @param roleAddOrEditVO
     */
    private void insertRoleAuthorityBatch(RoleAddOrEditVO roleAddOrEditVO) {
        String[] list = roleAddOrEditVO.getRolePermission().trim().split(",");
        List<TwmpSysRoleAuthority> authorityList = new ArrayList<>(40);
        for(String o : list){
            TwmpSysRoleAuthority twmpSysRoleAuthority = new TwmpSysRoleAuthority();
            twmpSysRoleAuthority.setId(SnowflakeIdWorkerUtil.generateLongId());
            twmpSysRoleAuthority.setRoleId(roleAddOrEditVO.getRoleId());
            twmpSysRoleAuthority.setAuthorityId(Long.valueOf(o.trim()));
            authorityList.add(twmpSysRoleAuthority);
        }
        twmpSysRoleAuthorityMapper.insertRoleAuthorityBatch(authorityList);
    }

    /**
     * 角色分页列表
     * @param token
     * @param roleQueryVO
     * @return
     */
    @Override
    public PagedItemsVO<TwmpSysRole> queryRoleByPage(String token, RoleQueryVO roleQueryVO) {
        PageHelper.offsetPage(roleQueryVO.getPageNo(),roleQueryVO.getPageSize());
        List<TwmpSysRole> list = twmpSysRoleMapper.queryRoleByPage(roleQueryVO.getRoleName());
        //查询未删除角色总条数
        TwmpSysRole twmpSysRole = new TwmpSysRole();
        twmpSysRole.setDeleted((byte)1);
        int total = twmpSysRoleMapper.selectCount(twmpSysRole);
        return new PagedItemsVO<>(total , list);
    }

    /**
     * 删除角色
     * @param token
     * @param roleId
     * @return
     */
    @Override
    public Integer deleteRole(String token, Long roleId) {
        TwmpSysRole twmpSysRole = new TwmpSysRole();
        twmpSysRole.setDeleted((byte) TmpBaseConstants.FLAG_DELETE_TRUE);
        twmpSysRole.setRoleId(roleId);
        int result = twmpSysRoleMapper.updateByPrimaryKeySelective(twmpSysRole);
        //删除角色权限表相应数据
        twmpSysRoleAuthorityMapper.deleteAllAuthByRoleId(roleId);
        return result;
    }

    @Override
    public TwmpSysRoleAddResultVO addOrEditRole(String token, RoleAddOrEditVO roleAddOrEditVO)throws BusinessException{
        if(null == roleAddOrEditVO.getRoleId()){
            //校验角色是否已存在
            if(existRoleName(roleAddOrEditVO.getRoleName())){
                throw new BusinessException("role name existed !");
            }
            //新增角色
            addRole(token,roleAddOrEditVO);
        }else{
            editRole(token , roleAddOrEditVO);
        }

        return new TwmpSysRoleAddResultVO();
    }

    @Override
    public List<MessageSubType> getMessageSubTypeByRoleId(Long roleId) {
        List<MessageSubType> messageSubTypeList = new ArrayList<>();

        TwmpSysRole twmpSysRole = twmpSysRoleMapper.selectByPrimaryKey(roleId);
        if(twmpSysRole!=null && !StringUtils.isNullOrEmpty(twmpSysRole.getAcceptMessageSubType())){
            List<DictResultVo> dictLists = SysDictRedis.getAllSysDict().get(MessageSubType.failureToReportInTime.type);
            if(dictLists!=null && dictLists.size()>0){
                for(DictResultVo dictResultVo : dictLists){
                    for(String value: twmpSysRole.getAcceptMessageSubType().split(",")){
                        if(dictResultVo.getDictValue() == Byte.parseByte(value)){
                            messageSubTypeList.add(MessageSubType.get(dictResultVo.getDictValue()));
                            break;
                        }
                    }
                }
            }
        }
        return messageSubTypeList ;
    }


    private void editRole(String token , RoleAddOrEditVO roleAddOrEditVO) {
        //更新角色表
        TwmpSysRole twmpSysRole = new TwmpSysRole();
        Long roleId = roleAddOrEditVO.getRoleId() ;

        twmpSysRole.setRoleId(roleId);
        twmpSysRole.setRoleName(roleAddOrEditVO.getRoleName());
        twmpSysRole.setUpdater(TokenUtils.getUserName(token));
        twmpSysRole.setUpdateTime(new Date());
        twmpSysRole.setUpdaterId(Long.valueOf(TokenUtils.getUserID(token)));
        twmpSysRoleMapper.updateByPrimaryKeySelective(twmpSysRole);

        //更新角色权限表(先删除此角色下所有权限，在重新插入新的权限)
        twmpSysRoleAuthorityMapper.deleteAllAuthByRoleId( roleId);
        insertRoleAuthorityBatch(roleAddOrEditVO);
    }

    /**
     * 校验角色
     * true : 此角色已存在
     * false：此角色不存在
     * @param roleName
     * @return
     */
    private boolean existRoleName(String roleName) {
        int count = twmpSysRoleMapper.getRoleCountByRoleName(roleName);
        return count >= 1 ? true:false;
    }
}
