package com.wlhse.service.impl;

import com.github.pagehelper.PageHelper;
import com.wlhse.dao.FileDao;
import com.wlhse.dto.inDto.FileInDto;
import com.wlhse.entity.FilePojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.RuleRegulationService;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RuleRegulationServiceImpl implements RuleRegulationService {

    @Resource
    private FileDao fileDao;

    @Value("${RESOURCES_URL}")
    private String url;

    @Override
    @Transactional
    public String removeRuleRegulation(Integer id) {
        if (fileDao.deleteFile(id) <= 0)
            throw new WLHSException("删除失败");
        return NR.r();
    }

    @Override
    @Transactional
    public String addRuleRegulationName(FilePojo filePojo) {
        if (fileDao.addFile(filePojo) <= 0)
            throw new WLHSException("新增失败");
        return NR.r();
    }

    @Override
    public String getRuleRegulationByCondition(FileInDto fileInDto) {
        fileInDto.setUrl(url);
        if (fileInDto.getType().equals("all"))
            return NR.r(fileDao.queryFilesByCondition(fileInDto));
        int total = fileDao.queryTotalByCondition(fileInDto);//数据总数
        PageHelper.startPage(fileInDto.getPageIdx(), fileInDto.getPageSize());
        List<FilePojo> list = fileDao.queryFilesByCondition(fileInDto);
        return NR.r(list, total, fileInDto.getPageIdx());
    }

    @Override
    public String queryFileById(Integer id) {
        return NR.r(fileDao.queryFileById(id));
    }
}
