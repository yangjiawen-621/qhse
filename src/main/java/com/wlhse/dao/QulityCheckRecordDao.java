package com.wlhse.dao;

import com.wlhse.entity.QulityCheckRecordPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * tobing
 */
@Repository
public interface QulityCheckRecordDao {

    // 添加质量检查记录
    void addQualityCheckRecord(QulityCheckRecordPojo qulityCheckRecordPojo);

    // 查询质量检查记录
    List<QulityCheckRecordPojo> queryQualityCheckRecord();

    // 删除质量检查记录
    void deleteQualityCheckRecord(Integer id);

    // 更新质量检查记录
    void updateQualityCheckRecord(QulityCheckRecordPojo qulityCheckRecordPojo);

    // 根据质量检查checkId查询质量检查记录
    List<QulityCheckRecordPojo> queryQulityCheckRecordByCheckId(String checkId);

    // 根据id查询质量检查记录
    QulityCheckRecordPojo queryQualityCheckRecordById(String id);
}
