package com.wlhse.service;

import com.wlhse.dto.ReportDto;
import com.wlhse.dto.SampleDto;

import java.util.List;

public interface SampleService {
    public String addSample(List<SampleDto> sampleDtos);
}
