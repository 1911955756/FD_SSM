package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.*;
import com.qiang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-16
 */
@Controller
@RequestMapping("/user")
public class UserController{
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ILoginService loginService;
    @RequestMapping("/findByPhone")
    public @ResponseBody boolean findByPhone(@RequestBody User1 user) {
        String passwordByphone = userService.findPasswordByphone(user.getPhone());
        if(user.getPassword().equals(passwordByphone)){
            System.out.println("查找成功");
        return true;}
        else{
            System.out.println("查找失败");
            return false;
         }

    }
    @RequestMapping("/findPhone")
    public @ResponseBody boolean findPhone(@RequestParam String phone){
        String passwordByphone = userService.findPasswordByphone(phone);
        if(passwordByphone==null){
            return true;
        }else{
            return false;
        }

    }
    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
        public ModelAndView  login(String phone, HttpSession httpSession) {
        System.out.println("表现层：密码正确，登录。。。");
        ModelMap modelMap=new ModelMap();
        //调用service的方法
        //暂无
            List<User1> allByPhone = userService.findAllByPhone(phone);
            for(User1 user1:allByPhone){
                loginService.savelogin(user1.getUserid());
            }
            ArrayList<String> list = new ArrayList<String>();
            if(httpSession.getServletContext().getAttribute("Count")!=null){
            list=(ArrayList<String>)httpSession.getServletContext().getAttribute("Count");}
            list.add(list.size(),phone);
            httpSession.getServletContext().setAttribute("Count",list);
            modelMap.addAttribute("uphone",phone);
            modelMap.addAttribute("userrolelist",allByPhone);
            ModelAndView mv=new ModelAndView("manager",modelMap);
            return mv;
    }
    @RequestMapping("/userlist")
    public void userlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/user/findAll").forward(request,response);
    }
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = false,defaultValue ="1") Integer num,
                                @RequestParam(required = false) String uphone,
                                @RequestParam(required = false) String  uname,
                                @RequestParam(required = false) String ujob){
        ModelMap modelMap=new ModelMap();
        PageInfo<User1> list = userService.findAll(num,uname,ujob,uphone);
        List<Role> list2 = roleService.findroleAll();
        modelMap.addAttribute("userlist",list);
        modelMap.addAttribute("rolelist",list2);
        modelMap.addAttribute("uphone",uphone);
        modelMap.addAttribute("uname",uname);
        modelMap.addAttribute("ujob",ujob);
        ModelAndView mv=new ModelAndView("list",modelMap);
        mv.setViewName("userlist");
        return mv;
    }
    @RequestMapping("/findAll2")
    public @ResponseBody ModelAndView findAll2(@RequestParam(required = false,defaultValue ="1") Integer num,
                                               @RequestParam(required = false) String uphone,
                                               @RequestParam(required = false) String  uname,
                                               @RequestParam(required = false) String  ujob){
        ModelMap modelMap=new ModelMap();
        PageInfo<User1> list = userService.findAll(num,uname,ujob,uphone);
        modelMap.addAttribute("userlist",list);
        modelMap.addAttribute("uphone",uphone);
        modelMap.addAttribute("uname",uname);
        modelMap.addAttribute("ujob",ujob);
        ModelAndView mv=new ModelAndView("message",modelMap);
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
        userService.saveUser(user1);
        request.getRequestDispatcher("/user/findAll").forward(request,response);
    }
    @RequestMapping("/updateUser")
    public void updateUser(User1 user1,HttpServletRequest request, HttpServletResponse response) throws Exception{
        userService.updateUser(user1);
        request.getRequestDispatcher("/user/findAll").forward(request,response);
    }
    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public String  updatepassword(String account,String pwd){
        User1 user1=new User1();
        user1.setPhone(account);
        user1.setPassword(pwd);
        userService.updatepassword(user1);
        return "redirect:/";
    }
    @RequestMapping("/deleteUser")
    public void deleteUser(String userid,HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("deleteUser执行了"+userid);
        //调用service的方法
        userService.updateUserDeleteflag(userid);
        request.getRequestDispatcher("/user/findAll").forward(request,response);
    }
    @RequestMapping("/checkapplication")
    public @ResponseBody boolean checkapplication(HttpSession httpSession,@RequestParam String phone){
        ArrayList<String> list = new ArrayList<String>();
        if(httpSession.getServletContext().getAttribute("Count")!=null){
        list=(ArrayList<String>)httpSession.getServletContext().getAttribute("Count");}
        boolean equals = list.contains(phone);
        return equals;
    }
    @RequestMapping("/deleteapplication")
    public @ResponseBody void deleteapplication(HttpSession httpSession,@RequestParam String phone){
        ArrayList<String> list = new ArrayList<String>();
        if(httpSession.getServletContext().getAttribute("Count")!=null){
            list=(ArrayList<String>)httpSession.getServletContext().getAttribute("Count");}
        list.remove(phone);
        httpSession.getServletContext().setAttribute("Count",list);
        System.out.println("deletesession执行了");
    }
    @RequestMapping("/findcountnum")
    public @ResponseBody int findcountnum(HttpSession httpSession){
        ArrayList<String> list = new ArrayList<String>();
        if(httpSession.getServletContext().getAttribute("Count")!=null){
            list=(ArrayList<String>)httpSession.getServletContext().getAttribute("Count");}
        int size = list.size();
        return size;
    }
    @RequestMapping("/checkemail")
    public @ResponseBody boolean checkemail(String email){
        List<User1> checkemail = userService.checkemail(email);
        if(checkemail.size()!=0){
            return true;
        }else{
            return false;
        }
    }
    @RequestMapping("/checkcaptcha")
    public @ResponseBody boolean checkcaptcha(HttpSession httpSession,@RequestParam String captcha){
        String captcha1 = (String)httpSession.getAttribute("Captcha");
        if(captcha.equals(captcha1)){
            return true;
        }else {
            return false;
        }
    }
    @RequestMapping("/deletecaptcha")
    public @ResponseBody void deletecaptcha(HttpSession httpSession){
        httpSession.removeAttribute("Captcha");
    }
}
