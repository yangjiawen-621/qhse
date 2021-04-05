package com.wlhse.controller;


import com.wlhse.service.FactorDepartmentService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("FactorDepartmentController")
@RequestMapping("/api/v3")
public class FactorDepartmentController {
    @Resource
    private FactorDepartmentService factorDepartmentService;


    @RequestMapping(value = "/factor_department", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryFactorDepartment() {
        return factorDepartmentService.queryFactorDepartment();
    }

    @RequestMapping(value = "/factor_department_bycode/{factorCode}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R getFactorDepartment(@PathVariable String factorCode) {
        return factorDepartmentService.getFactorDepartment(factorCode);
    }

}
