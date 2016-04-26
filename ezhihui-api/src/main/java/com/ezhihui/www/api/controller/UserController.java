package com.ezhihui.www.api.controller;

import com.ezhihui.www.api.annotations.Login;
import com.ezhihui.www.api.user.UserHolder;
import com.ezhihui.www.auth.AuthManager;
import com.ezhihui.www.domain.User;
import com.ezhihui.www.enums.CommonCode;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lxq
 */
@Controller
@RequestMapping("/")
public class UserController extends BaseController {
    @Autowired
    private AuthManager authManager;

    /**
     * 登录
     *
     * @param name
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<String> login(@RequestParam("name") String name, @RequestParam("password") String password, HttpServletResponse response) {
        String token = authManager.auth(name, password);
        if (token == null) {
            BaseResponse<String> baseResponse = new BaseResponse<String>();
            baseResponse.setCode(CommonCode.ACCOUNT_PWD_ERROR.code);
            baseResponse.setMessage(CommonCode.ACCOUNT_PWD_ERROR.message);
            return baseResponse;
        }

        BaseResponse<String> baseResponse = new BaseResponse<String>();
        baseResponse.setData(token);
        baseResponse.setCode(CommonCode.SUCCESS.code);
        baseResponse.setMessage(CommonCode.SUCCESS.message);
        CookieUtils.addToken(token, response);
        return baseResponse;
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    @Login
    public BaseResponse<String> logout(HttpServletRequest request,
                                       HttpServletResponse response) {
        User account = UserHolder.get();
        authManager.logout(account);
        CookieUtils.deleteToken(request, response);
        return new BaseResponse();
    }
}
