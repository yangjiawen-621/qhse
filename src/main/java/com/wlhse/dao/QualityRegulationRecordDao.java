package com.wlhse.dao;


import com.wlhse.dto.QualityRegulationRecordDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityRegulationRecordDao {

    //新增违章记录
    int addRegulationRecord(QualityRegulationRecordDto regulationRecordDto);
    //删除违章记录
    int deleteRegulationRecord(QualityRegulationRecordDto regulationRecordDto);
    //修改违章记录
    int updateRegulationRecord(QualityRegulationRecordDto regulationRecordDto);
    //按ID查询违章记录
    List<QualityRegulationRecordDto> queryRegulationRecordById(@Param("id") Integer id);
    //条件查询违章记录
    int queryTotal(QualityRegulationRecordDto regulationRecordDto);
    List<QualityRegulationRecordDto> queryRegulationRecord(QualityRegulationRecordDto regulationRecordDto);
}
