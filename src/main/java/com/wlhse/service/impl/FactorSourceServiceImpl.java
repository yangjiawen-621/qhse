package com.wlhse.service.impl;

import com.wlhse.dao.FactorSourceDao;
import com.wlhse.dto.FactorSourceDto;
import com.wlhse.service.FactorSourceService;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class FactorSourceServiceImpl implements FactorSourceService {

    @Resource
    private FactorSourceDao factorSourceDao;

    @Override
    public String getAll() {
        List<FactorSourceDto> list = factorSourceDao.getAll();
        return NR.r(list);
    }

    @Override
    public R getFactorSource(String factorCode) {
        String str1 = factorSourceDao.getFactorSourceCode(factorCode);
        String[] str2 = str1.split(",");
        Set<String> set= new HashSet<>(Arrays.asList(str2));
        List<FactorSourceDto> dep = new ArrayList<>();
        for (String s:set
        ) {
            FactorSourceDto a = factorSourceDao.getFactorSource(s);
            dep.add(a);
        }
        return R.ok().put("data",dep);
    }
}
