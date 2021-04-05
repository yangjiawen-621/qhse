package com.wlhse.dao;

import com.wlhse.dto.QualityYearManagerDto;

import com.wlhse.dto.QualityYearManagerDtoWithEmployeeId;
import com.wlhse.dto.inDto.QualityYearElementsDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
public interface QualityYearManagerDao {
    List<QualityYearManagerDto> queryAll(QualityYearManagerDto qualityYearManagerDto);

    int deleteAll(int id);

    int addCompanyYearManager(QualityYearManagerDtoWithEmployeeId qualityYearManagerDtoWithEmployeeId);


    List<QualityYearElementsDto> queryYearElement(QualityYearElementsDto qualityYearManagerDto);

    int querySchedule(@Param("code") String code, String companyCode, String year);

    int querySchdules(String code,String companyCode,String year);
}
