package com.wlhse.service.impl;

import com.wlhse.dao.CheckListDao;
import com.wlhse.dao.CheckRecordDao;
import com.wlhse.dto.CheckListDto;
import com.wlhse.dto.CheckRecordDto;
import com.wlhse.dto.CheckRecordTreeDto;
import com.wlhse.dto.inDto.CheckRecordPOJO;
import com.wlhse.entity.CheckConditionPOJO;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.CheckRecordService;
import com.wlhse.util.R;
import com.wlhse.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CheckRecordServiceImpl implements CheckRecordService {

    @Resource
    private CheckRecordDao checkRecordDao;

    @Resource
    CheckListDao checkListDao;
    @Resource
    private TreeUtil treeUtil;

    @Override
    public R addCheckRecord(CheckRecordDto checkRecordDto) {
        log.info("接收到的参数"+checkRecordDto);
        if ( checkRecordDao.addCheckRecord(checkRecordDto)<= 0)
            throw new WLHSException("新增失败");
        return R.ok();
    }

    @Override
    public R queryAll() {
        R ok = R.ok();
        ok.put("data",checkRecordDao.queryAll());
        return ok;
    }

    @Override
    public R queryAllTree() {
        R ok = R.ok();
        //先查询checkrecord中前4位不同的code
        List<CheckRecordDto> checkRecordDtos = checkRecordDao.queryAll();
        System.out.println(checkRecordDtos);
        List<String> codes = new ArrayList<>();
        for (CheckRecordDto dto:checkRecordDtos) {
            if (codes.indexOf(dto.getCode().substring(0,4)) == -1) {
                codes.add(dto.getCode().substring(0,4));
            }
        }
        List<CheckRecordTreeDto> resultDtos = new ArrayList<>();
        for (String code:codes) {
            resultDtos.addAll(checkRecordDao.queryAllByParentCode(code));
        }
        System.out.println(resultDtos);
        ok.put("data", treeUtil.getCheckRecordTree(resultDtos));
        return ok;
    }

    @Override
    public R updateCheckrecord(int id, CheckRecordDto checkRecordDto) {
        checkRecordDto.setCheckRecordID(id);
        if (checkRecordDao.updateCheckRecord(checkRecordDto)<=0)
            throw new WLHSException("更新失败");
        return R.ok();
    }

    @Override
    public R deleteCheckrecord(int id) {
        if (checkRecordDao.deleteCheckRecord(id)<=0)
            throw new WLHSException("删除失败");
        return R.ok();
    }

    @Override
    public R queryByCondition(CheckConditionPOJO checkConditionPOJO) {
        R ok = R.ok();
        ok.put("data",checkRecordDao.queryByCondition(checkConditionPOJO));
        return ok;
    }

    @Override
    public R getCheckRecord(CheckRecordPOJO checkRecordPOJO) {
        String checkListCode = checkRecordPOJO.getCheckListCode();
        int maxLen = checkListDao.getMaxLen();
        List<CheckListDto> currentAllChild = checkListDao.getCurrentAllChild(checkListCode);
        List<CheckRecordDto> resultList=new ArrayList<>();
        /*System.out.println("子结点:"+currentAllChild);*/
        for (CheckListDto checkListDto:currentAllChild){
            //find leaf nodes.
            String checkListCode1 = checkListDto.getCheckListCode();
            if (checkListCode1.length()==maxLen){
                checkRecordPOJO.setName(checkListDto.getCheckListName());
                checkRecordPOJO.setCheckListCode(checkListCode1);
                CheckRecordDto checkRecord = checkRecordDao.getCheckRecord(checkRecordPOJO);
                //If the company doesn't have this checklist.
                if (checkRecord==null){
                    CheckRecordDto checkRecordDto=new CheckRecordDto();
                    checkRecordDto.setCheckDate(checkRecordPOJO.getCheckDate());
                    checkRecordDto.setCheckType(checkRecordPOJO.getCheckType());
                    checkRecordDto.setCompanyCode(checkRecordPOJO.getCompanyCode());
                    checkRecordDto.setCompanyName(checkRecordPOJO.getCompanyName());
                    checkRecordDto.setCode(checkListCode1);
                    checkRecordDto.setName(checkRecordPOJO.getName());
                    checkRecordDao.addCheckRecord(checkRecordDto);
                    CheckRecordDto checkRecord1 = checkRecordDao.getCheckRecord(checkRecordPOJO);
                    checkRecordDto.setCheckRecordID(checkRecord1.getCheckRecordID());
                    resultList.add(checkRecordDto);
                }
                else
                {
                    resultList.add(checkRecord);
                }
            }
        }
        R ok = R.ok();
        ok.put("data",resultList);
        return ok;
    }

}
