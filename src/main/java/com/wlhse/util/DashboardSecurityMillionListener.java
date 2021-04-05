package com.wlhse.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wlhse.dao.DashboardSecurityMillionDao;
import com.wlhse.entity.DashboardSecurityMillionPojo;

import java.util.ArrayList;

/**
 * @author tobing
 */
public class DashboardSecurityMillionListener extends AnalysisEventListener<DashboardSecurityMillionPojo> {

    private DashboardSecurityMillionDao dashboardSecurityMillionDao;

    private ArrayList<DashboardSecurityMillionPojo> list
            = new ArrayList<>();

    public DashboardSecurityMillionListener(DashboardSecurityMillionDao dashboardSecurityMillionDao) {
        this.dashboardSecurityMillionDao = dashboardSecurityMillionDao;
    }

    @Override
    public void invoke(DashboardSecurityMillionPojo dashboardSecurityMillionPojo, AnalysisContext analysisContext) {
        list.add(dashboardSecurityMillionPojo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 判断数据库中是否存在数据
        // 不存在：直接插入
        // 存在：直接更新
        int countDashboardSecurityMillion = dashboardSecurityMillionDao.countDashboardSecurityMillion();
        if (countDashboardSecurityMillion != 0) {
            for (DashboardSecurityMillionPojo dashboardSecurityMillionPojo : list) {
                // 数据完整性判断
                if (dashboardSecurityMillionPojo.getSubtotal() != null &&
                        dashboardSecurityMillionPojo.getCompanyCode() != null &&
                        dashboardSecurityMillionPojo.getCompanyName() != null) {
                    // TODO 更新 --> 插入
                    // dashboardSecurityMillionDao.updateDashboardSecurityMillion(dashboardSecurityMillionPojo);
                    dashboardSecurityMillionDao.insertDashboardSecurityMillion(dashboardSecurityMillionPojo);
                }
            }
        } else {
            for (DashboardSecurityMillionPojo dashboardSecurityMillionPojo : list) {
                dashboardSecurityMillionDao.insertDashboardSecurityMillion(dashboardSecurityMillionPojo);
            }
        }
    }
}
