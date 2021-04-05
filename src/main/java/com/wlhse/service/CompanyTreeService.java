package com.wlhse.service;

import com.wlhse.entity.CompanyPojo;
import com.wlhse.util.R;

public interface CompanyTreeService {
    //增加company
    String addCompanyTreeNode(CompanyPojo companyPojo);
    //删除company，删除失败时获取相关表信息
    String deleteCompany(String companyCode);
    //修改company
    String updateCompany(CompanyPojo companyPojo);

//    public R addContractingCompany(CompanyPojo companyPojo);
}
