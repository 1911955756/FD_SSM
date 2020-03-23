package com.qiang.controller;

import com.qiang.domain.UserRole;
import com.qiang.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody boolean saveUserRole(UserRole userRole){
        userRoleService.saveUserRole(userRole);
        return true;
    }
}
