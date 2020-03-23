package com.qiang.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-18
 */
@Data
public class Customer {
    private String cs_id;
    private String cs_name;
    private Integer phone;
    private String openid;
    private Timestamp createtime;
    private Timestamp updatetime;
    private  String image;
    private List<Order1> order1s;
}
