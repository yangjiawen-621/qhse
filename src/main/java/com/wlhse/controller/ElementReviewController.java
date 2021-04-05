package com.wlhse.controller;

import com.wlhse.dao.ElementReviewDao;
import com.wlhse.dao.QHSETaskDao;
import com.wlhse.dao.QhseElementsInputDao;
import com.wlhse.dto.QualityCheckTableRecordDto;
import com.wlhse.dto.inDto.ElementReviewDto;
import com.wlhse.dto.outDto.QhseEvidenceAttatchDto;
import com.wlhse.service.ElementReviewService;
import com.wlhse.util.GetCurrentUserIdUtil;
import com.wlhse.util.R;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController("ElementReviewController")
@RequestMapping("/api/v3")
public class ElementReviewController {
    @Resource
    private ElementReviewService elementReviewService;

    @Resource
    private GetCurrentUserIdUtil getCurrentUserIdUtil;

    @Resource
    private  ElementReviewDao elementReviewDao;
    @Resource
    QhseElementsInputDao qhseElementsInputDao;
    @Resource
    QHSETaskDao qhseTaskDao;
    //查询审核要素
    @RequestMapping(value = "/query_elementReviewer", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R elementReviewer(@ModelAttribute ElementReviewDto elementReviewDto) {
        return  elementReviewService.query(elementReviewDto);
    }
    //查询批准要素
    @RequestMapping(value = "/query_elementReviewers", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R elementReviewers(@ModelAttribute ElementReviewDto elementReviewDto,HttpServletRequest request ) {
        return  elementReviewService.queryS(elementReviewDto);
    }
    //审核人通过
    @RequestMapping(value = "/pass_elementReviewer", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R elementReviewer1(@RequestBody(required = false) ElementReviewDto elementReviewDto,HttpServletRequest request ) {
        elementReviewDto.setCheckStaffID(getCurrentUserIdUtil.getUserId(request));
        elementReviewDto.setStatus("未批准");
        elementReviewService.updateCheck(elementReviewDto);
        return  elementReviewService.updateStatus(elementReviewDto);
    }
    //审核人批准
    @RequestMapping(value = "/approval_elementReviewer", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R elementReviewer2(@RequestBody(required = false) ElementReviewDto elementReviewDto,HttpServletRequest request ) {
        elementReviewDto.setApproverStaffID(getCurrentUserIdUtil.getUserId(request));
        elementReviewService.updateApprove(elementReviewDto);
        elementReviewDto.setStatus("备案待查");
        return  elementReviewService.updateStatus(elementReviewDto);
    }
    //显示要素证据信息
    @RequestMapping(value = "/show_elementReviewer", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R show(@ModelAttribute QhseEvidenceAttatchDto qhseEvidenceAttatchDto) throws ParseException {
        return  elementReviewService.queryAll(qhseEvidenceAttatchDto);
    }
    //审核人不批准不通过
    @RequestMapping(value = "/no_elementReviewer", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    @Transactional
    public R elementReviewer3(@RequestBody(required = false) ElementReviewDto elementReviewDto) {
        //elementReviewService.deletes(elementReviewDto);不通过删除附件等所有信息
        elementReviewDto.setStatus("不通过");
        qhseElementsInputDao.updateCheckStatusByElementId(elementReviewDto.getqHSE_CompanyYearManagerSysElement_ID());
        return  elementReviewService.updateStatus(elementReviewDto);
    }
    //查询已审核或已审批
    @RequestMapping(value = "/show_approve_check", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R elementReviewer4( ElementReviewDto elementReviewDto) {
        return  elementReviewService.shows(elementReviewDto);
    }

    //查询未通过审核或未通过批准的要素
    @RequestMapping(value = "/show_no_pass_element",method = RequestMethod.GET)
    public R elementReviewer6(ElementReviewDto elementReviewDto){
        return elementReviewService.showNoPass(elementReviewDto);
    }
    //查询全要素
    @RequestMapping(value = "/showAllElement", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R elementReviewer5( ElementReviewDto elementReviewDto) {
        return  elementReviewService.queryAllElement(elementReviewDto);
    }


    //    -----------------------------质量部分审核审批-------------------------------  //
    //    录入查询代码复用queryCheckTreeByID/{id}

    //    查询单个叶子节点证据审核内容
    @RequestMapping(value = "/show_quality_envidence", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R showQualityEnvidence( QualityCheckTableRecordDto qualityCheckTableRecordDto) {
        QualityCheckTableRecordDto qualityCheckTableRecordDtos=elementReviewDao.queryQuality(qualityCheckTableRecordDto);
       if(qualityCheckTableRecordDtos!=null)
        qualityCheckTableRecordDtos.setUrl("http://39.98.173.131:7000/resources/QualityCheck");//文件地址路径
        System.out.println(qualityCheckTableRecordDtos);
        return  R.ok().put("data",qualityCheckTableRecordDtos);
    }
    //审核人批准人批准
    @RequestMapping(value = "/pass_quality/{tag}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public R showQualityEnvidence1( @RequestBody(required = false) QualityCheckTableRecordDto qualityCheckTableRecordDto,@PathVariable("tag") Integer tag)  {
        System.out.println(tag);
        if(tag==0) qualityCheckTableRecordDto.setCheckResult("不符合");
            else qualityCheckTableRecordDto.setCheckResult("符合");
        elementReviewDao.updateQuality(qualityCheckTableRecordDto);
        return  R.ok();
    }

    //一键审核通过/批准
    @RequestMapping("/passAll")
    public R passAll(@RequestParam("tableId")Integer tableId,@RequestParam("sourceId")Integer sourceId){
        return elementReviewService.passAll(tableId,sourceId);
    }

}
