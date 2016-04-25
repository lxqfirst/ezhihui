package com.ezhihui.www.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lxq on 16/4/25.
 */
public class CookieUtils {
    public static final int LIFE_CYCLE = 1800;
    //public static String DOMAIN = ".ezhihui101.com";

    public CookieUtils() {
    }

    public static String getToken(HttpServletRequest request) {
        String token = getCookie("token", request);
        if (token == null) {
            token = request.getParameter("token");
        }

        if (token != null) {
            return token;
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
                Cookie[] var3 = cookies;
                int var4 = cookies.length;

                for (int var5 = 0; var5 < var4; ++var5) {
                    Cookie cookie = var3[var5];
                    if ("token".equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }

                return null;
            } else {
                return null;
            }
        }
    }

    public static void addToken(String token, HttpServletResponse response) {
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        //cookie.setDomain(DOMAIN);
        response.addCookie(cookie);
    }

    public static void updateToken(HttpServletRequest request, HttpServletResponse response) {
        updateToken(request, response, 1800);
    }

    public static void updateToken(HttpServletRequest request, HttpServletResponse response, int expire) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            Cookie[] var4 = cookies;
            int var5 = cookies.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                Cookie cookie = var4[var6];
                if ("token".equals(cookie.getName())) {
                    cookie.setPath("/");
                    cookie.setMaxAge(expire);
                    //cookie.setDomain(DOMAIN);
                    response.addCookie(cookie);
                }
            }

        }
    }

    public static void deleteToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                if ("token".equals(cookie.getName())) {
                    //cookie.setDomain(DOMAIN);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    return;
                }
            }

        }
    }

    private static String getCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }

            return null;
        } else {
            return null;
        }
    }
}

