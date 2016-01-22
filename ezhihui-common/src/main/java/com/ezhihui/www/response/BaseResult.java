package com.ezhihui.www.response;

import com.ezhihui.www.enums.CommonCode;

import java.io.Serializable;

/**
 * Created by lxq on 16/1/22.
 */
public class BaseResult implements Serializable {
    private static final long serialVersionUID = -7214400262158236918L;

    /**
     * 调用返回码，一般为错误代码。
     */
    private Integer code;

    /**
     * 调用返回的消息，一般为错误消息。
     */
    private String message;

    /**
     * 请求Id
     */
    private String requestId;


    public BaseResult() {
        this.code = CommonCode.SUCCESS.code;
        this.message = CommonCode.SUCCESS.message;
    }

    /**
     * 设置错误信息
     *
     * @param code
     * @param message
     */
    @SuppressWarnings("unchecked")
    public <R extends BaseResult> R setErrorMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
        return (R) this;
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

}
