package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Type1;
import org.apache.ibatis.annotations.Delete;
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
    List<Type1> findtypeAll();


    /**
     * 分页查询菜单类型
     * @return
     */
    PageInfo<Type1> findAll(Integer num);

    /**
     * 更新类目状态
     * @param type1
     */
    void updatestatus(Type1 type1);

    /**
     * 保存类目信息
     * @param name
     */
    void saveType(String name);

    /**
     * 删除类目信息
     * @param typeid
     */
    void deletetype(String typeid);

    List<Type1> findTM(String mname);

    PageInfo<Type1> findPageTM(Integer num,String tyname,String mname);
}
