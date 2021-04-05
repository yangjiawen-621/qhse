package com.wlhse.controller;

import com.wlhse.entity.ReportUnitPricePojo;
import com.wlhse.service.ReportUnitPriceService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController("ReportUnitPriceController")
@RequestMapping("/api/v3")
public class ReportUnitPriceController {

    @Resource
    private ReportUnitPriceService reportUnitPriceService;

    //**************新增*****************
    @RequestMapping(value = "/unit_price_add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public R newAddUnitPrice(@RequestBody(required = false) ReportUnitPricePojo reportUnitPricePojo) {
        return reportUnitPriceService.newUnitPrice(reportUnitPricePojo);
    }

    //**************查询列表*****************
    @RequestMapping(value = "/unit_price_select", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String selectUnitPrice(@ModelAttribute ReportUnitPricePojo reportUnitPricePojo) {
        return reportUnitPriceService.queryUnitPrice(reportUnitPricePojo);
    }

    //**************查询详情*****************
    @RequestMapping(value = "/unit_price_detail/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String selectSingleUnitPrice(@PathVariable("id") int id) {
        return reportUnitPriceService.queryUnitPriceById(id);
    }

    //**************修改*****************
    @RequestMapping(value = "/unit_price_update/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateUnitPrice(@RequestBody(required = false) ReportUnitPricePojo reportUnitPricePojo, @PathVariable("id") int id) {
        reportUnitPricePojo.setUnitPriceID(id);
        return reportUnitPriceService.updateUnitPrice(reportUnitPricePojo);
    }

    //**************删除*****************
    @RequestMapping(value = "/unit_price_delete/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteUnitPrice(@PathVariable("id") int id) {
        return reportUnitPriceService.deleteUnitPrice(id);
    }
}
