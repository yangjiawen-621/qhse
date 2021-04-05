package com.wlhse.service;

import com.wlhse.entity.MesSumData;
import com.wlhse.util.R;

public interface MesSumDataService {
    R getAllSumDate();

    R getMesCheckDataByDate(String date);

    R deleteSumData(int id);

    R deleteByDate(String date);

    R updateData(MesSumData mesSumData);

    R getSumDataById(int id);

    R getSumDataInTimePeriod(String startTime, String endTime);



}
