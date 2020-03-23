package com.qiang.dao;

import com.qiang.domain.Coupon;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-07
 */
@Repository
public interface ICouponDao {
    /**
     * 查询所有上架的优惠券
     * @return
     */
    @Select("select * from coupon where status='上架' order by createtime desc")
    List<Coupon> findAll();

    /**
     * 保存优惠券
     * @param coupon
     */
    @Insert("insert into coupon set couponname=#{couponname},endtime=#{endtime},type=#{type},price=#{price}")
    void savecoupon(Coupon  coupon);

    /**
     * 根据couponid实现优惠券上下架
     * @param coupon
     */
    @Update("update coupon set status=#{status} where couponid=#{couponid}")
    void updatecouponstatus(Coupon coupon);
}
