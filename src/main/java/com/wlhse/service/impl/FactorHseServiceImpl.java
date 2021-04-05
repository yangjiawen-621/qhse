package com.wlhse.service.impl;

import com.wlhse.dao.FactorHseDao;
import com.wlhse.dto.FactorHseDto;
import com.wlhse.service.FactorHseService;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class FactorHseServiceImpl implements FactorHseService {

    @Resource
    private  FactorHseDao factorHseDao;

    @Override
    public String getAll() {
        List<FactorHseDto> list = factorHseDao.getAll();
        return NR.r(list);
    }

    @Override
    public R getFactorHse(String factorCode) {
        String str1 = factorHseDao.getFactorHseCode(factorCode);
        String[] str2 = str1.split(",");
        Set<String> set= new HashSet<>(Arrays.asList(str2));
        List<FactorHseDto> dep = new ArrayList<>();
        for (String s:set
        ) {
            FactorHseDto a = factorHseDao.getFactorHse(s);
            dep.add(a);
        }
        return R.ok().put("data",dep);
    }
}
