package com.ezhihui.www.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by lxq on 2017/9/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WechatMessageDto {
    private String Content;

    private String ToUserName;

    private String FromUserName;

    private String CreateTime;

    private String MsgType;
}
