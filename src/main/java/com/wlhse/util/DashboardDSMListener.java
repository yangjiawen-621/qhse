package com.wlhse.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wlhse.dao.DashboardDao;
import com.wlhse.entity.DashboardScheduleManagement;
import com.wlhse.entity.DashboardScheduleManagement;

import java.util.ArrayList;

public class DashboardDSMListener extends AnalysisEventListener<DashboardScheduleManagement> {

    private DashboardDao dashboardDao;

    private ArrayList<DashboardScheduleManagement> list
            = new ArrayList<>();

    public DashboardDSMListener(DashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    @Override
    public void invoke(DashboardScheduleManagement qualityManagement, AnalysisContext analysisContext) {
        list.add(qualityManagement);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 判断数据库中是否存在数据
        // 不存在：直接插入
        // 存在：直接更新
        int countDSM = dashboardDao.countDSM();
        if (countDSM != 0) {
            for (DashboardScheduleManagement dashboardScheduleManagement : list) {
                // 数据完整性判断
                if (dashboardScheduleManagement.getPlanNum() != null &&
                        dashboardScheduleManagement.getFirstDraftFinishNum() != null &&
                        dashboardScheduleManagement.getReviewPassNum() != null &&
                        dashboardScheduleManagement.getStandardReleaseNum() != null) {
                    dashboardDao.updateDSM(dashboardScheduleManagement);
                }
            }
        } else {
            for (DashboardScheduleManagement dashboardScheduleManagement : list) {
                dashboardDao.insertDSM(dashboardScheduleManagement);
            }
        }

    }
}
