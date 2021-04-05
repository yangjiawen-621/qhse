package com.wlhse.dao;

import com.wlhse.dto.QHSEAccidentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QHSEAccidentDao {

    int addAccident(QHSEAccidentDto qhseaccidentDto);

    int deleteAccident(@Param("id") Integer id);

    int updateAccident(QHSEAccidentDto qhseaccidentDto);

    //分页查询
    int queryTotal(QHSEAccidentDto qhseAccidentDto);
    List<QHSEAccidentDto> queryAccident(QHSEAccidentDto qhseaccidentDto);

    //按ID查询
    QHSEAccidentDto queryAccidentById(@Param("id") Integer id);
}
