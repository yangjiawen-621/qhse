package com.wlhse.entity;

import java.util.List;

public class ReportList {
    private Integer page;
    private Integer total;
    private List<ReportPojo> list;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ReportPojo> getList() {
        return list;
    }

    public void setList(List<ReportPojo> list) {
        this.list = list;
    }
}
