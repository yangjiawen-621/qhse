package com.wlhse.service;


import com.wlhse.util.R;

import javax.servlet.http.HttpServletRequest;

public interface CompanyService {

    R listTreeCompany(Integer id);

    R listQhseTreeCompany(HttpServletRequest request);

    String getCompanyOutDto(String sonName);

//    R listContractingCompany(BaseGetDto baseGetDto,String name);
}
