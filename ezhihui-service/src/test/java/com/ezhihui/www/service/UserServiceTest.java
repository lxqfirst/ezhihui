package com.ezhihui.www.service;

import com.ezhihui.www.domain.User;
import com.ezhihui.www.enums.GroupEnum;
import com.ezhihui.www.enums.RoleEnum;
import com.ezhihui.www.enums.StatusEnum;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.utils.MD5Utils;
import com.sun.org.apache.xerces.internal.util.Status;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by lxq on 16/4/26.
 */
public class UserServiceTest extends BaseTest {
    protected IUserService userService;

    @Override
    public void init() {
        super.init();
        userService = this.applicationContext.getBean(IUserService.class);
    }

    @Test
    public void testCreate() {
        User user = new User();
        user.setName("admin");
        user.setPassword(MD5Utils.md5("admin"));
        user.setRole(RoleEnum.ADMIN.value);
        user.setGroup(GroupEnum.ADMIN.value);
        user.setStatus(StatusEnum.NORMAL.value);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        BaseResponse<Integer> response = this.userService.create(user);

        Assert.assertNotNull(response.getData());
    }
}
