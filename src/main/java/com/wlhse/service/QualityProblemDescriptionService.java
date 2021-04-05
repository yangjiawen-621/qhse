package com.wlhse.service;

import com.wlhse.dto.QualityProblemDescriptionDto;
import com.wlhse.util.R;

public interface QualityProblemDescriptionService {
    //增
    R addProblemDescription(QualityProblemDescriptionDto problemDescriptionDto);
    //删
    R deleteProblemDescription(int  id);
    //改
    R updateProblemDescription(QualityProblemDescriptionDto problemDescriptionDto);
    //条件查询
    String queryProblemDescription (QualityProblemDescriptionDto problemDescriptionDto);

}
