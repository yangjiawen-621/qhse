package com.wlhse.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wlhse.dao.MonitorPlanDetailDao;
import com.wlhse.dto.MonitorPlanDetail;

import java.util.ArrayList;
import java.util.List;

public class MonitorDataListener extends AnalysisEventListener<MonitorPlanDetail> {

    /*设置每隔10条记录就入库*/
    private static final int BATCH_COUNT=10;
    private MonitorPlanDetailDao monitorPlanDetailDao;
    //监控计划ID
    private int monitorPlanId;
    List<MonitorPlanDetail> list=new ArrayList<>();

    public MonitorDataListener(MonitorPlanDetailDao monitorPlanDetailDao,int monitorPlanId) {
        this.monitorPlanDetailDao = monitorPlanDetailDao;
        this.monitorPlanId=monitorPlanId;
    }

    @Override
    public void invoke(MonitorPlanDetail monitorPlanDetail, AnalysisContext analysisContext) {
        monitorPlanDetail.setMonitorPlanID(monitorPlanId);
        //将读取到的记录保存至list中
        list.add(monitorPlanDetail);
        //list中保存的记录达到10条，进行入库操作，入库完成后清理list
        if (list.size()>=BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //确保没有遗留的数据未保存
        saveData();
    }

    private void saveData(){
        monitorPlanDetailDao.batchInsertNewRecord(list);
    }
}
