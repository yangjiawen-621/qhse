package com.wlhse.dao;

import com.wlhse.entity.ReportUnitPricePojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportUnitPriceDao {
    int addUnitPrice(ReportUnitPricePojo reportUnitPricePojo);

    int getUnitPriceCount(@Param("costYear") String costYear, @Param("reportType") String reportType);

    int updateUnitPrice(ReportUnitPricePojo reportUnitPricePojo);

    List<ReportUnitPricePojo> getUnitPriceByContiton(ReportUnitPricePojo reportUnitPricePojo);

    ReportUnitPricePojo getUnitPriceById(@Param("unitPriceID") int unitPriceID, @Param("url") String url);

    int deleteUnitPriceById(Integer unitPriceID);
}
