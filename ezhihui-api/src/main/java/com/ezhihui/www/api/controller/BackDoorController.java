package com.ezhihui.www.api.controller;

import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by lxq on 16/1/22.
 */
@Controller
@RequestMapping("/backdoor")
public class BackDoorController {
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/getByKey", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<String> getByKey(@RequestParam("key") String key){
        String result = this.redisService.get(key);
        return new BaseResponse<>(result);
    }
}
