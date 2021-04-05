package com.wlhse.service;

import com.wlhse.dto.inDto.QHSEProblemImportInDto;
import com.wlhse.dto.outDto.QHSEProblemImportDto;
import com.wlhse.util.R;

public interface QHSEProblemImportService {
    //新插入问题
    R newInsertProblemImport(QHSEProblemImportDto qhseProblemImportDto);
    //查询问题列表+分页
    String queryProblemImport(QHSEProblemImportInDto qhseProblemImportInDto);
    //查询问题详情
    String queryProblemImportById(int id);
    //修改问题
    String updateProblemImport(QHSEProblemImportDto qhseProblemImportDto);
    //删除问题
    String deleteProblem(int id);
}
