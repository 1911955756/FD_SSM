package com.qiang.controller;

import com.qiang.domain.Diets;
import com.qiang.domain.Mongodiet;
import com.qiang.service.IDietsService;
import com.qiang.service.IMongoDietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.锵
 * date 2020-05-08
 */
@Controller
@RequestMapping("/mongo")
public class MongoController {
    @Autowired
    private IMongoDietService mongoDietService;
    @Autowired
    private IDietsService dietsService;
    @RequestMapping("/findAll")
    public @ResponseBody  List<Map> findAll(@RequestParam(required=false)String name,
                                            @RequestParam(required=false,defaultValue = "0")Integer start,
                                            @RequestParam(required=false,defaultValue = "10")Integer size) {
        List<Mongodiet> all = mongoDietService.findAll(name,start,size);
        Long counttotal = mongoDietService.counttotal(name);
        Map map = new HashMap();
        map.put("mongodiet", all);
        map.put("total", counttotal);
        List<Map> list=new ArrayList<>();
        list.add(0,map);
        return list;
    }
    @RequestMapping("/findDietsBytitle")
    public @ResponseBody  List<Diets> findDietsBytitle(String title) {
        List<Diets> all = dietsService.findDietsBytitle(title);
        return all;
    }
}
