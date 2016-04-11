package com.ezhihui.www.enums;

import java.io.Serializable;

/**
 * Created by lxq on 16/1/22.
 */
public enum CourseStatusEnum implements Serializable {

    NOT_SIGNED(0, "未签到"), SIGNED(1, "已签到");

    public final int code;
    public final String message;

    private CourseStatusEnum(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
}
