package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Role;
import com.qiang.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-24
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/findAll")
    public @ResponseBody ModelAndView findAll(@RequestParam(required=false,defaultValue = "1") Integer num){
        ModelMap modelMap = new ModelMap();
        PageInfo<Role> all = roleService.findAll(num);
        modelMap.addAttribute("rolelist",all);
        ModelAndView mv = new ModelAndView("rolemoderMap", modelMap);
        mv.setViewName("rolelist");
        return mv;
    }
}
