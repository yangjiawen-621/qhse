package com.wlhse.controller;


import com.wlhse.dto.QualityRegulationRecordDto;
import com.wlhse.service.QualityRegulationRecordService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author tobing QQ:652916578
 * 【质量】违章模块
 */
@RestController()
@RequestMapping("/api/v3")
public class QualityRegulationRecordController {

    @Resource
    private QualityRegulationRecordService qualityRegulationRecordService;

    // 新增违章记录
    // 简单测试通过
    @RequestMapping(value = "/add_quality_regulationrecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addDangerRecord(@RequestBody(required = false) QualityRegulationRecordDto regulationRecordDto) {
        return qualityRegulationRecordService.addRegulationRecord(regulationRecordDto);
    }

    // 删除质量违章记录
    // 简单测试通过
    @RequestMapping(value = "/delete_quality_regulationrecord", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R deleteRegulationRecord(@ModelAttribute QualityRegulationRecordDto regulationRecordDto) {
        return qualityRegulationRecordService.deleteRegulationRecord(regulationRecordDto);
    }

    // 更新质量违章记录
    // 简单测试通过
    @RequestMapping(value = "/update_quality_regulationrecord/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R updateRegulationRecord(@PathVariable int id, @RequestBody(required = false) QualityRegulationRecordDto regulationRecordDto) {
        regulationRecordDto.setId(id);
        return qualityRegulationRecordService.updateRegulationRecord(regulationRecordDto);
    }

    // 根据id查询资料违章记录
    // 简单测试通过
    @RequestMapping(value = "/query_quality_regulationrecord/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryRegulationRecordById(@PathVariable int id) {
        return qualityRegulationRecordService.queryRegulationRecordById(id);
    }

    // 根据条件查询违章记录
    // 简单测试通过
    @RequestMapping(value = "/query_quality_regulationrecord", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryDangerRecord(@ModelAttribute QualityRegulationRecordDto regulationRecordDto) {
        return qualityRegulationRecordService.queryRegulationRecord(regulationRecordDto);
    }

}
