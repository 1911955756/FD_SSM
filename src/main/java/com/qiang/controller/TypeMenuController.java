package com.qiang.controller;

import com.qiang.domain.TypeMenu;
import com.qiang.service.ITypeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-09
 */
@Controller
@RequestMapping("/typemenu")
public class TypeMenuController {
    @Autowired
    private ITypeMenuService typeMenuService;
    @RequestMapping("/savetypemenu")
    public @ResponseBody
    boolean savetypemenu(String typeid, String menuid){
        TypeMenu typeMenu=new TypeMenu();
        typeMenu.setTypeid(typeid);
        typeMenu.setMenuid(menuid);
        List<TypeMenu> findtypeid = typeMenuService.findtypeid(typeid, menuid);
        System.out.println(findtypeid.size());
        boolean have=true;
        if (findtypeid.size()==0){
        typeMenuService.saveTypeMenu(typeMenu);
        have =false;}
        return have;
    }

    @RequestMapping("/deletetypemenu")
    public @ResponseBody
    void deletetypemenu(String typeid, String menuid, HttpServletRequest request, HttpServletResponse response)throws Exception{
        TypeMenu typeMenu=new TypeMenu();
        typeMenu.setTypeid(typeid);
        typeMenu.setMenuid(menuid);
        typeMenuService.deleteTypeMenu(typeMenu);
        request.getRequestDispatcher("/type/findPageTM").forward(request,response);
    }

    @RequestMapping("/findtypeid")
    public @ResponseBody Boolean findtypeid(String typeid){
        List<TypeMenu> findtypeid = typeMenuService.findtypeid(typeid,null);
        System.out.println(findtypeid.size());
        boolean have=false;
        if(findtypeid.size()!=0){
            have=true;
        }
        return have;
    }
}
