package com.wlhse.service.impl;

import com.wlhse.dao.CompanyDao;
import com.wlhse.dao.QHSEComSysEleStatusDao;
import com.wlhse.dao.QHSECompanySysElementsDao;
import com.wlhse.dto.QHSEComSysEleStatusDto;
import com.wlhse.dto.QHSEQualityFillDto;
import com.wlhse.entity.QHSEComSysEleStatusPojo;
import com.wlhse.entity.QHSECompanySysElementsPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QHSEQualityFillService;
import com.wlhse.util.TreeUtil;
import com.wlhse.util.state_code.NR;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class QHSEQualityFillServiceImpl implements QHSEQualityFillService {
    @Resource
    QHSECompanySysElementsDao qhseCompanySysElementsDao;

    @Resource
    private QHSEComSysEleStatusDao qhseComSysEleStatusDao;

    @Resource
    private TreeUtil treeUtil;

    @Resource
    private CompanyDao companyDao;

    @Value("${RESOURCES_QHSEComReport_Photoes_URL}")
    private String potoesurl;

    @Value("${RESOURCES_QHSEComReport_Videos_URL}")
    private String videosurl;

    @Override
    @Transactional
    public String addQHSEReport(QHSEQualityFillDto qhseQualityFillDto) {
        Integer count1=qhseCompanySysElementsDao.querryReport(qhseQualityFillDto);
        if(count1>0){
            throw new WLHSException("表单已存在");
        }
        try{
            Integer count=qhseCompanySysElementsDao.addQHSEReport(qhseQualityFillDto);
            //添加公司状态
            QHSEComSysEleStatusDto qhseComSysEleStatusDto=new QHSEComSysEleStatusDto();
            qhseComSysEleStatusDto.setYear(qhseQualityFillDto.getYear());
            qhseComSysEleStatusDto.setCompanyCode(qhseQualityFillDto.getCompanyCode());
            qhseComSysEleStatusDto.setStatus("未通过");
            qhseComSysEleStatusDao.addComstatus(qhseComSysEleStatusDto);
            if(count>0){
                return  NR.r();
            }else{
                throw new WLHSException("建表失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("建表失败");
        }
    }

    @Override
    public String querryQHSEReportElements(QHSEQualityFillDto qhseQualityFillDto) {
        try{
            qhseQualityFillDto.setPhotoesurl(potoesurl);
            qhseQualityFillDto.setVideosurl(videosurl);
            List<QHSECompanySysElementsPojo> list= qhseCompanySysElementsDao.querryQHSEReportElements(qhseQualityFillDto);
            List<QHSECompanySysElementsPojo> list2=treeUtil.getQHSEReportTree(list);
            //查询报告状态
            QHSEComSysEleStatusDto qhseComSysEleStatusDto=new QHSEComSysEleStatusDto();
            qhseComSysEleStatusDto.setYear(qhseQualityFillDto.getYear());
            qhseComSysEleStatusDto.setCompanyCode(qhseQualityFillDto.getCompanyCode());
            List<QHSEComSysEleStatusPojo> list3=qhseComSysEleStatusDao.querryComstatus(qhseComSysEleStatusDto);
            return NR.r(list2,companyDao.queryByCompanyCode(qhseQualityFillDto.getCompanyCode()),qhseQualityFillDto.getCompanyCode(),qhseQualityFillDto.getYear(),list3.get(0).getStatus());
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("查询失败");
        }
    }

    @Override
    @Transactional
    public String updateQHSEReportElements(QHSEQualityFillDto qhseQualityFillDto) {
        try{
            QHSECompanySysElementsPojo poj=qhseCompanySysElementsDao.querryActualScoreByID(qhseQualityFillDto.getId());//查询原始分数,Company,year,completedCount,Code
            qhseCompanySysElementsDao.updateQHSEReportElements(qhseQualityFillDto);
            qhseQualityFillDto.setDiertaScore(qhseQualityFillDto.getActualScore()-poj.getActualScore());//变化分数
            qhseQualityFillDto.setCompanyCode(poj.getCompanyCode());
            qhseQualityFillDto.setYear(poj.getYear());
            qhseQualityFillDto.setCode(poj.getCode());
            if(poj.getCompletedCount()>0){//不是第一次修改
                qhseQualityFillDto.setFlag(false);
            }
            String code=qhseQualityFillDto.getCode();
            int len=code.length();
            qhseQualityFillDto.setParentNodeCode(new LinkedList<String>());
            while(len>2){//所有父节点code
                len-=2;
                String str=code.substring(0,len);
                qhseQualityFillDto.getParentNodeCode().add(str);
            }
            qhseCompanySysElementsDao.updateParentNodes(qhseQualityFillDto);
            return NR.r();
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("修改失败");
        }
    }
}
