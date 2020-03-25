package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Menu;
import com.qiang.domain.Type1;
import com.qiang.service.ITypeService;
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
 * date 2020-02-24
 */
@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private ITypeService typeService;
    @RequestMapping("/findAll")
    public ModelAndView findtypeAll(@RequestParam(required = false,defaultValue ="1") Integer num,
                                    @RequestParam(required = false)String tyname,
                                    @RequestParam(required = false)String tystatus){
        ModelMap modelMap=new ModelMap();
        //调用service的方法
        PageInfo<Type1> list1 = typeService.findAll(num,tyname,tystatus);
        modelMap.addAttribute("typelist",list1);
        modelMap.addAttribute("tyname",tyname);
        modelMap.addAttribute("tystatus",tystatus);
        ModelAndView mv=new ModelAndView("list",modelMap);
        mv.setViewName("typelist");
        return mv;
    }
    @RequestMapping("/findPageTM")
    public ModelAndView  findPageTM(@RequestParam(required = false,defaultValue ="1") Integer num,
                                   @RequestParam(required = false) String tyname,
                                   @RequestParam(required = false) String  mname){

        ModelMap modelMap = new ModelMap();
        PageInfo<Type1> tmlist = typeService.findPageTM(num,tyname,mname);
        modelMap.addAttribute("tmlist",tmlist);
        modelMap.addAttribute("tyname",tyname);
        modelMap.addAttribute("mname",mname);
        ModelAndView typemenulist = new ModelAndView("typemenulist", modelMap);
        return typemenulist;
    }
    @RequestMapping("/savetype")
    public void savetype(String name, HttpServletRequest request, HttpServletResponse response)throws Exception{
        typeService.saveType(name);
        System.out.println("添加成功");
        request.getRequestDispatcher("/type/findAll").forward(request,response);
    }

    @RequestMapping("/updatetypestatus")
    public void updatetypestatus(String typeid,String status, Integer num,HttpServletRequest request, HttpServletResponse response)throws Exception{
        Type1 type1 = new Type1();
        type1.setTypeid(typeid);
        type1.setStatus(status);
        typeService.updatestatus(type1);
        request.getRequestDispatcher("/type/findAll?num="+num+"").forward(request,response);
    }
    @RequestMapping("/deletetype")
    public void deletetype(String typeid,HttpServletRequest request, HttpServletResponse response)throws Exception{
        typeService.deletetype(typeid);
        request.getRequestDispatcher("/type/findAll").forward(request,response);
    }
    @RequestMapping("/findTM")
    public @ResponseBody List findTM(@RequestParam(required = false) String mname){
        System.out.println("表现层：findTM方法执行了。。。");
        //调用service的方法
        List<Type1> list = typeService.findTM(mname);
        return list;
    }
    @RequestMapping("/findIdByname")
    public @ResponseBody Boolean findIdByname(String name){
        String idByname = typeService.findIdByname(name);
        if (idByname==null){
        return true;}
        else{
            return false;}
    }
}
