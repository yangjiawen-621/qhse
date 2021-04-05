package com.wlhse.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wlhse.dao.*;
import com.wlhse.dto.EmployeeManagementDto;
import com.wlhse.dto.ReportDto;
import com.wlhse.dto.ReportEmployeeRecordDto;
import com.wlhse.dto.SampleDto;
import com.wlhse.entity.CompanyPojo;
import com.wlhse.entity.CountReportsPojo;
import com.wlhse.entity.ReportList;
import com.wlhse.entity.ReportPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.ReportService;
import com.wlhse.util.GenerateReportCode;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.LinkedList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    ReportDao reportDao;

    @Resource
    SampleDao sampleDao;

    @Resource
    CompanyDao companyDao;

    @Resource
    GenerateReportCode generateReportCode;

    @Resource
    EmployeeManagementDao employeeManagementDao;

    @Resource
    ReportEmployeeRecordDao recordDao;

    @Override
    @Transactional
    public String addReports(List<ReportDto> reportDtos,int size) {

        String planDate=reportDtos.get(0).getReportPlanDate();
        String reportType=reportDtos.get(0).getReportType();
        String companyCode=reportDtos.get(0).getCompanyCode();
        List<String> reportCodes=generateReportCode.generator1(companyCode,planDate,reportType,size);//生成报告编码

        int count=0;
        for(ReportDto reportDto : reportDtos){
            reportDto.setReportCode(reportCodes.get(count++));
            if(reportDao.querryReportByReportCode(reportDto)>0){
                throw new WLHSException("报告已存在");
            }
            reportDao.addReport(reportDto);
        }
        return NR.r();
    }

    @Override
    public String addReportNumbSection(List<ReportDto> reportDtos,Integer start,Integer end) {

        String planDate=reportDtos.get(0).getReportPlanDate();
        String reportType=reportDtos.get(0).getReportType();
        String companyCode=reportDtos.get(0).getCompanyCode();
        List<String> reportCodes=generateReportCode.generator2(companyCode,planDate,reportType,start,end);//生成报告编码

        int count=0,flag=0;
        String Code="";
        for(ReportDto reportDto : reportDtos){
            reportDto.setReportCode(reportCodes.get(count++));
            if(reportDao.querryReportByReportCode(reportDto)>0){
                flag=1;
                Code+=reportDto.getReportCode()+"  ";
            }else {
                reportDao.addReport(reportDto);
            }
        }
        if(flag==1){
            throw new WLHSException("报告已存在,报告编号:"+Code);
        }
        return NR.r();
    }

    @Override
    public String reportList(ReportDto report) {
        PageHelper.startPage(report.getPageIdx(),report.getPageSize());
        try{
            String datas=NR.r(reportDao.reportList(report),reportDao.countReportList(report),(report.getPageIdx()));
            return datas;
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("查询失败");
        }
    }

    @Override
    public String deleteReport(Integer id) {
        if(reportDao.deleteReport(id)>0){
            return NR.r();
        }else{
            throw new WLHSException("删除失败");
        }
    }

    @Override
    public String updateReport(ReportDto report) {
        Integer id=reportDao.ByReportCodeGetID(report);
        if(id!=null){//没有更改code，更改code
            if(report.getReportID().equals(id)){//没有更改code
                if(reportDao.updateReport(report)>0){
                    return NR.r();
                }else{
                    throw new WLHSException("修改失败");
                }
            }else{//更改code，code重复
                throw new WLHSException("修改失败,报告编号不可重复");
            }
        }else{//更改code，且code不重复
            if(reportDao.updateReport(report)>0){
                return NR.r();
            }else{
                throw new WLHSException("修改失败");
            }
        }
    }

    @Override
    @Transactional
    public String compeletReport(ReportDto report,List<SampleDto> sampleDtos) {
        //删除之前样品和记录
        String  reportCode=reportDao.querryCompleteReport(report.getReportID()).getReportCode();
        recordDao.delectRecords(reportCode);
        sampleDao.deleteSample(report.getReportID());

        String[] approverIDs=report.getApproverIDs().split(",");
        String[] auditorIDs=report.getAuditorIDs().split(",");
        String approverNames="";
        String auditorNames="";
        List<ReportEmployeeRecordDto> approverRecords=new LinkedList<>();
        List<ReportEmployeeRecordDto> auditorRecords=new LinkedList<>();
        for(String id:approverIDs){
            EmployeeManagementDto employ=employeeManagementDao.queryById(Integer.parseInt(id));
            approverNames+=employ.getName()+",";
            ReportEmployeeRecordDto record=new ReportEmployeeRecordDto();
            record.setEmployeeId(Integer.parseInt(id));
            record.setReportId(report.getReportID());
            record.setSealDate(report.getSealDate());
            record.setStatus("审批");
            approverRecords.add(record);
        }
        if(recordDao.addRecords(approverRecords)<0){
            throw new WLHSException("提交报告失败");
        }
        approverNames=approverNames.substring(0,approverNames.length()-1);
        report.setApproverNames(approverNames);
        for(String id:auditorIDs){
            auditorNames+=employeeManagementDao.queryById(Integer.parseInt(id)).getName()+",";
            ReportEmployeeRecordDto record=new ReportEmployeeRecordDto();
            record.setEmployeeId(Integer.parseInt(id));
            record.setReportId(report.getReportID());
            record.setSealDate(report.getSealDate());
            record.setStatus("审核");
            auditorRecords.add(record);
        }
        if(recordDao.addRecords(auditorRecords)<0){
            throw new WLHSException("提交报告失败");
        }

        auditorNames=auditorNames.substring(0,auditorNames.length()-1);
        report.setAuditorNames(auditorNames);
        Integer reNo=reportDao.compeletReport(report);
        for(SampleDto sampleDto:sampleDtos){
            try{
                if(sampleDao.addSample(sampleDto)<0){
                    throw new WLHSException("提交报告失败");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(reNo>0){
            return NR.r();
        }else{
            throw new WLHSException("提交报告失败");
        }
    }

    @Override
    public String querryCompleteReport(Integer id) {
        try{
            ReportPojo report=reportDao.querryCompleteReport(id);
            if(report==null){
                String data=NR.r("查无此人");
                return data;
            }else{
                String data=NR.r(report);
                return data;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("查询报告失败");
        }
    }

    @Override
    public String countReports(ReportDto reportDto) {
        try{
            List<CompanyPojo> companys=null;
            //查询公司和一级子公司信息
            if(reportDto.getCompanyCode()==null||"".equals(reportDto.getCompanyCode())){
                companys=companyDao.subCompanysCodeByPersonId(reportDto.getPersonID());
            }else{
                companys=companyDao.subCompanysCodeByCompanyCode(reportDto.getCompanyCode());
            }
            List<CountReportsPojo> countlist=new LinkedList<>();
            for(CompanyPojo company:companys){//查询每个子公司报告情况
                CountReportsPojo count=new CountReportsPojo();
                count.setCompanyCode(company.getCompanyCode());
                count.setCompanyName(company.getName());
                count.setIncompelet(0);
                count.setCompelet(0);
                reportDto.setCompanyCode(company.getCompanyCode());
                count.setIncompelet(reportDao.countIncompleteReport(reportDto));
                count.setCompelet(reportDao.countCompleteReport(reportDto));
                count.setTotal(count.getCompelet()+count.getIncompelet());
                Format format=new DecimalFormat("0.00");
                String precent="";
                if(count.getTotal()!=0){
                    precent=format.format(((double)count.getCompelet()/count.getTotal())*100);
                    String[] data=precent.split("\\.");
                    count.setCompletePrecent(data[0]+"%");
                }else{
                    precent="0";
                    count.setCompletePrecent("0%");
                }
                countlist.add(count);
            }
            return NR.r(countlist);
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("报告统计失败");
        }
    }

    @Override
    public String incompeletReportCodes(Integer id) {
        try{
            EmployeeManagementDto employee=employeeManagementDao.queryById(id);
            List<ReportPojo> reports=reportDao.incompeletReport(employee.getCompanyCode());
            return NR.r(reports);
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("查询报告编号失败");
        }

    }

    @Override
    public List<String> querryReportCode(String reportCode) {//模糊查询报告编码
        return reportDao.querryReportCode(reportCode);
    }
}
