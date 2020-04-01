package com.qiang.dao;

import com.qiang.domain.OrderDetail;
import com.qiang.domain.Picture;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Repository
public interface IOrderDetailDao {
    /**
     * 根据状态查询上菜单
     * @param
     * @return
     */
    List<OrderDetail> findAll(@Param("orddstatus") String orddstatus, @Param("tbid") String tbid, @Param("mname") String mname, @Param("orddid") String orddid, @Param("ordid")String ordid,@Param("odcalls") String odcalls);

    /**
     * 根据odid更新上菜单状态
     * @param orderDetail
     */
    @Update("update order_detail set status=#{status} where odid=#{odid}")
    void updateorderDetail(OrderDetail orderDetail);

    /**
     * 根据odid催单
     * @param odid
     */
    @Update("update order_detail set called='已催单' where odid=#{odid}")
    void updateODcall(String odid);

    /**
     * 保存上菜单
     * @param orderDetail
     */
    @Insert("insert into order_detail(orderid,menuid,menu_num)values(#{orderid},#{menuid},#{menu_num})")
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
}
