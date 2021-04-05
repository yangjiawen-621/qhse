package com.wlhse.dao;

import com.wlhse.entity.QualityInputAttachPojo;
import com.wlhse.entity.QualityManergerSysElementPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author:melon
 * Origin:2020/10/5
 **/
public interface QualityElementReviewDao {
    //查录入树
    List<QualityManergerSysElementPojo> query(@Param("companyCode") String companyCode,@Param("year") String year);
    //查附件
    QualityInputAttachPojo queryAttach(@Param("id")Integer id);
    //录附件
    int insertAttach(QualityInputAttachPojo qualityInputAttachPojo);
    int updateAttach(QualityInputAttachPojo qualityInputAttachPojo);
    //审核批准查询
    List<QualityManergerSysElementPojo> queryCheck(@Param("companyCode") String companyCode,@Param("year") String year,@Param("status") Integer status);
    //通过与不通过
    int updatePass(@Param("id")Integer id,@Param("tag") Integer tag);
    int updateNoPass(@Param("id")Integer id,@Param("pass") String pass);
    //不通过意见
    int updateNegativeOpinion(@Param("negativeOpinion") String negativeOpinion,@Param("id") Integer id);
}
