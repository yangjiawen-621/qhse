package com.wlhse.service;




import com.wlhse.dto.QualityYearManagerDtoWithEmployeeId;
import com.wlhse.dto.inDto.QualityYearElementsDto;
import com.wlhse.util.R;

import javax.servlet.http.HttpServletRequest;

public interface QualityYearManagerService {

     R queryAll(QualityYearManagerDtoWithEmployeeId companyYearManagerDto, HttpServletRequest request);

     R deleteALL(int id);

     R addCompanyYearManager(QualityYearManagerDtoWithEmployeeId companyYearManagerDto, HttpServletRequest request);

     R queryYearElements(QualityYearElementsDto qualityYearManagerDto);

     R addYearElement(QualityYearElementsDto yearElementsDto);
}
