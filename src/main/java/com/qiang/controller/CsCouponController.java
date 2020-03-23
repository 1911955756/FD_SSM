package com.qiang.controller;

import com.qiang.domain.CsCoupon;
import com.qiang.service.ICsCouponService;
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
@RequestMapping("/cscoupon")
public class CsCouponController {
    @Autowired
    private ICsCouponService csCouponService;
    @RequestMapping("/findmine")
    public @ResponseBody List findmine(String cs_id){
        List<CsCoupon> mine = csCouponService.findMine(cs_id);
        return mine;
    }
    @RequestMapping("/saveCsCoupon")
    public @ResponseBody boolean saveCsCoupon(CsCoupon csCoupon){
        String s = csCouponService.checkCoupon(csCoupon);
        if(s!=null){
            return false;
        }else{
        csCouponService.saveCsCoupon(csCoupon);
        return true;
        }
    }
}
