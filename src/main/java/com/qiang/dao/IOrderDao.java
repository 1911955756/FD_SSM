package com.qiang.dao;

import com.qiang.domain.Order1;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Repository
public interface IOrderDao {

    /**
     * 分页模糊查询所有订单信息
     * @param now
     * @param tableid
     * @param orderid
     * @param status
     * @return
     */
    List<Order1> findAll(@Param("now") String now,@Param("tableid") String tableid,@Param("orderid") String orderid,@Param("status") String status);

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
    void updateorder(Order1  order1);

    /**
     * 更新实际支付金额
     * @param order1
     */
    @Insert("update order1 set couponid=#{couponid},total=#{total} where orderid=#{orderid}")
    void updateReality(Order1 order1);

    /**
     * 统计每日人数
     * @return
     */
    List<Map> countpeoplenum();

    /**
     * 统计销售额
     * @return
     */
    List<Map> countmoney();
}
