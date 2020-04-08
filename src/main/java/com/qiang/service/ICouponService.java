package com.qiang.service;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Coupon;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-03-07
 */
public interface ICouponService {
    /**
     * 查询所有的优惠券
     * @return
     */
    PageInfo<Coupon> findAll(Integer num, String couname,String coutype, String coustatus,Float couprice);

    /**
     * 查询所有上架的优惠券
     * @return
     */
    List<Coupon> findcouponAll();

    /**
     * 根据couponid查询优惠券信息
     * @param couponid
     * @return
     */
    Coupon findByid(String couponid);

    /**
     * 根据couponname查询优惠券信息
     * @param couponname
     * @return
     */
    List<Coupon> findCoupon(String couponname);

    /**
     * 保存优惠券
     * @param coupon
     */
    void savecoupon(Coupon  coupon);

    /**
     * 根据couponid实现优惠券删除
     * @param coupon
     */
    void updatecouponstatus(Coupon coupon);

    /**
     * 根据couponid实现优惠券上下架
     * @param coupon
     */
    void updatecoupon(Coupon coupon);
    /**
     * 统计优惠券使用情况
     * @return
     */
    List<Map> countCoupon();
}
