package com.wlhse.service;

import com.wlhse.dto.QHSEAccidentDto;
import com.wlhse.util.R;

public interface AccidentService {

    //增
    R addAccident(QHSEAccidentDto qhseaccidentDto);
    //删
    R deleteAccident(Integer id);
    //改
    R updateAccident(QHSEAccidentDto qhseaccidentDto);
    //条件查询
    String queryAccident (QHSEAccidentDto qhseaccidentDto);
    //按ID查询
    R queryAccidentById(int id);
}
