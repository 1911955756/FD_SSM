package com.qiang.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiang.dao.ICouponDao;
import com.qiang.domain.Coupon;
import com.qiang.service.ICouponService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-03-07
 */
@Service
public class ICouponServiceImpl implements ICouponService {
    @Autowired
    private ICouponDao couponDao;

    @Override
    public List<Coupon> findCoupon(String couponname) {
        return couponDao.findCoupon(couponname);
    }

    @Override
    public void updatecoupon(Coupon coupon) {
        couponDao.updatecoupon(coupon);
    }

    @Override
    public List<Map> countCoupon() {
        return couponDao.countCoupon();
    }

    @Override
    public Coupon findByid(String couponid) {
        return couponDao.findByid(couponid);
    }

    @Override
    public PageInfo<Coupon> findAll(Integer num, String couname,String coutype, String coustatus,Float couprice) {
        PageHelper.startPage(num,5);
        List<Coupon> all = couponDao.findAll(couname, coutype,coustatus, couprice);
        PageInfo<Coupon> couponPageInfo = new PageInfo<>(all);
        return couponPageInfo;
    }

    @Override
    public List<Coupon> findcouponAll() {
        return couponDao.findcouponAll();
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
