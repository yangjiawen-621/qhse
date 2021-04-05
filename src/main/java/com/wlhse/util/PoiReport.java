package com.wlhse.util;

import com.wlhse.dao.EmployeeManagementDao;
import com.wlhse.dao.ReportDao;
import com.wlhse.dao.ReportEmployeeRecordDao;
import com.wlhse.dao.SampleDao;
import com.wlhse.dto.EmployeeManagementDto;
import com.wlhse.dto.ExcelUploadReportDto;
import com.wlhse.dto.ReportDto;
import com.wlhse.dto.ReportEmployeeRecordDto;
import com.wlhse.exception.WLHSException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Component
public class PoiReport {
    @Resource
    ReportDao reportDao;
    @Resource
    SampleDao sampleDao;
    @Resource
    ReportEmployeeRecordDao recordDao;
    @Resource
    EmployeeManagementDao employeeManagementDao;

    @Transactional
    public String poiReports(List<ExcelUploadReportDto> reports){
        String duplic="";
        Iterator<ExcelUploadReportDto> iterator=reports.iterator();
        while(iterator.hasNext()){//排除编号重复的报告
            ExcelUploadReportDto report=iterator.next();
            ReportDto reportDto=new ReportDto();
            reportDto.setReportCode(report.getReportCode());
            if(reportDao.querryReportByReportCode(reportDto)>0){
                iterator.remove();
                duplic+=report.getReportCode()+" ";
            }else{//补全AuditorNames  ApproverNames
                if(report.getAuditorNames()==null||"".equals(report.getAuditorNames())){
                    report.setAuditorNames(splitNames(report.getAuditorIDs()));
                }
                if(report.getApproverNames()==null||"".equals(report.getApproverNames())){
                    report.setApproverNames(splitNames(report.getApproverIDs()));
                }
            }
        }
        if(reports.size()==0){
            return duplic;
        }else{
            reportDao.excelUploadReports(reports);
            for(ExcelUploadReportDto report:reports){
                if(report.getSampleDtoList().size()>0){//插入样品
                    sampleDao.excelUploadSamples(report.getSampleDtoList());
                }
                insertRecord(report);//插入记录
            }
            return duplic;
        }
    }

    public void insertRecord(ExcelUploadReportDto report){
        String[] approverIDs=report.getApproverIDs().split(",");
        String[] auditorIDs=report.getAuditorIDs().split(",");
        List<ReportEmployeeRecordDto> approverRecords=new LinkedList<>();
        List<ReportEmployeeRecordDto> auditorRecords=new LinkedList<>();
        if(approverIDs!=null&&approverIDs.length>0){
            for(String id:approverIDs){
                ReportEmployeeRecordDto record=new ReportEmployeeRecordDto();
                record.setReportCode(report.getReportCode());
                record.setEmployeeId(Integer.parseInt(id));
                record.setStatus("审批");
                approverRecords.add(record);
            }
            if(recordDao.excelAddRecords(approverRecords)<0){
                throw new WLHSException("提交报告失败");
            }
        }

        if(auditorIDs!=null&&auditorIDs.length>0){
            for(String id:auditorIDs){
                ReportEmployeeRecordDto record=new ReportEmployeeRecordDto();
                record.setReportCode(report.getReportCode());
                record.setEmployeeId(Integer.parseInt(id));
                record.setSealDate(report.getSealDate());
                record.setStatus("审核");
                auditorRecords.add(record);
            }
            if(recordDao.excelAddRecords(auditorRecords)<0){
                throw new WLHSException("提交报告失败");
            }
        }

    }

    public String splitNames(String IDs){
        String[] ids=IDs.split(",");
        String names="";
        for(String id:ids){
            EmployeeManagementDto employ=employeeManagementDao.queryById(Integer.parseInt(id));
            names+=employ.getName()+",";
        }
        names=names.substring(0,names.length()-1);
        return names;
    }

    public static boolean isDuplicReportCode(List<ExcelUploadReportDto> reports){
        int flag=0;
        for(ExcelUploadReportDto report:reports){
            flag=0;
            for(ExcelUploadReportDto rep:reports){
                if(report.getReportCode().equals(rep.getReportCode())){
                    flag++;
                }
            }
            if(flag>1){
                return true;
            }
        }
        return false;
    }
}
