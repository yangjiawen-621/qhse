package com.wlhse.service;


import com.wlhse.dto.outDto.FilePropagationDetailDto;
import com.wlhse.entity.FilePropagationPOJO;
import com.wlhse.entity.FilePropagationPOJO1;
import com.wlhse.util.R;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FilePropagationPlanService {
    R releaseNewFilePropagationPlan(FilePropagationPOJO1 filePropagationPOJO, HttpServletRequest request);
    R getFilePropagationPlanDetailByStaffId(HttpServletRequest request);
    R readFilePropagation(HttpServletRequest request,int detailId);

    R getAllFilePropagation();

    R getFilePropagationDetailIdByPropagationId(Long filePropagationId);

    R insertNewFilePropagationDetail(List<FilePropagationDetailDto> filePropagationDetailDto);

    R deleteFilePropagationPlan(Long id);

    R deleteFilePropagationPlanDetail(int id);

    R getFilePropagationPlanDetailByStaffIdInPage(HttpServletRequest request,int pageNum);

    R getReadHistoryByPropagationId(Long propagationId);

}
