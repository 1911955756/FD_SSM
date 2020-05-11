package com.qiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Mr.锵
 * date 2020-03-12
 */
@Controller
@RequestMapping("/odview")
public class OdViewController {
    @RequestMapping("/findAll")
    public ModelAndView findAll(String role){
        ModelAndView modelAndView = new ModelAndView();
        if(role.equals("服务员")){
            modelAndView.addObject("status","待上菜");
        }
        else if(role.equals("后厨")){
            modelAndView.addObject("status","待烹饪");
        }
        modelAndView.setViewName("odview");
        return modelAndView;
    }
}
