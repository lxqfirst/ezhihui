package com.ezhihui.www.auth;

import com.ezhihui.www.domain.User;
import com.ezhihui.www.service.IUserService;
import com.ezhihui.www.service.impl.RedisService;
import com.ezhihui.www.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author lxq
 */
@Slf4j
@Service("authManager")
public class AuthManagerImpl implements AuthManager {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IUserService userService;

    public static final String USER_PREFIX = "ezhihui:user:";
    public static final String TOKEN_PREFIX = "ezhihui:token:";

    public static final long REDIS_DEFAULT_EXPIRE = 3600 * 4;

    @Override
    public String auth(String name, String password) {
        User account = getUser(name, password);
        if (account == null) {
            return null;
        }
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString().replace("-", "");
        account.setToken(token);
        redisService.set(USER_PREFIX + name, account, REDIS_DEFAULT_EXPIRE, TimeUnit.SECONDS);
        redisService.set(TOKEN_PREFIX + token, account, REDIS_DEFAULT_EXPIRE, TimeUnit.SECONDS);

        return token;
    }

    /**
     * 获取用户信息
     *
     * @param name
     * @param password
     * @return
     */
    private User getUser(String name, String password) {
        User account = this.userService.getByName(name).getData();
        if (account == null) {
            return null;
        }

        if (MD5Utils.md5(password).equals(account.getPassword())) {
            return account;
        }
        return null;
    }


    @Override
    public User getAccount(String token) {
        log.info("the token is " + token);
        User account = redisService.get(TOKEN_PREFIX + token, User.class);
        return account;
    }

    @Override
    public void updateToken(String token) {
        redisService.expire(token, REDIS_DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }

    @Override
    public void logout(User user) {
        redisService.delete(USER_PREFIX + user.getName());
        redisService.delete(TOKEN_PREFIX + user.getToken());
    }
}
