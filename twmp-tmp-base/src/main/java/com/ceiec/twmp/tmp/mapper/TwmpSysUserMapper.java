package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.SystemUserEntity;
import com.ceiec.twmp.tmp.entity.TwmpSysUser;
import com.ceiec.twmp.tmp.vo.user.query.UserListQueryVO;
import com.ceiec.twmp.tmp.vo.user.result.UserListResultVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * The interface Twmp sys user mapper.
 */
@Component(value = "twmpSysUserMapper")
public interface TwmpSysUserMapper extends Mapper<TwmpSysUser> {

    /**
     * get system user's information
     *
     * @param userID system user's userID
     * @return the redis user login info
     */
    SystemUserEntity getRedisUserLoginInfo(Long userID);

    /**
     * Gets user list.
     *
     * @param userListQueryVO the user list query vo
     * @return the user list
     */
    List<UserListResultVO> getUserList(UserListQueryVO userListQueryVO);

    /**
     * 根据userName查询用户数量
     * @param userName
     * @return
     */
    Integer getUserCountByUserName(String userName);

    /**
     * 根据组织机构Id查询用户数量
     * @param departmentIdlist
     * @return
     */
    int userCountByDepartmentId(List<Long> departmentIdlist);
}