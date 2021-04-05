package com.wlhse.controller;


import com.wlhse.dto.ProblemDescriptionDto;
import com.wlhse.service.ProblemDescriptionService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("ProblemDescriptionController")
@RequestMapping("/api/v3")
public class ProblemDescriptionController {

    @Resource
    private ProblemDescriptionService problemDescriptionService;

    @RequestMapping(value = "/add_problemDescription", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public R addProblemDescription(@RequestBody(required = false) ProblemDescriptionDto problemDescriptionDto) {
        problemDescriptionDto.setStatus("未整改");
        return problemDescriptionService.addProblemDescription(problemDescriptionDto);
    }

    @RequestMapping(value = "/delete_problemDescription/{id}", method = RequestMethod.DELETE, produces = {"application/json;charset=UTF-8"})
    public R deleteProblemDescription(@PathVariable int id) {
        return problemDescriptionService.deleteProblemDescription(id);
    }

    @RequestMapping(value = "/update_problemDescription/{id}", method = RequestMethod.PUT, produces = {"application/json;charset=UTF-8"})
    public R updateProblemDescription(@PathVariable int id, @RequestBody(required = false) ProblemDescriptionDto problemDescriptionDto) {
        problemDescriptionDto.setqHSE_AuditProblemRecord_ID(id);
        return problemDescriptionService.updateProblemDescription(problemDescriptionDto);
    }

    @RequestMapping(value = "/query_problemDescription", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryProblemDescription(@ModelAttribute ProblemDescriptionDto problemDescriptionDto) {
        return problemDescriptionService.queryProblemDescription(problemDescriptionDto);
    }
}
