package com.wlhse.dao;

import com.wlhse.entity.DashboardQueryParam;
import com.wlhse.entity.DashboardSecurityMillionDto;
import com.wlhse.entity.DashboardSecurityMillionPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author tobing
 * @Date 2020/11/21 21:06
 * @Description
 */
@Repository
public interface DashboardSecurityMillionDao {
    // 废弃
    // List<DashboardSecurityMillionPojo> queryDashboardSecurityMillion(DashboardSecurityMillionPojo millionPojo);

    int countDashboardSecurityMillion();

    void updateDashboardSecurityMillion(DashboardSecurityMillionPojo dashboardSecurityMillionPojo);

    void insertDashboardSecurityMillion(DashboardSecurityMillionPojo dashboardSecurityMillionPojo);

    List<DashboardSecurityMillionDto> queryMonthDSM(DashboardQueryParam monthParam);

    List<DashboardSecurityMillionDto> queryYearDSM(DashboardQueryParam monthParam);
}
