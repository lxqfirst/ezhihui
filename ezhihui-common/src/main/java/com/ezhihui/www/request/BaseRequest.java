package com.ezhihui.www.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by lxq on 16/4/15.
 */
@Getter
@Setter
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = -3482503166503743532L;

    private String requestId;
}
