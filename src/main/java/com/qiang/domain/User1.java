package com.qiang.domain;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-16
 */
@Data
public class User1 {
    private String userid;
    private String username;
    private Integer phone;
    private String password;
    private String job;
    private Integer deleteflag;
    private Timestamp createtime;
    private Timestamp updatetime;
    private List<Role> roles;

}
