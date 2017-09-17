package com.ezhihui.www.api.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by lxq on 2017/9/16.
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController {
    private static final String WECHAT_TOEN = "4391fd2545b14c64b52511fdd0f2";

    @RequestMapping(value = "/getToken")
    @ResponseBody
    public String getToken(HttpServletRequest request, HttpServletResponse response, @RequestParam("signature") String signature,
                           @RequestParam(value = "echostr", required = false) String echostr,
                           @RequestParam(value = "openid", required = false) String openid,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce) {


        String[] arr = new String[]{WECHAT_TOEN, timestamp, nonce};
        Arrays.sort(arr);

        String content = arr[0] + arr[1] + arr[2];

        String temp = DigestUtils.shaHex(content);

        if (temp.equalsIgnoreCase(signature)) {
            return echostr;
        }

        return "wechat_token_error";
    }
}
