package com.qiang.service.impl;

import com.qiang.dao.ICustomerDao;
import com.qiang.domain.Customer;
import com.qiang.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-29
 */
@Service("customerService")
public class ICustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerDao customerDao;
    @Override
    public List<Customer> findCustomerByUId(String cs_id) {
        return customerDao.findCustomerByUId(cs_id);
    }

    @Override
    public List<Customer> findCsidByOpenid(String openid) {
        return customerDao.findCsidByOpenid(openid);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }
}
