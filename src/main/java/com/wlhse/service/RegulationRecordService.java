package com.wlhse.service;

import com.wlhse.dto.RegulationRecordDto;
import com.wlhse.util.R;

public interface RegulationRecordService {

    //增
    R addRegulationRecord(RegulationRecordDto regulationRecordDto);
    //删
    R deleteRegulationRecord(RegulationRecordDto regulationRecordDto);
    //改
    R updateRegulationRecord (RegulationRecordDto regulationRecordDto);
    //按ID查询
    R queryRegulationRecordById(Integer id);
    //查询
    R queryRegulationRecord(RegulationRecordDto regulationRecordDto);
    /**
     * 查询违章项最多的公司
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return R
     */
    R queryMostRegulationRecord(String startDate, String endDate);
}
