package com.wlhse.service;

import com.wlhse.dto.QHSEQualityFillDto;
import com.wlhse.entity.QHSECompanySysElementsPojo;

import java.util.List;

public interface QHSEQualityFillService {
    String addQHSEReport(QHSEQualityFillDto qhseQualityFillDto);
    String querryQHSEReportElements(QHSEQualityFillDto qhseQualityFillDto);
    String updateQHSEReportElements(QHSEQualityFillDto qhseQualityFillDto);
}
