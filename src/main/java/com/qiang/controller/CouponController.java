package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Coupon;
import com.qiang.domain.Menu;
import com.qiang.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
    public @ResponseBody
    ModelAndView findAll(@RequestParam(required = false,defaultValue = "1")Integer num,
                         @RequestParam(required = false)String couname,
                         @RequestParam(required = false)String coutype,
                         @RequestParam(required = false)String coustatus,
                         @RequestParam(required = false)Float couprice){
        ModelMap modelMap=new ModelMap();
        PageInfo<Coupon> all = couponService.findAll(num, couname, coutype,coustatus, couprice);
        modelMap.addAttribute("coulist",all);
        modelMap.addAttribute("couname",couname);
        modelMap.addAttribute("coutype",coutype);
        modelMap.addAttribute("coutype",coustatus);
        modelMap.addAttribute("couprice",couprice);
        ModelAndView modelAndView = new ModelAndView("couponlist",modelMap);
        return modelAndView;
    }
    @RequestMapping("/findcouponAll")
    public @ResponseBody List findcouponAll(){
        List<Coupon> all = couponService.findcouponAll();
        return all;
    }
    @RequestMapping("/toupdatecoupon")
    public ModelAndView toupdatecoupon(String couponid){
        ModelAndView mv=new ModelAndView();
        //调用service的方法
        Coupon byid = couponService.findByid(couponid);
        mv.addObject("bycouponid",byid);
        mv.setViewName("coupon");
        return mv;
    }

    @RequestMapping("/saveCoupon")
    public  void saveCoupon(Coupon coupon ,HttpServletRequest request, HttpServletResponse response) throws Exception{
        couponService.savecoupon(coupon);
        System.out.println("添加成功");
        request.getRequestDispatcher("/coupon/findAll").forward(request,response);
    }

    @RequestMapping("/updateCoupon")
    public  void updateCoupon(Coupon coupon,HttpServletRequest request, HttpServletResponse response) throws Exception{
        couponService.updatecoupon(coupon);
        System.out.println("更新成功");
        request.getRequestDispatcher("/coupon/findAll").forward(request,response);
    }

    @RequestMapping("/deleteCoupon")
    public  void deleteCoupon(Coupon coupon,HttpServletRequest request, HttpServletResponse response) throws Exception{
        couponService.updatecouponstatus(coupon);
        System.out.println("删除成功");
        request.getRequestDispatcher("/coupon/findAll").forward(request,response);
    }

    @RequestMapping("/findCoupon")
    public @ResponseBody List findCoupon(@RequestParam String couponname){
        List<Coupon> coupon = couponService.findCoupon("%"+couponname+"%");
        return coupon;
    }
    @RequestMapping("/CountCoupon")
    public @ResponseBody List CountCoupon(){
        List<Map> maps = couponService.countCoupon();
        return maps;
    }
}
