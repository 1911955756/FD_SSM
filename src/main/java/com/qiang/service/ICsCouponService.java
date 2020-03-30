package com.qiang.service;

import com.qiang.domain.CsCoupon;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-07
 */
public interface ICsCouponService {
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
    void saveCsCoupon(CsCoupon csCoupon);

    /**
     * 查询顾客是否有该优惠券
     * @param csCoupon
     * @return
     */
    String checkCoupon(CsCoupon csCoupon);
}
