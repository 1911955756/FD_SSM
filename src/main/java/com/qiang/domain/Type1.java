package com.qiang.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Data
public class Type1 {
    private String typeid;
    private String name;
    private String status;
    private Timestamp createtime;
    private Timestamp updatetime;
    private List<Menu> menus;
}
