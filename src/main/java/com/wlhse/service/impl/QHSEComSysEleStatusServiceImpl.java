package com.wlhse.service.impl;

import com.wlhse.dao.QHSEComSysEleStatusDao;
import com.wlhse.dto.QHSEComSysEleStatusDto;
import com.wlhse.entity.QHSEComSysEleStatusPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QHSEComSysEleStatusService;
import com.wlhse.util.state_code.NR;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QHSEComSysEleStatusServiceImpl implements QHSEComSysEleStatusService {
    @Resource
    private QHSEComSysEleStatusDao qhseComSysEleStatusDao;

    @Override
    public String addQHSEComSysEleStatus(QHSEComSysEleStatusDto qhseComSysEleStatusDto) {
        try{
            int i = qhseComSysEleStatusDao.addComstatus(qhseComSysEleStatusDto);
            if(i>0){
                return NR.r();
            }
            else{
                throw new WLHSException("添加失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("添加失败！");
        }
    }

    @Override
    public String deleteQHSEComSysEleStatus(Integer id) {
        try{
            int i = qhseComSysEleStatusDao.deleteComstatus(id);
            if(i>0){
                return NR.r();
            }
            else{
                throw new WLHSException("删除失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("删除失败！");
        }
    }

    @Override
    public String updateQHSEComSysEleStatus(QHSEComSysEleStatusDto qhseComSysEleStatusDto) {
        try{
            int i = qhseComSysEleStatusDao.updateComstatus(qhseComSysEleStatusDto);
            if(i>0){
                return NR.r();
            }
            else{
                throw new WLHSException("修改失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("修改失败！");
        }
    }

    @Override
    public String querryQHSEComSysEleStatus(QHSEComSysEleStatusDto qhseComSysEleStatusDto) {
        try{
            List<QHSEComSysEleStatusPojo> qhseComSysEleStatusPojo = qhseComSysEleStatusDao.querryComstatus(qhseComSysEleStatusDto);
            if(qhseComSysEleStatusPojo==null){
                String data=NR.r("查询结果为空！");
                return data;
            }else{
                String data=NR.r(qhseComSysEleStatusPojo);
                return data;
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new WLHSException("查询失败!");
        }
    }
}
