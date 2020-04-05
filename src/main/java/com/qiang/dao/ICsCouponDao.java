package com.qiang.dao;

import com.qiang.domain.CsCoupon;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-07
 */
@Repository
public interface ICsCouponDao {

    /**
     * 根据cs_id查询我的优惠券
     * @param cs_id
     * @return
     */
    List<CsCoupon> findMine(String cs_id);

    /**
     * 保存顾客优惠券
     * @param csCoupon
     */
    @Insert("insert into cs_coupon(cs_id,couponid) values(#{cs_id},#{couponid})")
    void saveCsCoupon(CsCoupon csCoupon);

    /**
     * 查询顾客是否有该优惠券
     * @param csCoupon
     * @return
     */
    @Select("select couponid from cs_coupon where cs_id=#{cs_id} and status='待使用' and couponid=#{couponid}")
    String  checkCoupon(CsCoupon csCoupon);

}
