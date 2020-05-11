package com.qiang.controller;

import com.qiang.domain.TypeMenu;
import com.qiang.domain.UserRole;
import com.qiang.service.IUserRoleService;
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
 * date 2020-03-12
 */
@Controller
@RequestMapping("/userrole")
public class UserRoleController {
    @Autowired
    private IUserRoleService userRoleService;
    @RequestMapping("/saveUserRole")
    public @ResponseBody boolean saveUserRole(String userid,String roleid){
        UserRole userRole = new UserRole();
        userRole.setUserid(userid);;
        userRole.setRoleid(roleid);
        List<UserRole> findroleid = userRoleService.findroleid(roleid, userid);
        boolean have=true;
        if (findroleid.size()==0){
            userRoleService.saveUserRole(userRole);
            have =false;}
        return have;
    }
    @RequestMapping("/deleteuserrole")
    public @ResponseBody
    void deleteuserrole(String roleid, String userid, HttpServletRequest request, HttpServletResponse response)throws Exception{
        UserRole userRole = new UserRole();
        userRole.setRoleid(roleid);
        userRole.setUserid(userid);
        userRoleService.deleteuserrole(userRole);
        request.getRequestDispatcher("/role/findPageUR").forward(request,response);
    }

    @RequestMapping("/findroleid")
    public @ResponseBody Boolean findtypeid(@RequestParam(required = false) String roleid,
                                            @RequestParam(required = false) String userid){
        List<UserRole> findroleid = userRoleService.findroleid(roleid, userid);
        boolean have=false;
        if(findroleid.size()!=0){
            have=true;
        }
        return have;
    }
}
