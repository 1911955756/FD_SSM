package com.qiang.controller;

import com.qiang.dao.ICustomerDao;
import com.qiang.domain.Customer;
import com.qiang.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-29
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @RequestMapping("/saveCustomer")
    public @ResponseBody String saveCustomer(String openid, String cs_name,String image){
        Customer customer=new Customer();
        customer.setOpenid(openid);
        customer.setCs_name(cs_name);
        customer.setImage(image);
        String csid="";
        List<Customer> csByOpenid = customerService.findCsidByOpenid(openid);
        if(csByOpenid==null){
            customerService.saveCustomer(customer);
            csByOpenid = customerService.findCsidByOpenid(openid);
            for(Customer cs : csByOpenid){
                csid=cs.getCs_id();
            }
        }
        else if(csByOpenid!=null){
            for(Customer cs : csByOpenid){
                customer.setOpenid(cs.getOpenid());
                customer.setCs_name(cs.getCs_name());
                customer.setImage(cs.getImage());
                customerService.updateCustomer(customer);
                csid=cs.getCs_id();
            }
        }
        return csid;
    }
}
