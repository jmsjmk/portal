package com.cheyipai.utils.page;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Page Vo Custom
 * Created by andy on 16/4/26.
 */
public class PageVo<T> implements Serializable {
    private static final long serialVersionUID = 7561478282699366116L;
    private int curPage; // 当前页码

    private int pageSize;// 每页数据量

    private int totalPage;// 总页数

    private int totalResults; // 总数据量

    private List<T> result;

    public PageVo(int curPage, int pageSize, int totalResults, List<T> result) {
        super();
        this.curPage = curPage;
        this.pageSize = pageSize;
        this.totalResults = totalResults;
        this.result = result;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        if (totalResults > 0) {
            totalPage = totalResults / pageSize;
            if ((totalResults % pageSize) > 0) {
                totalPage += 1;
            }
        }
        return totalPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public String toJSON() {
        return JSONObject.toJSONString(this);
    }
}
