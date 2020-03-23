package com.qiang.service;

import com.qiang.domain.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-22
 */
public interface IUserRoleService {
    /**
     * 保存用户角色
     * @param userRole
     */
    @Insert("insert into userrole(userid,roleid) values(#{userid},#{roleid})")
    void saveUserRole(UserRole userRole);
}
