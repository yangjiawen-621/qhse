package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.*;
import com.wlhse.dto.CheckRecordDto;
import com.wlhse.dto.inDto.SafetyProblemImportInDto;
import com.wlhse.dto.outDto.NewSafetyProblemDto;
import com.wlhse.dto.outDto.SafetyProblemImportDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.SafetyProblemImportService;
import com.wlhse.util.HashUtil;
import com.wlhse.util.R;
import com.wlhse.util.state_code.NR;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SafetyProblemImportServiceImpl implements SafetyProblemImportService {
    @Resource
    private SafetyProblemImportDao safetyProblemImportDao;

    @Resource
    private CheckRecordDao checkRecordDao;

    @Value("${RESOURCES_URL}")
    private String url;

    @Override
    @Transactional
    public R newInsertProblemImport(NewSafetyProblemDto newSafetyProblemDto) {
//        String hash = HashUtil.hash(newSafetyProblemDto.getCompanyName() + newSafetyProblemDto.getCheckDate() + newSafetyProblemDto.getCheckListItemCode());
//        if (safetyProblemImportDao.getHashCount(hash) != 0)
//            throw new WLHSException("重复录入问题,请检查!");
//        newSafetyProblemDto.setHash(hash);
        //新增问题同时新增checkrecord检查记录
        CheckRecordDto checkRecordDto=new CheckRecordDto();
        checkRecordDto.setCode(newSafetyProblemDto.getCheckListItemCode());
        checkRecordDto.setName(newSafetyProblemDto.getCheckListItemName());
        checkRecordDto.setCheckType(newSafetyProblemDto.getCheckType());
        checkRecordDto.setCompanyName(newSafetyProblemDto.getCompanyName());
        checkRecordDto.setCompanyCode(newSafetyProblemDto.getCompanyCode());
        checkRecordDto.setCheckDate(newSafetyProblemDto.getCheckDate());
        int i = safetyProblemImportDao.addNewProblem(newSafetyProblemDto);
        int j = checkRecordDao.addCheckRecord(checkRecordDto);
        if (i*j <= 0)
            throw new WLHSException("更新失败");
        return R.ok();
    }

    @Override
    public String queryProblemImport(SafetyProblemImportInDto safetyProblemImportInDto) {
        //查询所有
        safetyProblemImportInDto.setUrl(url);
        setDate(safetyProblemImportInDto);
        if (safetyProblemImportInDto.getType().equals("all"))
            return NR.r(safetyProblemImportDao.getProblemImportDtoByContiton(safetyProblemImportInDto));
        //分页查询
        int total = safetyProblemImportDao.getProblemImportDtoCount(safetyProblemImportInDto);
        PageHelper.startPage(safetyProblemImportInDto.getPageIdx(), safetyProblemImportInDto.getPageSize());
        List<SafetyProblemImportDto> list = safetyProblemImportDao.getProblemImportDtoByContiton(safetyProblemImportInDto);
        return NR.r(list, total, safetyProblemImportInDto.getPageIdx());
    }
    private void setDate(SafetyProblemImportInDto safetyProblemImportInDto) {
        if (StringUtils.isNotBlank(safetyProblemImportInDto.getCheckDate1()) && StringUtils.isBlank(safetyProblemImportInDto.getCheckDate2())) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            safetyProblemImportInDto.setCheckDate2(df.format(new Date()));// new Date()为获取当前系统时间
        }
    }

    @Override
    public String querySafetyProblemImportById(int id) {
        return NR.r(safetyProblemImportDao.getSafetyProblemImportDtoById(id, url));
    }

    @Override
    public String updateProblemImport(SafetyProblemImportDto safetyProblemImportDto) {
        if (safetyProblemImportDao.updateProblemImportDto(safetyProblemImportDto) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    @Transactional
    public String deleteProblem(int id) {
        if (safetyProblemImportDao.deleteProblem(id) <= 0)
            throw new WLHSException("删除失败");
        return NR.r();
    }


}
