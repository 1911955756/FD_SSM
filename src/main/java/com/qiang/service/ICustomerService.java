package com.qiang.service;

import com.qiang.domain.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-29
 */
public interface ICustomerService {
    /**
     * 根据cs_id查询顾客信息
     * @param cs_id
     * @return
     */
    List<Customer> findCustomerByUId(String cs_id);

    /**
     * 根据openid查询顾客id
     * @param openid
     * @return
     */
    String findCsidByOpenid(String openid);

    /**
     * 保存顾客信息
     * @param customer
     */
    void saveCustomer(Customer customer);

    /**
     * 更新顾客信息
     * @param customer
     */
    void updateCustomer(Customer customer);
}
