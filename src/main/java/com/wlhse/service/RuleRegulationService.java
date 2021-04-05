package com.wlhse.service;

import com.wlhse.dto.inDto.FileInDto;
import com.wlhse.entity.FilePojo;

public interface RuleRegulationService {

    String removeRuleRegulation(Integer id);

    String addRuleRegulationName(FilePojo filePojo);//查询具体名字

    String getRuleRegulationByCondition(FileInDto fileInDto);

    String queryFileById(Integer id);
}
