package com.wlhse.dao;

import com.wlhse.dto.QHSEproblemDiscriptionDto;
import com.wlhse.dto.inDto.QSHEMSElementInDto;
import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.dto.outDto.ElementAndConfigStatusDto;
import com.wlhse.dto.outDto.QhseElementsOutDto;
import com.wlhse.entity.QHSECompanySysElementsPojo;
import com.wlhse.entity.QHSEManageSysElements;
import com.wlhse.entity.QhseElementsPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QHSEManageSysElementsDao {
    String querryLastQHSEChildCode(@Param("code") String code);
    String querryLastQHSEChildCode2(@Param("code") String code);//查询最后一个字节的重写

    Integer addQHSERule(QHSEManageSysElements rule);
    Integer addQHSEElement(QhseElementsPojo element);//换类型重写
    Integer addExcelQHSEElement(QSHEMSElementInDto element);//excel文件添加节点
    Integer addExcelQHSEElemenForInerPople(QhseElementsOutDto element);


    List<QHSECompanySysElementsPojo> querryQHSEReportElements(@Param("status") String status);

    List<QHSECompanySysElementsPojo> querryQHSEReportElements1();

    Integer addTotalCount(@Param("code") String code,@Param("count") Integer count);

    Integer addInitialScore(@Param("code") String code,@Param("score") Integer score);

    Integer updateRule(QHSEManageSysElements rule);
    Integer updateElement(QhseElementsPojo rule);//换类型重写
    Integer updateExcelElement(QSHEMSElementInDto element);//excel文件更新内容

    String querryStatus(String code);

    Integer querryScore(String code);

    Integer setOff(@Param("status") String status,@Param("code") String code);
    Integer setOn(@Param("status") String status,@Param("code") String code);
    Integer getScore(@Param("code") String code);
    //Integer subSoreCount(@Param("code") String code,@Param("score") Integer score);
    Integer sumScore(@Param("code") String code,@Param("max_len") Integer max_len);
    Integer sumCount(@Param("code") String code,@Param("max_len") Integer max_len);
    Integer updateScoreCount(@Param("code") String code,@Param("score") Integer score,@Param("count") Integer count);
    Integer toZero(@Param("code") String code,@Param("max_len") Integer max_len);
    Integer addScoreCount(@Param("code") String code,@Param("score") Integer score);
    //根据id查询节点
    QhseElementsPojo getElementById(@Param("id")int id);
    //根据code查询节点
    QhseElementsPojo getElementByCode(@Param("code")String code);

    //th----查询基本数据表，仅启用
    List<QhseElementsPojo> queryQhseElements();
    //查询年度要素表
    List<YearElementsDto> queryQhseYearElements(YearElementsDto yearElementsDto);
    //查看任务是否被接收
    String queryTask(@Param("year")String year,@Param("companyCode") String companyCode);
    //melon查询进度
    int querySchedule(@Param("code") String code,String companyCode,String year);
    int querySchdules(@Param("code") String code,String companyCode,String year);
    //melon质量查询进度
    int querySchedule1(@Param("code") String code,String companyCode,String year);
    int querySchdules1(@Param("code") String code,String companyCode,String year);
    //th----查询基本数据表两级
    List<QhseElementsPojo> queryQhseChildElements();
    //th----查询所有的数据表
    List<QhseElementsPojo> queryQhseAllElements();

    //查询所有编码
    List<String> queryAllCode();

    //导入excel问题描述时，先删除所有问题描述
    Integer deleteAllDescription();

    //导入excel问题描述时，根据code添加对应问题描述
    Integer addProblemDescription(@Param("code") String code,@Param("description") String description);
    //根据code查询对应的问题描述
    List<QHSEproblemDiscriptionDto>  querryDescriptionBycode(@Param("code") String code);
    //根据ID删除对应的问题描述
    Integer deleteDescriptionById(@Param("id") Integer id);
    //更新
    Integer updateDescriptionById(QHSEproblemDiscriptionDto qHSEproblemDiscriptionDto);
    //查询所有问题描述，导入Excel
    List<QHSEproblemDiscriptionDto> querryAllDescription();
    //批量导入
    int batchInsertRecord(List<QhseElementsOutDto> element);









    List<YearElementsDto> queryElementsByCode(String c);
    YearElementsDto queryElementByCode(String code);
    Integer findMaxLen();

    Integer addYearElement(YearElementsDto yearElementsDto);

    //查找指定tableid
    List<YearElementsDto> queryByTableID(@Param("id") Integer id);

    int deleteByTableID(@Param("id") Integer id);

    List<ElementAndConfigStatusDto> selectCodeAndConfigStatusByTableId(int tableId);
    int updateConfigStatus(String code,int tableId,String status);

    int getCheckedElementNumber(int tableId);

    int getAllLeafNodeNumber(int table);

    List<YearElementsDto> queryYearElement(YearElementsDto yearElementsDto);

    int getInputtedNum(int tableId);

    int getCheckedNum(int tableId);

    int getApprovedNum(int tableId);

    int getReInputNum(int tableId);

    int updateInvolveStatus(int elementId,int status);
}
