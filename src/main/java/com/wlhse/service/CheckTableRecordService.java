package com.wlhse.service;

import com.wlhse.dto.QualityCheckDto;
import com.wlhse.entity.QualityCheckTableRecord;
import com.wlhse.util.R;


public interface CheckTableRecordService {

    //添加配置要素
    R saveQualityCheckTableRecord(QualityCheckTableRecord quility);

    //回显节点
    R showBackElement(Integer QualityCheckID);
}
