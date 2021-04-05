package com.wlhse.service.impl;

import com.wlhse.dao.QHSEManageSysElementsDao;
import com.wlhse.dao.QhseChecklistManageSysDao;
import com.wlhse.dto.inDto.QHSEManageSysElementsDto;
import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.entity.QHSECompanyYearManagerSysElementPojo;
import com.wlhse.entity.QHSECompanyYearManagerSysElementTablePojo;
import com.wlhse.entity.QHSEManageSysElements;
import com.wlhse.service.QhseChecklistManageSysService;
import com.wlhse.util.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service("QhseChecklistManageSysService")
public class QhseChecklistManageSysServiceImpl implements QhseChecklistManageSysService {
    @Resource
    private QhseChecklistManageSysDao QhseChecklistManageSysDao;
    @Resource
    QHSEManageSysElementsDao qhseManageSysElementsDao;

    @Override
    public List<QHSECompanyYearManagerSysElementPojo> query(QHSEManageSysElementsDto qHSEManageSysElementsDto) {
        return QhseChecklistManageSysDao.query(qHSEManageSysElementsDto);
    }

    @Transactional
    @Override
    public int add(QHSEManageSysElementsDto qHSEManageSysElementsDto) {
        return QhseChecklistManageSysDao.add(qHSEManageSysElementsDto);
    }


    @Override
    public QHSEManageSysElementsDto query2(QHSEManageSysElementsDto qHSEManageSysElementsDto) {
        return QhseChecklistManageSysDao.query2(qHSEManageSysElementsDto);
    }

    @Override
    public R addAll(List<QHSECompanyYearManagerSysElementPojo> qHSECompanyYearManagerSysElementPojo, QHSEManageSysElementsDto qHSEManageSysElementsDto) {
        Integer len = qhseManageSysElementsDao.findMaxLen();
        for (QHSECompanyYearManagerSysElementPojo pojo:qHSECompanyYearManagerSysElementPojo) {
            if(len.equals(pojo.getCode().length()))
            pojo.setStatus("未提供");
            pojo.setqHSECompanyYearManagerSysElementTableID(qHSEManageSysElementsDto.getId());
            pojo.setCompanyName(qHSEManageSysElementsDto.getCompanyName());
            pojo.setCompanyCode(qHSEManageSysElementsDto.getCompanyCode());
            pojo.setYear(qHSEManageSysElementsDto.getYear());
            QhseChecklistManageSysDao.addAll(pojo);
        }
        return R.ok();
    }
}


