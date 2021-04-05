package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.cache.JedisClient;
import com.wlhse.dao.DangerRecordDao;
import com.wlhse.dto.RecordCountDto;
import com.wlhse.dto.DangerRecordDto;
import com.wlhse.entity.RecordDateQueryParam;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.DangerRecordService;
import com.wlhse.service.EmployeeManagementService;
import com.wlhse.util.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DangerRecordServiceImpl implements DangerRecordService {

    @Resource
    private DangerRecordDao dangerRecordDao;
    @Resource
    JedisClient jedisClient;
    @Resource
    EmployeeManagementService employeeManagementService;

    @Override
    public R addDangerRecord(DangerRecordDto dangerRecordDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Map<String, String> map = jedisClient.hGetAll(token);
        int employeeId = Integer.valueOf(map.get("employeeId"));
        dangerRecordDto.setSafeStaff_ID(employeeId);
        String employeeName = employeeManagementService.getEmployeeNameByEmployeeID(employeeId);
        dangerRecordDto.setSafeStaff_Name(employeeName);
        if (dangerRecordDao.addDangerRecord(dangerRecordDto) <= 0)
            throw new WLHSException("新增失败");
        return R.ok();
    }

    @Override
    public R deleteDangerRecord(DangerRecordDto dangerRecordDto) {
        if (dangerRecordDao.deleteDangerRecord(dangerRecordDto) <= 0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Override
    public R updateDangerRecord(DangerRecordDto dangerRecordDto) {
        try {
            dangerRecordDao.updateDangerRecord(dangerRecordDto);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("更新失败");
        }
        return R.ok();
    }

    @Override
    public R queryDangerRecordById(Integer id) {
        List<DangerRecordDto> list = dangerRecordDao.queryDangerRecordById(id);
        if (list != null && list.size() != 0) {
            for (DangerRecordDto dangerRecordDto : list) {
                if (dangerRecordDto.getEndDate() != null && dangerRecordDto.getEndDate().length() > 10)
                    dangerRecordDto.setEndDate(dangerRecordDto.getEndDate().substring(0, 10));

                if (dangerRecordDto.getLimitDate() != null && dangerRecordDto.getLimitDate().length() > 10)
                    dangerRecordDto.setLimitDate(dangerRecordDto.getLimitDate().substring(0, 10));

                if (dangerRecordDto.getReceptionDate() != null && dangerRecordDto.getReceptionDate().length() > 10)
                    dangerRecordDto.setReceptionDate(dangerRecordDto.getReceptionDate().substring(0, 10));

                if (dangerRecordDto.getRecordDate() != null && dangerRecordDto.getRecordDate().length() > 10)
                    dangerRecordDto.setRecordDate(dangerRecordDto.getRecordDate().substring(0, 10));

                if (dangerRecordDto.getStartDate() != null && dangerRecordDto.getStartDate().length() > 10)
                    dangerRecordDto.setStartDate(dangerRecordDto.getStartDate().substring(0, 10));

                if (dangerRecordDto.getSupervisionDate() != null && dangerRecordDto.getSupervisionDate().length() > 10)
                    dangerRecordDto.setSupervisionDate(dangerRecordDto.getSupervisionDate().substring(0, 10));
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return R.ok(map);
    }

    @Override
    public R queryDangerRecord(DangerRecordDto dangerRecordDto) {
        int total = dangerRecordDao.queryTotal(dangerRecordDto);
        int pageIdx = dangerRecordDto.getPageIdx();
        PageHelper.startPage(pageIdx, dangerRecordDto.getPageSize());
        List<DangerRecordDto> list = dangerRecordDao.queryDangerRecord(dangerRecordDto);
        if (list != null && list.size() != 0) {
            for (DangerRecordDto temp : list) {
                if (temp.getEndDate() != null && temp.getEndDate().length() > 10)
                    temp.setEndDate(temp.getEndDate().substring(0, 10));

                if (temp.getLimitDate() != null && temp.getLimitDate().length() > 10)
                    temp.setLimitDate(temp.getLimitDate().substring(0, 10));

                if (temp.getReceptionDate() != null && temp.getReceptionDate().length() > 10)
                    temp.setReceptionDate(temp.getReceptionDate().substring(0, 10));

                if (temp.getRecordDate() != null && temp.getRecordDate().length() > 10)
                    temp.setRecordDate(temp.getRecordDate().substring(0, 10));

                if (temp.getStartDate() != null && temp.getStartDate().length() > 10)
                    temp.setStartDate(temp.getStartDate().substring(0, 10));

                if (temp.getSupervisionDate() != null && temp.getSupervisionDate().length() > 10)
                    temp.setSupervisionDate(temp.getSupervisionDate().substring(0, 10));
            }
        }
        Map<String, Object> res = new HashMap<>();
        res.put("page", pageIdx);
        res.put("total", total);
        res.put("list", list);
        return R.ok(res);
    }

    @Override
    public R problemVerification(DangerRecordDto dangerRecordDto) {
        if (dangerRecordDao.problemVerification(dangerRecordDto) <= 0)
            throw new WLHSException("修改失败");
        return R.ok();
    }

    @Override
    public R queryMostDangerRecord(String startDate, String endDate) {
        RecordDateQueryParam param = new RecordDateQueryParam(startDate, endDate);
        List<RecordCountDto> recordCountDtoList = null;
        try {
            recordCountDtoList = dangerRecordDao.queryMostDangerRecord(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", recordCountDtoList);
        return R.ok(map);
    }


}
