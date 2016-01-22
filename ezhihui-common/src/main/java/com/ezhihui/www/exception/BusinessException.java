package com.ezhihui.www.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by lxq on 16/1/22.
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -1094570587107106587L;

    private int code;
    private String message;

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
