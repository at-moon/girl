package com.atmoon.vo;

import org.springframework.data.domain.Sort;

/**
 * 分页参数
 */
public class PageConfig {

    private Integer pageNum = 1;//页数

    private Integer pageSize = 10;//每页记录数

    private Sort sort;//排序

    public PageConfig() {
    }

    public PageConfig(Integer pageNum, Integer pageSize, Sort sort) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
