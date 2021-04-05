package com.wlhse.service;

import com.wlhse.entity.ReportUnitPricePojo;
import com.wlhse.util.R;

public interface ReportUnitPriceService {
    //新增
    R newUnitPrice(ReportUnitPricePojo reportUnitPricePojo);
    //查询
    String queryUnitPrice(ReportUnitPricePojo reportUnitPricePojo);
    //id 查询
    String queryUnitPriceById(int id);
    //修改
    String updateUnitPrice(ReportUnitPricePojo reportUnitPricePojo);
    //删除
    String deleteUnitPrice(int id);
}
