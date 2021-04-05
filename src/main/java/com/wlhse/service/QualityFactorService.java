package com.wlhse.service;

import com.wlhse.dto.inDto.FactorDto;
import com.wlhse.dto.inDto.QualityFactorDto;
import com.wlhse.util.R;

public interface QualityFactorService {

    R queryFactor(QualityFactorDto factorDto);
}
