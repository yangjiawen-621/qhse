package com.wlhse.dao;

import com.wlhse.dto.inDto.SafetyProblemImportInDto;
import com.wlhse.dto.outDto.NewSafetyProblemDto;
import com.wlhse.dto.outDto.SafetyProblemImportDto;
import com.wlhse.entity.ProblemPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SafetyProblemImportDao {
    //录入问题
    int addNewProblem(NewSafetyProblemDto newSafetyProblemDto);
    int getHashCount(@Param("hash") String hash);
    List<SafetyProblemImportDto> getProblemImportDtoByContiton(SafetyProblemImportInDto safetyProblemImportInDto);
    int getProblemImportDtoCount(SafetyProblemImportInDto safetyProblemImportInDto);
    SafetyProblemImportDto getSafetyProblemImportDtoById(@Param("problemID") int problemID, @Param("url") String url);
    int updateProblemImportDto(SafetyProblemImportDto safetyProblemImportDto);
    int deleteProblem(Integer id);

}
