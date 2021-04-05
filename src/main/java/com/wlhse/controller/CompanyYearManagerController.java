package com.wlhse.controller;


import com.wlhse.dto.CompanyYearManagerDtoWithEmployeeId;
import com.wlhse.dto.inDto.CompanyYearManagerDto;
import com.wlhse.dto.inDto.ElementReviewDto;
import com.wlhse.dto.outDto.QhseEvidenceAttatchDto;
import com.wlhse.service.CompanyYearManagerService;
import com.wlhse.service.ElementReviewService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController("CompanyYearManagerController")
@RequestMapping("/api/v3")
public class CompanyYearManagerController {
    @Resource
    private CompanyYearManagerService companyYearManagerService;
    //显示管理表信息
    @RequestMapping(value = "/show_companyYearManager", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R show(@ModelAttribute CompanyYearManagerDtoWithEmployeeId companyYearManagerDto, HttpServletRequest request) {
        return companyYearManagerService.queryAll(companyYearManagerDto,request);
    }
    //状态修改
    @RequestMapping(value = "/approval_companyYearManager/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R updateCompanyYearManager(@PathVariable int id) {
        return companyYearManagerService.updateStatus(id);
    }
    //删除记录
    @RequestMapping(value = "/delete_companyYearManager/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R deleteCompanyYearManager(@PathVariable int id) {
        return companyYearManagerService.deleteALL(id);
    }
    //新增检查表
    @RequestMapping(value = "/companyYearManager", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addCompanyYearManager(@RequestBody CompanyYearManagerDtoWithEmployeeId companyYearManagerDto,HttpServletRequest request) {
        return companyYearManagerService.addCompanyYearManager(companyYearManagerDto,request);
    }

}
