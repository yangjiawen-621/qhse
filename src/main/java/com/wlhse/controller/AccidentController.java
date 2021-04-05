package com.wlhse.controller;


import com.wlhse.dto.QHSEAccidentDto;
import com.wlhse.service.AccidentService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController("AccidentController")
@RequestMapping("/api/v3")
public class AccidentController {
    @Resource
    private AccidentService accidentService;

    @RequestMapping(value = "/add_accident", method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public R addAccident(@RequestBody(required = false) QHSEAccidentDto qhseaccidentDto)
    {
        return accidentService.addAccident(qhseaccidentDto);
    }

    @RequestMapping(value = "/delete_accident/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=UTF-8"})
    public R deleteAccident(@PathVariable int id)
    {
        return accidentService.deleteAccident(id);
    }

    @RequestMapping(value = "/update_accident/{id}", method = RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
    public R updateAccident(@PathVariable int id,@RequestBody(required = false) QHSEAccidentDto qhseaccidentDto)
    {
        qhseaccidentDto.setAccidentID(id);
        return accidentService.updateAccident(qhseaccidentDto);
    }

    @RequestMapping(value = "/query_accident", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public String queryAccident(@ModelAttribute QHSEAccidentDto qhseaccidentDto) {
        return accidentService.queryAccident(qhseaccidentDto);
    }

    @RequestMapping(value = "/query_accident/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public R queryAccidentById(@PathVariable int id) {
        return accidentService.queryAccidentById(id);
    }
}
