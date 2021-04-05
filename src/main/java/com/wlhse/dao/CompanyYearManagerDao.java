package com.wlhse.dao;

import com.wlhse.dto.inDto.CompanyYearManagerDto;
import com.wlhse.dto.inDto.ElementReviewDto;
import com.wlhse.dto.outDto.QHSECompanyYearManagerSysElementDto;
import com.wlhse.dto.outDto.QhseEvidenceAttatchDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CompanyYearManagerDao")
public interface CompanyYearManagerDao {
    //修改发布状态
    int updateAll(@Param("id") int id);

    //查询证据关联信息根据ID
    List<CompanyYearManagerDto> queryAll(CompanyYearManagerDto companyYearManagerDto);
    //删除信息
    int deleteAll(int id);
    //新增检查表
    int addCompanyYearManager(CompanyYearManagerDto companyYearManagerDto);

    List<Integer> getInputPersonId(int tableId);
}
