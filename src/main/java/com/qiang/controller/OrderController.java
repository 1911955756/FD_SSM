package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Order1;
import com.qiang.domain.Picture;
import com.qiang.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = false,defaultValue ="1") Integer num,
                                @RequestParam(required = false,defaultValue ="yes") String now,
                                @RequestParam(required = false) String tableid,
                                @RequestParam(required = false) String orderid,
                                @RequestParam(required = false) String status){
        ModelMap modelMap = new ModelMap();
        //调用service的方法
        PageInfo<Order1> list = orderService.findAll(num,now,tableid,orderid,status);
        modelMap.addAttribute("orderlist",list);
        modelMap.addAttribute("now",now);
        modelMap.addAttribute("orderid",orderid);
        modelMap.addAttribute("tableid",tableid);
        modelMap.addAttribute("status",status);
        ModelAndView mv=new ModelAndView("mvlist",modelMap);
        mv.setViewName("orderlist");
        return mv;
    }
    @RequestMapping("/findAll2")
    public @ResponseBody PageInfo findAll2(@RequestParam(required = false,defaultValue ="1") Integer num,
                                           @RequestParam(required = false,defaultValue ="yes") String now,
                                           @RequestParam(required = false) String tableid,
                                           @RequestParam(required = false) String orderid,
                                           @RequestParam(required = false) String status){
        //调用service的方法
        PageInfo<Order1> list = orderService.findAll(num,now,tableid,orderid,status);
        return list;
    }
    @RequestMapping("/saveorder")
    public @ResponseBody String saveorder(String cs_id,String tableid){
        String orid="";
        String oridByCsid = orderService.findOridByCsid(cs_id);
        if(oridByCsid==null){
            Order1 order1=new Order1();
            order1.setCs_id(cs_id);
            order1.setTableid(tableid);
            orderService.saveOrder(order1);
            orid=orderService.findOridByCsid(cs_id);
        }
        else if(oridByCsid!=null){
            orid=oridByCsid;
        }
        return orid;
    }

    @RequestMapping("/findMyOrder")
    public @ResponseBody List findMyOrder(String cs_id,Integer type){
        Order1 order1=new Order1();
        order1.setCs_id(cs_id);
        if(type==1){
            order1.setStatus("待上菜");
        }else if(type==2){
            order1.setStatus("待评价");
        }else{
            order1.setStatus("已评价");
        }
        List<Order1> myOrderByCsid = orderService.findMyOrderByCsid(order1);
        return myOrderByCsid;
    }

    @RequestMapping("/updateMyorder")
    public @ResponseBody void updateMyorder(String orderid,Integer type){
        Order1 order1=new Order1();
        order1.setOrderid(orderid);
        if(type==1){
            order1.setStatus("待评价");
        }else{
            order1.setStatus("已评价");
        }
        orderService.updateorder(order1);
    }
    @RequestMapping("/updateReality")
    public @ResponseBody void updateReality(Order1 order1){
        orderService.updateReality(order1);
        orderService.updateorder(order1);
    }
    @RequestMapping("/countpeoplenum")
    public @ResponseBody List countpeoplenum(){
        List<Map> countpeoplenum = orderService.countpeoplenum();
        return countpeoplenum;
    }
    @RequestMapping("/countmoney")
    public @ResponseBody List countmoney(){
        List<Map> countmoney = orderService.countmoney();
        return countmoney;
    }
}
