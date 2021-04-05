package com.wlhse.service;

import com.wlhse.dto.inDto.QualityCheckInDto;
import com.wlhse.util.R;

public interface QualityCheckListService {
    R getTreeDto(int tag);

    R addQualityCheckNode(QualityCheckInDto qualityCheckInDto);

    R updateQualityCheck(QualityCheckInDto qualityCheckInDto);

    R deleteQualityCheck(int id);

    R getList();

    R deleteQualityCheckNode(String code);

    R queryNodeByCode(String code);

    R getTreeDto(String checkedListCode);
}
