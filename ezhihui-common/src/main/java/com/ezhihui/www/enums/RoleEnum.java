package com.ezhihui.www.enums;

import java.io.Serializable;

/**
 * Created by lxq on 16/1/22.
 */
public enum RoleEnum implements Serializable {

    NORMAL(1, "普通用户"), ADMIN(2, "管理员");

    public final int value;
    public final String message;

    private RoleEnum(int value, String msg) {
        this.value = value;
        this.message = msg;
    }
}
