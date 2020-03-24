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

    /**
     * 查询roleid和userid
     * @param roleid
     * @param userid
     * @return
     */
    List<UserRole> findroleid(@Param("roleid") String roleid,@Param("userid") String userid);

    /**
     * 删除用户角色信息
     * @param userrole
     */
    @Delete("delete from userrole where userid=#{userid} and roleid=#{roleid}")
    void deleteuserrole(UserRole userrole);
}
