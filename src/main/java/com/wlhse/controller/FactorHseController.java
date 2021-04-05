package com.wlhse.controller;


import com.wlhse.service.FactorHseService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("FactorHseController")
@RequestMapping("/api/v3")
public class FactorHseController {

    @Resource
    private FactorHseService factorHseService;


    @RequestMapping(value = "/factorhse_getall", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public String getAll()
    {
        return factorHseService.getAll();
    }

    @RequestMapping(value = "/factor_hse_bycode/{factorCode}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R getFactorHse(@PathVariable String factorCode) {
        return factorHseService.getFactorHse(factorCode);
    }

}
