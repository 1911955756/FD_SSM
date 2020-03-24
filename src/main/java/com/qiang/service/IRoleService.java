package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Role;
import com.qiang.domain.Type1;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-22
 */
public interface IRoleService {
    /**
     * 根据roleid查询角色名称
     * @param roleid
     * @return
     */
    List<Role> findByRid(String  roleid);
    /**
     * 查询所有角色
     * @return
     */
    List<Role> findroleAll();

    /**
     * 分页查询所有角色
     * @return
     */
    PageInfo<Role> findAll(Integer num);

    /**
     * 删除角色信息
     * @param roleid
     */
    void deleterole(String roleid);

    /**
     * 更新角色状态
     * @param role
     */
    void updaterolestatus(Role role);

    /**
     * 保存角色信息
     * @param rolename
     */
    void saverole(String rolename);

    PageInfo<Role> findPageUR(Integer num, String rname, String uname);
}
