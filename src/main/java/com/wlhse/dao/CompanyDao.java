package com.wlhse.dao;

import com.wlhse.dto.ReportDto;
import com.wlhse.dto.outDto.CompanyOutDto;
import com.wlhse.entity.CompanyPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDao {
    List<CompanyPojo> queryCompany(Integer id);//根据员工id 查询其所在公司及下属公司

    String getCompanyByEmployeeId(int id);

    List<CompanyPojo> queryQhseCompany();//查询所有公司

    String queryByCompanyName(String companyName);

    String queryByCompanyCode(String companyCode);

    int queryMaxLenth();

    List<CompanyOutDto> getListCompanyOutSonDto(@Param("length") int length, @Param("name") String name);

    String getCompanyOutDto(String companyCode);

    String getCompanyCode(@Param("companyCode") String companyCode, @Param("name") String name);

    int getNameCount(@Param("name") String name);

    List<CompanyPojo> queryContractingCompany(@Param("name") String name);

    int getContractingCompanyCount(@Param("name") String name);

    public List<CompanyPojo> subCompanysCodeByCompanyCode(String companyCode);//根据公司id查询公司及其一级子公司信息

    public List<CompanyPojo> subCompanysCodeByPersonId(Integer id);

    String querryCode(String companyCode);

    CompanyPojo getCompanyByCompanyCode(String code);

}
