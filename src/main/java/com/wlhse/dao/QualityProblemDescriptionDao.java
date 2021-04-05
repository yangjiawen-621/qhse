package com.wlhse.dao;


import com.wlhse.dto.QualityProblemDescriptionDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityProblemDescriptionDao {

    int addProblemDescription(QualityProblemDescriptionDto problemDescriptionDto);

    int deleteProblemDescription(int id);

    int updateProblemDescription(QualityProblemDescriptionDto problemDescriptionDto);

    List<QualityProblemDescriptionDto> queryProblemDescription(QualityProblemDescriptionDto problemDescriptionDto);


}
