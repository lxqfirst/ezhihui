package com.ezhihui.www.enums;

import java.io.Serializable;

/**
 * Created by lxq on 16/1/22.
 */
public enum StatusEnum implements Serializable {

    NORMAL(0, "正常状态"), FORBIDDEN_OR_DELETED(1, "禁用或删除状态");

    public final int value;
    public final String message;

    private StatusEnum(int value, String msg) {
        this.value = value;
        this.message = msg;
    }
}
