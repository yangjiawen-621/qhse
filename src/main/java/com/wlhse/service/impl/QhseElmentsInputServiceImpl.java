package com.wlhse.service.impl;


import com.wlhse.cache.JedisClient;
import com.wlhse.dao.QHSEManageSysElementsDao;
import com.wlhse.dao.QHSETaskDao;
import com.wlhse.dao.QhseElementsInputDao;
import com.wlhse.dto.QualityFileInputInfoDto;
import com.wlhse.dto.inDto.ElementEvidenceAttachInDto;
import com.wlhse.entity.ElementInputFileInfo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QhseElementsInputService;
import com.wlhse.util.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class QhseElmentsInputServiceImpl implements QhseElementsInputService {
    @Resource
    QhseElementsInputDao qhseElementsInputDao;

    @Value("${RESOURCES_QHSE_ElementInput_Evidence_URL}")
    private String url;

    @Resource
    JedisClient jedisClient;
    @Resource
    QHSEManageSysElementsDao elementsDao;
    @Resource
    QHSETaskDao taskDao;

    //TODO 这里还有不涉及的Bug要改
    @Override
    @Transactional
    public R addElementEvidenceAttach(ElementEvidenceAttachInDto elementEvidenceAttachInDto, HttpServletRequest request) {
        //首次录入数据
        elementsDao.updateInvolveStatus(elementEvidenceAttachInDto.getId(),1);
        int checkPersonId = getUserId(request);
        ElementEvidenceAttachInDto query = qhseElementsInputDao.query(elementEvidenceAttachInDto);
        if (query== null) {
            qhseElementsInputDao.add(elementEvidenceAttachInDto);
            //将附件attach对应id放入elementFileInfo
            if(elementEvidenceAttachInDto.getAttach()!=null&&!"".equals(elementEvidenceAttachInDto.getAttach())) {
            String[] strs=elementEvidenceAttachInDto.getAttach().split(";");
            ElementInputFileInfo elementInputFileInfo = new ElementInputFileInfo();
            elementInputFileInfo.setQHSE_CompanyYearManagerSysElementEvidence_ID(elementEvidenceAttachInDto.getEvidenceID());
            for (String str : strs) {
                    elementInputFileInfo.setNewElementFileName(str);
                    qhseElementsInputDao.updateNewOriginFileName(elementInputFileInfo);
                }
            }
            qhseElementsInputDao.addAttach(elementEvidenceAttachInDto);
        } else {
            //再次录入数据
            //将附件attach对应id放入elementFileInfo
            if(elementEvidenceAttachInDto.getAttach()!=null&&!"".equals(elementEvidenceAttachInDto.getAttach())) {
                String[] strs = elementEvidenceAttachInDto.getAttach().split(";");
                ElementInputFileInfo elementInputFileInfo = new ElementInputFileInfo();
                elementInputFileInfo.setQHSE_CompanyYearManagerSysElementEvidence_ID(elementEvidenceAttachInDto.getEvidenceID());
                for (String str : strs) {
                    elementInputFileInfo.setNewElementFileName(str);
                    qhseElementsInputDao.updateNewOriginFileName(elementInputFileInfo);
                }
            }
            int i= qhseElementsInputDao.update(elementEvidenceAttachInDto);
            int j = qhseElementsInputDao.updateAttach(elementEvidenceAttachInDto);
            if (i * j < 0) throw new WLHSException("更新失败");
        }
        qhseElementsInputDao.updateStatus(elementEvidenceAttachInDto.getId(),checkPersonId);//状态变为审核
        return R.ok();
    }

    @Override
    public R queryAll(ElementEvidenceAttachInDto elementEvidenceAttachInDto) {
        ElementEvidenceAttachInDto elementEvidenceAttachInDtos=qhseElementsInputDao.query(elementEvidenceAttachInDto);
       if(elementEvidenceAttachInDtos!=null) elementEvidenceAttachInDtos.setUrl(url);
        R ok = R.ok();
        ok.put("data",elementEvidenceAttachInDtos);
        return ok;
    }

    @Override
    public String queryOriginFileName(String newElementFileName) {

        return qhseElementsInputDao.queryOriginFileName(newElementFileName);
    }

    @Override
    public String queryQualityOriginFileName(String newElementFileName) {
        return qhseElementsInputDao.queryQualityOriginFileName(newElementFileName);
    }

    @Override
    public void insertNewOriginFileName(ElementInputFileInfo elementInputFileInfo) {
       qhseElementsInputDao.insertNewOriginFileName(elementInputFileInfo);
    }

    @Override
    public void insertNewOriginFileNames(QualityFileInputInfoDto qualityFileInputInfoDto) {
        qhseElementsInputDao.insertNewOriginFileNames(qualityFileInputInfoDto);
    }

    @Override
    @Transactional
    public R submitInputResult(int tableId,int tag) {
        switch (tag){
            //在录入处点击提交按钮
            case 0:{
                //将录入状态元素的checkStatus改为1---即改为可进入审核状态
                qhseElementsInputDao.updateCheckStatus(tableId,0,1);
                //将任务状态改为审核中
                //第一次点击提交
                if (isFistSubmit(tag,tableId)){
                    taskDao.updateCheckStatus(tableId,"审核中");
                }
                else
                    taskDao.updateCheckStatus(tableId,"重新审核中");
            }break;
            //在审核处点击提交按钮
            case 1:{
                //将审核状态元素的checkStatus改为2---即改为可进入批准状态
                qhseElementsInputDao.updateCheckStatus(tableId,1,2);
                //若是第一次点击提交
                if (isFistSubmit(tag,tableId)) {
                    taskDao.updateCheckStatus(tableId, "批准中");
                }
                else
                    taskDao.updateCheckStatus(tableId,"重新批准中");
            }break;
            //在批准处点击提交
            case 2:{
                //将审核元素状态的checkStatus改为3---即不可再进行任何更改，直接进入下一流程
                qhseElementsInputDao.updateCheckStatus(tableId,2,3);
                //只能点一次提交,提交后，清空缓存
                taskDao.updateCheckStatus(tableId,"任务完成");
                //清空缓存
                jedisClient.delManyCahce(0 + "table" + tableId,0);
                jedisClient.delManyCahce(1 + "table" + tableId,0);

            }
        }

        return R.ok();
    }

    @Override
    public R notInvolve(int elementId,HttpServletRequest request) {
        elementsDao.updateInvolveStatus(elementId,0);
        //TODO 更改状态
        int checkPersonId = getUserId(request);
        qhseElementsInputDao.updateStatus(elementId,checkPersonId);//状态变为审核
        return R.ok();
    }

    private boolean isFistSubmit(int tag,int tableId){
        String s = jedisClient.get(tag + "table" + tableId);
        //第一次点击提交按钮
        if (s==null){
            jedisClient.set(tag+"table"+tableId,"true");
            return true;
        }
        return false;
    }

    private int getUserId(HttpServletRequest request){
        try {
            String token = request.getHeader("Authorization");
            if (StringUtils.isNotBlank(token))
                return Integer.parseInt(jedisClient.hGetAll(token).get("userId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}


