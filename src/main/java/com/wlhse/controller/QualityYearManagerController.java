package com.wlhse.controller;


import com.wlhse.dto.QualityYearManagerDtoWithEmployeeId;
import com.wlhse.dto.inDto.QualityYearElementsDto;

import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.service.QualityYearManagerService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v3")
public class QualityYearManagerController {
    @Resource
    QualityYearManagerService qualityYearManagerService;


    @RequestMapping(value = "/quality_show_companyYearManager", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R showCompanyYearManager(@ModelAttribute QualityYearManagerDtoWithEmployeeId companyYearManagerDto, HttpServletRequest request) {
        return qualityYearManagerService.queryAll(companyYearManagerDto, request);
    }

    @RequestMapping(value = "/quality_delete_companyYearManager/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R delete(@PathVariable("id") Integer id) {
        return qualityYearManagerService.deleteALL(id);
    }

    @RequestMapping(value = "/quality_companyYearManager", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addCompanyYearManager(@RequestBody QualityYearManagerDtoWithEmployeeId companyYearManagerDto, HttpServletRequest request) {
        return qualityYearManagerService.addCompanyYearManager(companyYearManagerDto, request);
    }

    //获取当前检查表已经配置的要素
    @RequestMapping(value = "/quality_queryYearElement", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R qualityQueryYearElement(@ModelAttribute QualityYearElementsDto yearElementsDto){
        return qualityYearManagerService.queryYearElements(yearElementsDto);
    }

    @RequestMapping(value = "/quality_addYearElement", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R qualityAddYearElement(@RequestBody(required = false) QualityYearElementsDto yearElementsDto){
        return qualityYearManagerService.addYearElement(yearElementsDto);
    }
}