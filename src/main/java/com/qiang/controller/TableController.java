package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Table1;
import com.qiang.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-23
 */
@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private ITableService tableService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = false,defaultValue ="1") Integer num,
                                @RequestParam(required = false) String taid,
                                @RequestParam(required = false) Integer  penum,
                                @RequestParam(required = false) String   tasta){
        System.out.println("表现层：查询所有订单。。。");
        System.out.println(num+","+taid+","+penum+","+tasta);
        ModelAndView mv=new ModelAndView();
        //调用service的方法
        PageInfo<Table1> list = tableService.findAll(num,taid,penum,tasta);
        mv.addObject("tablelist",list);
        mv.setViewName("tablelist");
        return mv;
    }
    @RequestMapping("/findAll2")
    public @ResponseBody PageInfo findAll2(@RequestParam(required = false,defaultValue ="1") Integer num,
                                           @RequestParam(required = false) String  taid,
                                           @RequestParam(required = false) Integer  penum,
                                           @RequestParam(required = false) String   tasta){
        System.out.println(num+","+taid+","+penum+","+tasta);
        //调用service的方法
        PageInfo<Table1> list = tableService.findAll(num,taid,penum,tasta);
        return list;
    }
    @RequestMapping("/checktableid")
    public @ResponseBody boolean checktableid(String tableid){
        System.out.println(tableid);
        List<Table1> findtable = tableService.findtable();
        boolean table=false;
        for (Table1 table1:findtable){
            if(table1.getTableid().equals(tableid)){
                table=true;
            }
        }
        return table;
    }
    @RequestMapping("/savetable")
    public @ResponseBody boolean savetable(String tableid,Integer people_num){
        Table1 table1=new Table1();
        table1.setTableid(tableid);
        table1.setPeople_num(people_num);
        tableService.saveTable(table1);
        System.out.println("添加成功");
        return true;
    }
    @RequestMapping("/updatetablestatus")
    public  void updatetablestatus(String tableid, String status, Integer num,HttpServletRequest request, HttpServletResponse response) throws Exception{
        Table1 table1=new Table1();
        table1.setTableid(tableid);
        table1.setStatus(status);
        tableService.updatetablestatus(table1);
        System.out.println(tableid+"更新成功"+status);
        request.getRequestDispatcher("/table/findAll?num="+num+"").forward(request,response);
    }

    @RequestMapping("/findTpeoplenum")
    public @ResponseBody List findTpeoplenum(){
        List<Table1> tpeoplenum = tableService.findTpeoplenum();
        return tpeoplenum;
    }
}
