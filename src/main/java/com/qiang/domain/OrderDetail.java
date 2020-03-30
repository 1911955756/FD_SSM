package com.qiang.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Data
public class OrderDetail {
    private String odid;
    private String orderid;
    private String menuid;
    private Integer menu_num;
    private String status;
    private String called;
    private Timestamp createtime;
    private Timestamp updatetime;
    private Menu menu;
    private Order1 order1;
}
