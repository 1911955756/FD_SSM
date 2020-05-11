package com.qiang.service;

import com.qiang.domain.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
    void saveUserRole(UserRole userRole);

    /**
     * 查询roleid和userid
     * @param roleid
     * @param userid
     * @return
     */
    List<UserRole> findroleid(String roleid,String userid);

    /**
     * 删除用户角色信息
     * @param userrole
     */
    void deleteuserrole(UserRole userrole);
}
