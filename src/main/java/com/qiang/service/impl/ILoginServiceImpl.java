package com.qiang.service.impl;

import com.qiang.dao.ILoginDao;
import com.qiang.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.锵
 * date 2020-04-02
 */
@Service("/loginService")
public class ILoginServiceImpl implements ILoginService {
    @Autowired
    private ILoginDao loginDao;
    @Override
    public void savelogin(String userid) {
        loginDao.savelogin(userid);
    }
}
