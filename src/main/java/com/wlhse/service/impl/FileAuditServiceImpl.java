package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlhse.dao.FileAuditDao;
import com.wlhse.dto.FileAuditDto;
import com.wlhse.dto.FileAuditRecordDto;
import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.FileAuditService;
import com.wlhse.util.R;

import javax.annotation.Resource;
import java.util.List;

import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FileAuditServiceImpl implements FileAuditService {

    @Resource
    private FileAuditDao fileAuditDao;

    @Override
    public String queryExistFile(FileAuditDto fileAuditDto) {
        int total = fileAuditDao.queryTotal(fileAuditDto);
        int pageIdx = fileAuditDto.getPageIdx();
        PageHelper.startPage(pageIdx, fileAuditDto.getPageSize());
        List<FileAuditDto> list = fileAuditDao.queryExistFile(fileAuditDto);
        return NR.r(list, total, pageIdx);
    }

    @Override
    public R addFileAudit(FileAuditDto fileAuditDto) {
        if (fileAuditDao.addFileAudit(fileAuditDto) <= 0)
            throw new WLHSException("新增失败");
        return R.ok();
    }

    @Override
    public R deleteFileAudit(Integer id) {
        if (fileAuditDao.deleteFileAudit(id) <= 0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Override
    public R addFileAuditRecord(FileAuditRecordDto fileAuditRecordDto) {
        if (fileAuditDao.addFileAuditRecord(fileAuditRecordDto) <= 0)
            throw new WLHSException("新增记录失败");
        // 将id生成器返回的主键进行返回
        return R.ok().put("key", fileAuditRecordDto.getqHSE_FileAudit_RecordID());
    }

    @Override
    public String queryRecordId(FileAuditRecordDto fileAuditRecordDto) {
        List<FileAuditRecordDto> list = fileAuditDao.queryRecordId(fileAuditRecordDto);
        return NR.r(list);
    }

    @Override
    public R deleteFileAuditRecord(Integer id1) {
        if (fileAuditDao.deleteFileAuditRecord(id1) <= 0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Override
    public String updateStatus(FileAuditRecordDto fileAuditRecordDto) {
        if (fileAuditDao.updateStatus(fileAuditRecordDto) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    public String updateScore(FileAuditRecordDto fileAuditRecordDto) {
        if (fileAuditDao.updateScore(fileAuditRecordDto) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    public String getScore(FileAuditRecordDto fileAuditRecordDto) {
        List<FileAuditRecordDto> list = fileAuditDao.getScore(fileAuditRecordDto);
        return NR.r(list);
    }

    @Override
    public String getStatus(FileAuditRecordDto fileAuditRecordDto) {
        List<FileAuditRecordDto> list = fileAuditDao.getStatus(fileAuditRecordDto);
        return NR.r(list);
    }

    @Override
    public String updateCheckStatus(YearElementsDto yearElementsDto) {
        if (fileAuditDao.updateCheckStatus(yearElementsDto) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    public R getAllFileAuditPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<FileAuditDto> fileAuditDtoList = fileAuditDao.getAllFileAudit();
        PageInfo pageInfo = new PageInfo(fileAuditDtoList);
        R r = new R();
        r.put("list", pageInfo.getList());
        r.put("totals", pageInfo.getTotal());
        return r;
    }

    @Override
    public R noPassReasonFileAudit(FileAuditRecordDto fileAuditRecordDto) {
        if (fileAuditRecordDto.getFileAuditId() == null
                || fileAuditRecordDto.getCode() == null) {
            return R.error("参数不完整");
        }
        fileAuditDao.noPassReasonFileAudit(fileAuditRecordDto);
        return R.ok("更新成功");
    }


}
