package com.wlhse.controller;

import com.wlhse.dto.QHSEComSysEleStatusDto;
import com.wlhse.dto.inDto.ReportManageAnalysisInDto;
import com.wlhse.service.ReportManageAnalysisService;
import com.wlhse.util.GetCurrentUserIdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.wlhse.service.QHSEComSysEleStatusService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("QHSEComSysEleStatus")
@RequestMapping("/api/v3")
public class QHSEComSysEleStatusController {
    @Resource
    private QHSEComSysEleStatusService qhseComSysEleStatusService;

    @Resource
    private GetCurrentUserIdUtil getCurrentUserIdUtil;

    @Resource
    private ReportManageAnalysisInDto reportManageAnalysisInDto;

    @Resource
    private ReportManageAnalysisService reportManageAnalysisService;

    @RequestMapping(value = "/addComSysEleStatus", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addComStatus(@RequestBody(required = false) QHSEComSysEleStatusDto qhseComSysEleStatusDto)
    {
        return qhseComSysEleStatusService.addQHSEComSysEleStatus(qhseComSysEleStatusDto);
    }


    @RequestMapping(value = "/updateComSysEleStatus", method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateComSysEleStatus(@RequestBody(required = false) QHSEComSysEleStatusDto qhseComSysEleStatusDto)
    {
        return qhseComSysEleStatusService.updateQHSEComSysEleStatus(qhseComSysEleStatusDto);
    }


    @RequestMapping(value = "/deleteComSysEleStatus/{ID}", method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deleteComSysEleStatus(@PathVariable(value = "ID") Integer ID)
    {
        return qhseComSysEleStatusService.deleteQHSEComSysEleStatus(ID);
    }


    @RequestMapping(value = "/querryComSysEleStatus", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String querryComSysEleStatus(@ModelAttribute QHSEComSysEleStatusDto qhseComSysEleStatusDto, HttpServletRequest request)
    {
        String companyCode = qhseComSysEleStatusDto.getCompanyCode();
        if(companyCode == null || companyCode.equals("")){
            Integer employeeID = getCurrentUserIdUtil.getUserId(request);
            reportManageAnalysisInDto.setEmployeeID(getCurrentUserIdUtil.getUserId(request));
            qhseComSysEleStatusDto.setCompanyCode(reportManageAnalysisService.getCompanyCodeByEmployeeID(employeeID));
        }
        return qhseComSysEleStatusService.querryQHSEComSysEleStatus(qhseComSysEleStatusDto);
    }
}
