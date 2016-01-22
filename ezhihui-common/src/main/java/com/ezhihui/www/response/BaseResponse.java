package com.ezhihui.www.response;

import java.io.Serializable;

/**
 * Created by lxq on 16/1/22.
 */
public class BaseResponse<T> extends BaseResult implements Serializable {
    private static final long serialVersionUID = -7234125697775254951L;

    private T data;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseResponse() {
        super();
    }

    public BaseResponse(T data) {
        super();
        this.setData(data);
    }
}
