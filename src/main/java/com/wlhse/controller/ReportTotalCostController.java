package com.wlhse.controller;

import com.wlhse.entity.ReportTotalCostPojo;
import com.wlhse.service.ReportTotalCostService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController("ReportTotalCostController")
@RequestMapping("/api/v3")
public class ReportTotalCostController {

    @Resource
    private ReportTotalCostService reportTotalCostService;

    //**************问题录入*****************
    @RequestMapping(value = "/total_cost_add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public R newAddTotalCost(@RequestBody(required = false) ReportTotalCostPojo reportTotalCostPojo) {
        return reportTotalCostService.newTotalCost(reportTotalCostPojo);
    }

    //**************问题列表查询+分页*****************
    @RequestMapping(value = "/total_cost_select", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String selectTotalCost(@ModelAttribute ReportTotalCostPojo reportTotalCostPojo) {
        return reportTotalCostService.queryTotalCost(reportTotalCostPojo);
    }

    //**************修改*****************
    @RequestMapping(value = "/total_cost_update/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateTotalCost(@RequestBody(required = false) ReportTotalCostPojo reportTotalCostPojo, @PathVariable("id") int id) {
        reportTotalCostPojo.setCostID(id);
        return reportTotalCostService.updateTotalCost(reportTotalCostPojo);
    }
}
