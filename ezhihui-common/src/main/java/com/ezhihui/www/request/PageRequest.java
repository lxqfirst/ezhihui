package com.ezhihui.www.request;

import com.ezhihui.www.enums.CommonCode;
import com.ezhihui.www.exception.BusinessException;
import com.ezhihui.www.response.PageList;

/**
 * Created by lxq on 16/4/15.
 */
public class PageRequest extends BaseRequest {
    private static final long serialVersionUID = -4281674584094428023L;
    private int pageIndex = 1;
    private int pageSize = 5;
    private int pageOffset;
    private Boolean isPage;

    public PageRequest() {
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }

    }

    public int getPageOffset() {
        this.pageOffset = ((this.pageIndex < 1 ? 1 : this.pageIndex) - 1) * this.pageSize;
        return this.pageOffset;
    }

    public static <T> PageList<T> initPagedList(PageRequest inputQuery, Class<T> clazz) throws BusinessException {
        if (null == inputQuery) {
            throw new BusinessException(Integer.valueOf(CommonCode.PARAM_ERROR.code), CommonCode.PARAM_ERROR.message);
        } else {
            PageList pagedList = new PageList();
            pagedList.setPageIndex(inputQuery.getPageIndex());
            pagedList.setPageSize(inputQuery.getPageSize());
            return pagedList;
        }
    }

    public Boolean getIsPage() {
        return this.isPage;
    }

    public void setIsPage(Boolean isPage) {
        this.isPage = isPage;
    }
}
