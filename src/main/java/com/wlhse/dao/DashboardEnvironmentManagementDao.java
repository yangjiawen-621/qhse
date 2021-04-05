package com.wlhse.dao;

import com.wlhse.entity.DashboardEnvironmentManagement;
import com.wlhse.entity.DashboardEnvironmentManagementDto;
import com.wlhse.entity.DashboardQueryParam;

import java.util.Date;
import java.util.List;

/**
 * @Author tobing
 * @Date 2020/11/23 19:54
 * @Description
 */
public interface DashboardEnvironmentManagementDao {

    List<DashboardEnvironmentManagement> queryDashboardEnvironmentManagement(DashboardEnvironmentManagement dashboardEnvironmentManagement);

    int countDashboardEnvironmentManagement();

    void updateDashboardEnvironmentManagement(DashboardEnvironmentManagement dashboardEnvironmentManagement);

    void insertDashboardEnvironmentManagement(DashboardEnvironmentManagement dashboardEnvironmentManagement);

    List<DashboardEnvironmentManagementDto> queryMonthDEM(DashboardQueryParam dashboardQueryParam);

    List<DashboardEnvironmentManagementDto> queryYearDEM(DashboardQueryParam dashboardQueryParam);

    List<DashboardEnvironmentManagementDto> queryQuarterDEM(DashboardQueryParam dashboardQueryParam);
}
