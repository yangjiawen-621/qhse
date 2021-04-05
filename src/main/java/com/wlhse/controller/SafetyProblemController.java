package com.wlhse.controller;

import com.wlhse.dto.inDto.SafetyProblemImportInDto;
import com.wlhse.dto.outDto.NewSafetyProblemDto;
import com.wlhse.dto.outDto.SafetyProblemImportDto;
import com.wlhse.service.SafetyProblemImportService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController("SafetyProblemController")
@RequestMapping("/api/v3")
public class SafetyProblemController {

    @Resource
    private SafetyProblemImportService safetyProblemImportService;

    //**************问题录入*****************
    @RequestMapping(value = "/safety_problem_add", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public R newAddProblemImportDto(@RequestBody(required = false) NewSafetyProblemDto newSafetyProblemDto) {
        return safetyProblemImportService.newInsertProblemImport(newSafetyProblemDto);
    }

    //**************问题列表查询+分页*****************
    @RequestMapping(value = "/safety_problem_select", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String selectAllProblemImportDto(@ModelAttribute SafetyProblemImportInDto safetyProblemImportInDto) {
        return safetyProblemImportService.queryProblemImport(safetyProblemImportInDto);
    }

    //**************问题列表查询详情*****************
    @RequestMapping(value = "/safety_problem_detail/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String selectSingleProblemImportDto(@PathVariable("id") int id) {
        return safetyProblemImportService.querySafetyProblemImportById(id);
    }

    //**************问题修改*****************
    @RequestMapping(value = "/safety_problem_update/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public String updateProblemImportDto(@RequestBody(required = false) SafetyProblemImportDto safetyProblemImportDto, @PathVariable("id") int id) {
        safetyProblemImportDto.setProblemID(id);
        return safetyProblemImportService.updateProblemImport(safetyProblemImportDto);
    }
    //**************问题删除*****************
    @RequestMapping(value = "safety_problem_delete/{problem_id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteProblem(@PathVariable("problem_id") int problem_id) {
        return safetyProblemImportService.deleteProblem(problem_id);
    }
}
