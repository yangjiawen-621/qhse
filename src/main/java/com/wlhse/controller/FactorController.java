package com.wlhse.controller;


import com.wlhse.dto.inDto.FactorDto;
import com.wlhse.service.FactorDepartmentService;
import com.wlhse.service.FactorService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("FactorController")
@RequestMapping("/api/v3")
public class FactorController {
    @Resource
    private FactorService factorService;

    @RequestMapping(value = "/factor", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryFactor(@ModelAttribute FactorDto factorDto) {
        return factorService.queryFactor(factorDto);
    }

}
