package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.ReportManageAnalysisDao;
import com.wlhse.dto.inDto.ReportManageAnalysisInDto;
import com.wlhse.dto.outDto.*;
import com.wlhse.service.ReportManageAnalysisService;
import com.wlhse.util.state_code.NR;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportManageAnalysisServiceImpl implements ReportManageAnalysisService {

    @Resource
    private ReportManageAnalysisDao reportManageAnalysisDao;

    @Value("${RESOURCES_URL}")
    private String url;

    @Override
    public String getReportCostManageAnalysisOutDtoList(ReportManageAnalysisInDto reportManageAnalysisInDto) {
        List<ReportCostManageOutDto> costManageList = reportManageAnalysisDao.getReportCostManageAnalysis(reportManageAnalysisInDto);
        return NR.r(costManageList);
    }

    @Override
    public String getSealStatisticsAnalysis(ReportManageAnalysisInDto reportManageAnalysisInDto) {
        return NR.r(reportManageAnalysisDao.getSealStatisticsAnalysisOutDto(reportManageAnalysisInDto));
    }

    @Override
    public String getWorkloadStatisticsAnalysis(ReportManageAnalysisInDto reportManageAnalysisInDto) {
        List<ReportWorkloadStatisticsOutDto> list1=reportManageAnalysisDao.getWorkloadStatisticsAnalysisOutDto(reportManageAnalysisInDto);
        List<ReportWorkloadStatisticsOutDto> list2 = new ArrayList<>();
        String pc = reportManageAnalysisInDto.getPersonCategory();
        if (pc != null){
            for (ReportWorkloadStatisticsOutDto r : list1){
                if (r.getPersonCategory().equals(pc)){
                    list2.add(r);
                }
            }
        }else {
            list2 = list1;
        }
        int total = list2.size();
        PageHelper.startPage(reportManageAnalysisInDto.getPageIdx(), reportManageAnalysisInDto.getPageSize());
        return NR.r(list2, total,reportManageAnalysisInDto.getPageIdx());
    }

    @Override
    public String getCompanyCodeByEmployeeID(Integer employeeID) {
        String companyCode = reportManageAnalysisDao.getCompanyCodeByEmployeeID(employeeID);
        return companyCode;
    }
}
