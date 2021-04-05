package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.FactorDepartmentDao;
import com.wlhse.dao.QHSEAccidentDao;
import com.wlhse.dto.QHSEAccidentDto;
import com.wlhse.dto.outDto.FactorDepartmentOutDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.AccidentService;
import com.wlhse.service.FactorDepartmentService;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class FactorDepartmentServiceImpl implements FactorDepartmentService {
    @Resource
    private FactorDepartmentDao factorDepartmentDao;

    @Override
    public R queryFactorDepartment() {


        return R.ok().put("data",factorDepartmentDao.findAll());
    }

    @Override
    public R getFactorDepartment(String factorCode) {

        String str1 = factorDepartmentDao.getFactorDepartmentCode(factorCode);
        String[] str2 = str1.split(",");
        Set<String> set= new HashSet<>(Arrays.asList(str2));
        List<FactorDepartmentOutDto> dep = new ArrayList<>();
        for (String s:set
        ) {
            FactorDepartmentOutDto a = factorDepartmentDao.getFactorDepartment(s);
            dep.add(a);
        }
        return R.ok().put("data",dep);
    }

}
