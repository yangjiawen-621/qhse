package com.wlhse.controller;

import com.wlhse.dto.QualityCheckDto;
import com.wlhse.entity.QualityCheckTableRecord;
import com.wlhse.service.CheckTableRecordService;
import com.wlhse.service.QualityCheckListService;
import com.wlhse.util.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 配置要素
 * @Author YangJiaWen
 * @Date 2021/4/2 20:00
 * @Version 1.0
 **/

@RestController("QualityConfigElement")
@RequestMapping("/api/v3")
public class QualityConfigElement {

    @Resource
    private QualityCheckListService qualityCheckListService;

    @Resource
    private CheckTableRecordService tableRecordService;

    /**
     * @Author YangJiaWen
     * @Description //查询指定表名的要素配置
     * @Date 20:02 2021/4/2
     * @Param [tag]
     * @return com.wlhse.util.R
     **/
    //查询
    @RequestMapping(value = "/Quality_Check_element", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R getConfigElementTree( @RequestParam("checkedListCode") String checkedListCode) {
        return qualityCheckListService.getTreeDto(checkedListCode);
    }

    /**
     * @Author YangJiaWen
     * @Description //配置要素,并保存到数据库中
     * @Date 12:57 2021/4/3
     * @Param
     * @return
     **/

    @RequestMapping(value = "/saveConfigElement",method = RequestMethod.POST)
    public R saveConfigElement(QualityCheckTableRecord quality){
       return tableRecordService.saveQualityCheckTableRecord(quality);
    }


    @RequestMapping(value = "/showBack",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
   public R showBackElement( @RequestParam("QualityCheckID") Integer QualityCheckID){
       return tableRecordService.showBackElement(QualityCheckID);
   }

}
