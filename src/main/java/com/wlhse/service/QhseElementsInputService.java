package com.wlhse.service;

import com.wlhse.dto.QualityFileInputInfoDto;
import com.wlhse.dto.inDto.ElementEvidenceAttachInDto;
import com.wlhse.entity.ElementInputFileInfo;
import com.wlhse.util.R;

import javax.servlet.http.HttpServletRequest;


public interface QhseElementsInputService {

    R addElementEvidenceAttach(ElementEvidenceAttachInDto elementEvidenceAttachInDto, HttpServletRequest request);
    R queryAll(ElementEvidenceAttachInDto elementEvidenceAttachInDto);
    String queryOriginFileName (String newElementFileName);
    //质量原文件名
    String queryQualityOriginFileName (String newElementFileName);
    void insertNewOriginFileName(ElementInputFileInfo elementInputFileInfo);
    void insertNewOriginFileNames(QualityFileInputInfoDto qualityFileInputInfoDto);
  //  void updateNewOriginFileName(ElementInputFileInfo elementInputFileInfo);
    R submitInputResult(int tableId,int tag);

    R notInvolve(int elementId,HttpServletRequest request);
}
