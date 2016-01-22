package com.ezhihui.www.service.impl;

import com.ezhihui.www.dao.AppDAO;
import com.ezhihui.www.domain.App;
import com.ezhihui.www.response.BaseResponse;
import com.ezhihui.www.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lxq on 16/1/22.
 */
@Service("appService")
public class AppServiceImpl extends BaseService implements IAppService {
    @Autowired
    private AppDAO appDAO;

    @Override
    public BaseResponse<App> getAppById(Long appId) {
        App app = this.appDAO.selectByPrimaryKey(appId);
        return new BaseResponse<>(app);
    }
}
