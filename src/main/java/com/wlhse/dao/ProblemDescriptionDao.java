package com.wlhse.dao;


import com.wlhse.dto.ProblemDescriptionDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemDescriptionDao {

    int addProblemDescription(ProblemDescriptionDto problemDescriptionDto);

    int deleteProblemDescription(@Param("id") int id);

    int updateProblemDescription(ProblemDescriptionDto problemDescriptionDto);

    List<ProblemDescriptionDto> queryProblemDescription(ProblemDescriptionDto problemDescriptionDto);


}
