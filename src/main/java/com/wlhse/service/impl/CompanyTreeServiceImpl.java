package com.wlhse.service.impl;

import com.wlhse.dao.CompanyTreeDao;
import com.wlhse.entity.CompanyPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.CompanyTreeService;
import com.wlhse.util.SortCodeUtil;
import com.wlhse.util.state_code.NR;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CompanyTreeServiceImpl implements CompanyTreeService {
    @Resource
    private CompanyTreeDao companyTreeDao;

    @Resource
    private SortCodeUtil sortCodeUtil;

    @Override
    @Transactional
    public String addCompanyTreeNode(CompanyPojo companyPojo) {
        //可以使用Validator
        if (StringUtils.isBlank(companyPojo.getName()))
            throw new WLHSException("输入为空");
//        if (companyDao.getNameCount(companyPojo.getName()) > 0)
//            throw new WLHSException("单位已存在");
        String parent = companyPojo.getCompanyCode();
        String code = companyTreeDao.queryChildCode(parent);
        if (StringUtils.isNotBlank(code))
            companyPojo.setCompanyCode(sortCodeUtil.next(code));
        else
            companyPojo.setCompanyCode(parent + "01");

        if (companyTreeDao.addCompanyTreeNode(companyPojo) <= 0)
            throw new WLHSException("新增失败");
        return NR.r();
    }

    @Override
    @Transactional
    public String deleteCompany(String companyCode) {
        if (companyTreeDao.deleteCompanyTreeNode(companyCode) <= 0)
            throw new WLHSException("删除失败");
        return NR.r();
    }

    @Override
    @Transactional
    public String updateCompany(CompanyPojo companyPojo) {
        if (companyTreeDao.updateCompanyTreeNode(companyPojo) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

}
