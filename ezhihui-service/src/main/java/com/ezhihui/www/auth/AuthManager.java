package com.ezhihui.www.auth;

import com.ezhihui.www.domain.User;


/**
 * 用户登录
 *
 * @author lxq
 */
public interface AuthManager {
    /**
     * 登录授权, 登录成功返回token，失败范围null
     *
     * @param name     用户名
     * @param password 密码
     * @return
     */
    public String auth(String name, String password);

    /**
     * 获取登录信息
     *
     * @param token
     * @return
     */
    public User getAccount(String token);

    /**
     * 更新token时间
     *
     * @param token
     */
    public void updateToken(String token);

    /**
     * 登出
     *
     * @param user
     */
    void logout(User user);
}
