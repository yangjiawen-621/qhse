package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.cache.JedisClient;
import com.wlhse.dao.QualityDangerRecordDao;
import com.wlhse.dto.QualityDangerRecordDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.EmployeeManagementService;
import com.wlhse.service.Quality_DangerRecordService;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author tobing QQ:652916578
 * 【质量】隐患接口
 */
@Service
public class QualityDangerRecordServiceImpl implements Quality_DangerRecordService {

    @Resource
    private QualityDangerRecordDao qualityDangerRecordDao;
    @Resource
    JedisClient jedisClient;
    @Resource
    EmployeeManagementService employeeManagementService;

    @Override
    public R addDangerRecord(QualityDangerRecordDto dangerRecordDto, HttpServletRequest request) {
        // 根据token拿到员工id
        String token = request.getHeader("Authorization");
        Map<String, String> map = jedisClient.hGetAll(token);
        int employeeId = Integer.valueOf(map.get("employeeId"));
        // 将员工id复制保存
        dangerRecordDto.setSafeStaff_ID(employeeId);
        // 通过员工模块拿到员工名称
        String employeeName = employeeManagementService.getEmployeeNameByEmployeeID(employeeId);
        dangerRecordDto.setSafeStaff_Name(employeeName);
        try {
            qualityDangerRecordDao.addDangerRecord(dangerRecordDto);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("新增失败");
        }
        return R.ok();
    }

    @Override
    public R deleteDangerRecord(QualityDangerRecordDto dangerRecordDto) {
        if (qualityDangerRecordDao.deleteDangerRecord(dangerRecordDto) <= 0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Override
    public R updateDangerRecord(QualityDangerRecordDto dangerRecordDto) {

        try {
            qualityDangerRecordDao.updateDangerRecord(dangerRecordDto);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("更新失败");
        }
        return R.ok();
    }

    @Override
    public String queryDangerRecordById(Integer id) {
        try {
            List<QualityDangerRecordDto> list = qualityDangerRecordDao.queryDangerRecordById(id);
            System.out.println(list.size());
            return NR.r(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String queryDangerRecord(QualityDangerRecordDto dangerRecordDto) {
        // PageHelper 分页查询
        int total = qualityDangerRecordDao.queryTotal(dangerRecordDto);
        int pageIdx = dangerRecordDto.getPageIdx();
        PageHelper.startPage(pageIdx, dangerRecordDto.getPageSize());
        List<QualityDangerRecordDto> list = qualityDangerRecordDao.queryDangerRecord(dangerRecordDto);
        return NR.r(list, total, pageIdx);
    }

    @Override
    public R problemVerification(QualityDangerRecordDto dangerRecordDto) {
        if (qualityDangerRecordDao.problemVerification(dangerRecordDto) <= 0)
            throw new WLHSException("修改失败");
        return R.ok();
    }

}
