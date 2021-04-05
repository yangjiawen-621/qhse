package com.wlhse.controller;

import com.wlhse.dto.inDto.FileInDto;
import com.wlhse.entity.FilePojo;
import com.wlhse.service.RuleRegulationService;
import com.wlhse.util.GetCurrentUserIdUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController("RuleRegulationController")
@RequestMapping("/api/v3")
public class RuleRegulationController {

    @Resource
    private GetCurrentUserIdUtil getCurrentUserIdUtil;

    @Resource
    private RuleRegulationService ruleRegulationService;
    //根据条件查询
    @RequestMapping(value = "/rule_regulation", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getRuleRegulation(@ModelAttribute FileInDto fileInDto) {
        return ruleRegulationService.getRuleRegulationByCondition(fileInDto);
    }

    //根据id查询
    @RequestMapping(value = "/rule_regulation/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String getRuleRegulation(@PathVariable("id") Integer id) {
        return ruleRegulationService.queryFileById(id);
    }

    //删除
    @RequestMapping(value = "/rule_regulation/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public String deleteRuleRegulation(@PathVariable("id") Integer id) {
        return ruleRegulationService.removeRuleRegulation(id);
    }

    //新增
    @RequestMapping(value = "/rule_regulation", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String addRuleRegulationName(@RequestBody(required = false) FilePojo filePojo, HttpServletRequest request) {
        Integer userId = getCurrentUserIdUtil.getUserId(request);
        filePojo.setUploadPersonId(userId);
        return ruleRegulationService.addRuleRegulationName(filePojo);
    }
}
