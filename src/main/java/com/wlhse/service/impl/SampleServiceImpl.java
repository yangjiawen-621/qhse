package com.wlhse.service.impl;

import com.wlhse.dao.SampleDao;
import com.wlhse.dto.SampleDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.SampleService;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {
    @Resource
    SampleDao sampleDao;

    @Override
    @Transactional
    public String addSample(List<SampleDto> sampleDtos) {
        for(SampleDto sampleDto:sampleDtos){
            if(sampleDao.addSample(sampleDto)<0){
                throw new WLHSException("提交报告失败");
            }
        }
        return NR.r();
    }
}
