package com.qiang.dao;

import com.qiang.domain.OrderDetail;
import com.qiang.domain.Picture;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Repository
public interface IOrderDetailDao {
    List<OrderDetail> findAll(String status);

    /**
     * 根据odid更新上菜单状态
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
