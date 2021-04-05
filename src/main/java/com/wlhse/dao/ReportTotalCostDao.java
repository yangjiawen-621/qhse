package com.wlhse.dao;

import com.wlhse.dto.outDto.ReportCostManageOutDto;
import com.wlhse.entity.ReportTotalCostPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportTotalCostDao {
    int addTotalCost(ReportTotalCostPojo reportTotalCostPojo);

    int getTotalCostCount(@Param("costYear") String costYear,@Param("category") String category);

    int updateTotalCost(ReportTotalCostPojo reportTotalCostPojo);

    List<ReportTotalCostPojo> getTotalCostByContiton(ReportTotalCostPojo reportTotalCostPojo);

    List<ReportCostManageOutDto> getReportCostManageAnalysis(ReportTotalCostPojo reportTotalCostPojo);
}
