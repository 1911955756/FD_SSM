package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
public interface IOrderDetailService {
    PageInfo<OrderDetail> findAll(Integer num,String status);

    /**
     * 根据orderid和menuid更新上菜单状态
     * @param orderDetail
     */
    @Update("update order_detail set status=#{status} where odid=#{odid}")
    void updateorderDetail(OrderDetail orderDetail);

    /**
     * 保存上菜单
     * @param orderDetail
     */
    @Insert("insert into order_detail(orderid,menuid,menu_num)values(#{orderid},#{menuid},#{menu_num})")
    void saveorderDetail(OrderDetail orderDetail);

    List<OrderDetail> findMyOD(String cs_id);

    List<OrderDetail> findMyorderdetail(String orderid);
}
