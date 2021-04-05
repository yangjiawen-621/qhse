package com.wlhse.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlhse.dao.QualityFileAuditDao;
import com.wlhse.dto.QualityFileAuditDto;
import com.wlhse.dto.QualityFileAuditRecordDto;
import com.wlhse.dto.inDto.QualityYearElementsDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QualityFileAuditService;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QualityFileAuditServiceImpl implements QualityFileAuditService {


    @Resource
    private QualityFileAuditDao qualityfileAuditDao;

    @Override
    public String queryExistFile(QualityFileAuditDto qualityFileAuditDto) {
        int total = qualityfileAuditDao.queryTotal(qualityFileAuditDto);
        int pageIdx = qualityFileAuditDto.getPageIdx();
        PageHelper.startPage(pageIdx, qualityFileAuditDto.getPageSize());
        List<QualityFileAuditDto> list = qualityfileAuditDao.queryExistFile(qualityFileAuditDto);
        return NR.r(list, total, pageIdx);
    }

    @Override
    public R addFileAudit(QualityFileAuditDto qualityFileAuditDto) {
        if (qualityfileAuditDao.addFileAudit(qualityFileAuditDto) <= 0)
            throw new WLHSException("新增失败");
        return R.ok();
    }

    @Override
    public R deleteFileAudit(Integer id) {
        if (qualityfileAuditDao.deleteFileAudit(id) <= 0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Override
    public R addFileAuditRecord(QualityFileAuditRecordDto qualityFileAuditRecordDto) {
        if (qualityfileAuditDao.addFileAuditRecord(qualityFileAuditRecordDto) <= 0)
            throw new WLHSException("新增记录失败");
        return R.ok();
    }

    @Override
    public String queryRecordId(QualityFileAuditRecordDto qualityFileAuditRecordDto) {
        List<QualityFileAuditRecordDto> list = qualityfileAuditDao.queryRecordId(qualityFileAuditRecordDto);
        return NR.r(list);
    }

    @Override
    public R deleteFileAuditRecord(Integer id1) {
        if (qualityfileAuditDao.deleteFileAuditRecord(id1) <= 0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Override
    public String updateStatus(QualityFileAuditRecordDto qualityFileAuditRecordDto) {
        if (qualityfileAuditDao.updateStatus(qualityFileAuditRecordDto) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    public String updateScore(QualityFileAuditRecordDto qualityFileAuditRecordDto) {
        if (qualityfileAuditDao.updateScore(qualityFileAuditRecordDto) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    public String getScore(QualityFileAuditRecordDto qualityFileAuditRecordDto) {
        List<QualityFileAuditRecordDto> list = qualityfileAuditDao.getScore(qualityFileAuditRecordDto);
        return NR.r(list);
    }

    @Override
    public String getStatus(QualityFileAuditRecordDto qualityFileAuditRecordDto) {
        List<QualityFileAuditRecordDto> list = qualityfileAuditDao.getStatus(qualityFileAuditRecordDto);
        return NR.r(list);
    }

    @Override
    public String updateCheckStatus(QualityYearElementsDto yearElementsDto) {
        if (qualityfileAuditDao.updateCheckStatus(yearElementsDto) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    public R getAllFileAuditPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<QualityFileAuditDto> qualityFileAuditDtoList = qualityfileAuditDao.getAllFileAudit();
        PageInfo pageInfo = new PageInfo(qualityFileAuditDtoList);
        R r = new R();
        r.put("list", pageInfo.getList());
        r.put("totals", pageInfo.getTotal());
        return r;
    }

    @Override
    public R noPassReasonFileAudit(QualityFileAuditRecordDto qualityFileAuditRecordDto) {
        if (qualityFileAuditRecordDto.getFileAuditId() == null
                || qualityFileAuditRecordDto.getCode() == null) {
            return R.error("参数不完整");
        }
        qualityfileAuditDao.noPassReasonFileAudit(qualityFileAuditRecordDto);
        return R.ok("更新成功");
    }


}
