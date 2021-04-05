package com.wlhse.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wlhse.dao.DashboardEnvironmentManagementDao;
import com.wlhse.entity.DashboardEnvironmentManagement;

import java.util.ArrayList;

/**
 * @author tobing
 */
public class DashboardEnvironmentManagementListener extends AnalysisEventListener<DashboardEnvironmentManagement> {

    private DashboardEnvironmentManagementDao dashboardEnvironmentManagementDao;

    private ArrayList<DashboardEnvironmentManagement> list
            = new ArrayList<>();

    public DashboardEnvironmentManagementListener(DashboardEnvironmentManagementDao dashboardEnvironmentManagementDao) {
        this.dashboardEnvironmentManagementDao = dashboardEnvironmentManagementDao;
    }

    @Override
    public void invoke(DashboardEnvironmentManagement dashboardEnvironmentManagement, AnalysisContext analysisContext) {
        list.add(dashboardEnvironmentManagement);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 判断数据库中是否存在数据
        // 不存在：直接插入
        // 存在：直接更新
        int countDashboardSecurityProject = dashboardEnvironmentManagementDao.countDashboardEnvironmentManagement();
        if (countDashboardSecurityProject != 0) {
            for (DashboardEnvironmentManagement dashboardEnvironmentManagement : list) {
                // 数据完整性判断
                if (dashboardEnvironmentManagement.getCompanyCode() != null &&
                        dashboardEnvironmentManagement.getCompanyName() != null &&
                        dashboardEnvironmentManagement.getElectricity() != null &&
                        dashboardEnvironmentManagement.getGas() != null &&
                        dashboardEnvironmentManagement.getGasoline() != null &&
                        dashboardEnvironmentManagement.getSewageTransfer() != null &&
                        dashboardEnvironmentManagement.getSewageVolume() != null &&
                        dashboardEnvironmentManagement.getDiesel() != null) {
                    // TODO 将更新改为插入
                    // dashboardEnvironmentManagementDao.updateDashboardEnvironmentManagement(dashboardEnvironmentManagement);
                    dashboardEnvironmentManagementDao.insertDashboardEnvironmentManagement(dashboardEnvironmentManagement);


                }
            }
        } else {
            for (DashboardEnvironmentManagement dashboardEnvironmentManagement : list) {
                dashboardEnvironmentManagementDao.insertDashboardEnvironmentManagement(dashboardEnvironmentManagement);
            }
        }
    }
}
