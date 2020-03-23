package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.IPictureDao;
import com.qiang.domain.Menu;
import com.qiang.domain.Picture;
import com.qiang.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-20
 */
@Service("putureService")
public class IPictureServiceImpl implements IPictureService {
    @Autowired
    private IPictureDao pictureDao;
    @Override
    public PageInfo<Picture> finAll(Integer num) {
        PageHelper.startPage(num,5);
        List<Picture> pictures = pictureDao.finAll();
        PageInfo<Picture> pageInfo = new PageInfo<Picture>(pictures);
        return pageInfo;
    }

    @Override
    public void deletePicture(String pictureid) {
        pictureDao.deletePicture(pictureid);
    }

    @Override
    public void updatePicture(Picture picture) {
    pictureDao.updatePicture(picture);
    }

    @Override
    public String finStatusByPicid(String pictureid) {
       return pictureDao.finStatusByPicid(pictureid);
    }

    @Override
    public void savePicture(String url) {
        pictureDao.savePicture(url);
    }

    @Override
    public List<Picture> finAllInused() {
        return pictureDao.finAllInused();
    }
}
