package com.wlhse.dao;

import com.wlhse.dto.inDto.ReportManageAnalysisInDto;
import com.wlhse.dto.outDto.ReportCostManageOutDto;
import com.wlhse.dto.outDto.ReportSealStatisticsOutDto;
import com.wlhse.dto.outDto.ReportWorkloadStatisticsOutDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReportManageAnalysisDao {
    //获取费用管理
    List<ReportCostManageOutDto> getReportCostManageAnalysis(ReportManageAnalysisInDto reportManageAnalysisInDto);
    //获取印章统计
    List<ReportSealStatisticsOutDto> getSealStatisticsAnalysisOutDto(ReportManageAnalysisInDto reportManageAnalysisInDto);
    //获取工作量统计
    List<ReportWorkloadStatisticsOutDto> getWorkloadStatisticsAnalysisOutDto(ReportManageAnalysisInDto reportManageAnalysisInDto);
    // 根据登录人ID查询公司
    String getCompanyCodeByEmployeeID(@Param("employeeID") Integer employeeID);
}
