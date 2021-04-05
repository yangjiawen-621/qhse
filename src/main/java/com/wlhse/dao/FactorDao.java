package com.wlhse.dao;

import com.wlhse.dto.outDto.FactorDepartmentOutDto;
import com.wlhse.dto.outDto.FactorOutDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactorDao {


    List<FactorOutDto> findAll(@Param("code") String code);

    String findByName(@Param("name") String name);
}
