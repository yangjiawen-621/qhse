package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.EmployeeManagementDao;
import com.wlhse.dao.ReportCodeRuleDao;
import com.wlhse.dto.ReportCodeRule;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.ReportCodeRuleService;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportCodeRuleServiceImpl implements ReportCodeRuleService {
    @Resource
    ReportCodeRuleDao reportCodeRuleDao;

    @Resource
    private EmployeeManagementDao employeeManagementDao;

    @Override
    public String querryRule(String ReportType) {
        String Code="";
        try{
            Code=reportCodeRuleDao.querryRule(ReportType);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Code;
    }

    @Override
    public String addRule(ReportCodeRule rule) {
        try{
            Integer count=reportCodeRuleDao.addRule(rule);
            if(count>0){
                return NR.r();
            }else{
                throw new WLHSException("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("添加失败");
        }
    }

    @Override
    public String updateRule(ReportCodeRule rule) {
        try{
            Integer count=reportCodeRuleDao.updateRule(rule);
            if(count>0){
                return NR.r();
            }else{
                throw new WLHSException("更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("更新失败");
        }

    }

    @Override
    public String deleteRule(Integer Id) {
        try{
            Integer count=reportCodeRuleDao.deleteRule(Id);
            if(count>0){
                return NR.r();
            }else{
                throw new WLHSException("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("删除失败");
        }
    }

    @Override
    public String querryRules(ReportCodeRule rule, int id) {
        String companyCode = rule.getCompanyCode();
        if(companyCode == null || companyCode.equals("")) {
            companyCode = employeeManagementDao.queryCompanyCodeByEmpId(id);
            rule.setCompanyCode(companyCode);
        }
        PageHelper.startPage(rule.getPageIdx(),rule.getPageSize());
        try{
            List<ReportCodeRule> list=reportCodeRuleDao.querryRules(rule);
            return NR.r(list,reportCodeRuleDao.countRules(rule),rule.getPageIdx());
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("查询失败");
        }
    }

    @Override
    public String querryReportType(int id) {
        try{
            return NR.r(reportCodeRuleDao.querryReportType());
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("查询失败");
        }
    }

    @Override
    public String querryRuleByID(Integer id) {
        try{
            ReportCodeRule rule=reportCodeRuleDao.querryRuleByID(id);
            if (rule!=null){
                return NR.r(rule);
            }
            throw new WLHSException("查无此信息");
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("查询失败");
        }
    }
}
