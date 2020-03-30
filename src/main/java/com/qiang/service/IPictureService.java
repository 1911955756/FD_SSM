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
    PageInfo<Picture> finAll(Integer num);
    /**
     * 根据pictureid删除轮播图
     * @param pictureid
     */
    void deletePicture(String pictureid);

    /**
     * 根据pictureid更新轮播图
     * @param picture
     */
    void updatePicture(Picture picture);

    /**
     * 根据pictureid查询轮播图状态
     * @param pictureid
     */
    String finStatusByPicid(String pictureid);

    /**
     * 保存轮播图
     * @param url
     */
    void savePicture(String url);

    /**
     * 查询所有轮播图信息
     * @return
     */
    @Select("select url from picture where status='使用'")
    List<Picture> finAllInused();
}
