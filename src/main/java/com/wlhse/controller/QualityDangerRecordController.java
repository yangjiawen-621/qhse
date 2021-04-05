package com.wlhse.controller;


import com.wlhse.dto.DangerRecordDto;
import com.wlhse.dto.QualityDangerRecordDto;
import com.wlhse.service.DangerRecordService;
import com.wlhse.service.Quality_DangerRecordService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author tobing QQ:652916578
 * 【质量】隐患接口
 */
@RestController()
@RequestMapping("/api/v3")
public class QualityDangerRecordController {

    @Resource
    private Quality_DangerRecordService qualityDangerRecordService;

    // 新增隐患清单
    // 简单测试通过
    @RequestMapping(value = "/add_quality_dangerrecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addDangerRecord(@RequestBody(required = false) QualityDangerRecordDto qualityDangerRecordDto, HttpServletRequest request) {
        return qualityDangerRecordService.addDangerRecord(qualityDangerRecordDto, request);
    }

    // 删除隐患清单
    // 简单测试通过
    @RequestMapping(value = "/delete_quality_dangerrecord", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R deleteDangerRecord(@ModelAttribute QualityDangerRecordDto qualityDangerRecordDto) {
        return qualityDangerRecordService.deleteDangerRecord(qualityDangerRecordDto);
    }

    // 更新隐患清单
    // 简单测试通过
    @RequestMapping(value = "/update_quality_dangerrecord/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R updateDangerRecord(@PathVariable int id, @RequestBody(required = false) QualityDangerRecordDto qualityDangerRecordDto) {
        qualityDangerRecordDto.setId(id);
        return qualityDangerRecordService.updateDangerRecord(qualityDangerRecordDto);
    }

    // 根据id查询
    // 简单测试通过
    @RequestMapping(value = "/query_quality_dangerrecord/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryDangerRecordById(@PathVariable int id) {
        return qualityDangerRecordService.queryDangerRecordById(id);
    }

    // 根据条件查询
    // 简单测试通过
    @RequestMapping(value = "/query_quality_dangerrecord", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryDangerRecord(@ModelAttribute QualityDangerRecordDto qualityDangerRecordDto) {
        return qualityDangerRecordService.queryDangerRecord(qualityDangerRecordDto);
    }

    // 隐患问题验证
    // 简单测试通过
    @RequestMapping(value = "/quality_problemverification/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R problemVerification(@PathVariable int id, @RequestBody(required = false) QualityDangerRecordDto dangerRecordDto) {
        dangerRecordDto.setId(id);
        return qualityDangerRecordService.problemVerification(dangerRecordDto);
    }

}
