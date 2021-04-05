package com.wlhse.service;

import com.wlhse.dto.QHSEAccidentDto;
import com.wlhse.util.R;

public interface FactorDepartmentService {

    R queryFactorDepartment();
    R getFactorDepartment(String factorCode);
}
