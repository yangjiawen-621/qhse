package com.wlhse.dao;

import com.wlhse.dto.outDto.FilePropagationDetailDto;
import com.wlhse.dto.outDto.FilePropagationResultDto;
import com.wlhse.dto.outDto.ReadHistoryDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FilePropagationDetailDao {
    List<FilePropagationResultDto> getFilePropagationByStaffId(int staffId);

    int addNewDetail(FilePropagationDetailDto filePropagationDetailDto);

    int updateFilePropagationStatus(int filePropagationPlanDetailID,int staffId );

    Set<FilePropagationResultDto> queryAllPropagationDetailIdByFilePropagationId(Long filePropagationId);

    int deleteFilePropagationPlanDetail(int detailId);

    List<ReadHistoryDto> getReadHistory(Long propagationId);
}
