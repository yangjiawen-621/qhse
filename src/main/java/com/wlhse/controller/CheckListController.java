package com.wlhse.controller;

import com.wlhse.dto.inDto.CheckListAddDto;
import com.wlhse.dto.outDto.CompanyOutDto;
import com.wlhse.service.CheckListService;
import com.wlhse.service.CompanyService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController("CheckListController")
@RequestMapping("/api/v3")
public class CheckListController {

    @Resource
    private CheckListService checkListService;
    //查询
    @RequestMapping(value = "/check_list_tree/{tag}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R getCheckListParentAndSon(@PathVariable("tag") int tag) {
        return checkListService.getTreeDto(tag);
    }
    //添加
    @RequestMapping(value = "/addCheckListNode", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R addCheckListNode(@RequestBody(required = false)CheckListAddDto checkListAddDto) {
        return checkListService.addCheckListNode(checkListAddDto);
    }

    //改检查情况，改一级节点下的所有节点
    @RequestMapping(value = "/updateCheckList", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R updateCheckList(@RequestBody(required = false)CheckListAddDto checkListAddDto) {
        return checkListService.updateCheckList(checkListAddDto);
    }
    //更改状态
    @RequestMapping(value = "/deleteCheckList/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R deleteCheckList(@PathVariable("id") int id) {
        return checkListService.deleteCheckList(id);
    }

}
