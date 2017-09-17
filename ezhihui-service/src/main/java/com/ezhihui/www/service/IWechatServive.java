package com.ezhihui.www.service;

import com.ezhihui.www.response.BaseResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lxq on 2017/9/17.
 */
public interface IWechatServive {
    /**
     * 处理微信发送过来的消息
     *
     * @param request
     * @return
     */
    BaseResponse<String> wechatMessageProcess(HttpServletRequest request);
}
