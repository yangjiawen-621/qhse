package com.wlhse.service.impl;

import com.wlhse.dao.QualityCheckDao;
import com.wlhse.dao.QualityCheckListDao;
import com.wlhse.dao.QualityCheckTableRecordDao;
import com.wlhse.dto.QualityCheckDto;
import com.wlhse.dto.QualityCheckTableRecordDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QualityCheckService;
import com.wlhse.util.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QualityCheckServiceImpl implements QualityCheckService {

    @Resource
    private QualityCheckDao qualityCheckDao;

    @Resource
    private QualityCheckListDao qualityCheckListDao;

    @Resource
    private QualityCheckTableRecordDao qualityCheckTableRecordDao;
    @Transactional
    @Override
    public R addQualityCheck(QualityCheckDto qualityCheckDto) {
        qualityCheckDto.setIsPush("未推送");
        qualityCheckDto.setIssued("未下达");
        if(qualityCheckDao.addQualityCheck(qualityCheckDto)<0)
            throw new WLHSException("插入失败");
        if(qualityCheckDto.getCheckListCode()==null){
            throw new WLHSException("检查表为空");
        }
        List<QualityCheckTableRecordDto> allQualityCheckTableRecord=getCheckTree(qualityCheckDto.getQualityCheckID(),qualityCheckDto.getCheckListCode());
//        if(allQualityCheckTableRecord.size()>0){
//            if(qualityCheckTableRecordDao.batchInsertTree(allQualityCheckTableRecord)<0)
//                throw new WLHSException("插入失败");
//        }
        return R.ok();
    }

    @Override
    public R deleteQualityCheck(Integer qualityCheckID) {
        if(qualityCheckDao.deleteQualityCheck(qualityCheckID)<0)//设置了级联删除，删除该表，后面的检查表也会删除
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Transactional
    @Override
    public R updateQualityCheck(QualityCheckDto qualityCheckDto) {
        Integer qualityCheckID=qualityCheckDto.getQualityCheckID();
        String newCheckListCode=qualityCheckDto.getCheckListCode();
        String oldCheckListCode=qualityCheckDao.queryCheckListCodeById(qualityCheckID);
        //内容更新
//        if(qualityCheckDao.updateQualityCheck(qualityCheckDto)<0)
//            throw new WLHSException("更新失败");
        //更新后面的QualityCheckTableRecord表,有变化才更新
        if(!newCheckListCode.equals(oldCheckListCode)){//直接删除原数据，重新写入。
            qualityCheckTableRecordDao.deleteChickList(qualityCheckID);
            List<QualityCheckTableRecordDto> allQualityCheckTableRecord=getCheckTree(qualityCheckID,newCheckListCode);
            if(allQualityCheckTableRecord.size()>0){
                if(qualityCheckTableRecordDao.batchInsertTree(allQualityCheckTableRecord)<0)
                    throw new WLHSException("插入失败");
            }
        }
        return R.ok();
    }

    @Override
    public R queryAllTable() {
        R ok = R.ok();
        ok.put("data",qualityCheckDao.queryAllTable());
        return ok;
    }

    @Override
    public R queryTableByYearAndCom(QualityCheckDto qualityCheckDto) {
        R ok = R.ok();
        String beginDate=qualityCheckDto.getCheckDate();
        String endDate=qualityCheckDto.getCheckEndDate();
        String CheckedCompanyCode=qualityCheckDto.getCheckedCompanyCode();
        if(beginDate==null&&CheckedCompanyCode==null&&endDate==null){
            ok.put("data",qualityCheckDao.queryAllTable());
            return ok;
        }
        else{
            if(CheckedCompanyCode==null&&((beginDate==null&&endDate!=null)||(beginDate!=null&&endDate==null))){
                throw new WLHSException("起始时间请选完整");
            }
            if (CheckedCompanyCode==null){
                ok.put("data",qualityCheckDao.queryTableByDate(beginDate,endDate));
                return ok;
            }
            else if(beginDate==null&&endDate==null){
                ok.put("data",qualityCheckDao.queryTableByCom(qualityCheckDto.getCheckedCompanyCode()));
                return ok;
            }
            else{
                ok.put("data",qualityCheckDao.queryTableByDateCom(CheckedCompanyCode,beginDate,endDate));
                return ok;
            }

        }
    }

    @Override
    public R pushTable(Integer qualityCheckID) {
       if(qualityCheckDao.pushTable(qualityCheckID)<0)
           throw new WLHSException("更新失败");
        return R.ok();
    }

    @Override
    public R queryTableByYearAndComAndPush(QualityCheckDto qualityCheckDto) {
        R ok = R.ok();
        String beginDate=qualityCheckDto.getCheckDate();
        String endDate=qualityCheckDto.getCheckEndDate();
        System.out.println(beginDate);
        System.out.println(endDate);
        System.out.println(qualityCheckDto.getCheckedCompanyCode());
        ok.put("data",qualityCheckDao.queryTableByDateAndPush(qualityCheckDto.getCheckedCompanyCode(),beginDate,endDate));
        return ok;
    }

    @Override
    public R issuedTable(QualityCheckDto qualityCheckDto) {
        if("通过".equals(qualityCheckDto.getIsPush())){//是通过，修改推送字段。
            if(qualityCheckDao.passTable(qualityCheckDto.getQualityCheckID())<0)
                throw new WLHSException("更新失败");
        }
        else{
            if("下达".equals(qualityCheckDto.getIsPush())){
                if(qualityCheckDao.issuedTable(qualityCheckDto.getQualityCheckID())<0)
                    throw new WLHSException("更新失败");
            }
            else{
                throw new WLHSException("字段错误");
            }
        }
        return R.ok();
    }

    @Override
    public R queryTableByYearAndComAndIssued(QualityCheckDto qualityCheckDto) {
        R ok = R.ok();
        String beginDate=qualityCheckDto.getCheckDate();
        String endDate=qualityCheckDto.getCheckEndDate();
        ok.put("data",qualityCheckDao.queryTableByDateAndIssue(qualityCheckDto.getCheckedCompanyCode(),beginDate,endDate));
        return ok;
    }

    @Override
    public R modifyPush(Integer qualityCheckID) {
        if(qualityCheckDao.modifyPush(qualityCheckID)<0)
            throw new WLHSException("更新失败");
        return R.ok();
    }

    @Override
    public R queryByYearComAndModify(QualityCheckDto qualityCheckDto) {
        R ok = R.ok();
        String beginDate=qualityCheckDto.getCheckDate();
        String endDate=qualityCheckDto.getCheckEndDate();
        ok.put("data",qualityCheckDao.queryByYearComAndModify(qualityCheckDto.getCheckedCompanyCode(),beginDate,endDate));
        return ok;
    }

    @Override
    public R backTable(QualityCheckDto qualityCheckDto) {
        if("通过".equals(qualityCheckDto.getIsPush())){//是通过，修改推送字段。
            if(qualityCheckDao.passTable2(qualityCheckDto)<0)
                throw new WLHSException("更新失败");
        }
        else{
            if("打回".equals(qualityCheckDto.getIsPush())){
                if(qualityCheckDao.issuedTable(qualityCheckDto.getQualityCheckID())<0)
                    throw new WLHSException("更新失败");
            }
            else{
                throw new WLHSException("字段错误");
            }
        }
        return R.ok();
    }

    @Override
    public R queryAllPassTable() {
        R ok = R.ok();
        ok.put("data",qualityCheckDao.queryAllPassTable());
        return ok;
    }

    @Override
    public R addQualityCheck2(QualityCheckDto qualityCheckDto) {
        qualityCheckDto.setIsPush("已推送");
        qualityCheckDto.setIssued("未下达");
        if(qualityCheckDao.addQualityCheck(qualityCheckDto)<0)
            throw new WLHSException("插入失败");
        return R.ok();
    }

    public List<QualityCheckTableRecordDto> getCheckTree(Integer qualityCheckID,String checkCode)
    {
        String[] checkListCodes =checkCode.split(";");
        List<QualityCheckTableRecordDto> allQualityCheckTableRecord=new ArrayList<>();
        for(String code: checkListCodes){
            List<QualityCheckTableRecordDto> QualityCheckTableRecords=qualityCheckListDao.findTreeByCode(code);
            for(QualityCheckTableRecordDto qualityCheckTableRecordDto:QualityCheckTableRecords){
                qualityCheckTableRecordDto.setQualityCheckID(qualityCheckID);
                qualityCheckTableRecordDto.setCheckResult("未审核");
                qualityCheckTableRecordDto.setScore(0);
            }
            allQualityCheckTableRecord.addAll(QualityCheckTableRecords);
        }

        return allQualityCheckTableRecord;
    }
}
