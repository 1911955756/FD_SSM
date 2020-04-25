package com.qiang.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * @author Mr.锵
 * date 2020-04-14
 */
@Controller
@RequestMapping("/email")
public class SenEmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.smtp.username}")
    private String emailFrom;

    @RequestMapping("/send")
    public @ResponseBody  Boolean  senMsg(HttpSession httpSession, @RequestParam String email){
        //生成六位数验证码
        String Captcha = String.valueOf(new Random().nextInt(899999) + 100000);//0~899999之间+100000
        httpSession.setAttribute("Captcha",Captcha);
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人的邮箱地址
        message.setFrom(emailFrom);
        //收件人的邮箱地址
        message.setTo(email);
        //邮件主题
        message.setSubject("饭店后台管理系统");
        //邮件内容
        message.setText("找回密码-验证码："+Captcha+"\n"+"有效期：1分钟");
        //发送邮件
        javaMailSender.send(message);
        return true;
    }
}

