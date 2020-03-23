package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.*;
import com.qiang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * date 2020-02-16
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/findByPhone")
    public @ResponseBody boolean findByPhone(@RequestBody User1 user) throws Exception {
        System.out.println("表现层：通过电话号码查询密码。。。");
        //调用service的方法
        String passwordByphone = userService.findPasswordByphone(user.getPhone());
        if(passwordByphone.equals(user.getPassword())){
            System.out.println("成功");
        return true;}
        else{
            System.out.println("失败");
            System.out.println(user.getPhone());
            System.out.println(user.getPassword());
            return false;
         }

    }
    @RequestMapping("/login")
        public ModelAndView  login(Integer phone) {
        System.out.println("表现层：密码正确，登录。。。");
        System.out.println(phone);
        ModelAndView mv=new ModelAndView();
        //调用service的方法
        //暂无
        if(phone!=null){
            List<User1> allByPhone = userService.findAllByPhone(phone);
            for(User1 user1:allByPhone){
                System.out.println(user1);
                System.out.println(user1.getRoles());
            }
            mv.addObject("userrolelist",allByPhone);
            mv.setViewName("manager");
            return mv;}
        else {
            mv.setViewName("error");
            return mv;}
    }
    @RequestMapping("/exit")
    public String exit() {
        System.out.println("exit执行");
        //调用service的方法
        //暂无
        return "redirect:/index.jsp";
    }
    @RequestMapping("/userlist")
    public void userlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("userlist执行");
        request.getRequestDispatcher("/user/findAll").forward(request,response);
    }
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = false,defaultValue ="1") Integer num){
        ModelMap modelMap=new ModelMap();
        PageInfo<User1> list = userService.findAll(num);
        List<Role> list2 = roleService.findAll();
        modelMap.addAttribute("userlist",list);
        modelMap.addAttribute("rolelist",list2);
        ModelAndView mv=new ModelAndView("list",modelMap);
        System.out.println("加入成功");
        mv.setViewName("userlist");
        return mv;
    }
    @RequestMapping("/toupdateUser")
    public ModelAndView toupdateUser(String userid){
        System.out.println("toupdateMenu方法执行"+userid);
        ModelAndView mv=new ModelAndView();
        //调用service的方法
        User1 allByuserid = userService.findAllByuserid(userid);
        mv.addObject("byuserid",allByuserid);
        mv.setViewName("user");
        return mv;
    }
    @RequestMapping("/saveUser")
    public void saveUser(User1 user1,HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println(user1);
        userService.saveUser(user1);
        request.getRequestDispatcher("/user/findAll").forward(request,response);
    }
    @RequestMapping("/updateUser")
    public void updateUser(User1 user1,HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println(user1);
        userService.updateUser(user1);
        request.getRequestDispatcher("/user/findAll").forward(request,response);
    }
    @RequestMapping("/deleteUser")
    public void deleteUser(String userid,HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("deleteUser执行了"+userid);
        //调用service的方法
        userService.updateUserDeleteflag(userid);
        request.getRequestDispatcher("/user/findAll").forward(request,response);
    }

}
