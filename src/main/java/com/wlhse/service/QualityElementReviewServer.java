package com.wlhse.service;

import com.wlhse.entity.QualityInputAttachPojo;
import com.wlhse.entity.QualityManergerSysElementPojo;
import com.wlhse.util.R;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author:melon
 * Origin:2020/10/5
 **/
public interface QualityElementReviewServer {
    //查录入树
   R query(String companyCode, String year);
    //查附件
    R queryAttach(Integer id);
    //录附件
    R insertAttach(QualityInputAttachPojo qualityInputAttachPojo);
    R queryCheck(Integer tag,String companyCode, String year);
    //质量要素通过不通过
    R pass(Integer id,Integer tag,String pass,String NegativeOpinion);
}
