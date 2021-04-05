package com.wlhse.service;

import com.wlhse.dto.DangerRecordDto;
import com.wlhse.util.R;

import javax.servlet.http.HttpServletRequest;

public interface DangerRecordService {
    // 增
    R addDangerRecord(DangerRecordDto dangerRecordDto, HttpServletRequest request);

    //删
    R deleteDangerRecord(DangerRecordDto dangerRecordDto);

    //改
    R updateDangerRecord(DangerRecordDto dangerRecordDto);

    //按ID查询
    R queryDangerRecordById(Integer id);

    //查询
    R queryDangerRecord(DangerRecordDto dangerRecordDto);

    //问题验证
    R problemVerification(DangerRecordDto dangerRecordDto);

    /**
     * 查询隐患项最多的公司
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return R
     */
    R queryMostDangerRecord(String startDate, String endDate);
}
