package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.QHSEProblemImportDao;
import com.wlhse.dto.inDto.QHSEProblemImportInDto;
import com.wlhse.dto.outDto.QHSEProblemImportDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QHSEProblemImportService;
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
public class QHSEProblemImportServiceImpl implements QHSEProblemImportService {
    @Resource
    private QHSEProblemImportDao qhseProblemImportDao;

    @Value("RESOURCES_QHSEProblem_URL")
    private String url;

    @Override
    @Transactional
    public R newInsertProblemImport(QHSEProblemImportDto qhseProblemImportDto) {
        String hash = HashUtil.hash(qhseProblemImportDto.getCompanyName() + qhseProblemImportDto.getCheckDate() + qhseProblemImportDto.getCheckItemCode());
        if (qhseProblemImportDao.getHashCount(hash) != 0)
            throw new WLHSException("重复录入问题,请检查!");
        qhseProblemImportDto.setHash(hash);
        qhseProblemImportDao.addNewProblem(qhseProblemImportDto);
        return R.ok();
    }

    @Override
    public String queryProblemImport(QHSEProblemImportInDto qhseProblemImportInDto) {
        //查询所有
        qhseProblemImportInDto.setUrl(url);
        setDate(qhseProblemImportInDto);
        if (qhseProblemImportInDto.getType().equals("all"))
            return NR.r(qhseProblemImportDao.getProblemImportDtoByContiton(qhseProblemImportInDto));
        //分页查询
        int total = qhseProblemImportDao.getProblemImportDtoCount(qhseProblemImportInDto);
        PageHelper.startPage(qhseProblemImportInDto.getPageIdx(), qhseProblemImportInDto.getPageSize());
        List<QHSEProblemImportDto> list = qhseProblemImportDao.getProblemImportDtoByContiton(qhseProblemImportInDto);
        return NR.r(list, total, qhseProblemImportInDto.getPageIdx());
    }

    private void setDate(QHSEProblemImportInDto qhseProblemImportInDto) {
        if (StringUtils.isNotBlank(qhseProblemImportInDto.getCheckDate1()) && StringUtils.isBlank(qhseProblemImportInDto.getCheckDate2())) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            qhseProblemImportInDto.setCheckDate2(df.format(new Date()));// new Date()为获取当前系统时间
        }
    }

    @Override
    public String queryProblemImportById(int id) {
        return NR.r(qhseProblemImportDao.getProblemImportDtoById(id, url));
    }

    @Override
    public String updateProblemImport(QHSEProblemImportDto qhseProblemImportDto) {
        if (qhseProblemImportDao.updateProblemImportDto(qhseProblemImportDto) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    @Transactional
    public String deleteProblem(int id) {
        if (qhseProblemImportDao.deleteProblem(id) <= 0)
            throw new WLHSException("删除失败");
        return NR.r();
    }


}
