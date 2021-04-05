package com.wlhse.controller;

import com.wlhse.dto.CheckRecordDto;
import com.wlhse.dto.inDto.CheckRecordPOJO;
import com.wlhse.entity.CheckConditionPOJO;
import com.wlhse.service.CheckRecordService;
import com.wlhse.util.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController("CheckRecordController")
@RequestMapping("/api/v3")
public class CheckRecordController {

    @Resource
    private CheckRecordService checkRecordService;

    //新增checkrecord
    @RequestMapping(value = "/check_record", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addCheckRecord(@RequestBody(required = false) CheckRecordDto checkRecordDto) {
        return checkRecordService.addCheckRecord(checkRecordDto);
    }

    //查询所有记录
    @RequestMapping(value = "/check_record", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryAllCheckRecord(CheckConditionPOJO checkConditionPOJO) {

        if (checkConditionPOJO.getIsAll()==1)
            return checkRecordService.queryAll();
       return checkRecordService.queryByCondition(checkConditionPOJO);
    }

    //查询所有checkrecord的树状结构
    @RequestMapping(value = "/check_record_tree", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryAllCheckRecordTree() {
        return checkRecordService.queryAllTree();
    }

    //修改checkrecord
    @RequestMapping(value = "/check_record/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R updateCheckRecord(@PathVariable int id,@RequestBody(required = false) CheckRecordDto checkRecordDto) {
        return checkRecordService.updateCheckrecord(id,checkRecordDto);
    }

    //删除checkrecord
    @RequestMapping(value = "/check_record/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public R deleteCheckRecord(@PathVariable int id) {
        return checkRecordService.deleteCheckrecord(id);
    }

    //get check record in condition ||add new check record
    @RequestMapping(value = "/add_checkList",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public R getCheckRecord( @RequestBody CheckRecordPOJO checkRecordPOJO){
        return checkRecordService.getCheckRecord(checkRecordPOJO);
    }

}
