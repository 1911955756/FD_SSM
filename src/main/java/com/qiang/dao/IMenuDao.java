package com.qiang.dao;

import com.qiang.domain.Menu;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Repository
public interface IMenuDao {
    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> findAll(@Param("mname") String mname,@Param("msta") String msta);
    /**
     * 根据menuid删除菜品
     * @param menuid
     */
    @Delete("delete from menu where menuid=#{menuid}")
    void deleteMenu(String menuid);

    /**
     * 根据menuid查询菜单信息
     * @param menuid
     */
    @Select("select * from menu where menuid=#{menuid}")
    Menu findByMenuid(String menuid);

    /**
     * 根据menuname查询菜单id
     * @param menuname
     */
    @Select("select menuid from menu where menuname=#{menuname}")
    String findIdByMenuname(String menuname);

    /**
     * 更新菜单
     * @param menu
     */
    @Update("update menu set menuname=#{menuname},image=#{image},status=#{status},kucun=#{kucun},price=#{price},description=#{description} where menuid=#{menuid}")
    void updateMenu(Menu menu);

    /**
     * 保存菜单
     * @param menu
     */
    @Insert("insert into menu(menuname,image,kucun,description,price)values(#{menuname},#{image},#{kucun},#{description},#{price})")
    void saveMenu(Menu menu);
    /**
     * 根据菜名模糊查询菜品信息
     * @param menuname
     * @return
     */
    List<Menu> findMenuByName(String menuname);
    /**
     * 统计月销情况
     * @return
     */
    List<Map> countsellnum();
    /**
     * 统计新菜受欢迎程度
     * @return
     */
    List<Map> countnewfood();
}
