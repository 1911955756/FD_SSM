package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.IOrderDao;
import com.qiang.domain.Order1;
import com.qiang.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Service("orderService")
public class IOrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;
    @Override
    public PageInfo<Order1> findAll(Integer num,String now,String tableid,String orderid,String status) {
        PageHelper.startPage(num,10);
        List<Order1> all = orderDao.findAll(now,tableid,orderid,status);
        PageInfo<Order1> order1PageInfo = new PageInfo<>(all);
        return order1PageInfo;
    }

    @Override
    public Order1 findByOrderid(String orderid) {
        return orderDao.findByOrderid(orderid);
    }

    @Override
    public String findOridByCsid(String cs_id) {
        return orderDao.findOridByCsid(cs_id);
    }

    @Override
    public void saveOrder(Order1 order1) {
        orderDao.saveOrder(order1);
    }

    @Override
    public List<Order1> findMyOrderByCsid(Order1 order1) {
        return orderDao.findMyOrderByCsid(order1);
    }

    @Override
    public void updateorder(Order1 order1) {
        orderDao.updateorder(order1);
    }

    @Override
    public void updateReality(Order1 order1) {
        orderDao.updateReality(order1);
    }

    @Override
    public List<Map> countpeoplenum() {
        return orderDao.countpeoplenum();
    }

    @Override
    public List<Map> countmoney() {
        return orderDao.countmoney();
    }
}
