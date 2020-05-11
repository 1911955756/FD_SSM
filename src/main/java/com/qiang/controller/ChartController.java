package com.qiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mr.锵
 * date 2020-03-13
 */
@Controller
@RequestMapping("/chart")
public class ChartController {
    @RequestMapping("/findAll")
    public String findAll(){
        return "chartlist";
    }
}
