package com.ezhihui.www.enums;

import java.io.Serializable;

/**
 * Created by lxq on 16/1/22.
 */
public enum CommonCode implements Serializable {

    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数错误"),
    SALAY_PARAM_ERROR(1, "薪水设置有误"),
    ACCOUNT_PWD_ERROR(1000, "用户名密码错误");

    public final int code;
    public final String message;

    private CommonCode(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
