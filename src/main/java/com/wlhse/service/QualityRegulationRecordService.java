package com.wlhse.service;

import com.wlhse.dto.QualityRegulationRecordDto;
import com.wlhse.util.R;

public interface QualityRegulationRecordService {
    //增
    R addRegulationRecord(QualityRegulationRecordDto regulationRecordDto);
    //删
    R deleteRegulationRecord(QualityRegulationRecordDto regulationRecordDto);
    //改
    R updateRegulationRecord (QualityRegulationRecordDto regulationRecordDto);
    //按ID查询
    String queryRegulationRecordById(Integer id);
    //查询
    String queryRegulationRecord(QualityRegulationRecordDto regulationRecordDto);
}
