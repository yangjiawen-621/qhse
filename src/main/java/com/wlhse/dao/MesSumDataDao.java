package com.wlhse.dao;

import com.wlhse.dto.inDto.MesSearchCondition;
import com.wlhse.entity.MesSumData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesSumDataDao {

    int batchInsertNewSumData(List<MesSumData> mesSumDataList);

    List<String> getAllSumDate();

    List<MesSumData> getSumDataByDate(String date);

    int deleteSumData(int id);

    int deleteByDate(String date);

    int updateMesData(MesSumData mesSumData);

    MesSumData getMesDataById(int id);

    List<MesSumData> getSumDataInTimePeriod(String startTime, String endTime);

    int getPlanDeviceNum(String companyName);


    List<Double> getUseRate(String companyName);

    List<Double> getCoverageRate(String companyName);

    List<Double> getAvaiRate(String companyName);
}
