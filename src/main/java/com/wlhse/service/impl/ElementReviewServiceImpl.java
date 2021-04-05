package com.wlhse.service.impl;

import com.wlhse.cache.JedisClient;
import com.wlhse.dao.ElementReviewDao;
import com.wlhse.dao.QHSEManageSysElementsDao;
import com.wlhse.dao.QHSETaskDao;
import com.wlhse.dao.QhseElementsInputDao;
import com.wlhse.dto.TaskStatusDto;
import com.wlhse.dto.inDto.ElementReviewDto;
import com.wlhse.dto.outDto.QHSECompanyYearManagerSysElementDto;
import com.wlhse.dto.outDto.QhseEvidenceAttatchDto;
import com.wlhse.entity.QualityManergerSysElementPojo;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.ElementReviewService;
import com.wlhse.util.R;
import com.wlhse.util.TreeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ElementReviewServiceImpl implements ElementReviewService {
    @Resource
    private ElementReviewDao elementReviewDao;


    @Resource
    private TreeUtil treeUtil;

    @Value("${RESOURCES_QHSE_ElementInput_Evidence_URL}")
    private String url;
    @Resource
    JedisClient jedisClient;
    @Resource
    QhseElementsInputDao qhseElementsInputDao;
    @Resource
    QHSEManageSysElementsDao elementsDao;
    @Resource
    QHSETaskDao taskDao;
    @Override
    public R query(ElementReviewDto elementReviewDto) {
    List<QHSECompanyYearManagerSysElementDto> lists=elementReviewDao.query(elementReviewDto);
        List<String> codes = new ArrayList<>() ;
        if(lists!=null && !lists.isEmpty()){
            for (int i=0;i<lists.size();i++) codes.add(lists.get(i).getCode().substring(0,lists.get(i).getCode().length() - 3));
            //去重父节点
            for (int i = 0; i < codes.size() - 1; i++) {
                for (int j = codes.size() - 1; j > i; j--) {
                    if (codes.get(j).equals(codes.get(i))) codes.remove(j);
                }
            }
            //查询父节点并插入
            for (String code : codes) {
                List <QHSECompanyYearManagerSysElementDto> parent=elementReviewDao.queryParent(code);
                if(parent!=null && !parent.isEmpty())
                lists.add(parent.get(0));
            }
        }
        R ok = R.ok();
        ok.put("data", treeUtil.getCurrentQhseElementTree1(lists));
        return ok;
    }

    @Override
    public R queryS(ElementReviewDto elementReviewDto) {
        List<QHSECompanyYearManagerSysElementDto> lists=elementReviewDao.queryS(elementReviewDto);
        List<String> codes = new ArrayList<>() ;
        if(lists!=null && !lists.isEmpty()){
            for (int i=0;i<lists.size();i++) codes.add(lists.get(i).getCode().substring(0,lists.get(i).getCode().length() - 3));
            //去重父节点
            for (int i = 0; i < codes.size() - 1; i++) {
                for (int j = codes.size() - 1; j > i; j--) {
                    if (codes.get(j).equals(codes.get(i))) codes.remove(j);
                }
            }
            //查询父节点并插入
            for (String code : codes) {
                List <QHSECompanyYearManagerSysElementDto>parent=new ArrayList<>();
                parent=elementReviewDao.queryParent(code);
                if(parent!=null && !parent.isEmpty())
                    lists.add(parent.get(0));
            }
        }
        R ok = R.ok();
        ok.put("data", treeUtil.getCurrentQhseElementTree1(lists));
        return ok;
    }

    @Override
    @Transactional
    public R updateStatus(ElementReviewDto elementReviewDto) {
        int tableId = qhseElementsInputDao.getQHSEYearManagerTableIdByElementId(elementReviewDto.getqHSE_CompanyYearManagerSysElement_ID());
        String status = elementReviewDto.getStatus();
        int i=elementReviewDao.update(elementReviewDto);
        int j=1;
        //TODO 添加不批准的逻辑
        if (status.equals("不通过")&&elementReviewDto.getNegativeOpinion()!=null){
            j=elementReviewDao.updateAddvice(elementReviewDto);
        }
        // 更新状态
        taskDao.updateCheckStatus(tableId,"重新录入中");
        if (i*j< 0)
            throw new WLHSException("更新失败");
        return R.ok();
    }


    @Override
    public R queryAll(QhseEvidenceAttatchDto qhseEvidenceAttatchDto) throws ParseException {
        qhseEvidenceAttatchDto.setUrl(url);
        List<QhseEvidenceAttatchDto> qhseEvidenceAttatchDtos = elementReviewDao.queryAll(qhseEvidenceAttatchDto);
        QhseEvidenceAttatchDto returnPojo=new QhseEvidenceAttatchDto();
        if(qhseEvidenceAttatchDtos!=null && !qhseEvidenceAttatchDtos.isEmpty()){
            Long dates[] = new Long[qhseEvidenceAttatchDtos.size()];
            for (int i = 0; i <qhseEvidenceAttatchDtos.size(); i++) {
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                String str=qhseEvidenceAttatchDtos.get(i).getUploadTime();
                Date date=df.parse(str);
                dates[i] = date.getTime();
            }
            Long maxIndex = dates[0];// 定义最大值为该数组的第一个数
            int j,k = 0;
            for (j = 1; j < dates.length; j++) {
                if (maxIndex <=dates[j]){
                    maxIndex = dates[j];
                    k=j;
                }
            }
            returnPojo = qhseEvidenceAttatchDtos.get(k);
            //拼接图片
            if(returnPojo.getAttach()!=null&&!"".equals(returnPojo.getAttach())) {//修复空指针异常
                String[] urs = returnPojo.getAttach().split(";");
                String strs = "";
                for (String str : urs) {
                    strs += url + str + ";";
                }
                returnPojo.setAttach(strs);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", returnPojo);
        return R.ok(map);
    }

    @Override
    public List<QHSECompanyYearManagerSysElementDto> queryParent(String code) {
        return elementReviewDao.queryParent(code);
    }

    @Override
    public int updateCheck(ElementReviewDto elementReviewDto) {
        // 比对审核个数和叶子结点总数，若审核个数和叶子节点总数相等，将任务状态改为批准中
        int tableId = qhseElementsInputDao.getQHSEYearManagerTableIdByElementId(elementReviewDto.getqHSE_CompanyYearManagerSysElement_ID());
        //第一次对表中元素进行检查
        if(jedisClient.get("TCheck" + tableId)==null){
            jedisClient.set("TCheck"+tableId,String.valueOf(1));
        }
        else{
            jedisClient.set("TCheck"+tableId,String.valueOf(Integer.valueOf(jedisClient.get("TCheck"+tableId))+1));
        }
        if (jedisClient.get("T"+tableId)==null){
            int allLeafNodeNumber = elementsDao.getAllLeafNodeNumber(tableId);
            jedisClient.set("T"+tableId,String.valueOf(allLeafNodeNumber));
        }
        else {
            //全部审核完毕
            if (jedisClient.get("TCheck" + tableId).equals(jedisClient.get("T" + tableId))) {
                TaskStatusDto taskStatusDto=new TaskStatusDto(tableId,"批准中");
                taskDao.updateTaskStatusByTableId(taskStatusDto);
            }
        }
        return  elementReviewDao.updateCheck(elementReviewDto);
    }

    @Override
    public int updateApprove(ElementReviewDto elementReviewDto) {
        // 比对批准个数和叶子结点总数，若批准个数和叶子节点总数相等，将任务状态改为任务完成
        int tableId = qhseElementsInputDao.getQHSEYearManagerTableIdByElementId(elementReviewDto.getqHSE_CompanyYearManagerSysElement_ID());
        //第一次对表中元素进行批准
        if(jedisClient.get("TApprove" + tableId)==null){
            jedisClient.set("TApprove"+tableId,String.valueOf(1));
        }
        else{
            jedisClient.set("TApprove"+tableId,String.valueOf(Integer.valueOf(jedisClient.get("TApprove"+tableId))+1));
        }
        if (jedisClient.get("T"+tableId)==null){
            int allLeafNodeNumber = elementsDao.getAllLeafNodeNumber(tableId);
            jedisClient.set("T"+tableId,String.valueOf(allLeafNodeNumber));
        }
        else {
            //全部批准完毕
            if (jedisClient.get("TApprove" + tableId).equals(jedisClient.get("T" + tableId))) {
                TaskStatusDto taskStatusDto=new TaskStatusDto(tableId,"任务完成");
                taskDao.updateTaskStatusByTableId(taskStatusDto);
            }
        }
        return  elementReviewDao.updateApprove(elementReviewDto);
    }

    @Override
    public R deletes(ElementReviewDto elementReviewDto) {
        int j= elementReviewDao.deleteAttach(elementReviewDto);
        int k=elementReviewDao.deleteNewOriginFile(elementReviewDto);
       int i= elementReviewDao.delete(elementReviewDto);
       if(i*j<0||i*k<0||j*k<0) throw new WLHSException("不通过删除附件失败");
        return R.ok();
    }

    @Override
    public R shows(ElementReviewDto elementReviewDto) {
        List<QHSECompanyYearManagerSysElementDto> lists=elementReviewDao.queryCheck(elementReviewDto);
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
              QHSECompanyYearManagerSysElementDto parent=elementReviewDao.queryParents(code,elementReviewDto.getCompanyCode(),elementReviewDto.getYear());
              lists.add(parent);
            }
        }
        R ok = R.ok();
        ok.put("data", treeUtil.getCurrentQhseElementTree1(lists));
        return ok;
    }

    @Override
    public R qualityShows(QualityManergerSysElementPojo qualityManergerSysElementPojo) {
        List<QualityManergerSysElementPojo> lists=elementReviewDao.queryQualityCheck(qualityManergerSysElementPojo);
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
              QualityManergerSysElementPojo parent=elementReviewDao.queryParentss(code,qualityManergerSysElementPojo.getCompanyCode(),qualityManergerSysElementPojo.getYear());
                lists.add(parent);
            }
        }
        R ok = R.ok();
        ok.put("data", treeUtil.getCurrentQualityElementTree(lists));
        return ok;
    }

    @Override
    public R queryAllElement(ElementReviewDto elementReviewDto) {
        R r=new R();
        r.put("AllElement",elementReviewDao.queryAllElement(elementReviewDto));
        r.put("NotInput",elementsDao.querySchdules(null,elementReviewDto.getCompanyCode(),elementReviewDto.getYear()));
        return r;
    }

    @Override
    public R queryQualityAllElement(QualityManergerSysElementPojo qualityManergerSysElementPojo) {
        R r=new R();
        r.put("AllElement",elementReviewDao.queryQualityAllElement(qualityManergerSysElementPojo));
        r.put("NotInput",elementsDao.querySchdules1(null,qualityManergerSysElementPojo.getCompanyCode(),qualityManergerSysElementPojo.getYear()));
        return r;
    }

    @Override
    public R showNoPass(ElementReviewDto elementReviewDto) {
        List<QHSECompanyYearManagerSysElementDto> lists=elementReviewDao.queryNoPasElement(elementReviewDto);
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
                QHSECompanyYearManagerSysElementDto parent=elementReviewDao.queryParents(code,elementReviewDto.getCompanyCode(),elementReviewDto.getYear());
                lists.add(parent);
            }
        }
        R ok = R.ok();
        ok.put("data", treeUtil.getCurrentQhseElementTree1(lists));
        return ok;
    }

    @Override
    public R passAll(int tableId, int sourceId) {
        //一键审核
        if (sourceId==1){
            elementReviewDao.passAll(tableId);
        }
        //一键批准
        else
        {
            elementReviewDao.approveAll(tableId);
        }
        return R.ok();
    }


}
