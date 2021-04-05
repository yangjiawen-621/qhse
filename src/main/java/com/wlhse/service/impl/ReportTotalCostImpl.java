package com.wlhse.service.impl;

import com.wlhse.dao.ReportTotalCostDao;
import com.wlhse.dto.outDto.ReportCostManageOutDto;
import com.wlhse.entity.ReportTotalCostPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.ReportTotalCostService;
import com.wlhse.util.HashUtil;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportTotalCostImpl implements ReportTotalCostService {
    @Resource
    private ReportTotalCostDao reportTotalCostDao;

    @Value("${RESOURCES_URL}")
    private String url;

    @Override
    @Transactional
    public R newTotalCost(ReportTotalCostPojo reportTotalCostPojo) {
        String costYear = reportTotalCostPojo.getCostYear();
        String category = reportTotalCostPojo.getCategory();
        if (reportTotalCostDao.getTotalCostCount(costYear,category) != 0)
            throw new WLHSException("重复新增年度总金额,请检查!");
        reportTotalCostDao.addTotalCost(reportTotalCostPojo);
        return R.ok();
    }

    @Override
    public String queryTotalCost(ReportTotalCostPojo reportTotalCostPojo) {
        reportTotalCostPojo.setUrl(url);
        Float totalCost = getReportCostManageAnalysisOutDtoList(reportTotalCostPojo);
        List<ReportTotalCostPojo> list = reportTotalCostDao.getTotalCostByContiton(reportTotalCostPojo);
        for (ReportTotalCostPojo r : list){
            r.setSurplusTotalCost(r.getTotalCost()-totalCost);
        }
        return NR.r(list);
    }

    public Float getReportCostManageAnalysisOutDtoList(ReportTotalCostPojo reportTotalCostPojo) {
        List<ReportCostManageOutDto> costManageList = reportTotalCostDao.getReportCostManageAnalysis(reportTotalCostPojo);
        Float totalCost =0.00f;
        for (ReportCostManageOutDto r : costManageList){
            totalCost = totalCost+r.getReportTypeTotalCost();
        }
        return totalCost;
    }

    @Override
    public String updateTotalCost(ReportTotalCostPojo reportTotalCostPojo) {
        if (reportTotalCostDao.updateTotalCost(reportTotalCostPojo) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }


}
