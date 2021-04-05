package com.wlhse.service.impl;

import com.wlhse.dao.ElementReviewDao;
import com.wlhse.dao.QHSEManageSysElementsDao;
import com.wlhse.dao.QualityElementReviewDao;
import com.wlhse.dto.inDto.YearElementsDto;
import com.wlhse.dto.outDto.QHSECompanyYearManagerSysElementDto;
import com.wlhse.entity.QualityInputAttachPojo;
import com.wlhse.entity.QualityManergerSysElementPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QualityElementReviewServer;
import com.wlhse.util.R;
import com.wlhse.util.TreeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Author:melon
 * Origin:2020/10/5
 **/
@Service
public class QualityElementReviewServiceImp  implements QualityElementReviewServer {
    @Resource
    QualityElementReviewDao qualityElementReviewDao;
    @Resource
    TreeUtil treeUtil;
    @Value("${RESOURCES_Quality_ElementInput_Evidence_URL}")
    private String url;
    @Resource
    QHSEManageSysElementsDao qhseManageSysElementsDao;
    @Resource
    private ElementReviewDao elementReviewDao;

    @Override
    public R query(String companyCode, String year) {
        if("未接收".equals(qhseManageSysElementsDao.queryTask(year,companyCode))) return R.ok();
        List<QualityManergerSysElementPojo> lists = qualityElementReviewDao.query(companyCode,year);
        for (QualityManergerSysElementPojo pojo:lists) {
            int sums=qhseManageSysElementsDao.querySchedule1(pojo.getCode(),pojo.getCompanyCode(),pojo.getYear());
            int num=qhseManageSysElementsDao.querySchdules1(pojo.getCode(),pojo.getCompanyCode(),pojo.getYear());
            int num1=sums-num;
            if(pojo.getCode().length()!=12)//树的最大编码
                pojo.setSchedule(num1+"/"+sums);
        }
        R ok = R.ok();
       return ok.put("data",treeUtil.getCurrentQualityElementTree(lists));
    }

    @Override
    public R queryAttach(Integer id) {
        QualityInputAttachPojo qualityInputAttachPojo=qualityElementReviewDao.queryAttach(id);
        qualityInputAttachPojo.setUrl(url);
        return R.ok().put("data",qualityInputAttachPojo);
    }

    @Override
    public R insertAttach(QualityInputAttachPojo qualityInputAttachPojo) {
        QualityInputAttachPojo pojo=qualityElementReviewDao.queryAttach(qualityInputAttachPojo.getQuality_CompanyYearManagerSysElement_ID());
        if(pojo==null) qualityElementReviewDao.insertAttach(qualityInputAttachPojo);
        else  qualityElementReviewDao.updateAttach(qualityInputAttachPojo);
        return R.ok();
    }

    @Override
    public R queryCheck(Integer tag, String companyCode, String year) {
            List<QualityManergerSysElementPojo> lists=qualityElementReviewDao.queryCheck(companyCode,year,tag);
            List<String> codes = new ArrayList<>() ;
            if(lists!=null && !lists.isEmpty()){
                int layer=2,grad=3;//可以抽取方法返回指定层数,grad为树状code梯度
                for(;grad<3*layer;grad+=3){
                    for (int i=0;i<lists.size();i++)
                        codes.add(lists.get(i).getCode().substring(0,lists.get(i).getCode().length() - grad));
                }
                //去重父节点最高效
                HashSet<String> hs=new HashSet(codes);
                codes.clear();
                codes.addAll(hs);
                //查询父节点并插入
                for (String code : codes) {
                    QualityManergerSysElementPojo parent=elementReviewDao.queryParentss(code,companyCode,year);
                    lists.add(parent);
                }
            }
            R ok = R.ok();
           return  ok.put("data", treeUtil.getCurrentQualityElementTree(lists));
    }

    @Override
    public R pass(Integer id, Integer tag, String pass,String NegativeOpinion) {
        if("通过".equals(pass)) qualityElementReviewDao.updatePass(id,tag);
        else {
            qualityElementReviewDao.updateNoPass(id,pass);
            if(NegativeOpinion!=null)qualityElementReviewDao.updateNegativeOpinion(NegativeOpinion,id);
        }
        return R.ok();
    }


}
