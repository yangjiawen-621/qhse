package com.wlhse.dao;

import com.wlhse.dto.ReportCodeRule;

import java.util.List;

public interface ReportCodeRuleDao {
    public String querryRule(String type);
    public Integer addRule(ReportCodeRule rule);
    public Integer updateRule(ReportCodeRule rule);
    public Integer deleteRule(Integer Id);
    public List<ReportCodeRule> querryRules(ReportCodeRule rule);
    public Integer countRules(ReportCodeRule rule);
    public List<String> querryReportType();
    public ReportCodeRule querryRuleByID(Integer id);
}
