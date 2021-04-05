package com.wlhse.service;

import com.wlhse.dto.inDto.ReportManageAnalysisInDto;


public interface ReportManageAnalysisService {
    //获取费用管理
    String getReportCostManageAnalysisOutDtoList(ReportManageAnalysisInDto reportManageAnalysisInDto);
    //获取印章分析数据
    String getSealStatisticsAnalysis(ReportManageAnalysisInDto reportManageAnalysisInDto);
    //获取工作量统计数据
    String getWorkloadStatisticsAnalysis(ReportManageAnalysisInDto reportManageAnalysisInDto);

    //根据登录人id查询所在公司
    String getCompanyCodeByEmployeeID(Integer employeeID);
}
