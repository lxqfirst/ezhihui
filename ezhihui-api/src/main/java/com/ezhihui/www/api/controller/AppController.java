package com.ezhihui.www.api.controller;

import com.ezhihui.www.domain.App;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.service.IAppService;
import com.ezhihui.www.service.impl.AppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lxq on 16/1/22.
 */
@Controller
@RequestMapping("/app")
public class AppController extends BaseController {
    @Autowired
    private IAppService appService;

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse<App> getById(@RequestParam("appId") Long appId) {
        return this.appService.getAppById(appId);
    }
}
