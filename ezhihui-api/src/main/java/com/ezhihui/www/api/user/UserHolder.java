package com.ezhihui.www.api.user;

import com.ezhihui.www.domain.Account;

/**
 * 用户信息存储
 *
 * @author lxq
 */
public class UserHolder {
    private static ThreadLocal<Account> currentUser = new ThreadLocal<>();

    public static void add(Account user) {
        currentUser.set(user);
    }

    public static void clear() {
        currentUser.remove();
    }

    public static Account get() {
        return currentUser.get();
    }
}
