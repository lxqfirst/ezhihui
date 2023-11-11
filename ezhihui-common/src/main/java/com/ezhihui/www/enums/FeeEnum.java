package com.ezhihui.www.enums;

import java.io.Serializable;

/**
 * Created by lxq on 16/1/22.
 */
public enum FeeEnum implements Serializable {

    DEDUCTION(1, "已扣款"), NO_SIGNED_REFUND(2, "签到取消后退款"), DELETE_REFUND(3, "删除课程后退款"), NO_CHARGE(4, "未计费");

    public final int value;
    public final String message;

    private FeeEnum(int value, String msg) {
        this.value = value;
        this.message = msg;
    }
}
