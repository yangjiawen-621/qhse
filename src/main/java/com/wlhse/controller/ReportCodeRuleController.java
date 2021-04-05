package com.wlhse.controller;

import com.wlhse.dto.ReportCodeRule;
import com.wlhse.service.ReportCodeRuleService;
import com.wlhse.util.GetCurrentUserIdUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController("ReportCodeRuleController")
@RequestMapping("/api/v3")
public class ReportCodeRuleController {

    @Resource
    private ReportCodeRuleService reportCodeRuleService;

    @Resource
    private GetCurrentUserIdUtil getCurrentUserIdUtil;

    @RequestMapping(value = "/querryRules", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public String querryRule(@ModelAttribute ReportCodeRule reportCodeRule, HttpServletRequest request){
        int id = getCurrentUserIdUtil.getUserId(request);
        return reportCodeRuleService.querryRules(reportCodeRule, id);
    }

    @RequestMapping(value = "/querryRuleByID/{id}", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public String querryRuleByID(@PathVariable("id") int id){
        return reportCodeRuleService.querryRuleByID(id);
    }

    @RequestMapping(value = "/addRule", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public String addRule(@RequestBody(required = false) ReportCodeRule reportCodeRule){
        return reportCodeRuleService.addRule(reportCodeRule);
    }

    @RequestMapping(value = "/deleteRule/{id}", method = RequestMethod.DELETE, produces = {"application/json;charset=utf-8"})
    public String deleteRule(@PathVariable("id") int id){
        return reportCodeRuleService.deleteRule(id);
    }

    @RequestMapping(value = "/updateRule/{id}", method = RequestMethod.PUT, produces = {"application/json;charset=utf-8"})
    public String updateRule(@RequestBody(required = false) ReportCodeRule reportCodeRule, @PathVariable("id") int id){
        reportCodeRule.setReportCodeRuleID(id);
        return reportCodeRuleService.updateRule(reportCodeRule);
    }

    @RequestMapping(value = "/querryReportType", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public String querryReportType( HttpServletRequest request){
        int id = getCurrentUserIdUtil.getUserId(request);
        return reportCodeRuleService.querryReportType(id);
    }
}
