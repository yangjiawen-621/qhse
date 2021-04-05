package com.wlhse.service;

import com.wlhse.dto.ReportDto;
import com.wlhse.dto.SampleDto;
import com.wlhse.entity.CountReportsPojo;
import com.wlhse.entity.ReportPojo;

import java.util.List;

public interface ReportService {
    public String addReports(List<ReportDto> reportDtos,int size);
    public String addReportNumbSection(List<ReportDto> reportDtos,Integer start,Integer end);
    public String reportList(ReportDto report);
    public String deleteReport(Integer id);
    public String updateReport(ReportDto report);
    public String compeletReport(ReportDto report,List<SampleDto> samples );
    public String querryCompleteReport(Integer id);
    public String countReports(ReportDto reportDto);//查询统计报告完成情况
    public String incompeletReportCodes(Integer id);//查询登录人所在公司未完成的报告的编码
    public List<String> querryReportCode(String reportCode);
}
