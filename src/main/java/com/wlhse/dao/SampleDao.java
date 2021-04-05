package com.wlhse.dao;

import com.wlhse.dto.ExcelUploadSampleDto;
import com.wlhse.dto.SampleDto;

import java.util.List;

public interface SampleDao {
    public Integer addSample(SampleDto sampleDto);
    public Integer deleteSample(Integer reportId);
    public Integer excelUploadSamples(List<ExcelUploadSampleDto> samples);
}
