package com.ezhihui.www.api.interceptors;

import com.ezhihui.www.api.annotations.Login;
import com.ezhihui.www.api.user.UserHolder;
import com.ezhihui.www.auth.AuthManager;
import com.ezhihui.www.domain.Account;
import com.ezhihui.www.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * 基础拦截器
 *
 * @author lxq
 */
public abstract class BaseInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private AuthManager authManager;

    private boolean before(HttpServletRequest request,
                           HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Annotation[] annotations = handlerMethod.getMethod()
                    .getDeclaredAnnotations();

            if (isNeedLogin(annotations)) {
                Account account = authManager
                        .getAccount(CookieUtils.getToken(request));
                if (account != null) {
                    UserHolder.add(account);
                    return true;
                }
                CookieUtils.deleteToken(request, response);
                return this.validate(request, response);
            }
        }

        return true;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getToken(request);
        if (!this.before(request, response, handler)) {
            return false;
        }
        this.updateSession(token, request, response);
        return super.preHandle(request, response, handler);
    }

    public abstract boolean validate(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception;

    /**
     * @param annotations
     * @return
     */
    private boolean isNeedLogin(Annotation annotations[]) {
//        if (annotations == null || annotations.length == 0) {
//            return false;
//        }
//
//        for (Annotation annotation : annotations) {
//            if (annotation.annotationType().equals(Login.class)) {
//                return true;
//            }
//        }
//
//        return false;
        return true;
    }

    private void updateSession(String token, HttpServletRequest request,
                               HttpServletResponse response) {
        if (token != null && !token.isEmpty()) {
            authManager.updateToken(token);
            CookieUtils.updateToken(request, response, CookieUtils.LIFE_CYCLE);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
     * afterCompletion(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object,
     * java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        UserHolder.clear();
        super.afterCompletion(request, response, handler, ex);
    }
}
