package com.wlhse.controller;

import com.wlhse.dto.outDto.CompanyOutDto;
import com.wlhse.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController("CompanyController")
@RequestMapping("/api/v3")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    @RequestMapping(value = "/company", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getCompanyParentAndSon(@ModelAttribute CompanyOutDto companyOutDto) {
        return companyService.getCompanyOutDto(companyOutDto.getSonName());
    }

}
