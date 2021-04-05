package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.QualityRegulationRecordDao;
import com.wlhse.dto.QualityRegulationRecordDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QualityRegulationRecordService;
import com.wlhse.service.RegulationRecordService;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class QualityRegulationRecordServiceImpl implements QualityRegulationRecordService {

    @Resource
    private QualityRegulationRecordDao qualityregulationRecordDao;

    @Override
    public R addRegulationRecord(QualityRegulationRecordDto regulationRecordDto) {
        if (qualityregulationRecordDao.addRegulationRecord(regulationRecordDto) <= 0)
            throw new WLHSException("新增失败");
        return R.ok();
    }

    @Override
    public R deleteRegulationRecord(QualityRegulationRecordDto regulationRecordDto) {
        try {
            System.out.println("删除操作");
            qualityregulationRecordDao.deleteRegulationRecord(regulationRecordDto);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("删除失败");
        }
        return R.ok();
    }

    @Override
    public R updateRegulationRecord(QualityRegulationRecordDto regulationRecordDto) {
        try {
            System.out.println("更新操作");
            qualityregulationRecordDao.updateRegulationRecord(regulationRecordDto);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("更新失败");
        }
        return R.ok();
    }

    @Override
    public String queryRegulationRecordById(Integer id) {
        List<QualityRegulationRecordDto> list = qualityregulationRecordDao.queryRegulationRecordById(id);
        return NR.r(list);
    }

    @Override
    public String queryRegulationRecord(QualityRegulationRecordDto regulationRecordDto) {
        int total = qualityregulationRecordDao.queryTotal(regulationRecordDto);
        int pageIdx = regulationRecordDto.getPageIdx();
        PageHelper.startPage(pageIdx, regulationRecordDto.getPageSize());
        List<QualityRegulationRecordDto> list = qualityregulationRecordDao.queryRegulationRecord(regulationRecordDto);
        return NR.r(list, total, pageIdx);
    }
}
