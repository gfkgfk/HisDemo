package com.kent.hisdemo.common.response;

import com.kent.hisdemo.common.exception.HisDemoException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页信息。
 * Page index从1开始递增，第1页的page index为1，第2页的page index为2，以此类推第n页的page index为n。
 */
public class PagerInfo implements Serializable {

    private static final long serialVersionUID = 1878320431649126061L;

    public PagerInfo() {
    }

    /**
     * 创建分页信息对象。
     *
     * @param pageSize  每页记录数。必须大于0。
     * @param pageIndex 第几页。Page index从1开始递增，第1页的page index为1，第2页的page index为2，以此类推第n页的page index为n。
     */
    public PagerInfo(int pageSize, int pageIndex) {
        if (pageIndex <= 0)
            throw new HisDemoException("分页信息错误，page index必须从1开始递增");
        if (pageSize <= 0)
            throw new HisDemoException("分页信息错误，page size必须大于0");
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    private int pageIndex = 1;

    /**
     * 获取第几页。
     * <p>
     * Page index从1开始递增，第1页的page index为1，第2页的page index为2，以此类推第n页的page index为n。
     *
     * @return
     */
    public int getPageIndex() {
        return this.pageIndex;
    }

    /**
     * 取MySQL数据库 limit m,n 语句的开始索引值m。
     *
     * @return
     */
    public int getStart() {
        return (this.pageIndex - 1) * this.pageSize;
    }

    private int pageSize = 20;

    /**
     * 获取每页记录数。
     *
     * @return
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 设定每页记录数。
     *
     * @return
     */
    public int setPageSize(int pageSize) {
        return this.pageSize = pageSize;
    }

    private int rowsCount = 0;

    /**
     * 获取符合条件的总记录数。
     *
     * @return
     */
    public int getRowsCount() {
        return this.rowsCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    @Override
    public String toString() {
        return "{ pageIndex:" + this.pageIndex + ", pageSize:" + this.pageSize + ", rowsCount:"
                + this.rowsCount + " }";
    }

    /**
     * 生成分页数据的扩展信息，返回数据API接口，有分页展示时使用
     * 2019.01.05 john
     *
     * @return Map<String, Object>
     */
    public Map<String, Object> apiPage() {
        Map<String, Object> map = new HashMap<>();
        map.put("current", this.getPageIndex());
        map.put("pageSize", this.getPageSize());
        map.put("total", this.getRowsCount());
        return map;
    }

}
