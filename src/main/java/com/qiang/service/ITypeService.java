package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Type1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
public interface ITypeService {
    /**
     * 查询菜单类型
     * @return
     */
    @Select("select * from type1 where status='上架' order by typeid ")
    List<Type1> findtypeAll();

    /**
     * 分页查询菜单类型
     * @return
     */
    @Select("select * from type1 order by typeid ")
    PageInfo<Type1> findAll(Integer num);

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

    List<Type1> findTM();

    PageInfo<Type1> findPageTM(Integer num,String tyname,String mname);
}
