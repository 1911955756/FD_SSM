package com.qiang.controller;

import com.qiang.domain.TypeMenu;
import com.qiang.service.ITypeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        typeMenuService.saveTypeMenu(typeMenu);
        return true;
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
}
