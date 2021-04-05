package com.wlhse.util;

import com.wlhse.dao.CompanyDao;
import com.wlhse.dao.ReportCodeRuleDao;
import com.wlhse.dao.ReportDao;
import com.wlhse.dto.ReportDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Component
public class GenerateReportCode {
    @Resource
    ReportCodeRuleDao reportCodeRuleDao;

    @Resource
    ReportDao reportDao;

    @Resource
    CompanyDao companyDao;

    public List<String> generator1(String companyCode,String ReportPlanDate,String ReportType,int count){
        String type=codeprefix(companyCode,ReportPlanDate,ReportType);

        List<String> reportCodes=reportDao.querryReportCode('%'+type+'%');//查出前缀相同的reportCode

        //找出目前最大的流水号
        int max=0;
        for(String reportCode:reportCodes){
            String[] spilt=reportCode.split("-");
            int index=Integer.parseInt(spilt[spilt.length-1]);
            if(index>max){
                max=index;
            }
        }

        List<String> newReportCodes=new LinkedList<>();
        //生成报告编码
        for(int i=0;i<count;i++){
            max++;
            newReportCodes.add(type+"-"+max);
        }
        return newReportCodes;
    }

    public List<String> generator2(String companyCode,String ReportPlanDate,String ReportType,Integer start,Integer end){
        String type=codeprefix(companyCode,ReportPlanDate,ReportType);

        List<String> reportCodes=reportDao.querryReportCode('%'+type+'%');//查出前缀相同的reportCode


        List<String> newReportCodes=new LinkedList<>();
        //生成报告编码
        for(int i=start;i<=end;i++){
            newReportCodes.add(type+"-"+i);
        }
        return newReportCodes;
    }

    //生成编号前缀
    public String codeprefix(String companyCode,String ReportPlanDate,String ReportType){
        String Code=companyDao.querryCode(companyCode);
        String type="BG";
        type=Code+"-"+type;
        String businessCode=reportCodeRuleDao.querryRule(ReportType);//报告类型对应的业务编号
        type+=businessCode;
        String[] year=ReportPlanDate.split("-");
        type+="-"+year[0];
        return type;
    }
}
