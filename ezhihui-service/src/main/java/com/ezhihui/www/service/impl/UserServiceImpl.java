package com.ezhihui.www.service.impl;

import com.ezhihui.www.dao.UserDAO;
import com.ezhihui.www.domain.User;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lxq on 16/4/26.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public BaseResponse<User> getByName(String name) {
        User user = new User();
        user.setName(name);
        List<User> userList = this.userDAO.getByCondi(user);

        if (userList != null && userList.size() == 1) {
            return new BaseResponse<>(userList.get(0));
        }

        return new BaseResponse<>(null);
    }
}
