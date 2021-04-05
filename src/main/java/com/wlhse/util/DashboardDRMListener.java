package com.wlhse.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wlhse.dao.DashboardDao;
import com.wlhse.entity.DashboardRecorderManagement;
import com.wlhse.entity.DashboardRecorderManagement;

import java.util.ArrayList;

public class DashboardDRMListener extends AnalysisEventListener<DashboardRecorderManagement> {

    private DashboardDao dashboardDao;

    private ArrayList<DashboardRecorderManagement> list
            = new ArrayList<>();

    public DashboardDRMListener(DashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    @Override
    public void invoke(DashboardRecorderManagement qualityManagement, AnalysisContext analysisContext) {
        list.add(qualityManagement);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 判断数据库中是否存在数据
        // 不存在：直接插入
        // 存在：直接更新
        int countDRM = dashboardDao.countDRM();
        if (countDRM != 0) {

            for (DashboardRecorderManagement dashboardRecorderManagement : list) {
                // 数据完整性判断
                if (dashboardRecorderManagement.getWeeklyCollectNum() != null &&
                        dashboardRecorderManagement.getWeeklyNormalNum() != null &&
                        dashboardRecorderManagement.getWeeklySupervisionNum() != null) {
                    dashboardDao.updateDRM(dashboardRecorderManagement);
                }
            }
        } else {
            for (DashboardRecorderManagement dashboardRecorderManagement : list) {
                dashboardDao.insertDRM(dashboardRecorderManagement);
            }
        }
    }
}
