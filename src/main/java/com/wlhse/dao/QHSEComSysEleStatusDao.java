package com.wlhse.dao;

import com.wlhse.entity.QHSEComSysEleStatusPojo;
import org.springframework.stereotype.Repository;
import com.wlhse.dto.QHSEComSysEleStatusDto;

import java.util.List;

@Repository
public interface QHSEComSysEleStatusDao {

    //添加
    Integer addComstatus(QHSEComSysEleStatusDto qhseComSysEleStatusDto);
    //删除
    Integer deleteComstatus(Integer Id);
    //更改
    Integer updateComstatus(QHSEComSysEleStatusDto qhseComSysEleStatusDto);
    // 查询
    List<QHSEComSysEleStatusPojo> querryComstatus(QHSEComSysEleStatusDto  qhseComSysEleStatusDto);
}
