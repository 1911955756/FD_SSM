package com.qiang.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author
 * date 2020-02-21
 */
@Data
public class Book {
    private String bookid;
    private String cs_id;
    private Integer plan_num;
    private String status;
    private Timestamp createtime;
    private Timestamp updatetime;
    private Integer wait_num;
}
