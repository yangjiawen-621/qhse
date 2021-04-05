package com.wlhse.dao;

import com.wlhse.dto.QualityAuditRecord;
import com.wlhse.dto.QualityCheckTableRecordDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityCheckTableRecordDao {
    Integer batchInsertTree(List<QualityCheckTableRecordDto> QualityCheckLists);

    Integer deleteChickList(Integer qualityCheckID);

    List<QualityCheckTableRecordDto> queryCheckTreeByID(Integer qualityCheckID);
    Integer addInformAndAttach(QualityCheckTableRecordDto qualityCheckTableRecordDto);
    Integer queryID(Integer id);
    Integer insertCheckInfo(QualityAuditRecord qualityAuditRecord);
    Integer updateCheckInfo(QualityAuditRecord qualityAuditRecord);
    QualityAuditRecord queryCheckInfo(Integer id);

    Integer insertConfigElement(String elementId);

    List<String> findCheckedListCodeById(Integer QualityCheckID);

    List<String> findQualityId(Integer qualityId);

}
