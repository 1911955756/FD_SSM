package com.qiang.domain;

import lombok.Data;
import java.sql.Timestamp;

/**
 * @author Mr.锵
 * date 2020-02-20
 */
@Data
public class Picture {
    private String pictureid;
    private String url;
    private Timestamp createtime;
    private Timestamp updatetime;
    private String status;
}
