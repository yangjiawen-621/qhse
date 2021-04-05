package com.wlhse.service.impl;

import com.wlhse.dao.FileDao;
import com.wlhse.dao.QualityCheckTableRecordDao;
import com.wlhse.dto.QualityAuditRecord;
import com.wlhse.dto.QualityCheckTableRecordAttachInfoDto;
import com.wlhse.dto.QualityCheckTableRecordDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QualityCheckTableRecordService;
import com.wlhse.util.R;
import com.wlhse.util.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QualityCheckTableRecordServiceImpl implements QualityCheckTableRecordService {

    @Resource
    private QualityCheckTableRecordDao qualityCheckTableRecordDao;

    @Resource
    private TreeUtil treeUtil;

    @Resource
    private FileDao fileDao;

    @Override
    public R queryCheckTreeByID(Integer qualityCheckID) {
        R ok = R.ok();
        ok.put("data",treeUtil.getQualityCheckRecordTree(qualityCheckTableRecordDao.queryCheckTreeByID(qualityCheckID)));
        return ok;
    }

    @Override
    public R addInformAndAttach(QualityCheckTableRecordDto qualityCheckTableRecordDto) {
        if(qualityCheckTableRecordDao.addInformAndAttach(qualityCheckTableRecordDto)<0)
            throw new WLHSException("更新失败");
        return R.ok();
    }

    @Override
    public R addCheckInfo(QualityAuditRecord qualityAuditRecord) {
        if (qualityCheckTableRecordDao.queryID(qualityAuditRecord.getId()) == null) {
            //没有就插入
            if(qualityCheckTableRecordDao.insertCheckInfo(qualityAuditRecord)<=0){
                throw new WLHSException("插入失败");
            }
        }else{//有就更新
            if(qualityCheckTableRecordDao.updateCheckInfo(qualityAuditRecord)<=0){
                throw new WLHSException("更新失败");
            }
        }
        return R.ok();
    }

    @Override
    public R queryCheckInfoByCheckId(Integer id) {
        R ok = R.ok();
        ok.put("data",qualityCheckTableRecordDao.queryCheckInfo(id));
        return ok;
    }
}
