package com.ezhihui.www.api.user;

import com.ezhihui.www.domain.User;

/**
 * 用户信息存储
 *
 * @author lxq
 */
public class UserHolder {
    private static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static void add(User user) {
        currentUser.set(user);
    }

    public static void clear() {
        currentUser.remove();
    }

    public static User get() {
        return currentUser.get();
    }
}
