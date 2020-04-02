package com.qiang.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Mr.锵
 * date 2020-04-02
 */
@Data
public class Login {
    private String loginid;
    private String userid;
    private Timestamp createtime;
}
