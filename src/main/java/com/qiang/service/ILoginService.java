package com.qiang.service;

import org.apache.ibatis.annotations.Insert;

/**
 * @author Mr.锵
 * date 2020-04-02
 */
public interface ILoginService {
    /**
     * 新增登录记录
     * @param userid
     */
    void savelogin(String userid);
}
