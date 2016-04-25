package com.ezhihui.www.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lxq on 16/4/25.
 */
@Setter
@Getter
public class Account {
    private Integer id;
    private String name;
    private String password;
    private Integer type;
    private Integer status;
    private Date gmtCreated;
    private Date gmtModified;
    private String token;
}
