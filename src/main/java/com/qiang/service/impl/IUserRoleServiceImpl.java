package com.qiang.service.impl;

import com.qiang.dao.IUserRoleDao;
import com.qiang.domain.UserRole;
import com.qiang.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-22
 */
@Service("userRoleService")
public class IUserRoleServiceImpl implements IUserRoleService {
    @Autowired
    private IUserRoleDao userRoleDao;

    @Override
    public void saveUserRole(UserRole userRole) {
        userRoleDao.saveUserRole(userRole);
    }
}
