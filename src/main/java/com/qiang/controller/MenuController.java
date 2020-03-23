package com.qiang.controller;

import com.qiang.domain.Menu;
import com.qiang.domain.Type1;
import com.qiang.service.IMenuService;
import com.qiang.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;
    @Autowired
    private ITypeService typeService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = false,defaultValue ="1") Integer num,
                                @RequestParam(required = false) String  mname,
                                @RequestParam(required = false) String  msta){
        ModelMap modelMap=new ModelMap();
        //调用service的方法
        PageInfo<Menu> list1 = menuService.findAll(num,mname,msta);
        List<Type1> list2=typeService.findtypeAll();
        modelMap.addAttribute("listmenu",list1);
        modelMap.addAttribute("listtype",list2);
        ModelAndView mv=new ModelAndView("tmlist",modelMap);
        mv.setViewName("menulist");
        return mv;
    }
    @RequestMapping("/saveMenu")
    public  void saveMenu(Menu menu, HttpServletRequest request, HttpServletResponse response) throws Exception{
        menuService.saveMenu(menu);
        System.out.println("添加成功");
        request.getRequestDispatcher("/menu/findAll").forward(request,response);
    }

    @RequestMapping("/updateMenu")
    public  void updateMenu(Menu menu,HttpServletRequest request, HttpServletResponse response) throws Exception{
        menuService.updateMenu(menu);
        System.out.println("更新成功");
        request.getRequestDispatcher("/menu/findAll").forward(request,response);
    }
    @RequestMapping("/deleteMenu")
    public  void deleteMenu(String menuid,HttpServletRequest request, HttpServletResponse response)throws Exception{
        System.out.println(menuid);
        try {
            menuService.deleteMenu(menuid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("被占用，无法删除");
        }
        System.out.println("删除成功");
        request.getRequestDispatcher("/menu/findAll").forward(request,response);
    }
    @RequestMapping("/toupdateMenu")
    public ModelAndView toupdateMenu(String menuid){
        ModelAndView mv=new ModelAndView();
        //调用service的方法
        Menu byMenuid = menuService.findByMenuid(menuid);
        mv.addObject("bymenuid",byMenuid);
        mv.setViewName("menu");
        return mv;
    }

    @RequestMapping("/findMenu")
    public @ResponseBody List findMenu(@RequestParam String menuname){
        List<Menu> tm = menuService.findMenuByName("%"+menuname+"%");
        return tm;
    }
    @RequestMapping("/countsellnum")
    public @ResponseBody List countsellnum(){
        List<Map> countsellnum = menuService.countsellnum();
        return countsellnum;
    }
    @RequestMapping("/countnewfood")
    public @ResponseBody List countnewfood(){
        List<Map> countnewfood = menuService.countnewfood();
        return countnewfood;
    }
    @RequestMapping(value="uploadMenu",method= {RequestMethod.POST})
    public void uploadMenu(@RequestParam(value="file_excel") MultipartFile file,HttpServletRequest request, HttpServletResponse response)throws Exception{
        String readResult =null;
        try {
            readResult = menuService.readExcelFile(file);
            System.out.println(readResult);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("upload falure");
        }
        request.getRequestDispatcher("/menu/findAll").forward(request,response);
    }
}
