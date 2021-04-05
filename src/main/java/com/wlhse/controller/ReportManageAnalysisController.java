package com.wlhse.controller;

import com.wlhse.dto.inDto.ReportManageAnalysisInDto;
import com.wlhse.service.ReportManageAnalysisService;
import com.wlhse.util.GetCurrentUserIdUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController("ReportCostManageController")
@RequestMapping("/api/v3")
public class ReportManageAnalysisController {

    @Resource
    private ReportManageAnalysisService reportManageAnalysisService;

    @Resource
    private GetCurrentUserIdUtil getCurrentUserIdUtil;

//    费用详情
    @RequestMapping(value = "/cost_detail_analysis", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryReportCostManageAnalysisInDtoList(@ModelAttribute ReportManageAnalysisInDto reportManageAnalysisInDto, HttpServletRequest request) {
        String companyCode = reportManageAnalysisInDto.getCompanyCode();
        if(companyCode == null || companyCode.equals("")){
            Integer employeeID = getCurrentUserIdUtil.getUserId(request);
            reportManageAnalysisInDto.setEmployeeID(getCurrentUserIdUtil.getUserId(request));
            reportManageAnalysisInDto.setCompanyCode(reportManageAnalysisService.getCompanyCodeByEmployeeID(employeeID));
        }
        return reportManageAnalysisService.getReportCostManageAnalysisOutDtoList(reportManageAnalysisInDto);
    }

//    印章统计分析
    @RequestMapping(value = "/company_seal_statistics", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String querySealStatistics(@ModelAttribute ReportManageAnalysisInDto reportManageAnalysisInDto, HttpServletRequest request) {
        String companyCode = reportManageAnalysisInDto.getCompanyCode();
        if(companyCode == null || companyCode.equals("")){
            Integer employeeID = getCurrentUserIdUtil.getUserId(request);
            reportManageAnalysisInDto.setEmployeeID(getCurrentUserIdUtil.getUserId(request));
            reportManageAnalysisInDto.setCompanyCode(reportManageAnalysisService.getCompanyCodeByEmployeeID(employeeID));
        }
        return reportManageAnalysisService.getSealStatisticsAnalysis(reportManageAnalysisInDto);
    }

    //工作量统计
    @RequestMapping(value = "/workload_statistics", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryWorkloadStatistics(@ModelAttribute ReportManageAnalysisInDto reportManageAnalysisInDto, HttpServletRequest request) {
        String companyCode = reportManageAnalysisInDto.getCompanyCode();
        if(companyCode == null || companyCode.equals("")){
            Integer employeeID = getCurrentUserIdUtil.getUserId(request);
            reportManageAnalysisInDto.setEmployeeID(getCurrentUserIdUtil.getUserId(request));
            reportManageAnalysisInDto.setCompanyCode(reportManageAnalysisService.getCompanyCodeByEmployeeID(employeeID));
        }
        return reportManageAnalysisService.getWorkloadStatisticsAnalysis(reportManageAnalysisInDto);
    }
}
