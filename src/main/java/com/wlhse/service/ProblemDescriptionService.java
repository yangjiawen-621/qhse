package com.wlhse.service;

import com.wlhse.dto.ProblemDescriptionDto;
import com.wlhse.util.R;

public interface ProblemDescriptionService {
    //增
    R addProblemDescription(ProblemDescriptionDto problemDescriptionDto);
    //删
    R deleteProblemDescription(int id);
    //改
    R updateProblemDescription(ProblemDescriptionDto problemDescriptionDto);
    //条件查询
    R queryProblemDescription (ProblemDescriptionDto problemDescriptionDto);

}
