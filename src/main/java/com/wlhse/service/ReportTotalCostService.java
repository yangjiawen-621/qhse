package com.wlhse.service;

import com.wlhse.entity.ReportTotalCostPojo;
import com.wlhse.util.R;

public interface ReportTotalCostService {
    //新增
    R newTotalCost(ReportTotalCostPojo reportTotalCostPojo);
    //查询
    String queryTotalCost(ReportTotalCostPojo reportTotalCostPojo);
    //修改
    String updateTotalCost(ReportTotalCostPojo reportTotalCostPojo);
}
