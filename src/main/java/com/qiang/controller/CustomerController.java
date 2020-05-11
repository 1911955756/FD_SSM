package com.qiang.controller;

import com.qiang.dao.ICustomerDao;
import com.qiang.domain.Customer;
import com.qiang.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        String csidByOpenid = customerService.findCsidByOpenid(openid);
        if(csidByOpenid==null){
            customerService.saveCustomer(customer);
            csid=customerService.findCsidByOpenid(openid);
        }
        else if(csidByOpenid!=null){
            customerService.updateCustomer(customer);
            csid=csidByOpenid;
        }
        return csid;
    }
}
