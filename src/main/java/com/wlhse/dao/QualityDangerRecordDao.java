package com.wlhse.dao;

import com.wlhse.dto.QualityDangerRecordDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityDangerRecordDao {

    //新增隐患记录
    int addDangerRecord(QualityDangerRecordDto dangerRecordDto);

    //删除隐患记录
    int deleteDangerRecord(QualityDangerRecordDto dangerRecordDto);

    //修改隐患记录
    int updateDangerRecord(QualityDangerRecordDto dangerRecordDto);

    //按ID查询隐患记录
    default List<QualityDangerRecordDto> queryDangerRecordById(@Param("id") Integer id) {
        return null;
    }

    //条件查询隐患记录
    int queryTotal(QualityDangerRecordDto dangerRecordDto);

    List<QualityDangerRecordDto> queryDangerRecord(QualityDangerRecordDto dangerRecordDto);

    //问题整改
    int problemVerification(QualityDangerRecordDto dangerRecordDto);

}
