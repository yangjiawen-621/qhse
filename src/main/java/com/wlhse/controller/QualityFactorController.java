package com.wlhse.controller;


import com.wlhse.dto.inDto.FactorDto;
import com.wlhse.dto.inDto.QualityFactorDto;
import com.wlhse.service.FactorService;
import com.wlhse.service.QualityFactorService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tobing  QQ:652916578
 * 【质量】查询类别
 */
@RestController()
@RequestMapping("/api/v3")
public class QualityFactorController {
    @Resource
    private QualityFactorService qualityFactorService;

    @RequestMapping(value = "/quality_factor", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryFactor(@ModelAttribute QualityFactorDto factorDto) {
        return qualityFactorService.queryFactor(factorDto);
    }

}
