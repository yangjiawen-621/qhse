package com.wlhse.service.impl;

import com.wlhse.cache.JedisClient;
import com.wlhse.dao.CompanyDao;
import com.wlhse.dto.outDto.CompanyOutDto;
import com.wlhse.entity.CompanyPojo;
import com.wlhse.service.CompanyService;
import com.wlhse.util.R;
import com.wlhse.util.TreeUtil;
import com.wlhse.util.state_code.NR;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyDao companyDao;

    @Resource
    private TreeUtil treeUtil;
    @Resource
    JedisClient jedisClient;

    @Override
    public R listTreeCompany(Integer id) {
        R ok = R.ok();
        ok.put("data", treeUtil.getCompanyTree(companyDao.queryCompany(id)));
        return ok;
    }

    @Override
    public R listQhseTreeCompany(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        String employeeId = "";
        if (StringUtils.isNotBlank(token)) {
            Map<String, String> map = jedisClient.hGetAll(token);
            employeeId= map.get("employeeId");
        }
        String companyCode = companyDao.getCompanyByEmployeeId(Integer.valueOf(employeeId));
        R ok = R.ok();
        //职能部门
        if (companyCode.contains("0001")||companyCode.equals("00")){

            ok.put("data", treeUtil.getQhseCompanyTree(companyDao.queryQhseCompany()));
        }
        else {
            //基层单位
            List<CompanyPojo> resultList=new ArrayList<>();
            resultList.add(companyDao.getCompanyByCompanyCode(companyCode));
            ok.put("data",treeUtil.getQhseCompanyTree(resultList));
        }
        return ok;
    }

    @Override
    public String getCompanyOutDto(String sonName) {
        int max = companyDao.queryMaxLenth();
        List<CompanyOutDto> listCompanyOutSonDto = new ArrayList<>();
        List<CompanyOutDto> listCompanyOutSonDto1 = companyDao.getListCompanyOutSonDto(max, null);
        for (CompanyOutDto companyOutDto : listCompanyOutSonDto1) {
            String parentCode = companyOutDto.getSonCode().substring(0, 4);
            String parentName = companyDao.getCompanyOutDto(parentCode);
            companyOutDto.setParentCode(parentCode);
            companyOutDto.setParentName(parentName);
            listCompanyOutSonDto.add(companyOutDto);
        }
        return NR.r(listCompanyOutSonDto);
    }
}

