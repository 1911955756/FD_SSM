package com.qiang.dao;

import com.qiang.domain.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-18
 */
@Repository
public interface ICustomerDao {
    @Select("select * from customer where cs_id=#{cs_id}")
    List<Customer> findCustomerByUId(String cs_id);

    @Select("select cs_id from customer where openid=#{openid}")
    String findCsidByOpenid(String openid);

    @Insert("insert into customer(cs_name,openid) values(#{cs_name},#{openid})")
    void saveCustomer(Customer customer);

    @Update("update customer set cs_name=#{cs_name},image=#{image} where openid=#{openid}")
    void updateCustomer(Customer customer);
}
