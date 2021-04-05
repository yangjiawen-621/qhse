package com.wlhse.service;

import com.wlhse.dto.QHSEEventDto;
import com.wlhse.util.R;

public interface EventService {

    //增
    R addEvent(QHSEEventDto qhseeventDto);
    //删
    R deleteEvent(Integer id);
    //改
    R updateEvent(QHSEEventDto qhseeventDto);
    //分页查询
    String queryEvent (QHSEEventDto qhseeventDto);
    //按ID查询
    R queryEventById(int id);
}
