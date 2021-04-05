package com.wlhse.controller;


import com.wlhse.service.QualityFactorDepartmentService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author tobing QQ:652916578
 * 【质量】
 */
@RestController()
@RequestMapping("/api/v3")
public class QualityFactorDepartmentController {
    @Resource
    private QualityFactorDepartmentService qualityFactorDepartmentService;


    // 简单测试通过
    @RequestMapping(value = "/quality_factor_department", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryFactorDepartment() {
        return qualityFactorDepartmentService.queryFactorDepartment();
    }

    // 简单测试通过
    @RequestMapping(value = "/quality_factor_department_bycode/{factorCode}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R getFactorDepartment(@PathVariable String factorCode) {
        return qualityFactorDepartmentService.getFactorDepartment(factorCode);
    }

}
