package com.wlhse.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wlhse.dao.DashboardSecurityProjectDao;
import com.wlhse.entity.DashboardSecurityProjectPojo;

import java.util.ArrayList;

public class DashboardSecurityProjectListener extends AnalysisEventListener<DashboardSecurityProjectPojo> {

    private DashboardSecurityProjectDao dashboardSecurityProjectDao;

    private ArrayList<DashboardSecurityProjectPojo> list
            = new ArrayList<>();

    public DashboardSecurityProjectListener(DashboardSecurityProjectDao dashboardSecurityProjectDao) {
        this.dashboardSecurityProjectDao = dashboardSecurityProjectDao;
    }

    @Override
    public void invoke(DashboardSecurityProjectPojo dashboardSecurityProjectPojo, AnalysisContext analysisContext) {
        list.add(dashboardSecurityProjectPojo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 判断数据库中是否存在数据
        // 不存在：直接插入
        // 存在：直接更新
        int countDashboardSecurityProject = dashboardSecurityProjectDao.countDashboardSecurityProject();
        if (countDashboardSecurityProject != 0) {
            for (DashboardSecurityProjectPojo dashboardSecurityProjectPojo : list) {
                // 数据完整性判断
                if (dashboardSecurityProjectPojo.getCompanyCode() != null &&
                        dashboardSecurityProjectPojo.getCompanyName() != null &&
                        dashboardSecurityProjectPojo.getProjectLevel() != null &&
                        dashboardSecurityProjectPojo.getProjectName() != null &&
                        dashboardSecurityProjectPojo.getProjectFunds() != null &&
                        dashboardSecurityProjectPojo.getRecordedFundsRate() != null) {
                    // TODO 更新 --> 插入
                    //dashboardSecurityProjectDao.updateDashboardSecurityProject(dashboardSecurityProjectPojo);
                    dashboardSecurityProjectDao.insertDashboardSecurityProject(dashboardSecurityProjectPojo);
                }
            }
        } else {
            for (DashboardSecurityProjectPojo dashboardSecurityProjectPojo : list) {
                dashboardSecurityProjectDao.insertDashboardSecurityProject(dashboardSecurityProjectPojo);
            }
        }
    }
}
