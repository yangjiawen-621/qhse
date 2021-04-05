package com.wlhse.service;

import com.wlhse.dto.QualityDangerRecordDto;
import com.wlhse.util.R;

import javax.servlet.http.HttpServletRequest;

public interface Quality_DangerRecordService {
    // 增
    R addDangerRecord(QualityDangerRecordDto dangerRecordDto, HttpServletRequest request);
    //删
    R deleteDangerRecord(QualityDangerRecordDto dangerRecordDto);
    //改
    R updateDangerRecord(QualityDangerRecordDto dangerRecordDto);
    //按ID查询
    String queryDangerRecordById(Integer id);
    //查询
    String queryDangerRecord(QualityDangerRecordDto dangerRecordDto);
    //问题验证
    R problemVerification(QualityDangerRecordDto dangerRecordDto);
}
