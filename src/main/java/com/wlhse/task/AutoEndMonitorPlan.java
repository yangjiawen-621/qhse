package com.wlhse.task;


import com.wlhse.dao.MonitorPlanDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("autoEndMonitorPlan")
public class AutoEndMonitorPlan {


    @Resource
    MonitorPlanDao monitorPlanDao;

    //每天凌晨12:00触发
    @Scheduled(cron = "0 0 00 * * ?")
    public void endTask(){
        //自动结束远程监控计划
        monitorPlanDao.endMonitorPlan();
    }
}
