package com.qiang.service.impl;

import com.qiang.dao.IRoleDao;
import com.qiang.domain.Role;
import com.qiang.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-22
 */
@Service("roleService")
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findByRid(String roleid) {
        return roleDao.findByRid(roleid);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
