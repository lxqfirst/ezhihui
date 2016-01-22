package com.ezhihui.www.service;

import com.ezhihui.www.domain.App;
import com.ezhihui.www.response.BaseResponse;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by lxq on 16/1/22.
 */
public class AppServiceTest extends BaseTest {
    protected static IAppService appService;

    @Override
    public void init() {
        super.init();
        appService = this.applicationContext.getBean(IAppService.class);
    }

    @Test
    public void getByIdTest(){
        BaseResponse<App> result = appService.getAppById(1L);
        Assert.assertNotNull(result.getData());
    }
}
