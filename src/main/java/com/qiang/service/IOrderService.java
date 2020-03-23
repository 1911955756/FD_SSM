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
    @Select("select * from order1 where orderid=#{orderid}")
    Order1 findByOrderid(String orderid);

    /**
     * 根据cs_id查询未结账的orderid
     * @param cs_id
     * @return
     */
    @Select("select orderid from order1 where cs_id=#{cs_id} and status='待上菜' or status='待点餐' order by createtime desc")
    String findOridByCsid(String cs_id);

    /**
     * 保存订单
     * @param order1
     */
    @Insert("insert into order1(cs_id,tableid)values(#{cs_id},#{tableid})")
    void saveOrder(Order1 order1);

    /**
     * 根据cs_id查询我的的订单
     * @param order1
     * @return
     */
    @Select("select * from order1 where cs_id=#{cs_id} and status=#{status} order by createtime desc")
    List<Order1> findMyOrderByCsid(Order1 order1);

    /**
     * 根据orderid更新订单状态
     * @param order1
     */
    @Update("update order1 set status=#{status} where orderid=#{orderid}")
    void updateorder(Order1 order1);

    /**
     * 更新实际支付金额
     * @param order1
     */
    @Insert("update order1 set couponid=#{couponid},total=#{total} where orderid=#{orderid}")
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
