package com.wlhse.service;

import com.wlhse.dto.inDto.QHSEManageSysElementsDto;
import com.wlhse.entity.QHSECompanyYearManagerSysElementPojo;
import com.wlhse.entity.QHSECompanyYearManagerSysElementTablePojo;
import com.wlhse.entity.QHSEManageSysElements;
import com.wlhse.util.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QhseChecklistManageSysService {
    //查询节点子子孙孙
    List<QHSECompanyYearManagerSysElementPojo> query(QHSEManageSysElementsDto qHSEManageSysElementsDto);

    //增加年度表
    int add(QHSEManageSysElementsDto qHSEManageSysElementsDto);


    //根据公司和年度查id和其他必要
    QHSEManageSysElementsDto query2(QHSEManageSysElementsDto qHSEManageSysElementsDto);

    //插入细节表
    R addAll(List<QHSECompanyYearManagerSysElementPojo> qHSECompanyYearManagerSysElementPojo,
             QHSEManageSysElementsDto qHSEManageSysElementsDto);
}
