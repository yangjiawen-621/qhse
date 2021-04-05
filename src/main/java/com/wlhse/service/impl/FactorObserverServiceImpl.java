package com.wlhse.service.impl;

import com.wlhse.dao.FactorObserverDao;
import com.wlhse.dto.FactorObserverDto;
import com.wlhse.service.FactorObserverService;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class FactorObserverServiceImpl implements FactorObserverService {

    @Resource
    private FactorObserverDao factorObserverDao;

    @Override
    public String getAll() {
        List<FactorObserverDto> list = factorObserverDao.getAll();
        return NR.r(list);
    }


    @Override
    public R getFactorObserver(String factorCode) {
        String str1 = factorObserverDao.getFactorObserverCode(factorCode);
        if (str1 != null) {
            String[] str2 = str1.split(",");
            Set<String> set = new HashSet<>(Arrays.asList(str2));
            List<FactorObserverDto> dep = new ArrayList<>();
            for (String s : set
            ) {
                FactorObserverDto a = factorObserverDao.getFactorObserver(s);
                dep.add(a);
            }
            return R.ok().put("data", dep);
        }
        return R.ok("该FactorCode无FactorObserver数据！");
    }
}
