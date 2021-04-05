package com.wlhse.service;

import com.wlhse.dto.CompanyYearManagerDtoWithEmployeeId;
import com.wlhse.dto.inDto.CompanyYearManagerDto;
import com.wlhse.util.R;

import javax.servlet.http.HttpServletRequest;

public interface CompanyYearManagerService {

    //修改审核人状态
    R updateStatus(int id);
    //查询信息
    R queryAll(CompanyYearManagerDtoWithEmployeeId companyYearManagerDto, HttpServletRequest request);
    //删除信息
    R deleteALL(int id);
    //新增年度检查表
    R addCompanyYearManager(CompanyYearManagerDtoWithEmployeeId companyYearManagerDto,HttpServletRequest request);
}
