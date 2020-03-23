package com.qiang.domain;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Data
public class Menu {
    private String menuid;
    private String menuname;
    private Float price;
    private String Image;
    private Timestamp createtime;
    private Timestamp updatetime;
    private Integer sell_num;
    private String status;
    private String description;
    private Integer kucun;
    private List<Type1> type1s;
    private Type1 type1;
}
