package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.RegulationRecordDao;
import com.wlhse.dto.RecordCountDto;
import com.wlhse.dto.RegulationRecordDto;
import com.wlhse.entity.RecordDateQueryParam;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.RegulationRecordService;
import com.wlhse.util.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RegulationRecordServiceImpl implements RegulationRecordService {
    @Resource
    private RegulationRecordDao regulationRecordDao;

    @Override
    public R addRegulationRecord(RegulationRecordDto regulationRecordDto) {
        if (regulationRecordDao.addRegulationRecord(regulationRecordDto) <= 0)
            throw new WLHSException("新增失败");
        return R.ok();
    }

    @Override
    public R deleteRegulationRecord(RegulationRecordDto regulationRecordDto) {
        if (regulationRecordDao.deleteRegulationRecord(regulationRecordDto) <= 0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Override
    public R updateRegulationRecord(RegulationRecordDto regulationRecordDto) {
        if (regulationRecordDao.updateRegulationRecord(regulationRecordDto) <= 0)
            throw new WLHSException("修改失败");
        return R.ok();
    }

    @Override
    public R queryRegulationRecordById(Integer id) {
        List<RegulationRecordDto> list = regulationRecordDao.queryRegulationRecordById(id);
        Map<String, Object> res = new HashMap<>();
        res.put("data", list);
        return R.ok(res);
    }

    @Override
    public R queryRegulationRecord(RegulationRecordDto regulationRecordDto) {
        int total = regulationRecordDao.queryTotal(regulationRecordDto);
        int pageIdx = regulationRecordDto.getPageIdx();
        PageHelper.startPage(pageIdx, regulationRecordDto.getPageSize());
        List<RegulationRecordDto> list = regulationRecordDao.queryRegulationRecord(regulationRecordDto);
        Map<String, Object> res = new HashMap<>();
        res.put("page", pageIdx);
        res.put("total", total);
        res.put("list", list);
        return R.ok(res);
//        return NR.r(list, total, pageIdx);
    }

    @Override
    public R queryMostRegulationRecord(String startDate, String endDate) {
        RecordDateQueryParam param = new RecordDateQueryParam(startDate, endDate);
        List<RecordCountDto> recordCountDtoList = null;
        try {
            recordCountDtoList = regulationRecordDao.queryMostRegulationRecord(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", recordCountDtoList);
        return R.ok(map);
    }
}
