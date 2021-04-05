package com.wlhse.service;

import com.wlhse.dto.ReportCodeRule;

import java.util.List;

public interface ReportCodeRuleService {
    public String querryRule(String type);
    public String addRule(ReportCodeRule rule);
    public String updateRule(ReportCodeRule rule);
    public String deleteRule(Integer Id);
    public String querryRules(ReportCodeRule rule, int id);
    public String querryReportType(int userId);
    public String querryRuleByID(Integer id);
}
