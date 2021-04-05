package com.wlhse.dao;

import com.wlhse.dto.inDto.RegulationInDto;
import com.wlhse.entity.RegulationPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegulationDao {
    //新增检查项标准
    int addRegulationPojo(RegulationPojo regulationPojo);
    //条件查询规章制度
    List<RegulationPojo> getRegulationPojoByCondition(RegulationInDto regulationInDto);
    //条件查询规章制度Total
    Integer getTotalRegulationPojoByCondition(RegulationInDto regulationInDto);
    //根据id查询规章制度
    RegulationPojo getRegulationPojoById(@Param("id") Integer id, @Param("url") String url);
    //修改规章制度
    int updateRegulationPojo(RegulationPojo regulationPojo);
    //删除规章制度
    int deleteRegulation(@Param("id") Integer id);
    //查询文件名称和code
    List<RegulationPojo> queryRegulationNameAndCode(@Param("regName") String regName);
}
