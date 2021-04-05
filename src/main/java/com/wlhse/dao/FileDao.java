package com.wlhse.dao;

import com.wlhse.dto.QualityCheckTableRecordAttachInfoDto;
import com.wlhse.dto.inDto.FileInDto;
import com.wlhse.dto.inDto.FilePropagationFileInfo;

import com.wlhse.entity.FilePojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileDao {
    //新增文件
    int addFile(FilePojo filePojo);
    //条件查询文件
    List<FilePojo> queryFilesByCondition(FileInDto fileInDto);
    //根据id查询文件
    FilePojo queryFileById(@Param("id") Integer id);
    //更改
//    int updateFile(FilePojo filePojo);
    //删除
    int deleteFile(@Param("id") Integer id);

    int queryTotalByCondition(FileInDto fileInDto);

    //insert file propagation file
    int insertFilePropagationFile(FilePropagationFileInfo filePropagationFileInfo);

    int updateFilePropagationID(String filePath,Long id);

    List<FilePropagationFileInfo> getFileInfoByPropagationId(Long id);

    String getFilePropagationOriginFileName(String fileName);

    Integer InsertQualityAttachInfo(QualityCheckTableRecordAttachInfoDto qualityCheckTableRecordAttachInfoDto);

    String getQualityAttachOriginFileName(String fileName);

    Integer deleteAttachOriginFileName(String fileName);
}
