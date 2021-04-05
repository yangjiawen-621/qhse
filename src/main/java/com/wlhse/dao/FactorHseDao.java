package com.wlhse.dao;

import com.wlhse.dto.FactorHseDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactorHseDao {
    //查询所有
    List<FactorHseDto> getAll();
    String getFactorHseCode(@Param("factorCode") String factorCode);
    FactorHseDto getFactorHse(@Param("factorHseCode2") String factorHseCode2);
}
