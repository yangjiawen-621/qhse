package com.wlhse.dao;

import com.wlhse.dto.CheckRecordDto;
import com.wlhse.dto.CheckRecordTreeDto;
import com.wlhse.dto.inDto.CheckRecordPOJO;
import com.wlhse.entity.CheckConditionPOJO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CheckRecordDao {

    Integer addCheckRecord(CheckRecordDto checkRecordDto);

    //查询所有
    List<CheckRecordDto> queryAll();

    List<CheckRecordDao> queryByCondition(CheckConditionPOJO checkConditionPOJO);

    List<CheckRecordTreeDto> queryAllByParentCode(@Param("code") String code);

    int updateCheckRecord(CheckRecordDto checkRecordDto);

    int deleteCheckRecord(int id);

    CheckRecordDto getCheckRecord( CheckRecordPOJO checkRecordPOJO);

}
