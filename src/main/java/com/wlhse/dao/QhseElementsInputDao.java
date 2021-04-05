package com.wlhse.dao;

import com.wlhse.dto.QualityFileInputInfoDto;
import com.wlhse.dto.inDto.ElementEvidenceAttachInDto;
import com.wlhse.entity.ElementInputFileInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QhseElementsInputDao {
    //查询证据附件
    ElementEvidenceAttachInDto query(ElementEvidenceAttachInDto elementEvidenceAttachInDto);

    //增加证据附件
    int add(ElementEvidenceAttachInDto elementEvidenceAttachInDto);

    //增加附件
    int addAttach(ElementEvidenceAttachInDto elementEvidenceAttachInDto);

    //修改证据
    int update(ElementEvidenceAttachInDto elementEvidenceAttachInDto);

    //修改附件
    int updateAttach(ElementEvidenceAttachInDto elementEvidenceAttachInDto);

    //更改状态
    int updateStatus(Integer id,int checkPersonId);

    //查询要素附件原始名
    String queryOriginFileName(@Param("newElementFileName") String newElementFileName);
    String queryQualityOriginFileName(@Param("NewFileName") String NewFileName);

    //删除要素附件新旧名称
    int deleteNewOriginFileName(@Param("id") Integer id);

    //插入要素附件新旧名称
    int insertNewOriginFileName(ElementInputFileInfo elementInputFileInfo);
    //质量插入新旧文件名称
    int insertNewOriginFileNames(QualityFileInputInfoDto qualityFileInputInfoDto);
    //将对应证据表id存入方便删除
    int updateNewOriginFileName(ElementInputFileInfo elementInputFileInfo);


    List<Integer> getCompanyManagerSysElementId(int tableId);

    int deleteFromCompanyManagerSysElement(int tableId);

    List<Integer> getCompanyYearManagerSysElementEvidenceId(int elementId);

    int deleteFromCompanyYearManagerSysElementEvidence(int elementId);

    int deleteFromCompanyYearManagerSysElementEvidenceAttach(int evidenceId);

    //根据证据信息获取TableId
    int getQHSEYearManagerTableIdByElementId(int id);

    //修改CheckStatus状态
    int updateCheckStatus(int tableId,int oldStatus,int newStatus);

    int updateCheckStatusByElementId(@Param("id") Integer id);
}
