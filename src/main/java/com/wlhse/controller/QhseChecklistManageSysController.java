package com.wlhse.controller;

import com.wlhse.dto.inDto.ElementReviewDto;
import com.wlhse.dto.inDto.QHSEManageSysElementsDto;
import com.wlhse.entity.QHSECompanyYearManagerSysElementPojo;
import com.wlhse.service.QhseChecklistManageSysService;
import com.wlhse.util.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v3")

public class QhseChecklistManageSysController {
    @Resource
    private QhseChecklistManageSysService qhseChecklistManageSysService;
    //lhl-添加年度要素表、要素细节表
    @RequestMapping(value = "/add_elementReviewer", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public R elementReviewer(@RequestBody QHSEManageSysElementsDto qHSEManageSysElementsDto) {
        //获取二级节点所以
        String[] codes = qHSEManageSysElementsDto.getCode().split(";");
        List<QHSECompanyYearManagerSysElementPojo> lists=new ArrayList<>();
        for(int i=0;i<codes.length;i++){
            qHSEManageSysElementsDto.setCode(codes[i]);
            List<QHSECompanyYearManagerSysElementPojo> list=qhseChecklistManageSysService.query(qHSEManageSysElementsDto);
            lists.addAll(list);
        }
        QHSEManageSysElementsDto Dto=qhseChecklistManageSysService.query2(qHSEManageSysElementsDto);
        if(Dto==null) {
            qhseChecklistManageSysService.add(qHSEManageSysElementsDto);
            QHSEManageSysElementsDto qHSEManageSysElementsDtos = qhseChecklistManageSysService.query2(qHSEManageSysElementsDto);
            return qhseChecklistManageSysService.addAll(lists, qHSEManageSysElementsDtos);
        }
        return R.error(200,"年度表已经存在");
    }

}
