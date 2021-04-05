package com.wlhse.controller;


import com.wlhse.dto.QHSEproblemDiscriptionDto;
import com.wlhse.dto.QualityManagerSysEleReviewTermsDto;
import com.wlhse.dto.QualityManagerSysElementProDesDto;
import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.entity.QhseElementsPojo;
import com.wlhse.entity.QualityElementsPojo;
import com.wlhse.service.QHSEManageSysElementsService;
import com.wlhse.service.QualityManagerSysElementService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("QualityManagerSysElementController")
@RequestMapping("/api/v3")
public class QualityManagerSysElementController {


    @Resource
    private QualityManagerSysElementService qualityManagerSysElementService;



    //th---查询QHSE_ManagerSysElement基本表
    @RequestMapping(value = "/queryQualityElement", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R queryQualityElement(){
        return qualityManagerSysElementService.queryAllElement();
    }

    //th---查询年度要素
    //条件查询，指定公司code和年度
    //TODO 适配文件审核
   /* @RequestMapping(value = "/querryYearElement", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R querryQhseYearElement(@ModelAttribute YearElementsDto yearElementsDto){
        return qhseManageSysElementsService.queryYearElement(yearElementsDto);
    }
    @RequestMapping(value = "/queryYearElement",method = RequestMethod.GET,produces =  {"application/json;charset=utf-8"})
    public  R queryYearElement(@ModelAttribute YearElementsDto yearElementsDto){
        return qhseManageSysElementsService.queryYearElements(yearElementsDto);
    }*/

    //th---查询QHSE_ManagerSysElement基本表两级
    @RequestMapping(value = "/queryQualityChild", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R queryQualityChild(){
        return qualityManagerSysElementService.queryChildElement();
    }

    //th---根据是否启用查询节点，包含问题描述
    @RequestMapping(value = "/queryQualityManagerSysEle/{tag}", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R queryQualityManagerSysEle(@PathVariable("tag") int tag){
        return qualityManagerSysElementService.queryAllElements(tag);
    }

    //th---跟新状态
    @RequestMapping(value = "/updateQualityElementStatus/{id}", method = RequestMethod.PUT, produces = {"application/json;charset=utf-8"})
    public R updateQualityElementStatus(@PathVariable("id") int id){
        return qualityManagerSysElementService.updateElementStatus(id);
    }
    //---更新内容
    @RequestMapping(value = "/updateQualityElement", method = RequestMethod.PUT, produces = {"application/json;charset=utf-8"})
    public R updateQualityElement(@RequestBody(required = false) QualityElementsPojo qhseManageSysElement){
        return qualityManagerSysElementService.updateElementcontent(qhseManageSysElement);
    }
    //---添加节点内容
    @RequestMapping(value = "/addQualityElement", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public R addQualityElement(@RequestBody(required = false) QualityElementsPojo qhseManageSysElement){
        return qualityManagerSysElementService.addElement(qhseManageSysElement);
    }
    //查询问题列表，根据返回的code查询
    @RequestMapping(value = "/queryQualityProDis/{code}", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R queryQualityProDis(@PathVariable("code") String code){
        return qualityManagerSysElementService.querryQhseProblemDiscription(code);
    }
    //删除
    @RequestMapping(value = "/deleteQualityProDis/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    public R deleteQualityProDis(@PathVariable("id") Integer id)
    {
        return qualityManagerSysElementService.deleteQhseProblemDiscription(id);
    }
    //跟新
    @RequestMapping(value = "/updateQualityProDis", method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    public R updateQualityProDis(@RequestBody(required = false)QualityManagerSysElementProDesDto qHSEproblemDiscriptionDto)
    {
        return qualityManagerSysElementService.updateQhseProblemDiscription(qHSEproblemDiscriptionDto);
    }
    //添加
    @RequestMapping(value = "/addQualityProDis", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public R addQualityProDis(@RequestBody(required = false) QualityManagerSysElementProDesDto qHSEproblemDiscriptionDto)
    {
        return qualityManagerSysElementService.addQhseProblemDiscription(qHSEproblemDiscriptionDto);
    }

    //查询审核内容，根据返回的code查询
    @RequestMapping(value = "/queryQualityReviewTerms/{code}", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R queryQualityReviewTerms(@PathVariable("code") String code){
        return qualityManagerSysElementService.queryQualityReviewTerms(code);
    }
    //删除
    @RequestMapping(value = "/deleteQualityReviewTerms/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    public R deleteQualityReviewTerms(@PathVariable("id") Integer id)
    {
        return qualityManagerSysElementService.deleteQualityReviewTerms(id);
    }
    //跟新
    @RequestMapping(value = "/updateQualityReviewTerms", method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    public R updateQualityReviewTerms(@RequestBody(required = false) QualityManagerSysEleReviewTermsDto qualityManagerSysEleReviewTermsDto)
    {
        return qualityManagerSysElementService.updateQualityReviewTerms(qualityManagerSysEleReviewTermsDto);
    }
    //添加
    @RequestMapping(value = "/addQualityReviewTerms", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public R addQualityReviewTerms(@RequestBody(required = false) QualityManagerSysEleReviewTermsDto qualityManagerSysEleReviewTermsDto)
    {
        return qualityManagerSysElementService.addQualityReviewTerms(qualityManagerSysEleReviewTermsDto);
    }


/*
    //新增年度qhse管理体系要素
    //前端二级节点用分号隔开，tableID
    //YearElementsDto
    @RequestMapping(value = "/addQHSEYearElement", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public R addQHSEYearElement(@RequestBody(required = false) YearElementsDto yearElementsDto){
        return qualityManagerSysElementService.addYearElement(yearElementsDto);
    }*/

}
