package com.wlhse.controller;

import com.wlhse.cache.JedisUtils;
import com.wlhse.dto.EmployeeManagementDto;
import com.wlhse.dto.QHSEQualityFillDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.EmployeeManagementService;
import com.wlhse.service.QHSEQualityFillService;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController("QHSEQualityFillController")
@RequestMapping("/api/v3")
public class QHSEQualityFillController {
    @Resource
    private QHSEQualityFillService qhseQualityFillService;

    @Resource
    private JedisUtils jedisUtils;

    @Resource
    private EmployeeManagementService employeeManagementService;

    @RequestMapping(value = "/addQHSEReport", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String addQHSEReport(@RequestBody(required = false) QHSEQualityFillDto qhseQualityFillDto){//建表
        if(qhseQualityFillDto.getYear()==null||qhseQualityFillDto.getCompanyCode()==null){
            throw new WLHSException("请传公司编码和年份");
        }
        return qhseQualityFillService.addQHSEReport(qhseQualityFillDto);
    }

    @RequestMapping(value = "/querryQHSEReportElements", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String querryQHSEReportElements(@ModelAttribute QHSEQualityFillDto qhseQualityFillDto, HttpServletRequest request){//填表
        if(qhseQualityFillDto.getCompanyCode()==null||"".equals(qhseQualityFillDto.getCompanyCode())){//如果不传companyCode，默认为登录人的公司
            Jedis jedis=jedisUtils.getJedis();
            Integer id=Integer.parseInt(jedis.hgetAll(request.getHeader("Authorization")).get("employeeId"));
            EmployeeManagementDto employeeManagementDto=employeeManagementService.querryRoleById(id);
            qhseQualityFillDto.setCompanyCode(employeeManagementDto.getCompanyCode());
        }
        if(qhseQualityFillDto.getYear()==null||"".equals(qhseQualityFillDto.getYear())){//如果不传year，默认为当前时间
            Date date=new Date();
            int year=date.getYear()+1900;
            System.out.print(year);
            qhseQualityFillDto.setYear(String.valueOf(year));
        }
        return qhseQualityFillService.querryQHSEReportElements(qhseQualityFillDto);
    }

    @RequestMapping(value = "/updateQHSEReportElements", method = RequestMethod.PUT, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String updateQHSEReportElements(@RequestBody(required = false) QHSEQualityFillDto qhseQualityFillDto){//打分

        return qhseQualityFillService.updateQHSEReportElements(qhseQualityFillDto);
    }
}
