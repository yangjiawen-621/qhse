package com.wlhse.dao;

import com.wlhse.dto.FactorObserverDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactorObserverDao {
    //查询所有
    List<FactorObserverDto> getAll();

    String getFactorObserverCode(@Param("factorCode") String factorCode);
    FactorObserverDto getFactorObserver(@Param("factorObserverCode2") String factorObserverCode2);
}
