package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.QHSEAccidentDao;
import com.wlhse.dto.QHSEAccidentDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.AccidentService;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccidentServiceimpl implements AccidentService {
    @Resource
    private QHSEAccidentDao qhseaccidentDao;

    @Override
    public R addAccident(QHSEAccidentDto qhseaccidentDto) {
        if(qhseaccidentDao.addAccident(qhseaccidentDto)<=0)
            throw new WLHSException("新增失败");
        return R.ok();
    }

    @Override
    public R deleteAccident(Integer id) {
        if(qhseaccidentDao.deleteAccident(id)<=0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Override
    public R updateAccident(QHSEAccidentDto qhseaccidentDto) {
        if(qhseaccidentDao.updateAccident(qhseaccidentDto)<=0)
            throw new WLHSException("更新失败");
        return R.ok();
    }

    @Override
    public String queryAccident(QHSEAccidentDto qhseaccidentDto) {
        int total = qhseaccidentDao.queryTotal(qhseaccidentDto);
        int pageIdx = qhseaccidentDto.getPageIdx();
        PageHelper.startPage(pageIdx, qhseaccidentDto.getPageSize());
        List<QHSEAccidentDto> list = qhseaccidentDao.queryAccident(qhseaccidentDto);
        return NR.r(list, total, pageIdx);
    }

    @Override
    public R queryAccidentById(int id) {
        QHSEAccidentDto qhseaccidentDto = qhseaccidentDao.queryAccidentById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("data", qhseaccidentDto);
        return R.ok(map);
    }
}
