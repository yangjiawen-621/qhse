package com.wlhse.controller;


import com.wlhse.dto.QualityProblemDescriptionDto;
import com.wlhse.service.QualityProblemDescriptionService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author tobing QQ:652916578
 * 【质量】问题
 */
@RestController()
@RequestMapping("/api/v3")
public class QualityProblemDescriptionController {

    @Resource
    private QualityProblemDescriptionService qualityProblemDescriptionService;

    // 新增问题记录
    // 简单测试通过
    @RequestMapping(value = "/add_quality_problemDescription", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public R addProblemDescription(@RequestBody(required = false) QualityProblemDescriptionDto problemDescriptionDto) {
        return qualityProblemDescriptionService.addProblemDescription(problemDescriptionDto);
    }

    // 删除问题记录
    // 简单测试通过
    @RequestMapping(value = "/delete_quality_problemDescription/{id}", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    public R deleteProblemDescription(@PathVariable int id) {
        return qualityProblemDescriptionService.deleteProblemDescription(id);
    }

    // 更新问题记录
    // 简单测试通过
    @RequestMapping(value = "/update_quality_problemDescription/{id}", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public R updateProblemDescription(@PathVariable int id, @RequestBody(required = false) QualityProblemDescriptionDto problemDescriptionDto) {
        problemDescriptionDto.setQquality_AuditProblemRecord_ID(id);
        return qualityProblemDescriptionService.updateProblemDescription(problemDescriptionDto);
    }

    // 查询问题记录
    // 简单测试通过
    @RequestMapping(value = "/query_quality_problemDescription", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryProblemDescription(@ModelAttribute QualityProblemDescriptionDto problemDescriptionDto) {
        return qualityProblemDescriptionService.queryProblemDescription(problemDescriptionDto);
    }
}
