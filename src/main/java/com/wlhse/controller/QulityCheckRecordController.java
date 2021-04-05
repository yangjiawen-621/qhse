package com.wlhse.controller;

import com.wlhse.entity.QulityCheckRecordPojo;
import com.wlhse.service.QulityCheckRecordService;
import com.wlhse.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tobing
 * 质量检查记录模块
 */
@RestController
@RequestMapping("/api/v3")
public class QulityCheckRecordController {


    @Autowired
    private QulityCheckRecordService qulityCheckRecordService;

    // 添加质量检查记录
    @RequestMapping(value = "/qulity_check_record_add", method = RequestMethod.POST)
    public R addQulityCheckRecord(@RequestBody QulityCheckRecordPojo qulityCheckRecordPojo) {
        return this.qulityCheckRecordService.addQulityCheckRecord(qulityCheckRecordPojo);
    }

    // 根据质量检查记录id，查询质量检查记录id
    @RequestMapping(value = "/qulity_check_record_query_by_id/{id}", method = RequestMethod.GET)
    public R queryQulityCheckRecordById(@PathVariable("id")String id) {
        return this.qulityCheckRecordService.queryQulityCheckRecordById(id);
    }

    // 查询所有质量检查记录
    @RequestMapping(value = "/qulity_check_record_query", method = RequestMethod.GET)
    public R queryQulityCheckRecordAll() {
        return this.qulityCheckRecordService.queryQulityCheckRecord();
    }

    // 删除质量检查记录
    @RequestMapping(value = "/qulity_check_record_delete/{id}", method = RequestMethod.DELETE)
    public R deleteQulityCheckRecord(@PathVariable("id") Integer id) {
        return this.qulityCheckRecordService.deleteQulityCheckRecord(id);
    }

    // 更新质量检查
    @RequestMapping(value = "/qulity_check_record_udpate/{id}", method = RequestMethod.PUT)
    public R updateQulityCheckRecord(@PathVariable("id") Integer id, @RequestBody QulityCheckRecordPojo qulityCheckRecordPojo) {
        return this.qulityCheckRecordService.updateQulityCheckRecord(id, qulityCheckRecordPojo);
    }

    // 根据quality_checkId查询质量检查
    @RequestMapping(value = "/qulity_check_record_query_by_checkId/{checkId}", method = RequestMethod.GET)
    public R queryQulityCheckRecordByCheckId(@PathVariable("checkId") String checkId) {
        return this.qulityCheckRecordService.queryQulityCheckRecordByCheckId(checkId);
    }
}
