package com.qiang.service.impl;

import com.qiang.dao.ICsCouponDao;
import com.qiang.domain.CsCoupon;
import com.qiang.service.ICsCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-07
 */
@Service
public class ICsCouponServiceImpl implements ICsCouponService {
    @Autowired
    private ICsCouponDao csCouponDao;
    @Override
    public List<CsCoupon> findMine(String cs_id) {
        return csCouponDao.findMine(cs_id);
    }

    @Override
    public void saveCsCoupon(CsCoupon csCoupon) {
        csCouponDao.saveCsCoupon(csCoupon);
    }

    @Override
    public String checkCoupon(CsCoupon csCoupon) {
        return csCouponDao.checkCoupon(csCoupon);
    }
}
