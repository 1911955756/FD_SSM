package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
public interface IOrderDetailService {
    /**
     * 根据状态分页查询上菜单
     * @param status
     * @return
     */
    PageInfo<OrderDetail> findAll(Integer num,String status,String tbid,String orddid,String mname,String ordid,String odcalls);

    /**
     * 根据orderid和menuid更新上菜单状态
     * @param orderDetail
     */
    void updateorderDetail(OrderDetail orderDetail);

    /**
     * 根据odid催单
     * @param odid
     */
    void updateODcall(String odid);

    /**
     * 保存上菜单
     * @param orderDetail
     */
    void saveorderDetail(OrderDetail orderDetail);

    /**
     * 根据cs_id查询我的上菜单
     * @param cs_id
     * @return
     */
    List<OrderDetail> findMyOD(String cs_id);

    /**
     * 根据orderid查询我的订单
     * @param orderid
     * @return
     */
    List<OrderDetail> findMyorderdetail(String orderid);
    /**
     * 根据不同status统计当天催单数量
     * @param odstatus
     * @return
     */
    List<Map> countcallnum(@Param("odstatus") String odstatus);
}
