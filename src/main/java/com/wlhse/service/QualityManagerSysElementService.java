package com.wlhse.service;

import com.wlhse.dto.QualityManagerSysEleReviewTermsDto;
import com.wlhse.dto.QualityManagerSysElementProDesDto;
import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.entity.QualityElementsPojo;
import com.wlhse.util.R;


public interface QualityManagerSysElementService {
    //------旧代码区----------------

    //--------新代码区--------------
    //th-查询基本数据表
    R queryAllElement();
    //年度要素
    R queryYearElement(YearElementsDto yearElementsDto);
    //th-查询基本数据表两级
    R queryChildElement();
    //th---根据是否启用查询节点
    R queryAllElements(Integer tag);

    //th---更新状态
    R updateElementStatus(int id);
    //th---更新内容
    R updateElementcontent(QualityElementsPojo qhseManageSysElement);
    //th---添加节点内容
    R addElement(QualityElementsPojo qhseManageSysElement);
    //根据code查询对应的问题描述
    R querryQhseProblemDiscription(String code);
    //根据ID删除对应的问题描述
    R deleteQhseProblemDiscription(Integer id);
    //改
    R updateQhseProblemDiscription(QualityManagerSysElementProDesDto qHSEproblemDiscriptionDto);
    //增加
    R addQhseProblemDiscription(QualityManagerSysElementProDesDto qHSEproblemDiscriptionDto);

    //根据code查询对应的条款
    R queryQualityReviewTerms(String code);
    //根据ID删除对应的条款
    R deleteQualityReviewTerms(Integer id);
    //改
    R updateQualityReviewTerms(QualityManagerSysEleReviewTermsDto qualityManagerSysEleReviewTermsDto);
    //增加
    R addQualityReviewTerms(QualityManagerSysEleReviewTermsDto qualityManagerSysEleReviewTermsDto);

    R addYearElement(YearElementsDto yearElementsDto);

    R getTableCheckedProgress(int tableId);

    R queryYearElements(YearElementsDto yearElementsDto);
}
