package com.wlhse.controller;


import com.wlhse.dao.QhseElementsInputDao;
import com.wlhse.dto.inDto.ElementEvidenceAttachInDto;
import com.wlhse.service.QhseElementsInputService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController("QhseElementsInputController")
@RequestMapping("/api/v3")
public class QhseElementsInputController {

    @Resource
    private QhseElementsInputService qhseElementsInputService;


    //melon-增加附件所有信息(前端传值code、id、uploadtime不能为空）
    @RequestMapping(value = "/addAll_evidence_attach", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    public R addElementEvidenceAttachs(@RequestBody(required = false) ElementEvidenceAttachInDto elementEvidenceAttachInDto,HttpServletRequest request){
        return qhseElementsInputService.addElementEvidenceAttach(elementEvidenceAttachInDto,request) ;
    }
    //melon-查询信息
    @RequestMapping(value = "/query_evidence_attach", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R query(ElementEvidenceAttachInDto elementEvidenceAttachInDto){
        return qhseElementsInputService.queryAll(elementEvidenceAttachInDto) ;
    }
    //melon-要素录入文件下载
    @RequestMapping(value = "/downloadElementFile", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    public R downloadElementFile(@RequestParam(value = "fileName")String fileName, HttpServletRequest request) throws IOException {
         String fileNames=qhseElementsInputService.queryOriginFileName(fileName);
         R r=new R();
         r.put("data",fileNames);
         return r;
    }
//    //要素提交
//    @RequestMapping(value = "/elementCommit/{id}", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
//    public R elementCommit(@PathVariable("id")Integer id) {
//        qhseElementsInputDao.updateStatus(id);
//        return R.ok();
//    }

    @RequestMapping(value = "/submitInputResult",method = RequestMethod.POST)
    public R submitInputResult(@RequestParam(value = "tableId",required = false)Integer tableId,@RequestParam("tag")Integer tag){
        return qhseElementsInputService.submitInputResult(tableId,tag);
    }


    @RequestMapping(value = "/notInvolve",method = RequestMethod.GET)
    public R notInvolve(@RequestParam("elementId")Integer elementId,HttpServletRequest request){
        return qhseElementsInputService.notInvolve(elementId,request);
    }

}
