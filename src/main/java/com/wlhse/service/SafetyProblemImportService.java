package com.wlhse.service;

import com.wlhse.dto.inDto.SafetyProblemImportInDto;
import com.wlhse.dto.outDto.NewSafetyProblemDto;
import com.wlhse.dto.outDto.SafetyProblemImportDto;
import com.wlhse.util.R;

public interface SafetyProblemImportService {
    //新插入问题
    R newInsertProblemImport(NewSafetyProblemDto newSafetyProblemDto);
    //查询问题列表+分页
    String queryProblemImport(SafetyProblemImportInDto safetyProblemImportInDto);
    //查询问题详情
    String querySafetyProblemImportById(int id);
    //修改问题
    String updateProblemImport(SafetyProblemImportDto safetyProblemImportDto);
    //删除问题
    String deleteProblem(int id);
}
