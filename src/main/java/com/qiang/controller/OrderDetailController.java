package com.qiang.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.PageInfo;
import com.qiang.domain.Order1;
import com.qiang.domain.OrderDetail;
import com.qiang.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Controller
@RequestMapping("/orderdetail")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = false,defaultValue ="1") Integer num,
                                @RequestParam(required = false,defaultValue ="1") String status){
        System.out.println("查询第"+num+"页,状态为："+status+"的上菜单");
        ModelMap modelMap=new ModelMap();
        //调用service的方法
        PageInfo<OrderDetail> list = orderDetailService.findAll(num,status);
        modelMap.addAttribute("orderdetaillist",list);
        modelMap.addAttribute("orderdetailstatus",status);
        ModelAndView mv=new ModelAndView("orderdetail",modelMap);
        mv.setViewName("orderdetaillist");
        return mv;
    }
    @RequestMapping("/findAll2")
    public @ResponseBody PageInfo findAll2(@RequestParam(required = false,defaultValue ="1") Integer num,
                                           @RequestParam(required = false,defaultValue ="1") String status){
        System.out.println("查询第"+num+"页,状态为："+status+"的上菜单");
        System.out.println("===================我是分界线=====================");
        //调用service的方法
        PageInfo<OrderDetail> list = orderDetailService.findAll(num,status);
        return list;
    }
    @RequestMapping("/cook")
    public void cook(String odid, HttpServletRequest request, HttpServletResponse response)throws Exception{
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOdid(odid);
        orderDetail.setStatus("烹饪中");
        orderDetailService.updateorderDetail(orderDetail);
        request.getRequestDispatcher("/orderdetail/findAll?status=待烹饪").forward(request,response);
    }
    @RequestMapping("/finishcook")
    public void finishcook(String odid, HttpServletRequest request, HttpServletResponse response)throws Exception{
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOdid(odid);
        orderDetail.setStatus("待上菜");
        orderDetailService.updateorderDetail(orderDetail);
        request.getRequestDispatcher("/orderdetail/findAll?status=烹饪中").forward(request,response);
    }
    @RequestMapping("/finish")
    public void finish(String odid,HttpServletRequest request, HttpServletResponse response)throws Exception{
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOdid(odid);
        orderDetail.setStatus("已上菜");
        orderDetailService.updateorderDetail(orderDetail);
        request.getRequestDispatcher("/orderdetail/findAll?status=待上菜").forward(request,response);
    }

    @RequestMapping("/saveorderdetail")
    public @ResponseBody boolean saveorderdetail(String menuid,String orderid,Integer menu_num){
        System.out.println(menuid);
        System.out.println(orderid);
        System.out.println(menu_num);
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setMenuid(menuid);
        orderDetail.setMenu_num(menu_num);
        orderDetail.setOrderid(orderid);
        orderDetailService.saveorderDetail(orderDetail);
        return true;
    }
    @RequestMapping("/findMyOD")
    public @ResponseBody List findMyOD(String cs_id){
        List<OrderDetail> myOD = orderDetailService.findMyOD(cs_id);
        return myOD;
    }

    @RequestMapping("/cancelOD")
    public @ResponseBody void cancelOD(String odid){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOdid(odid);
        orderDetail.setStatus("已取消");
        orderDetailService.updateorderDetail(orderDetail);
    }
    @RequestMapping("/findMyorderdetail")
    public @ResponseBody List findMyorderdetail(String orderid){
        List<OrderDetail> myorderdetail = orderDetailService.findMyorderdetail(orderid);
        return myorderdetail;
    }
}
