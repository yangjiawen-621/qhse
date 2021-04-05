package com.wlhse.controller;

import com.wlhse.service.FactorSourceService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("FactorSourceController")
@RequestMapping("/api/v3")
public class FactorSourceController {
    @Resource
    private FactorSourceService factorSourceService;

    @RequestMapping(value = "/factorsource_getall", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public String getAll()
    {
        return factorSourceService.getAll();
    }

    @RequestMapping(value = "/factor_source_bycode/{factorCode}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R getFactorSource(@PathVariable String factorCode) {
        return factorSourceService.getFactorSource(factorCode);
    }

}
