package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.ITableDao;
import com.qiang.domain.Table1;
import com.qiang.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Service("tableService")
public class ITableServiceImpl implements ITableService {
    @Autowired
    private ITableDao tableDao;
    @Override
    public PageInfo<Table1> findAll(Integer num,String taid,Integer penum,String tasta) {
        PageHelper.startPage(num,10);
        List<Table1> all = tableDao.findAll(taid,penum,tasta);
        PageInfo<Table1> table1PageInfo = new PageInfo<>(all);
        return table1PageInfo;
    }

    @Override
    public void updatetablestatus(Table1 table1) {
        tableDao.updatetablestatus(table1);
    }

    @Override
    public List<Table1> findtable() {
        return tableDao.findAll(null,null,null);
    }

    @Override
    public void saveTable(Table1 table1) {
        tableDao.saveTable(table1);
    }

    @Override
    public List<Table1> findTpeoplenum() {
        return tableDao.findTpeoplenum();
    }
}
