package com.wlhse.service.impl;

import com.wlhse.dao.FactorDao;
import com.wlhse.dao.FactorDepartmentDao;
import com.wlhse.dto.inDto.FactorDto;
import com.wlhse.dto.outDto.FactorOutDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.FactorDepartmentService;
import com.wlhse.service.FactorService;
import com.wlhse.util.R;
import com.wlhse.util.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FactorServiceImpl implements FactorService {
    @Resource
    private FactorDao factorDao;

    @Resource
    private TreeUtil treeUtil;

    @Override
    public R queryFactor(FactorDto factorDto) {
        List<FactorOutDto> result = new ArrayList<>();
        try {


            //System.out.println(treeUtil.getFactoryTree(factorDao.findAll()));
            if (factorDto.getName().equals("隐患")) {//物
                String code = factorDao.findByName("物的不安全状态");//环境不良
                result.addAll(factorDao.findAll(code));
                String code2 = factorDao.findByName("环境不良");//环境不良
                result.addAll(factorDao.findAll(code2));
            } else if (factorDto.getName().equals("违章")) {
                String code = factorDao.findByName("人的不安全行为");//环境不良
                result.addAll(factorDao.findAll(code));
                String code2 = factorDao.findByName("管理缺陷");//环境不良
                result.addAll(factorDao.findAll(code2));
            } else {
//            result.addAll(factorDao.findAll(""));
                throw new WLHSException("查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("失败");
        }
        return R.ok().put("data", treeUtil.getFactoryTree(result));
    }
}
