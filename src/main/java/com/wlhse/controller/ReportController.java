package com.wlhse.controller;

import com.alibaba.fastjson.JSONObject;
import com.wlhse.cache.JedisUtils;
import com.wlhse.dao.ReportCodeRuleDao;
import com.wlhse.dao.ReportDao;
import com.wlhse.dto.ReportDto;
import com.wlhse.dto.ReportList;
import com.wlhse.dto.SampleDto;
import com.wlhse.service.ReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@RestController("ReportController")
@RequestMapping("/api/v3")
public class ReportController {
    @Resource
    ReportService reportService;

    @Resource
    private JedisUtils jedisUtils;

    @Resource
    ReportCodeRuleDao reportCodeRuleDao;

    @Resource
    ReportDao reportDao;

    //批量增加报告  reportDtos:[{},{}]
//    @RequestMapping(value = "/addReports", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    public String addReportNumbs(@RequestBody(required = false) ReportList reports){
//        return reportService.addReports(reports.getReportDtos());
//    }
    public ReportList generotor(Integer start,Integer end,ReportDto reportDto){
        ReportList reports=new ReportList();
        List list=new LinkedList<ReportDto>();
        ReportDto reportDto1=null;
        for(int i=start;i<=end;i++){//生成count分报表
            reportDto1=new ReportDto();
            reportDto1.setCompanyCode(reportDto.getCompanyCode());
            reportDto1.setReportType(reportDto.getReportType());
            reportDto1.setReportCheckPersonID(reportDto.getReportCheckPersonID());
            reportDto1.setReportPlanDate(reportDto.getReportPlanDate());
            list.add(reportDto1);
        }
        reports.setReportDtos(list);
        return reports;
    }

    //增加报告  按份数
    @RequestMapping(value = "/addReport", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String addReportNumb(@RequestBody(required = false) ReportDto reportDto){
        ReportList reports=generotor(1,reportDto.getCount(),reportDto);
        return reportService.addReports(reports.getReportDtos(),reportDto.getCount());
    }

    //增加报告 按编号区间
    @RequestMapping(value = "/addReportNumbSection", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String addReportNumbSection(@RequestBody(required = false) ReportDto reportDto){
        ReportList reports=generotor(reportDto.getA(),reportDto.getB(),reportDto);
        return reportService.addReportNumbSection(reports.getReportDtos(),reportDto.getA(),reportDto.getB());
    }

    //查询报告列表  state=1已完成 state=2未完成 state=0所有
    @RequestMapping(value = "/reportList", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String reportList(@ModelAttribute ReportDto reportDto,HttpServletRequest request){
        //只能查询登录人的公司和其子公司的信息
        Jedis jedis=jedisUtils.getJedis();
        Integer id=Integer.parseInt(jedis.hgetAll(request.getHeader("Authorization")).get("employeeId"));
        reportDto.setPersonID(id);
        reportDto.setStart((reportDto.getPageIdx()-1)*reportDto.getPageSize());
        return reportService.reportList(reportDto);
    }
    //删除报告 reportID
    @RequestMapping(value = "/deleteReport/{report_id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteReport(@PathVariable("report_id") int report_id){
        return reportService.deleteReport(report_id);
    }

    @RequestMapping(value = "/updateReport", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateReport(@RequestBody(required = false) ReportDto reportDto){
        return reportService.updateReport(reportDto);
    }
    //完成报告所有内容
    @RequestMapping(value = "/completeReport", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String completeReport(@RequestBody(required = false) ReportDto reportDto){
        List<SampleDto> samples=reportDto.getSampleDtoList();
        for(SampleDto sample:samples){
            sample.setReportID(reportDto.getReportID());
        }
        return reportService.compeletReport(reportDto,samples);
    }
    //根据reportID查询完成的报告
    @RequestMapping(value = "/querryCompleteReport/{report_id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String querryCompleteReport(@PathVariable("report_id") int report_id){
        return reportService.querryCompleteReport(report_id);
    }

    //统计报告完成进度
    @RequestMapping(value = "/countReports", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String countReports(@ModelAttribute ReportDto reportDto, HttpServletRequest request){
        //只能查询登录人的公司和其子公司的信息
        Jedis jedis=jedisUtils.getJedis();
        reportDto.setPersonID(Integer.parseInt(jedis.hgetAll(request.getHeader("Authorization")).get("employeeId")));
        return reportService.countReports(reportDto);
    }

    //查询未完成报告编码
    @RequestMapping(value = "/incompeletReportCodes", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String incompeletReportCodes(HttpServletRequest request){
        Jedis jedis=jedisUtils.getJedis();
        Integer id=Integer.parseInt(jedis.hgetAll(request.getHeader("Authorization")).get("employeeId"));
        return reportService.incompeletReportCodes(id);
    }


//    @RequestMapping(value = "/test1234", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    public String test123(){
//        System.out.println(123123);
//        return null;
//    }
}
