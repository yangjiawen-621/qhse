package com.wlhse.controller;

import com.wlhse.entity.DataDictPojo;
import com.wlhse.dto.getDto.DataDictGetDto;
import com.wlhse.service.DataDictService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("DataDictController")
@RequestMapping("/api/v3")
//数据字典
public class DataDictController {

    @Resource
    private DataDictService dataDictService;

    @Resource
    private DataDictPojo dataDictPojo;


    @RequestMapping(value = "/dictionary", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getChirdren(@ModelAttribute DataDictGetDto dataDictGetDto) {
        return dataDictService.getChirdren(dataDictGetDto.getName());
    }

    @RequestMapping(value = "/dictionary", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String addDataDict(@RequestBody(required = false) DataDictPojo dictPojo) {
        return dataDictService.insertSonDataDict(dictPojo);
    }

    @RequestMapping(value = "/dictionary", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteDataDict(@RequestBody(required = false) DataDictPojo dictPojo) {
        return dataDictService.deleteDataDict(dictPojo);
    }

    @RequestMapping(value = "/dictionary/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteDataDictById(@PathVariable("id") int id) {
        return dataDictService.deleteDataDictById(id);
    }

    @RequestMapping(value = "/dictionary/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateDataDictById(@PathVariable("id") int id, @RequestBody DataDictPojo dictPojo) {
        dictPojo.setId(id);
        return dataDictService.updateDataDict(dictPojo);
    }

    @RequestMapping(value = "/dictionary/tree", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getDictionaryTree() {
        return dataDictService.getDataDictTree();
    }

}
