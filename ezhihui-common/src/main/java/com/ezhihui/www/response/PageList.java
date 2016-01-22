package com.ezhihui.www.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxq on 16/1/22.
 */
public class PageList<T> {
    private static int DEFAULT_PAGE_SIZE = 5; // 默认每页显示数量

    private static int DEFAULT_MAX_PAGE_SIZE = 50; // 每页最大显示数量

    private List<T> items; // 记录集
    private long total; // 总记录数
    private int pageIndex; // 当前页码
    private int pageSize = 5; // 每页显示数量
    private long totalPageCount = 0; // 总页数
    private boolean more; // 是否有下一页

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public PageList() {
        this(0, DEFAULT_PAGE_SIZE, new ArrayList<T>());
    }

    public PageList(int pageIndex, int pageSize, List<T> items) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.items = items;
    }

    public PageList(int pageIndex, int pageSize, int total, List<T> items) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.items = items;
        this.total = total;

        // 将页数也直接计算并赋值
        this.totalPageCount = getCalTotalPageCount();
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex < 1)
            this.pageIndex = 1;
        else
            this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1)
            this.pageSize = DEFAULT_PAGE_SIZE;
        if (pageSize > 50)
            this.pageSize = DEFAULT_MAX_PAGE_SIZE;
        else
            this.pageSize = pageSize;
    }

    /**
     * 取当前页的记录.
     */
    public List<T> getItems() {
        return items;
    }

    public void setList(List<T> items) {
        this.items = items;
    }

    /**
     * 取总记录数.
     */
    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        this.setTotalPageCount(this.getCalTotalPageCount());
        this.setMore(this.hasNextPage());
    }

    /**
     * 取总页数.
     */
    public long getCalTotalPageCount() {
        if (total % pageSize == 0)
            return total / pageSize;
        else
            return total / pageSize + 1;
    }

    /**
     * 取第一页.
     */
    public long getFirst() {
        return ((this.getPageIndex() - 1) * pageSize);
    }

    /**
     * 该页是否有下一页.
     */
    public boolean hasNextPage() {
        return this.getPageIndex() + 1 <= this.getCalTotalPageCount();
    }

    /**
     * 该页是否有上一页.
     */
    public boolean hasPreviousPage() {
        return this.getPageIndex() - 1 >= 1;
    }

    /**
     * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
     *
     * @see #getStartOfPage(int, int)
     */
    protected static int getStartOfPage(int pageIndex) {
        return getStartOfPage(pageIndex, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取任一页第一条数据在数据集的位置.
     *
     * @param pageIndex 页数索引
     * @param pageSize  每页记录条数
     * @return 该页第一条数据
     */
    public static int getStartOfPage(int pageIndex, int pageSize) {
        return (pageIndex - 1) * pageSize + 1;
    }

    /**
     * @return the more
     */
    public boolean isMore() {
        return more;
    }

    /**
     * @param more the more to set
     */
    public void setMore(boolean more) {
        this.more = more;
    }
}
