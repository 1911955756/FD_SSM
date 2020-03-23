package com.qiang.domain;

import lombok.Data;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-21
 */
@Data
public class Table1 {
    private String tableid;
    private Integer people_num;
    private  String status;
    private List<Book> books;
}
