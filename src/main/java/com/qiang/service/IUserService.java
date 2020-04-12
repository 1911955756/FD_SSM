package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.User1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-16
 */
public interface IUserService {
    /**
     * 根据phone查询用户密码
     * @param phone
     * @return
     */
    String findPasswordByphone(String phone);

    /**
     * 根据userid查询用户信息
     * @return
     */
    User1 findAllByuserid(String userid);

    /**
     * 查询删除标志不为1的所有用户
     * @return
     */
    PageInfo<User1> findAll(Integer num,String uname,String ujob,String uphone);

    /**
     * 把用户的删除标志改为1
     * @param userid
     */
    void updateUserDeleteflag(String userid);

    /**
     * 保存用户信息
     * @param user1
     */
    void saveUser(User1 user1);

    /**
     * 根据userid更新用户信息
     * @param user1
     */
    void updateUser(User1 user1);

    /**
     * 根据phone查询用户信息
     * @param phone
     * @return
     */
    List<User1> findAllByPhone(String phone);

}
