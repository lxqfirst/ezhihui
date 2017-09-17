package com.ezhihui.www.service.impl;

import com.ezhihui.www.constant.MessageConstant;
import com.ezhihui.www.dto.WechatMessageDto;
import com.ezhihui.www.enums.CommonCode;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.service.IWechatServive;
import com.ezhihui.www.utils.MessageUtil;
import org.aspectj.apache.bcel.classfile.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by lxq on 2017/9/17.
 */
@Service
public class WechatServiceImpl implements IWechatServive {
    @Override
    public BaseResponse<String> wechatMessageProcess(HttpServletRequest request) {
        String respMessage = null;
        try {

            // xml请求解析
            Map<String, String> requestMap = MessageUtil.xmlToMap(request);

            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // 消息内容
            String content = requestMap.get("Content");

            // 文本消息
            if (msgType.equals(MessageConstant.REQ_MESSAGE_TYPE_TEXT)) {

                //自动回复
                WechatMessageDto text = new WechatMessageDto();
                text.setContent("the text is" + content);
                text.setToUserName(fromUserName);
                text.setFromUserName(toUserName);
                text.setCreateTime(new Date().getTime() + "");
                text.setMsgType(msgType);

                respMessage = MessageUtil.textMessageToXml(text);

            }
        } catch (Exception e) {
            return new BaseResponse<>(CommonCode.PARAM_ERROR.code, CommonCode.PARAM_ERROR.message, null);
        }
        return new BaseResponse<>(respMessage);
    }
}
