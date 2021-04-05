package com.wlhse.service.impl;

import com.wlhse.dao.MesSumDataDao;
import com.wlhse.entity.MesSumData;
import com.wlhse.service.MesSumDataService;
import com.wlhse.util.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MesSumDataServiceImp implements MesSumDataService {

    @Resource
    MesSumDataDao mesSumDataDao;

    @Override
    public R getAllSumDate() {
        R r=new R();
        r.put("data",mesSumDataDao.getAllSumDate());
        return r;
    }

    @Override
    public R getMesCheckDataByDate(String date) {
        R r=new R();
        r.put("data",mesSumDataDao.getSumDataByDate(date));
        return r;
    }

    @Override
    public R deleteSumData(int id) {
        mesSumDataDao.deleteSumData(id);
        return R.ok();
    }

    @Override
    public R deleteByDate(String date) {
        mesSumDataDao.deleteByDate(date);
        return R.ok();
    }

    @Override
    public R updateData(MesSumData mesSumData) {
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
        mesSumDataDao.updateMesData(mesSumData);
        return R.ok();
    }

    @Override
    public R getSumDataById(int id) {
        R r=new R();
        r.put("data",mesSumDataDao.getMesDataById(id));
        return r;
    }

    @Override
    public R getSumDataInTimePeriod(String startTime, String endTime) {
        R r=new R();
        r.put("data",mesSumDataDao.getSumDataInTimePeriod(startTime,endTime));
        return r;
    }


}
