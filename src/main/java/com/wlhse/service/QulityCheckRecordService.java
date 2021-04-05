package com.wlhse.service;

import com.wlhse.entity.QulityCheckRecordPojo;
import com.wlhse.util.R;

/**
 * tobing
 */
public interface QulityCheckRecordService {
    R addQulityCheckRecord(QulityCheckRecordPojo qulityCheckRecordPojo);

    R queryQulityCheckRecord();

    R deleteQulityCheckRecord(Integer id);

    R updateQulityCheckRecord(Integer id, QulityCheckRecordPojo qulityCheckRecordPojo);

    R queryQulityCheckRecordByCheckId(String checkId);

    R queryQulityCheckRecordById(String id);
}
