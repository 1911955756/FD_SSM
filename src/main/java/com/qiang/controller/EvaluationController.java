package com.qiang.controller;

import com.qiang.domain.Evaluation;
import com.qiang.service.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-03-01
 */
@Controller
@RequestMapping("/evaluation")
public class EvaluationController {
    @Autowired
    private IEvaluationService evaluationService;

    @RequestMapping("/saveEvaluation")
    public @ResponseBody void saveEvaluation(String orderid,String e_content,String cs_id,Integer tuijian_num,
                                             @RequestParam(required = false) List photolist){
        System.out.println("saveEvaluation执行了");
        Evaluation evaluation=new Evaluation();
        evaluation.setOrderid(orderid);
        evaluation.setCs_id(cs_id);
        evaluation.setTuijian_num(tuijian_num);
        evaluation.setE_content(e_content);
        String photoname="";
        for(int i=0;i<photolist.size();i++){
            photoname=photoname+photolist.get(i);
        }
        evaluation.setPhotourl(photoname);
        System.out.println(evaluation);
        evaluationService.saveEvaluation(evaluation);
    }

    @RequestMapping("/findAll")
    public @ResponseBody List findAll(String cs_id,Integer type){
        List<Evaluation> list = null;
        if(type==0){
            list=evaluationService.findAll();
        }else{
            list=evaluationService.findMine(cs_id);
        }
        return list;
    }

    @RequestMapping("/uploadImage")
    public @ResponseBody String uploadImage(MultipartFile file)throws Exception{
        MultipartFile windowsfile=file;
        //MultipartFile linuxfile=file;
        System.out.println("uploadImage执行了");
        //String ideaurl="E:/MYLIFE/SSM框架学习/SSM整合/FD_SSM/src/main/webapp/images/";//IDEA
        String wtomcaturl="E:/Tomcat/apache-tomcat-8.5.47-8090/webapps/FD_SSM_war/images/";//windowsTomcat
        //String ltomcaturl="/usr/local/tomcat8.5/webapps/FD_SSM_war/images/";//linuxTomcat
        String filename = file.getOriginalFilename().substring(file.getOriginalFilename().length()-8);
        //String ideapath=ideaurl+filename;
        String wtomcatpath=wtomcaturl+filename;
        //String ltomcatpath=ltomcaturl+filename;
        //file.transferTo(new File(ideapath));
        windowsfile.transferTo(new File(wtomcatpath));
        //linuxfile.transferTo(new File(ltomcatpath));
        return filename;
    }
}
