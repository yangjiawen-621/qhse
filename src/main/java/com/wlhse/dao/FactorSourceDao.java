package com.wlhse.dao;

import com.wlhse.dto.FactorSourceDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactorSourceDao {
    //查询所有
    List<FactorSourceDto> getAll();

    String getFactorSourceCode(@Param("factorCode") String factorCode);
    FactorSourceDto getFactorSource(@Param("factorSourceCode") String factorSourceCode);
}
