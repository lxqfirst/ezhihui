package com.ezhihui.www.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lxq on 2017/9/16.
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController {
    private static final String WECHAT_TOEN = "4391fd2545b14c64b52511fdd0f2";

    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    @ResponseBody
    public String getToken() {
        return WECHAT_TOEN;
    }
}
