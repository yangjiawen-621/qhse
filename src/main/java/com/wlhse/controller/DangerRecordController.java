package com.wlhse.controller;


import com.wlhse.dto.DangerRecordDto;
import com.wlhse.service.DangerRecordService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController("DangerRecordController")
@RequestMapping("/api/v3")
public class DangerRecordController {

    @Resource
    private DangerRecordService dangerRecordService;

    @RequestMapping(value = "/add_dangerrecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addDangerRecord(@RequestBody(required = false) DangerRecordDto dangerRecordDto, HttpServletRequest request) {

        return dangerRecordService.addDangerRecord(dangerRecordDto, request);
    }

    @RequestMapping(value = "/delete_dangerrecord", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R deleteDangerRecord(@ModelAttribute DangerRecordDto dangerRecordDto) {

        return dangerRecordService.deleteDangerRecord(dangerRecordDto);
    }

    @RequestMapping(value = "/update_dangerrecord/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R updateDangerRecord(@PathVariable int id, @RequestBody(required = false) DangerRecordDto dangerRecordDto) {
        dangerRecordDto.setId(id);
        return dangerRecordService.updateDangerRecord(dangerRecordDto);
    }

    @RequestMapping(value = "/query_dangerrecord/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryDangerRecordById(@PathVariable int id) {

        return dangerRecordService.queryDangerRecordById(id);
    }

    @RequestMapping(value = "/query_dangerrecord", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryDangerRecord(@ModelAttribute DangerRecordDto dangerRecordDto) {
        R r = null;
        try {
            r = dangerRecordService.queryDangerRecord(dangerRecordDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    @RequestMapping(value = "/problemverification/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R problemVerification(@PathVariable int id, @RequestBody(required = false) DangerRecordDto dangerRecordDto) {
        dangerRecordDto.setId(id);
        return dangerRecordService.problemVerification(dangerRecordDto);
    }

    @RequestMapping(value = "/queryMostDangerRecord", method = RequestMethod.GET)
    public R queryMostDangerRecord(@RequestParam(value = "startDate", defaultValue = "") String startDate,
                                   @RequestParam(value = "endDate", defaultValue = "") String endDate) {
        return dangerRecordService.queryMostDangerRecord(startDate, endDate);
    }
}
