package com.ezhihui.www.service;

import com.ezhihui.www.domain.User;
import com.ezhihui.www.response.BaseResponse;

/**
 * Created by lxq on 16/4/26.
 */
public interface IUserService {
    BaseResponse<User> getByName(String name);
}
