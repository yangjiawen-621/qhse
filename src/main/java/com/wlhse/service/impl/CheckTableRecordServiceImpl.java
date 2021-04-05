package com.wlhse.service.impl;

import com.wlhse.dao.QualityCheckListDao;
import com.wlhse.dao.QualityCheckTableRecordDao;
import com.wlhse.dto.QualityCheckDto;
import com.wlhse.dto.QualityCheckTableRecordDto;
import com.wlhse.entity.QualityCheckTableRecord;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.CheckTableRecordService;
import com.wlhse.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author YangJiaWen
 * @Date 2021/4/3 13:03
 * @Version 1.0
 **/

@Service
public class CheckTableRecordServiceImpl implements CheckTableRecordService {

    @Autowired
    private QualityCheckTableRecordDao tableRecordDao;

    @Autowired
    private QualityCheckListDao qualityCheckListDao;

    @Resource
    private QualityCheckTableRecordDao qualityCheckTableRecordDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public R saveQualityCheckTableRecord(QualityCheckTableRecord qualityCheckDto) {

        R ok = new R();
        String codes = qualityCheckDto.getCheckListCode();
        Integer qualityCheckID = qualityCheckDto.getQualityCheckID();


        List<String> name = qualityCheckTableRecordDao.findQualityId(qualityCheckID);

        String[] strings = codes.split(";");
        List<QualityCheckTableRecordDto> allQualityCheckTableRecord = new ArrayList<>();
        for (String string : strings) {
            QualityCheckTableRecordDto treeByCode1 = qualityCheckListDao.findTreeByCode1(string);
            treeByCode1.setQualityCheckID(qualityCheckID);
            treeByCode1.setCheckResult("未审核");
            treeByCode1.setScore(0);
            allQualityCheckTableRecord.add(treeByCode1);
        }

        if (name.size() == 0) {
            Integer integer = qualityCheckTableRecordDao.batchInsertTree(allQualityCheckTableRecord);
            System.out.println(integer);
            return ok;
        }else {
            Integer integer = qualityCheckTableRecordDao.deleteChickList(qualityCheckID);
            logger.info("删除:"+integer);
            Integer res = qualityCheckTableRecordDao.batchInsertTree(allQualityCheckTableRecord);
            logger.info("插入:"+res);
            return ok;
        }
    }

    //回显用户选择的配置要素节点
    @Override
    public R showBackElement(Integer QualityCheckID) {
        //Integer count = qualityCheckTableRecordDao.deleteChickList(QualityCheckID);
        List<String> checkedListCode = qualityCheckTableRecordDao.findCheckedListCodeById(QualityCheckID);
        R ok = new R();
        ok.put("data",checkedListCode);
        return ok;
    }
}