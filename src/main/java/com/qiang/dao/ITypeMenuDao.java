package com.qiang.dao;


import com.qiang.domain.TypeMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-22
 */
@Repository
public interface ITypeMenuDao {
    /**
     * 保存菜单类型
     * @param tymenu
     */
    @Insert("insert into type_menu(typeid,menuid)values(#{typeid},#{menuid})")
    void saveTypeMenu(TypeMenu tymenu);

    /**
     * 删除菜单类型
     * @param tymenu
     */
    @Delete("delete from type_menu where typeid=#{typeid} and menuid=#{menuid}")
    void deleteTypeMenu(TypeMenu tymenu);

    /**
     * 查询typeid
     * @param typeid
     * @return
     */
    @Select("select * from type_menu where typeid=#{typeid}")
    List<TypeMenu> findtypeid(String typeid);
}
