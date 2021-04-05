package com.wlhse.dao;


import com.wlhse.dto.QualityFileAuditDto;
import com.wlhse.dto.QualityFileAuditRecordDto;

import com.wlhse.dto.inDto.QualityYearElementsDto;
import com.wlhse.dto.inDto.YearElementsDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QualityFileAuditDao {
    //分页+查询存在的记录
    int queryTotal(QualityFileAuditDto qualityFileAuditDto);

    List<QualityFileAuditDto> queryExistFile(QualityFileAuditDto qualityFileAuditDto);

    //新增审核记录
    int addFileAudit(QualityFileAuditDto qualityFileAuditDto);

    //删除审核记录
    int deleteFileAudit(@Param("id") Integer id);

    //增加审核记录
    int addFileAuditRecord(QualityFileAuditRecordDto qualityFileAuditRecordDto);

    //查询审核记录的ID
    List<QualityFileAuditRecordDto> queryRecordId(QualityFileAuditRecordDto qualityFileAuditRecordDto);

    //删除审核历史记录
    int deleteFileAuditRecord(@Param("id1") Integer id1);

    //更新文件审核通过与否状态
    int updateStatus(QualityFileAuditRecordDto qualityFileAuditRecordDto);

    //对结点打分
    int updateScore(QualityFileAuditRecordDto qualityFileAuditRecordDto);

    //获取结点分数
    List<QualityFileAuditRecordDto> getScore(QualityFileAuditRecordDto qualityFileAuditRecordDto);

    //获取结点审核状态
    List<QualityFileAuditRecordDto> getStatus(QualityFileAuditRecordDto qualityFileAuditRecordDto);

    //更改要素表中的审核状态
    int updateCheckStatus(QualityYearElementsDto yearElementsDto);

    /**
     * 查询所有文件审核
     *
     * @return 文件审核结果集
     */
    List<QualityFileAuditDto> getAllFileAudit();

    /**
     * 不录入文件审核
     *
     * @param qualityFileAuditRecordDto 文件审核记录字段
     */
    void noPassReasonFileAudit(QualityFileAuditRecordDto qualityFileAuditRecordDto);
}
