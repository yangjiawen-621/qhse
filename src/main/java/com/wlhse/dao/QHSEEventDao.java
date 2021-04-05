package com.wlhse.dao;

import com.wlhse.dto.QHSEEventDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QHSEEventDao {

    int addEvent(QHSEEventDto qhseeventDto);

    int deleteEvent(@Param("id") Integer id);

    int updateEvent(QHSEEventDto qhseeventDto);

    //时间+分页查询
    int queryTotal(QHSEEventDto qhseeventDto);
    List<QHSEEventDto> queryEvent(QHSEEventDto qhseeventDto);

    //按ID查询
    QHSEEventDto queryEventById(@Param("id") Integer id);
}
