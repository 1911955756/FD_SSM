package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Role;
import com.qiang.domain.Type1;
import com.qiang.service.IRoleService;
import com.qiang.service.IUserRoleService;
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

/**
 * @author Mr.锵
 * date 2020-03-24
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserRoleService userRoleService;
    @RequestMapping("/findAll")
    public @ResponseBody ModelAndView findAll(@RequestParam(required=false,defaultValue = "1") Integer num){
        ModelMap modelMap = new ModelMap();
        PageInfo<Role> all = roleService.findAll(num);
        modelMap.addAttribute("rolelist",all);
        ModelAndView mv = new ModelAndView("rolemoderMap", modelMap);
        mv.setViewName("rolelist");
        return mv;
    }

    @RequestMapping("/findPageUR")
    public ModelAndView  findPageUR(@RequestParam(required = false,defaultValue ="1") Integer num,
                                    @RequestParam(required = false) String rname,
                                    @RequestParam(required = false) String  uname){

        ModelMap modelMap = new ModelMap();
        PageInfo<Role> pageUR = roleService.findPageUR(num, rname, uname);
        modelMap.addAttribute("urlist",pageUR);
        modelMap.addAttribute("rname",rname);
        modelMap.addAttribute("uname",uname);
        ModelAndView userrolelist = new ModelAndView("userrolelist", modelMap);
        return userrolelist;
    }

    @RequestMapping("/saverole")
    public void saverole(@RequestParam String name, HttpServletRequest request, HttpServletResponse response)throws Exception{
        roleService.saverole(name);
        request.getRequestDispatcher("/role/findAll").forward(request,response);
    }

    @RequestMapping("/updaterolestatus")
    public void updaterolestatus(String roleid,String status, Integer num,HttpServletRequest request, HttpServletResponse response)throws Exception{
        Role role=new Role();
        role.setRoleid(roleid);
        role.setStatus(status);
        roleService.updaterolestatus(role);
        request.getRequestDispatcher("/role/findAll?num="+num+"").forward(request,response);
    }
    @RequestMapping("/deleterole")
    public void deleterole(String roleid,HttpServletRequest request, HttpServletResponse response)throws Exception{
        roleService.deleterole(roleid);
        request.getRequestDispatcher("/role/findAll").forward(request,response);
    }
}
