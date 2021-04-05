package com.wlhse.controller;

import com.wlhse.dto.outDto.FilePropagationDetailDto;
import com.wlhse.entity.FilePropagationPOJO1;
import com.wlhse.service.FilePropagationPlanService;
import com.wlhse.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author:Coco
 * create:2020-08-03 1:00 PM
 **/
@RestController("FilePropagationController")
@RequestMapping("/api/v3")
public class FilePropagationController {
    @Autowired
    FilePropagationPlanService filePropagationPlanService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/getFilePropagationDetailList",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    R getFilePropagationList(HttpServletRequest request){
        return  filePropagationPlanService.getFilePropagationPlanDetailByStaffId(request);
    }

    @RequestMapping(value = "/readPropagation",method =RequestMethod.GET,produces = "application/json; charset=utf-8" )
    R readPropagation(HttpServletRequest request,@RequestParam(value = "detailId") Integer detailId){
        return  filePropagationPlanService.readFilePropagation(request,detailId);
    }

    //query all propagation plans
    @RequestMapping(value = "/queryPropagationPlan",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    R queryAll(){
        return filePropagationPlanService.getAllFilePropagation();
    }

    @RequestMapping(value = "/queryPropagationDetailAll",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    R queryAllPropagationDetailId(@RequestParam(value = "filePropagationId")Long filePropagationId){
        return filePropagationPlanService.getFilePropagationDetailIdByPropagationId(filePropagationId);
    }

    @RequestMapping(value = "/insertPropagationPlan",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    R insertPropagationPlan(@RequestBody (required = false) FilePropagationPOJO1 filePropagationPOJO, HttpServletRequest request){
        logger.info(filePropagationPOJO.toString());
        return filePropagationPlanService.releaseNewFilePropagationPlan(filePropagationPOJO,request);
    }

    @RequestMapping(value = "/insertPropagationDetail ",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    R insertNewPropagationDetail(@RequestBody(required = false) List<FilePropagationDetailDto> filePropagationDetailDtos){
        logger.info("传入的数据:"+filePropagationDetailDtos.toString());
        return filePropagationPlanService.insertNewFilePropagationDetail(filePropagationDetailDtos);
    }

    @RequestMapping(value = "/deletePropagationPlan ",method = RequestMethod.DELETE,produces = "application/json; charset=utf-8")
    R deletePropagationPlan(@RequestParam(value = "filePropagationId")Long id){
        return filePropagationPlanService.deleteFilePropagationPlan(id);
    }

    @RequestMapping(value ="/deletePropagationDetail",method =RequestMethod.DELETE,produces = "application/json; charset=utf-8" )
    R deletePropagationDetail(@RequestParam(value = "filePropagationDetailId")int filePropagationDetailId){
        return filePropagationPlanService.deleteFilePropagationPlanDetail(filePropagationDetailId);
    }

    @RequestMapping(value = "/getFilePropagationDetailList/{pageNum}",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    R getFilePropagationListInPage(@PathVariable(value = "pageNum")int pageNum,HttpServletRequest request){
        return filePropagationPlanService.getFilePropagationPlanDetailByStaffIdInPage(request,pageNum);
    }

    @RequestMapping(value = "/getReadHistory",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    R getReadHistory(@RequestParam(value = "propagationId")Long propagationId){
        return filePropagationPlanService.getReadHistoryByPropagationId(propagationId);
    }
}
