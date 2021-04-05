package com.wlhse.service;
import com.wlhse.dto.QHSEComSysEleStatusDto;

public interface QHSEComSysEleStatusService {
    // 添加公司状态
    String addQHSEComSysEleStatus(QHSEComSysEleStatusDto qhseComSysEleStatusDto);
    // 删除公司状态
    String deleteQHSEComSysEleStatus(Integer id);
    //修改公司状态
    String updateQHSEComSysEleStatus(QHSEComSysEleStatusDto qhseComSysEleStatusDto);
    //查询公司状态
    String querryQHSEComSysEleStatus(QHSEComSysEleStatusDto qhseComSysEleStatusDto);
}
