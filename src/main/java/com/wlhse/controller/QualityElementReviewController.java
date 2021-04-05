package com.wlhse.controller;

import com.wlhse.dao.ElementReviewDao;
import com.wlhse.dao.QHSETaskDao;
import com.wlhse.dao.QhseElementsInputDao;
import com.wlhse.dto.QualityCheckTableRecordDto;
import com.wlhse.dto.inDto.ElementReviewDto;
import com.wlhse.dto.outDto.QhseEvidenceAttatchDto;
import com.wlhse.entity.QualityInputAttachPojo;
import com.wlhse.entity.QualityManergerSysElementPojo;
import com.wlhse.service.ElementReviewService;
import com.wlhse.service.QhseElementsInputService;
import com.wlhse.service.QualityElementReviewServer;
import com.wlhse.util.GetCurrentUserIdUtil;
import com.wlhse.util.R;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

/**
 * Author:melon
 * Origin:2020/10/5
 **/


@RestController("QualityElementReviewController")
@RequestMapping("/api/v3")
public class QualityElementReviewController {
    @Resource
    QualityElementReviewServer qualityElementReviewServer;
    @Resource
    ElementReviewService elementReviewService;
    @Resource
    QhseElementsInputService qhseElementsInputService;

    //质量要素录入查询
    @RequestMapping(value = "/quality_query_elementReviewer", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R elementReviewer(@RequestParam("companyCode") String companyCode,@RequestParam("year") String year) {
        return  qualityElementReviewServer.query(companyCode,year);
    }
    //要素细节附件查看
    @RequestMapping(value = "/quality_query_Attach/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R elementReviewer(@PathVariable("id")Integer id) {
        return  qualityElementReviewServer.queryAttach(id);
    }
    //要素录入
    @RequestMapping(value = "/quality_input_element", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R elementReviewers(@RequestBody(required = false) QualityInputAttachPojo qualityInputAttachPojo) {
        return  qualityElementReviewServer.insertAttach(qualityInputAttachPojo);
    }
    //质量要素审核审批查询
    @RequestMapping(value = "/quality_show_check/{tag}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R elementReviewer(@PathVariable("tag") Integer tag,@RequestParam("companyCode") String companyCode,@RequestParam("year") String year) {
        return  qualityElementReviewServer.queryCheck(tag,companyCode,year);
    }
    //质量通过or不通过
    @RequestMapping(value = "/passOrNoquality/{tag}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R passOrNoQuality(@PathVariable("tag") Integer tag,@RequestParam("id")Integer id,@RequestParam("pass") String pass,@RequestParam(required = false)String negativeOpinion)  {
        return  qualityElementReviewServer.pass(id,tag,pass,negativeOpinion);
    }
    //查询已审核或已审批
    @RequestMapping(value = "/quality_show_approve_check", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R qualityShowApproveCheck( QualityManergerSysElementPojo qualityManergerSysElementPojo) {
        return  elementReviewService.qualityShows(qualityManergerSysElementPojo);
    }
    //查询全要素
    @RequestMapping(value = "/showQualityAllElement", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R showQualityAllElement(QualityManergerSysElementPojo qualityManergerSysElementPojo) {
        return  elementReviewService.queryQualityAllElement(qualityManergerSysElementPojo);
    }
    //要素录入返回原文件名
    @RequestMapping(value = "/returnElementFile", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R returnElementFile(@RequestParam(value = "fileName")String fileName)  {
        String fileNames=qhseElementsInputService.queryQualityOriginFileName(fileName);
       return R.ok().put("data",fileNames);
    }
}
