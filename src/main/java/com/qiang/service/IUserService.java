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
    @Select("select password from user1 where phone=#{phone} and deleteflag=0")
    String findPasswordByphone(Integer phone);

    /**
     * 根据userid查询用户信息
     * @return
     */
    @Select("select * from user1 where  userid=#{userid}")
    User1 findAllByuserid(String userid);

    /**
     * 查询删除标志不为1的所有用户
     * @return
     */
    @Select("select* from user1 where  deleteflag=0")
    PageInfo<User1> findAll(Integer num);

    /**
     * 把用户的删除标志改为1
     * @param userid
     */
    @Update("update user1 set deleteflag=1 where userid=#{userid}")
    void updateUserDeleteflag(String userid);

    /**
     * 保存用户信息
     * @param user1
     */
    @Insert("insert into user1(username,phone,password,job)values(#{username},#{phone},#{password},#{job})")
    void saveUser(User1 user1);

    /**
     * 根据userid更新用户信息
     * @param user1
     */
    @Update("update user1 set username=#{username},phone=#{phone},job=#{job},password=#{password} where userid=#{userid}")
    void updateUser(User1 user1);

    List<User1> findAllByPhone(Integer phone);

}
