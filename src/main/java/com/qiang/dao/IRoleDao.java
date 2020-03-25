package com.qiang.dao;

import com.qiang.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-22
 */
@Repository
public interface IRoleDao {
    /**
     * 根据rolename查询角色
     * @param rolename
     * @return
     */
    @Select("select * from role where rolename=#{rolename}")
    List<Role> findByRname(String  rolename);

    /**
     * 查询可使用角色
     * @return
     */
    @Select("select * from role where status='使用' order by createtime desc")
    List<Role> findroleAll();

    /**
     * 分页查询所有角色
     * @return
     */
    List<Role> findAll(@Param("rname") String rname,@Param("rstatus") String rstatus);

    /**
     * 删除角色信息
     * @param roleid
     */
    @Delete("delete from role where roleid=#{roleid}")
    void deleterole(String roleid);

    /**
     * 保存角色信息
     * @param rolename
     */
    @Insert("insert into role(rolename)values(#{rolename})")
    void saverole(String rolename);

    /**
     * 更新角色状态
     * @param role
     */
    @Update("update role set status=#{status} where roleid=#{roleid}")
    void updaterolestatus(Role role);

    List<Role> findUR(@Param("rname") String rname,@Param("uname") String uname);
}
