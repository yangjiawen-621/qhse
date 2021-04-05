package com.wlhse.dao;

import com.wlhse.dto.ReportEmployeeRecordDto;

import java.util.List;

public interface ReportEmployeeRecordDao {
//    public Integer addRecord(ReportEmployeeRecordDto record);
    public Integer delectRecords(String reportCode);
    public Integer addRecords(List<ReportEmployeeRecordDto> records);
    public Integer excelAddRecords(List<ReportEmployeeRecordDto> records);
}
