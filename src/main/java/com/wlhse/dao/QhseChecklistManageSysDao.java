package com.wlhse.dao;

import com.wlhse.dto.inDto.QHSEManageSysElementsDto;
import com.wlhse.entity.QHSECompanyYearManagerSysElementPojo;
import com.wlhse.entity.QHSECompanyYearManagerSysElementTablePojo;
import com.wlhse.entity.QHSEManageSysElements;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("QhseChecklistManageSysDao")
public interface QhseChecklistManageSysDao {

    //查询节点子子孙孙
    List<QHSECompanyYearManagerSysElementPojo> query(QHSEManageSysElementsDto qHSEManageSysElementsDto);

    //增加年度表
    int add(QHSEManageSysElementsDto qHSEManageSysElementsDto);


    //根据公司和年度查id和其他必要
    QHSEManageSysElementsDto query2(QHSEManageSysElementsDto qHSEManageSysElementsDto);

    //插入细节表
    int addAll(QHSECompanyYearManagerSysElementPojo pojo);
}
