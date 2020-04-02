package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.IOrderDetailDao;
import com.qiang.domain.Order1;
import com.qiang.domain.OrderDetail;
import com.qiang.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Service("orderDetailService")
public class IOrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    private IOrderDetailDao orderDetailDao;

    @Override
    public List<Map> countcallnum(String odstatus) {
        return orderDetailDao.countcallnum(odstatus);
    }

    @Override
    public PageInfo<OrderDetail> findAll(Integer num,String status,String tbid,String orddid,String mname,String ordid,String odcalls) {
        PageHelper.startPage(num,3);
        List<OrderDetail> all = orderDetailDao.findAll(status,tbid,mname,orddid,ordid,odcalls);
        PageInfo<OrderDetail> orderDetailPageInfo = new PageInfo<>(all);
        return orderDetailPageInfo;
    }

    @Override
    public void updateorderDetail(OrderDetail orderDetail) {
        orderDetailDao.updateorderDetail(orderDetail);
    }

    @Override
    public void updateODcall(String odid) {
        orderDetailDao.updateODcall(odid);
    }

    @Override
    public void saveorderDetail(OrderDetail orderDetail) {
        orderDetailDao.saveorderDetail(orderDetail);
    }

    @Override
    public List<OrderDetail> findMyOD(String cs_id) {
        return orderDetailDao.findMyOD(cs_id);
    }

    @Override
    public List<OrderDetail> findMyorderdetail(String orderid) {
        return orderDetailDao.findMyorderdetail(orderid);
    }
}
