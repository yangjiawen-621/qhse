package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.QHSEEventDao;
import com.wlhse.dto.QHSEEventDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.EventService;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventServiceimpl implements EventService {
    @Resource
    private QHSEEventDao qhseeventDao;

    @Override
    public R addEvent(QHSEEventDto qhseeventDto) {
        if(qhseeventDao.addEvent(qhseeventDto)<=0)
            throw new WLHSException("新增失败");
        return R.ok();
    }

    @Override
    public R deleteEvent(Integer id) {
        if(qhseeventDao.deleteEvent(id)<=0)
            throw new WLHSException("更新失败");
        return R.ok();
    }

    @Override
    public R updateEvent(QHSEEventDto qhseeventDto) {
        if(qhseeventDao.updateEvent(qhseeventDto)<=0)
            throw new WLHSException("更新失败");
        return R.ok();
    }

    @Override
    public String queryEvent(QHSEEventDto qhseeventDto) {
        int total = qhseeventDao.queryTotal(qhseeventDto);
        int pageIdx = qhseeventDto.getPageIdx();
        PageHelper.startPage(pageIdx, qhseeventDto.getPageSize());
        List<QHSEEventDto> list = qhseeventDao.queryEvent(qhseeventDto);
        return NR.r(list, total, pageIdx);
    }

    @Override
    public R queryEventById(int id) {
        QHSEEventDto qhseeventDto = qhseeventDao.queryEventById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("data", qhseeventDto);
        return R.ok(map);
    }

}
