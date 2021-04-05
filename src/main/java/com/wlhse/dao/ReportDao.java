package com.wlhse.dao;

import com.wlhse.dto.ExcelUploadReportDto;
import com.wlhse.dto.ReportDto;
import com.wlhse.entity.CompanyPojo;
import com.wlhse.entity.ReportPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDao {
    public Integer addReport(ReportDto reportDto);
    public String selectReport();
    public Integer querryReportByReportCode(ReportDto reportDto);
    public List<ReportPojo> reportList(ReportDto reportDto);
    public Integer deleteReport(Integer id);
    public Integer updateReport(ReportDto reportDto);
    public Integer ByReportCodeGetID(ReportDto reportDto);
    public Integer compeletReport(ReportDto reportDto);
    public ReportPojo querryCompleteReport(Integer id);
    public Integer countCompleteReport(ReportDto reportDto);
    public Integer countIncompleteReport(ReportDto reportDto);
    public Integer countReportList(ReportDto reportDto);//根据条件查询报告数量
    public List<ReportPojo> incompeletReport(String companyCode);//查询某个公司未完成的报告
    public List<String> querryReportCode(String reportCode);//模糊查询报告编码
    String querryCode(String companyCode);//查询公司编号代码
    public Integer excelUploadReports(List<ExcelUploadReportDto> reports);//excel批量导入
}
