package com.wlhse.service.impl;

import com.wlhse.dao.FactorDao;
import com.wlhse.dao.QualityFactorDao;
import com.wlhse.dto.inDto.FactorDto;
import com.wlhse.dto.inDto.QualityFactorDto;
import com.wlhse.dto.outDto.FactorOutDto;
import com.wlhse.dto.outDto.QualityFactorOutDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.FactorService;
import com.wlhse.service.QualityFactorService;
import com.wlhse.util.R;
import com.wlhse.util.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QualityFactorServiceImpl implements QualityFactorService {
    @Resource
    private QualityFactorDao qualityFactorDao;

    @Resource
    private TreeUtil treeUtil;

    @Override
    public R queryFactor(QualityFactorDto factorDto) {
        List<QualityFactorOutDto> result = new ArrayList<>();
        try {
            if (factorDto.getName().equals("隐患")) {//物
                String code = qualityFactorDao.findByName("物的不安全状态");//环境不良
                result.addAll(qualityFactorDao.findAll(code));
                String code2 = qualityFactorDao.findByName("环境不良");//环境不良
                result.addAll(qualityFactorDao.findAll(code2));
            } else if (factorDto.getName().equals("违章")) {
                String code = qualityFactorDao.findByName("人的不安全行为");//环境不良
                result.addAll(qualityFactorDao.findAll(code));
                String code2 = qualityFactorDao.findByName("管理缺陷");//环境不良
                result.addAll(qualityFactorDao.findAll(code2));
            } else {
                throw new WLHSException("查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("失败");
        }
        System.out.println("查询。。。。。");
        return R.ok().put("data", treeUtil.getQualityFactoryTree(result));
    }
}
