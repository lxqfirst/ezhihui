package com.ezhihui.www.enums;

import java.io.Serializable;

/**
 * Created by lxq on 16/1/22.
 */
public enum CommonCode implements Serializable {

    SUCCESS(0, "成功");

    public final int code;
    public final String message;

    private CommonCode(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
