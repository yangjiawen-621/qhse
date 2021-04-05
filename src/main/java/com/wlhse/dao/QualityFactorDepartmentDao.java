package com.wlhse.dao;

import com.wlhse.dto.outDto.FactorDepartmentOutDto;
import com.wlhse.dto.outDto.QualityFactorDepartmentOutDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityFactorDepartmentDao {

    List<QualityFactorDepartmentOutDto> findAll();
    String getFactorDepartmentCode(@Param("factorCode") String factorCode);
    QualityFactorDepartmentOutDto getFactorDepartment(@Param("factorDepartmentID") String factorDepartmentID);
}
