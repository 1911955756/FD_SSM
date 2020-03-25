package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.IRoleDao;
import com.qiang.domain.Role;
import com.qiang.domain.Table1;
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
    public List<Role> findroleAll() {
        return roleDao.findroleAll();
    }

    @Override
    public List<Role> findByRname(String rolename) {
        return roleDao.findByRname(rolename);
    }

    @Override
    public void deleterole(String roleid) {
        roleDao.deleterole(roleid);
    }

    @Override
    public void saverole(String rolename) {
        roleDao.saverole(rolename);
    }

    @Override
    public PageInfo<Role> findPageUR(Integer num, String rname, String uname) {
        PageHelper.startPage(num,5);
        List<Role> ur = roleDao.findUR(rname, uname);
        PageInfo<Role> rolePageInfo = new PageInfo<>(ur);
        return rolePageInfo;
    }

    @Override
    public void updaterolestatus(Role role) {
    roleDao.updaterolestatus(role);
    }

    @Override
    public PageInfo<Role> findAll(Integer num,String rname,String rstatus) {
        PageHelper.startPage(num,5);
        List<Role> all = roleDao.findAll(rname,rstatus);
        PageInfo<Role> rolePageInfo = new PageInfo<>(all);
        return rolePageInfo;
    }
}
