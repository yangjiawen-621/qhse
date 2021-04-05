package com.wlhse.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;


import com.wlhse.dao.CompanyDao;
import com.wlhse.dao.MesSumDataDao;

import com.wlhse.entity.MesSumData;

import java.util.ArrayList;
import java.util.List;

public class MesDataListener extends AnalysisEventListener<MesSumData> {


    /*设置每隔10条记录就入库*/
    private static final int BATCH_COUNT=10;
    private MesSumDataDao mesSumDataDao;
    private CompanyDao companyDao;
    List<MesSumData> list=new ArrayList<>();

    public MesDataListener(MesSumDataDao mesSumDataDao,CompanyDao companyDao) {
        this.mesSumDataDao = mesSumDataDao;
        this.companyDao=companyDao;

    }

    @Override
    public void invoke(MesSumData mesSumData, AnalysisContext analysisContext) {
        //将读取到的记录保存至list中
        //配备记录仪数量/日报数量
        if (mesSumData.getDayReportNum()!=0)
            mesSumData.setCoverageRate((float)mesSumData.getRecordDeviceNum()/(float)mesSumData.getDayReportNum()*100);
        else
            mesSumData.setCoverageRate(0);
        //利用率=出库数/配备记录仪数
        if (mesSumData.getRecordDeviceNum()!=0)
            mesSumData.setAvailableRate((float)mesSumData.getOutStockNum()/(float)mesSumData.getRecordDeviceNum()*100);
        else
            mesSumData.setAvailableRate(0);
        //使用率=备用数量/出库数量
        if (mesSumData.getOutStockNum()!=0)
            mesSumData.setUseRate((float)mesSumData.getPowerOnNum()/(float)mesSumData.getOutStockNum()*100);
        else
            mesSumData.setUseRate(0);
        mesSumData.setCompanyCode(companyDao.queryByCompanyName(mesSumData.getCompanyName()));
        list.add(mesSumData);
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
        mesSumDataDao.batchInsertNewSumData(list);
    }
}
