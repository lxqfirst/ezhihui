package com.ezhihui.www.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lxq on 16/4/25.
 */
@Setter
@Getter
public class User {
    private Integer id;

    private String name;

    private String password;

    private Integer role;

    private Integer group;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String token;
}
