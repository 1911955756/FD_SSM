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
    /**
     * 根据cs_id查询顾客信息
     * @param cs_id
     * @return
     */
    @Select("select * from customer where cs_id=#{cs_id}")
    List<Customer> findCustomerByUId(String cs_id);

    /**
     * 根据openid查询顾客id
     * @param openid
     * @return
     */
    @Select("select cs_id from customer where openid=#{openid}")
    String findCsidByOpenid(String openid);

    /**
     * 保存顾客信息
     * @param customer
     */
    @Insert("insert into customer(cs_name,openid) values(#{cs_name},#{openid})")
    void saveCustomer(Customer customer);

    /**
     * 更新顾客信息
     * @param customer
     */
    @Update("update customer set cs_name=#{cs_name},image=#{image} where openid=#{openid}")
    void updateCustomer(Customer customer);
}
