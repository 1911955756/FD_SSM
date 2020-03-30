package com.qiang.dao;

import com.qiang.domain.Type1;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Repository
public interface ITypeDao {
    /**
     * 查询菜单类型
     * @return
     */
    @Select("select * from type1 where status='上架' order by typeid")
    List<Type1> findtypeAll();

    /**
     * 分页查询菜单类型
     * @return
     */
    List<Type1> findAll(@Param("tyname") String tyname,@Param("tystatus") String tystatus);


    /**
     * 更新类目状态
     * @param type1
     */
    @Update("update type1 set status=#{status} where typeid=#{typeid}")
    void updatestatus(Type1 type1);

    /**
     * 保存类目信息
     * @param name
     */
    @Insert("insert into type1(name)values(#{name})")
    void saveType(String name);

    /**
     * 删除类目信息
     * @param typeid
     */
    @Delete("delete from type1 where typeid=#{typeid}")
    void deletetype(String typeid);

    /**
     * 查询菜单类型
     * @return
     */
    @Select("select typeid from type1 where name=#{name}")
    String findIdByname(String name);

    /**
     * 分页模糊查询菜单类型
     * @param tyname
     * @param mname
     * @return
     */
    List<Type1> findTM(@Param("tyname") String tyname,@Param("mname") String mname);
}
