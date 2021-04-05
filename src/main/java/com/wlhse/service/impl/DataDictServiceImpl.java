package com.wlhse.service.impl;

import com.wlhse.dao.DataDictDao;
import com.wlhse.entity.DataDictPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.DataDictService;
import com.wlhse.util.DictCode;
import com.wlhse.util.TreeUtil;
import com.wlhse.util.state_code.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataDictServiceImpl implements DataDictService {

    @Resource
    private DataDictDao dataDictPojoDao;

    @Resource
    private TreeUtil treeUtill;

    @Override
    public String getChirdren(String parent) {
        return NR.r(dataDictPojoDao.queryAllChildren(parent));
    }

    @Override
    @Transactional
    public String insertSonDataDict(DataDictPojo dictPojo) {
        int i = -1;
        if (StringUtils.isNotBlank(dictPojo.getName())) {
            String dictCode = dictPojo.getDictCode();
            if (dictCode.length() > DictCode.DATA_DICT_LEVEL_1) {
                dictCode = dictCode.substring(0, DictCode.DATA_DICT_LEVEL_1);
            }
            i = dataDictPojoDao.insertDataDict(dictCode, dictPojo.getName());
        }
        if (i <= 0)
            throw new WLHSException("新增失败");
        return NR.r();
    }

    @Override
    @Transactional
    public String deleteDataDict(DataDictPojo dictPojo) {
        String code = dictPojo.getDictCode();
        int i = -1;
        if (StringUtils.isNotBlank(code) && code.length() != DictCode.DATA_DICT_LEVEL_1) {
            i = dataDictPojoDao.deleteDataDictByCode(code);
        }
        if (i <= 0)
            throw new WLHSException("删除失败");
        return NR.r();
    }

    @Override
    @Transactional
    public String updateDataDict(DataDictPojo dictPojo) {
        if (dataDictPojoDao.updateCode(dictPojo.getId(), dictPojo.getName()) <= 0)
            throw new WLHSException("更新失败");
        return NR.r();
    }

    @Override
    @Transactional
    public String deleteDataDictById(int id) {
        if (dataDictPojoDao.deleteDataDictById(id) <= 0)
            throw new WLHSException("删除失败");
        return NR.r();
    }

    @Override
    public String getDataDictTree() {
        return NR.r(treeUtill.getDataDictTree(dataDictPojoDao.getAllDataDictPojo()));
    }
}
