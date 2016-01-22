package com.ezhihui.www.service;

import com.ezhihui.www.domain.App;
import com.ezhihui.www.response.BaseResponse;

/**
 * Created by lxq on 16/1/22.
 */
public interface IAppService {
    public BaseResponse<App> getAppById(Long appId);
}
