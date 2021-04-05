package com.wlhse.controller;


import com.wlhse.dto.QHSEproblemDiscriptionDto;
import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.entity.QhseElementsPojo;
import com.wlhse.service.QHSEManageSysElementsService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("QHSEManageSysElementsController")
@RequestMapping("/api/v3")
public class QHSEManageSysElementsController {

    @Resource
    private QHSEManageSysElementsService qhseManageSysElementsService;


    //th---查询QHSE_ManagerSysElement基本表
    @RequestMapping(value = "/querryQhseElement", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R querryQhseManagerSysElement(){
        return qhseManageSysElementsService.queryAllElement();
    }

    //th---查询年度要素
    //条件查询，指定公司code和年度
    //TODO 适配文件审核
    @RequestMapping(value = "/querryYearElement", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R querryQhseYearElement(@ModelAttribute YearElementsDto yearElementsDto){
        return qhseManageSysElementsService.queryYearElement(yearElementsDto);
    }




    @RequestMapping(value = "/queryYearElement",method = RequestMethod.GET,produces =  {"application/json;charset=utf-8"})
    public  R queryYearElement(@ModelAttribute YearElementsDto yearElementsDto){
        return qhseManageSysElementsService.queryYearElements(yearElementsDto);
    }

    //th---查询QHSE_ManagerSysElement基本表两级
    @RequestMapping(value = "/querryQhseChildElement", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R querryQhseManagerSysChildElement(){
        return qhseManageSysElementsService.queryChildElement();
    }






    //th---根据是否启用查询节点，包含问题描述
    @RequestMapping(value = "/querryQhseElements/{tag}", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R querryQhseManagerSysElements(@PathVariable("tag") int tag){
        return qhseManageSysElementsService.queryAllElements(tag);
    }

    //th---跟新状态
    @RequestMapping(value = "/updateQHSEElementStatus/{id}", method = RequestMethod.PUT, produces = {"application/json;charset=utf-8"})
    public R updateQHSEElementStatus(@PathVariable("id") int id){
        return qhseManageSysElementsService.updateElementStatus(id);
    }
    //---更新内容
    @RequestMapping(value = "/updateQHSEElement", method = RequestMethod.PUT, produces = {"application/json;charset=utf-8"})
    public R updateQHSEElement(@RequestBody(required = false) QhseElementsPojo qhseManageSysElement){
        return qhseManageSysElementsService.updateElementcontent(qhseManageSysElement);
    }
    //---添加节点内容
    @RequestMapping(value = "/addQHSEElement", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public R addQHSEElement(@RequestBody(required = false) QhseElementsPojo qhseManageSysElement){
        return qhseManageSysElementsService.addElement(qhseManageSysElement);
    }
    //查询问题列表，根据返回的code查询
    @RequestMapping(value = "/querryQHSEproblemDiscription/{code}", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R querryQHSEproblemDiscription(@PathVariable("code") String code){
        return qhseManageSysElementsService.querryQhseProblemDiscription(code);
    }
    //删除
    @RequestMapping(value = "/deleteQHSEproblemDiscription/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    public R deleteQHSEproblemDiscription(@PathVariable("id") Integer id)
    {
        return qhseManageSysElementsService.deleteQhseProblemDiscription(id);
    }
    //跟新
    @RequestMapping(value = "/updateQHSEproblemDiscription", method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    public R updateQHSEproblemDiscription(@RequestBody(required = false)QHSEproblemDiscriptionDto qHSEproblemDiscriptionDto)
    {
        return qhseManageSysElementsService.updateQhseProblemDiscription(qHSEproblemDiscriptionDto);
    }
    //添加
    @RequestMapping(value = "/addQHSEproblemDiscription", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public R addQHSEproblemDiscription(@RequestBody(required = false) QHSEproblemDiscriptionDto qHSEproblemDiscriptionDto)
    {
        return qhseManageSysElementsService.addQhseProblemDiscription(qHSEproblemDiscriptionDto);
    }



    //新增年度qhse管理体系要素
    //前端二级节点用分号隔开，tableID
    //YearElementsDto
    @RequestMapping(value = "/addQHSEYearElement", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public R addQHSEYearElement(@RequestBody(required = false) YearElementsDto yearElementsDto){
        return qhseManageSysElementsService.addYearElement(yearElementsDto);
    }

}
