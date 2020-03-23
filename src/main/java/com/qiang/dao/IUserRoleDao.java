package com.qiang.dao;

import com.qiang.domain.UserRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-22
 */
@Repository
public interface IUserRoleDao {

    /**
     * 保存用户角色
     * @param userRole
     */
    @Insert("insert into userrole(userid,roleid) values(#{userid},#{roleid})")
    void saveUserRole(UserRole userRole);
}
