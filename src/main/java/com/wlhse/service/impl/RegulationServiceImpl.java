package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.RegulationDao;
import com.wlhse.dto.inDto.RegulationInDto;
import com.wlhse.entity.RegulationPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.RegulationService;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class RegulationServiceImpl implements RegulationService {

    @Resource
    private RegulationDao regulationDao;

    @Value("${RESOURCES_URL}")
    private String url;

    @Override
    @Transactional
    public String deleteRegulation(Integer id) {
        if (regulationDao.deleteRegulation(id) <= 0)
            throw new WLHSException("删除失败");
        return NR.r();
    }

    @Override
    @Transactional
    public String addRegulationPojo(RegulationPojo regulationPojo) {
        if (regulationDao.addRegulationPojo(regulationPojo) <= 0)
            throw new WLHSException("新增失败");
        return NR.r();
    }

    @Override
    public String getRegulationPojoByCondition(RegulationInDto regulationInDto) {
        regulationInDto.setUrl(url);
        if (regulationInDto.getType().equals("all"))
            return NR.r(regulationDao.getRegulationPojoByCondition(regulationInDto));
        int total = regulationDao.getTotalRegulationPojoByCondition(regulationInDto);//数据总数
        PageHelper.startPage(regulationInDto.getPageIdx(), regulationInDto.getPageSize());
        List<RegulationPojo> list = regulationDao.getRegulationPojoByCondition(regulationInDto);
        return NR.r(list, total, regulationInDto.getPageIdx());
    }

    @Override
    public String getRegulationPojoById(Integer id) {
        return NR.r(regulationDao.getRegulationPojoById(id, url));
    }

    @Override
    @Transactional
    public String updateRegulationPojo(RegulationPojo regulationPojo) {
        if (regulationDao.updateRegulationPojo(regulationPojo) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    public String listRegulationNameAndCode(RegulationInDto regulationInDto) {
        return NR.r(regulationDao.queryRegulationNameAndCode(regulationInDto.getRegName()));
    }
}
