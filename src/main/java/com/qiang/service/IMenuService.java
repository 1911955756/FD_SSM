package com.qiang.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qiang.domain.Book;
import com.qiang.domain.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
public interface IMenuService {
    /**
     * 查询所有菜单
     * @return
     */
    PageInfo<Menu> findAll(Integer num,String mname,String msta);
    /**
     * 根据menuid删除轮播图
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
     * 统计月销量图表
     * @return
     */
    List<Map> countsellnum();

    /**
     * 统计新菜欢迎度图表
     * @return
     */
    List<Map> countnewfood();

    /**
     * 批量保存菜谱信息
     * @param file
     * @return
     */
    String readExcelFile(MultipartFile file);
}
