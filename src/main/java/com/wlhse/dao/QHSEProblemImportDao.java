package com.wlhse.dao;

import com.wlhse.dto.inDto.QHSEProblemImportInDto;
import com.wlhse.dto.outDto.QHSEProblemImportDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QHSEProblemImportDao {
    //录入问题
    int addNewProblem(QHSEProblemImportDto qhseProblemImportDto);
    int getHashCount(@Param("hash") String hash);
    List<QHSEProblemImportDto> getProblemImportDtoByContiton(QHSEProblemImportInDto qhseProblemImportInDto);
    int getProblemImportDtoCount(QHSEProblemImportInDto qhseProblemImportInDto);
    QHSEProblemImportDto getProblemImportDtoById(@Param("problemID") int problemID, @Param("url") String url);
    int updateProblemImportDto(QHSEProblemImportDto qhseProblemImportDto);
    int deleteProblem(Integer unitPriceID);
}
