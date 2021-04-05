package com.wlhse.controller;

import com.wlhse.dto.inDto.RegulationInDto;
import com.wlhse.entity.RegulationPojo;
import com.wlhse.service.RegulationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("/RegulationController")
@RequestMapping("/api/v3")
public class RegulationController {

    @Resource
    private RegulationService regulationService;

    @RequestMapping(value = "/regulation", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String addRegulationPojo(@RequestBody(required = false) RegulationPojo regulationPojo) {
        return regulationService.addRegulationPojo(regulationPojo);
    }

    @RequestMapping(value = "/regulation/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getRegulationPojoById(@PathVariable("id") Integer id) {
        return regulationService.getRegulationPojoById(id);
    }

    @RequestMapping(value = "/regulation", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getRegulationPojoByCondition(@ModelAttribute RegulationInDto regulationInDto) {
        return regulationService.getRegulationPojoByCondition(regulationInDto);
    }

    @RequestMapping(value = "/regulation", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateRegulationPojo(@RequestBody(required = false) RegulationPojo regulationPojo) {
        return regulationService.updateRegulationPojo(regulationPojo);
    }

    @RequestMapping(value = "/regulation/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteTaskObject(@PathVariable("id") Integer id) {
        return regulationService.deleteRegulation(id);
    }

//  获取文件名字和code
    @RequestMapping(value = "/regulation_name_code", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String returnRegulationNameAndCode(@ModelAttribute RegulationInDto regulationInDto) {
        return regulationService.listRegulationNameAndCode(regulationInDto);
    }
}
