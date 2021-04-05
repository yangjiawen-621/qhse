package com.wlhse.controller;

import com.wlhse.dto.inDto.QHSEProblemImportInDto;
import com.wlhse.dto.outDto.QHSEProblemImportDto;
import com.wlhse.service.QHSEProblemImportService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController("QHSEProblemController")
@RequestMapping("/api/v3")
public class QHSEProblemController {

    @Resource
    private QHSEProblemImportService qhseProblemImportService;

    //**************问题录入*****************
    @RequestMapping(value = "/qhse_problem_add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public R newAddProblemImportDto(@RequestBody(required = false) QHSEProblemImportDto qhseProblemImportDto) {
        return qhseProblemImportService.newInsertProblemImport(qhseProblemImportDto);
    }

    //**************问题列表查询+分页*****************
    @RequestMapping(value = "/qhse_problem_select", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String selectAllProblemImportDto(@ModelAttribute QHSEProblemImportInDto qhseProblemImportInDto) {
        return qhseProblemImportService.queryProblemImport(qhseProblemImportInDto);
    }

    //**************问题列表查询详情*****************
    @RequestMapping(value = "/qhse_problem_detail/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String selectSingleProblemImportDto(@PathVariable("id") int id) {
        return qhseProblemImportService.queryProblemImportById(id);
    }

    //**************问题修改*****************
    @RequestMapping(value = "/qhse_problem_update/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateProblemImportDto(@RequestBody(required = false) QHSEProblemImportDto qhseProblemImportDto, @PathVariable("id") int id) {
        qhseProblemImportDto.setProblemID(id);
        return qhseProblemImportService.updateProblemImport(qhseProblemImportDto);
    }
    //**************问题删除*****************
    @RequestMapping(value = "qhse_problem_delete/{problem_id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteProblem(@PathVariable("problem_id") int problem_id) {
        return qhseProblemImportService.deleteProblem(problem_id);
    }
}
