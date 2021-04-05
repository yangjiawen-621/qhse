package com.wlhse.dao;

import com.wlhse.dto.MonitorPlanDetail;
import com.wlhse.entity.MesSumData;
import com.wlhse.entity.RemoteData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorPlanDetailDao {

    int batchInsertNewRecord(List<MonitorPlanDetail> monitorPlanDetails);

    int updateDetail(MonitorPlanDetail monitorPlanDetail);

    int deletePlanDetail(int id);

    List<MonitorPlanDetail> getDetailByPlanId(int planId);

    int  createNewPlanDetail(MonitorPlanDetail monitorPlanDetail);

    int endDetail(int detailId);

    List<MesSumData> getMesDataByDate(String date,List<Integer> detailId);

    List<MesSumData> getMesDataByDates(String date,String date1);

    List<Integer> getDetailIdByDate(String date);

    int batchInsertNewRecordFromRemote(List<RemoteData> remoteData);
}
