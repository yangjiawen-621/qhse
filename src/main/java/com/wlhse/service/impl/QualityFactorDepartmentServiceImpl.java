package com.wlhse.service.impl;

import com.wlhse.dao.QualityFactorDepartmentDao;
import com.wlhse.dto.outDto.QualityFactorDepartmentOutDto;
import com.wlhse.service.FactorDepartmentService;
import com.wlhse.service.QualityFactorDepartmentService;
import com.wlhse.util.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class QualityFactorDepartmentServiceImpl implements QualityFactorDepartmentService {
    @Resource
    private QualityFactorDepartmentDao qualityFactorDepartmentDao;

    @Override
    public R queryFactorDepartment() {
        return R.ok().put("data", qualityFactorDepartmentDao.findAll());
    }

    @Override
    public R getFactorDepartment(String factorCode) {

        List<QualityFactorDepartmentOutDto> dep = new ArrayList<>();
        try {
            String str1 = qualityFactorDepartmentDao.getFactorDepartmentCode(factorCode);
            String[] str2 = str1.split(",");
            Set<String> set = new HashSet<>(Arrays.asList(str2));
            for (String s : set
            ) {
                QualityFactorDepartmentOutDto a = qualityFactorDepartmentDao.getFactorDepartment(s);
                dep.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok().put("data", dep);
    }

}
