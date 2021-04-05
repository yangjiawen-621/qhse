package com.wlhse.dao;

import com.wlhse.dto.outDto.MonitorInputCheckRecordOutDto;
import com.wlhse.entity.MonitorInputCheckRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MonitorInputCheckDao {


    int insertNewInputRecord(MonitorInputCheckRecord monitorInputCheckRecord);

    //更改录入信息、检查均可调用该方法
    int updateInputRecord(MonitorInputCheckRecord monitorInputCheckRecord);

    //清除当日记录
    int deleteInputRecord(int detailId);

    List<MonitorInputCheckRecordOutDto> getRecordDetail(int detailId);

    //获取备用状态的录入记录
    List<MonitorInputCheckRecordOutDto> getCheckMonitor(int planId,String date);

    MonitorInputCheckRecordOutDto getRecordDetailByDate(int detailId,String date);

    List<MonitorInputCheckRecordOutDto> getNeedToCheckPlanDetails(int planId,String date);

    Set<MonitorInputCheckRecordOutDto> getDayReport(int planId, String date);

    List<MonitorInputCheckRecordOutDto> getInputAndCheckDetail(Integer detailId);

    int deleteInputInfo(int checkRecordId);

    int getItemNum(int planId);

}
