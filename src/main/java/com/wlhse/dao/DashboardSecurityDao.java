package com.wlhse.dao;

import com.wlhse.entity.DashboardSecurityPojo;
import com.wlhse.entity.DashboardSecurityProjectCount;
import com.wlhse.entity.DashboardSecurityProjectPojo;
import com.wlhse.util.R;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author tobing
 * @Date 2020/11/21 21:06
 * @Description
 */
@Repository
public interface DashboardSecurityDao {
    List<DashboardSecurityPojo> queryDashboardSecurity(DashboardSecurityPojo dashboardSecurityPojo);

    int countDashboardSecurity();

    void updateDashboardSecurity(DashboardSecurityPojo dashboardSecurityPojo);

    void insertDashboardSecurity(DashboardSecurityPojo dashboardSecurityPojo);


}
