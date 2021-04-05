package com.wlhse.service;

import com.wlhse.dto.inDto.FileInDto;
import com.wlhse.dto.inDto.RegulationInDto;
import com.wlhse.entity.FilePojo;
import com.wlhse.entity.RegulationPojo;

public interface RegulationService {

    //新增
    String addRegulationPojo(RegulationPojo regulationPojo);
    //条件查询规章制度
    String getRegulationPojoByCondition(RegulationInDto regulationInDto);
    //根据id查询规章制度
    String getRegulationPojoById(Integer id);
    //修改规章制度
    String updateRegulationPojo(RegulationPojo regulationPojo);
    //删除规章制度
    String deleteRegulation(Integer id);
    //查询文件名称和code
    String listRegulationNameAndCode(RegulationInDto regulationInDto);
}
