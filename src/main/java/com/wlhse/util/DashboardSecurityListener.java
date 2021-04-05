package com.wlhse.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wlhse.dao.DashboardSecurityDao;
import com.wlhse.entity.DashboardSecurityPojo;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
public class DashboardSecurityListener extends AnalysisEventListener<DashboardSecurityPojo> {

    private DashboardSecurityDao dashboardSecurityDao;

    private ArrayList<DashboardSecurityPojo> list
            = new ArrayList<>();

    public DashboardSecurityListener(DashboardSecurityDao dashboardSecurityDao) {
        this.dashboardSecurityDao = dashboardSecurityDao;
    }

    @Override
    public void invoke(DashboardSecurityPojo dashboardSecurityPojo, AnalysisContext analysisContext) {
        list.add(dashboardSecurityPojo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 判断数据库中是否存在数据
        // 不存在：直接插入
        // 存在：直接更新
        int countDashboardSecurity = dashboardSecurityDao.countDashboardSecurity();
        if (countDashboardSecurity != 0) {
            for (DashboardSecurityPojo dashboardSecurityPojo : list) {
                // 数据完整性判断
                if (dashboardSecurityPojo.getQuarterDangerIndex() != null &&
                        dashboardSecurityPojo.getQuarterRegulationIndex() != null &&
                        dashboardSecurityPojo.getQuarterEventIndex() != null &&
                        dashboardSecurityPojo.getActualFinishDanger() != null &&
                        dashboardSecurityPojo.getActualFinishRegulation() != null &&
                        dashboardSecurityPojo.getActualFinishEvent() != null) {
                    dashboardSecurityDao.updateDashboardSecurity(dashboardSecurityPojo);
                }
            }
        } else {
            for (DashboardSecurityPojo dashboardSecurityPojo : list) {
                dashboardSecurityDao.insertDashboardSecurity(dashboardSecurityPojo);
            }
        }
    }
}
