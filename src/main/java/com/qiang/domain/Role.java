package com.qiang.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Mr.锵
 * date 2020-02-16
 */
@Data
public class Role {
    private String roleid;
    private String rolename;
    private String status;
    private Timestamp createtime;
    private Timestamp updatetime;
    private List<User1> user1s;
}
