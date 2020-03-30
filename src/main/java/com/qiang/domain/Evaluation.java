package com.qiang.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Data
public class Evaluation {
    private String cs_id;
    private String orderid;
    private String e_content;
    private Timestamp createtime;
    private Timestamp updatetime;
    private Integer tuijian_num;
    private String photourl;
    private Customer customer;
}
