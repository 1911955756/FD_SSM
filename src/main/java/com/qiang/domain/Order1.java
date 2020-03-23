package com.qiang.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Mr.锵
 * date 2020-02-18
 */
@Data
public class Order1 {
    private String orderid;
    private String cs_id;
    private String tableid;
    private String status;
    private Timestamp createtime;
    private Timestamp updatetime;
    private Float total;
    private String couponid;
    private Customer customer;
}
