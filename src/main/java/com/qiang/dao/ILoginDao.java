package com.qiang.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author Mr.锵
 * date 2020-04-02
 */
@Repository
public interface ILoginDao {
    /**
     * 新增登录记录
     * @param userid
     */
    @Insert("insert into login(userid)values(#{userid})")
    void savelogin(String userid);
}
