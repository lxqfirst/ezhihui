package com.ezhihui.www.enums;

import java.io.Serializable;

/**
 * Created by lxq on 16/1/22.
 */
public enum GroupEnum implements Serializable {

    NORMAL(1, "普通用户组"), ADMIN(2, "管理员用户组");

    public final int value;
    public final String message;

    private GroupEnum(int value, String msg) {
        this.value = value;
        this.message = msg;
    }
}
