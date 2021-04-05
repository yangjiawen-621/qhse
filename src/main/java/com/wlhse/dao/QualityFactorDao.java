package com.wlhse.dao;

import com.wlhse.dto.outDto.FactorOutDto;
import com.wlhse.dto.outDto.QualityFactorOutDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityFactorDao {


    List<QualityFactorOutDto> findAll(@Param("code") String code);

    String findByName(@Param("name") String name);
}
