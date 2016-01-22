package com.ezhihui.www.response;

/**
 * Created by lxq on 16/1/22.
 */
public class PageListResponse<T> extends BaseResult {

    private static final long serialVersionUID = -6054145568967024478L;

    private PageList<T> data;

    /**
     * @return the data
     */
    public PageList<T> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(PageList<T> data) {
        this.data = data;
    }
}
