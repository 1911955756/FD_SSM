package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Order1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
public interface IOrderService {
    /**
     * 查询所有订单
     * @return
     */
    PageInfo<Order1> findAll(Integer num,String now,String tableid,String orderid,String status);

    /**
     * 根据orderid查询订单信息
     * @param orderid
     */
    Order1 findByOrderid(String orderid);

    /**
     * 根据cs_id查询未结账的orderid
     * @param cs_id
     * @return
     */
    String findOridByCsid(String cs_id);

    /**
     * 保存订单
     * @param order1
     */
    void saveOrder(Order1 order1);

    /**
     * 根据cs_id查询我的的订单
     * @param order1
     * @return
     */
    List<Order1> findMyOrderByCsid(Order1 order1);

    /**
     * 根据orderid更新订单状态
     * @param order1
     */
    void updateorder(Order1 order1);

    /**
     * 更新实际支付金额
     * @param order1
     */
    void updateReality(Order1 order1);

    /**
     * 统计月销图表
     * @return
     */
    List<Map> countpeoplenum();

    /**
     * 统计销售额图表
     * @return
     */
    List<Map> countmoney();

}
