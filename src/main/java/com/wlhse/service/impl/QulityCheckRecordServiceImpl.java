package com.wlhse.service.impl;

import com.wlhse.dao.QulityCheckRecordDao;
import com.wlhse.entity.QulityCheckRecordPojo;
import com.wlhse.service.QulityCheckRecordService;
import com.wlhse.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tobing
 */
@Service
@Transactional
public class QulityCheckRecordServiceImpl implements QulityCheckRecordService {

    @Autowired
    private QulityCheckRecordDao qulityCheckRecordDao;

    @Override
    public R addQulityCheckRecord(QulityCheckRecordPojo qulityCheckRecordPojo) {
        // 判断记录合法性
        if (qulityCheckRecordPojo == null) {
            return R.error("插入数据为空");
        }

        // 判断基础表数据是否为空
        if (qulityCheckRecordPojo.getQulity_CheckID() == null
                || qulityCheckRecordPojo.getCheckListCode() == null) {
            return R.error("参数不合法");
        }
        try {
            qulityCheckRecordDao.addQualityCheckRecord(qulityCheckRecordPojo);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("插入失败");
        }
        return R.ok("插入成功");
    }

    @Override
    public R queryQulityCheckRecord() {
        List<QulityCheckRecordPojo> qulityCheckRecordPojos = qulityCheckRecordDao.queryQualityCheckRecord();
        // 判断结果合法性
        if (qulityCheckRecordPojos == null || qulityCheckRecordPojos.size() == 0) {
            return R.error("No Result");
        }
        // 保存查询结果集
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", qulityCheckRecordPojos);
        return R.ok(res);
    }

    @Override
    public R deleteQulityCheckRecord(Integer id) {
        qulityCheckRecordDao.deleteQualityCheckRecord(id);
        return R.ok("删除成功");
    }

    @Override
    public R updateQulityCheckRecord(Integer id, QulityCheckRecordPojo qulityCheckRecordPojo) {
        if (id == null || qulityCheckRecordPojo == null) {
            return R.error("参数不合法");
        }
        qulityCheckRecordPojo.setQulity_CheckRecordID(id);
        try {
            qulityCheckRecordDao.updateQualityCheckRecord(qulityCheckRecordPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok("更新成功");
    }

    @Override
    public R queryQulityCheckRecordByCheckId(String checkId) {
        if (checkId == null) {
            return R.error("参数不合法");
        }
        List<QulityCheckRecordPojo> recordPojoList = qulityCheckRecordDao.queryQulityCheckRecordByCheckId(checkId);
        Map<String, Object> res = new HashMap<>();
        res.put("data", recordPojoList);
        return R.ok(res);
    }

    @Override
    public R queryQulityCheckRecordById(String id) {
        if (id == null) {
            return R.error("参数不合法");
        }
        QulityCheckRecordPojo recordPojo = qulityCheckRecordDao.queryQualityCheckRecordById(id);
        Map<String, Object> res = new HashMap<>();
        res.put("data", recordPojo);
        return R.ok(res);
    }
}
