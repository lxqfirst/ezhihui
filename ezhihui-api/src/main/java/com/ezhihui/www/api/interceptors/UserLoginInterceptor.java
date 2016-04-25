package com.ezhihui.www.api.interceptors;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录拦截器
 *
 * @author lxq
 */
public class UserLoginInterceptor extends BaseInterceptor {

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 1000);
        map.put("message", "请登录");
        String result = gson.toJson(map);
        response.getWriter().write(result);
        return false;
    }

}
