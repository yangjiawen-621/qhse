package com.wlhse.dao;

import com.wlhse.dto.QHSEAccidentDto;
import com.wlhse.dto.outDto.FactorDepartmentOutDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactorDepartmentDao {

    List<FactorDepartmentOutDto> findAll();
    String getFactorDepartmentCode(@Param("factorCode") String factorCode);
    FactorDepartmentOutDto getFactorDepartment(@Param("factorDepartmentID") String factorDepartmentID);
}
