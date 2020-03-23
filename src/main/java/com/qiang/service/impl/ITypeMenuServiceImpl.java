package com.qiang.service.impl;

import com.qiang.dao.ITypeMenuDao;
import com.qiang.domain.TypeMenu;
import com.qiang.service.ITypeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-22
 */
@Service("typeMenuService")
public class ITypeMenuServiceImpl implements ITypeMenuService {
    @Autowired
    private ITypeMenuDao typeMenuDao;
    @Override
    public void saveTypeMenu(TypeMenu tymenu) {
        typeMenuDao.saveTypeMenu(tymenu);
    }

    @Override
    public void deleteTypeMenu(TypeMenu tymenu) {
        typeMenuDao.deleteTypeMenu(tymenu);
    }


}
