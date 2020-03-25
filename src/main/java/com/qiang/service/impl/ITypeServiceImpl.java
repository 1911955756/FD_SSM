package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.ITypeDao;
import com.qiang.domain.Type1;
import com.qiang.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Service("typeService")
public class ITypeServiceImpl implements ITypeService {
    @Autowired
    private ITypeDao typeDao;
    @Override
    public List<Type1> findtypeAll() {
        return typeDao.findtypeAll();
    }




    @Override
    public PageInfo<Type1> findAll(Integer num,String tyname,String tystatus) {
        PageHelper.startPage(num,5);
        List<Type1> all = typeDao.findAll(tyname,tystatus);
        PageInfo<Type1> type1PageInfo = new PageInfo<>(all);
        return type1PageInfo;
    }

    @Override
    public void updatestatus(Type1 type1) {
        typeDao.updatestatus(type1);
    }

    @Override
    public void saveType(String name) {
        typeDao.saveType(name);
    }

    @Override
    public void deletetype(String typeid) {
        typeDao.deletetype(typeid);
    }

    @Override
    public String findIdByname(String name) {
        return typeDao.findIdByname(name);
    }

    @Override
    public List<Type1> findTM(String mname) {
        return typeDao.findTM(null,mname);
    }

    @Override
    public PageInfo<Type1> findPageTM(Integer num,String tyname,String mname) {
        PageHelper.startPage(num,5);
        List<Type1> all = typeDao.findTM(tyname,mname);
        PageInfo<Type1> tmPageInfo = new PageInfo<>(all);
        return tmPageInfo;
    }
}
