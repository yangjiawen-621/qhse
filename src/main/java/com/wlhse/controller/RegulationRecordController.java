package com.wlhse.controller;


import com.wlhse.dto.RegulationRecordDto;
import com.wlhse.service.RegulationRecordService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("RegulationRecordController")
@RequestMapping("/api/v3")
public class RegulationRecordController {

    @Resource
    private RegulationRecordService regulationRecordService;

    @RequestMapping(value = "/add_regulationrecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addDangerRecord(@RequestBody(required = false) RegulationRecordDto regulationRecordDto) {
        return regulationRecordService.addRegulationRecord(regulationRecordDto);
    }

    @RequestMapping(value = "/delete_regulationrecord", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R deleteRegulationRecord(@ModelAttribute RegulationRecordDto regulationRecordDto) {

        return regulationRecordService.deleteRegulationRecord(regulationRecordDto);
    }

    @RequestMapping(value = "/update_regulationrecord/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R updateRegulationRecord(@PathVariable int id, @RequestBody(required = false) RegulationRecordDto regulationRecordDto) {

        regulationRecordDto.setId(id);
        return regulationRecordService.updateRegulationRecord(regulationRecordDto);
    }

    @RequestMapping(value = "/query_regulationrecord/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryRegulationRecordById(@PathVariable int id) {

        return regulationRecordService.queryRegulationRecordById(id);
    }

    @RequestMapping(value = "/query_regulationrecord", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryDangerRecord(@ModelAttribute RegulationRecordDto regulationRecordDto) {

        return regulationRecordService.queryRegulationRecord(regulationRecordDto);
    }

    @RequestMapping(value = "/queryMostRegulationRecord", method = RequestMethod.GET)
    public R queryMostRegulationRecord(@RequestParam(value = "startDate", defaultValue = "") String startDate,
                                       @RequestParam(value = "endDate", defaultValue = "") String endDate) {
        return regulationRecordService.queryMostRegulationRecord(startDate, endDate);
    }

}
