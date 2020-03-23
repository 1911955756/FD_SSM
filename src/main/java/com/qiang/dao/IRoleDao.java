package com.qiang.dao;

import com.qiang.domain.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-22
 */
@Repository
public interface IRoleDao {
    /**
     * 根据roleid查询角色名称
     * @param roleid
     * @return
     */
    @Select("select * from role where roleid=#{roleid}")
    List<Role> findByRid(String  roleid);

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();
}
