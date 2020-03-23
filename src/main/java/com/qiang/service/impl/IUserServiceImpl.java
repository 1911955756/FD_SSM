package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.IUserDao;
import com.qiang.domain.User1;
import com.qiang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-16
 * user1表业务层
 */
@Service("userService")
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public String findPasswordByphone(Integer phone) {
        return userDao.findPasswordByphone(phone);
    }

    @Override
    public User1 findAllByuserid(String userid) {
        return userDao.findAllByuserid(userid);
    }

    @Override
    public PageInfo<User1> findAll(Integer num) {
        PageHelper.startPage(num,5);
        List<User1> all = userDao.findAll();
        PageInfo<User1> user1PageInfo = new PageInfo<>(all);
        return user1PageInfo;
    }

    @Override
    public void updateUserDeleteflag(String userid) {
        userDao.updateUserDeleteflag(userid);
    }

    @Override
    public void saveUser(User1 user1) {
        userDao.saveUser(user1);
    }

    @Override
    public void updateUser(User1 user1) {
        userDao.updateUser(user1);
    }

    @Override
    public List<User1> findAllByPhone(Integer phone) {
        return userDao.findAllByPhone(phone);
    }
}
