package com.qiang.controller;

import com.github.pagehelper.PageInfo;
import com.qiang.domain.Order1;
import com.qiang.domain.Picture;
import com.qiang.service.IPictureService;
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
 * date 2020-02-20
 */
@Controller
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private IPictureService pictureService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(required = false,defaultValue ="1") Integer num){
        System.out.println("表现层：查询所有轮播图。。。");
        ModelAndView mv=new ModelAndView();
        //调用service的方法
        PageInfo<Picture> list = pictureService.finAll(num);
        mv.addObject("picturelist",list);
        mv.setViewName("picturelist");
        return mv;
    }
    @RequestMapping("/findAll2")
    public @ResponseBody List findAll2(){
        //调用service的方法
        List<Picture> list = pictureService.finAllInused();
        for(Picture picture:list){
            System.out.println(picture);
        }
        return list;
    }

    @RequestMapping("/updatepicturestatus")
    public void updatepicturestatus(String pictureid, String status,Integer num,HttpServletRequest request, HttpServletResponse response)throws Exception{
        Picture picture=new Picture();
        picture.setPictureid(pictureid);
        picture.setStatus(status);
        pictureService.updatePicture(picture);
        request.getRequestDispatcher("/picture/findAll?num="+num+"").forward(request,response);
    }
    @RequestMapping("/deletePicture")
    public void deletePicture(String pictureid,HttpServletRequest request, Integer num,HttpServletResponse response)throws Exception{
        System.out.println(pictureid);
        pictureService.deletePicture(pictureid);
        System.out.println("删除成功");
        request.getRequestDispatcher("/picture/findAll?=num"+num+"").forward(request,response);
    }
    @RequestMapping("/savePicture")
    public void savePicture(String url,HttpServletRequest request, HttpServletResponse response)throws Exception{
        System.out.println(url);
        pictureService.savePicture(url);
        System.out.println("添加成功");
        request.getRequestDispatcher("/picture/findAll").forward(request,response);
    }
}
