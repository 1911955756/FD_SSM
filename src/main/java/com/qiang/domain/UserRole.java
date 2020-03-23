package com.qiang.domain;


import lombok.Data;
import java.sql.Timestamp;

/**
 * @author Mr.锵
 * date 2020-02-16
 */
@Data
public class UserRole {
    private String roleid;
    private String userid;
    private Timestamp createtime;
    private Timestamp updatetime;

}
