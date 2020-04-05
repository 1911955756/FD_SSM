package com.qiang.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author Mr.锵
 * date 2020-03-07
 */
@Data
public class Coupon {
    private String couponid;
    private String couponname;
    private String status;
    private Timestamp createtime;
    private Timestamp updatetime;
    private Timestamp endtime;
    private Float price;
    private String type;
    private String condition;
}
