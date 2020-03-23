package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Picture;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-20
 */
public interface IPictureService {
    /**
     * 查询所有轮播图信息
     * @return
     */
    @Select("select * from picture")
    PageInfo<Picture> finAll(Integer num);
    /**
     * 根据pictureid删除轮播图
     * @param pictureid
     */
    @Delete("delete from picture where pictureid=#{pictureid}")
    void deletePicture(String pictureid);

    /**
     * 根据pictureid更新轮播图
     * @param picture
     */
    @Update("update user set status=#{status} where pictureid=#{pictureid}")
    void updatePicture(Picture picture);

    /**
     * 根据pictureid查询轮播图状态
     * @param pictureid
     */
    @Select("select status from picture where pictureid=#{pictureid}")
    String finStatusByPicid(String pictureid);

    /**
     * 保存轮播图
     * @param url
     */
    @Insert("insert into picture(url)values(#{url})")
    void savePicture(String url);

    /**
     * 查询所有轮播图信息
     * @return
     */
    @Select("select url from picture where status='使用'")
    List<Picture> finAllInused();
}
