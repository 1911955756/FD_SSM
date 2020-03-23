package com.qiang.controller;

import com.qiang.domain.Coupon;
import com.qiang.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-07
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private ICouponService couponService;
    @RequestMapping("/findAll")
    public @ResponseBody List findAll(){
        List<Coupon> all = couponService.findAll();
        return all;
    }
}
