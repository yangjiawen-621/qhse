package com.wlhse.service;


import com.wlhse.dto.QualityCheckTableRecordAttachInfoDto;
import com.wlhse.dto.inDto.FilePropagationFileInfo;
import com.wlhse.util.R;

public interface UploadService {

    String uploadEmployees(String path) throws Exception;

    String uploadReports(String path) throws Exception;

    R uploadCheckList(String path) throws Exception;

    R uploadQHSEManageSysElements(String path) throws Exception;//管理要素审核excel录入数据库

    boolean insertFilePropagationFileRecord(FilePropagationFileInfo filePropagationFileInfo);

    boolean insertAttachInfoDto(QualityCheckTableRecordAttachInfoDto qualityCheckTableRecordAttachInfoDto);

    R uploadQualityCheck(String path) throws Exception;

    R uploadQualityManageSysElements(String path) throws Exception;//要素审核excel录入数据库

    R addQualityExcel(String path) throws Exception;//要素审核excel录入数据库
}
