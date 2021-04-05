package com.wlhse.dao;

import com.wlhse.dto.QHSEQualityFillDto;
import com.wlhse.entity.QHSECompanySysElementsPojo;

import java.util.List;

public interface QHSECompanySysElementsDao {
    Integer addQHSEReport(QHSEQualityFillDto qhseQualityFillDto);
    Integer querryReport(QHSEQualityFillDto qhseQualityFillDto);//查询报告是否存在
    List<QHSECompanySysElementsPojo> querryQHSEReportElements(QHSEQualityFillDto qhseQualityFillDto);
    QHSECompanySysElementsPojo querryActualScoreByID(Integer id);//查询某条项目的得分 和 完成数目  By ID
    Integer updateQHSEReportElements(QHSEQualityFillDto qhseQualityFillDto);//给某一条项目打分
    Integer updateParentNodes(QHSEQualityFillDto qhseQualityFillDto);//修改所有父节点分数和completedcount
}
