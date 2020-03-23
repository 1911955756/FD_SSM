package com.qiang.service.impl;

import com.qiang.dao.ICouponDao;
import com.qiang.domain.Coupon;
import com.qiang.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-07
 */
@Service
public class ICouponServiceImpl implements ICouponService {
    @Autowired
    private ICouponDao couponDao;
    @Override
    public List<Coupon> findAll() {
        return couponDao.findAll();
    }

    @Override
    public void savecoupon(Coupon coupon) {
        couponDao.savecoupon(coupon);
    }

    @Override
    public void updatecouponstatus(Coupon coupon) {
        couponDao.updatecouponstatus(coupon);
    }
}
