package com.wlhse.dao;

import com.wlhse.dto.QualityCheckDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityCheckDao {
    Integer addQualityCheck(QualityCheckDto qualityCheckDto);
    Integer deleteQualityCheck(Integer qualityCheckID);
    Integer updateQualityCheck(QualityCheckDto qualityCheckDto);
    String queryCheckListCodeById(Integer id);//code 可能不只一个，用';'分隔
    List<QualityCheckDto> queryAllTable();
    List<QualityCheckDto> queryTableByDateCom(String company,String beginDate,String endDate);
    List<QualityCheckDto> queryTableByDate(String beginDate,String endDate);
    List<QualityCheckDto> queryTableByCom(String company);

    Integer pushTable(Integer qualityCheckID);
    List<QualityCheckDto> queryTableByDateAndPush(String company,String beginDate,String endDate);
    Integer issuedTable(Integer qualityCheckID);
    Integer passTable(Integer qualityCheckID);
    Integer passTable2(QualityCheckDto qualityCheckDto);

    List<QualityCheckDto> queryTableByDateAndIssue(String company,String beginDate,String endDate);
    Integer modifyPush(Integer qualityCheckID);
    List<QualityCheckDto> queryByYearComAndModify(String company,String beginDate,String endDate);
    List<QualityCheckDto> queryAllPassTable();
}
