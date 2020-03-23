package com.qiang.dao;

import com.qiang.domain.Type1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    @Select("select * from type1 order by typeid")
    List<Type1> findAll();

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
     * 查询菜单类型
     * @return
     */
    @Select("select typeid from type1 where name=#{name}")
    String findIdByname(String name);

    List<Type1> findTM(@Param("tyname") String tyname,@Param("mname") String mname);
}
