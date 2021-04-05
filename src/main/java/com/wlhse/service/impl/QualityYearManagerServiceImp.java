package com.wlhse.service.impl;

import com.wlhse.cache.JedisClient;
import com.wlhse.dao.QualityManagerSysElementDao;
import com.wlhse.dao.QualityYearManagerDao;

import com.wlhse.dto.QualityYearManagerDto;
import com.wlhse.dto.QualityYearManagerDtoWithEmployeeId;
import com.wlhse.dto.inDto.QualityYearElementsDto;


import com.wlhse.dto.outDto.ElementAndConfigStatusDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.QualityYearManagerService;
import com.wlhse.util.R;
import com.wlhse.util.TreeUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class QualityYearManagerServiceImp implements QualityYearManagerService {
    @Resource
    JedisClient jedisClient;
    @Resource
    QualityYearManagerDao qualityYearManagerDao;
    @Resource
    QualityManagerSysElementDao qualityManagerSysElementDao;
    @Resource
    private TreeUtil treeUtil;
    private final int QHSEMSETREE_MAX_HEIGHT=4;//定义树的最大层数
    private final int QHSEMSETREE_CODE_BITS=3;//定义树的每级编码位数
    private final int QHSEMSETREE_CODE_MAXLEN=QHSEMSETREE_MAX_HEIGHT*QHSEMSETREE_CODE_BITS;//叶子节点长度
    @Override
    public R queryAll(QualityYearManagerDtoWithEmployeeId companyYearManagerDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Map<String, String> map1 = jedisClient.hGetAll(token);
        int employeeId = Integer.valueOf(map1.get("employeeId"));
        companyYearManagerDto.setEmployeeId(employeeId);
        List<QualityYearManagerDto> pojo=qualityYearManagerDao.queryAll(companyYearManagerDto);
        Map<String, Object> map = new HashMap<>();
        map.put("data", pojo);
        return R.ok(map);
    }

    @Override
    public R deleteALL(int id) {
        //Should use transactions to delete data from those tables.Ensure the atomicity of database transactions.
        try {
            qualityYearManagerDao.deleteAll(id);
        }
        catch (Exception e) {
            throw new WLHSException("删除失败");
        }
        return R.ok();
    }



    @Override
    public R addCompanyYearManager(QualityYearManagerDtoWithEmployeeId companyYearManagerDto, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        Map<String, String> map1 = jedisClient.hGetAll(token);
        int employeeId = Integer.valueOf(map1.get("employeeId"));
        companyYearManagerDto.setEmployeeId(employeeId);
        if(qualityYearManagerDao.addCompanyYearManager(companyYearManagerDto)<=0)
            throw new WLHSException("新增失败");
        return R.ok();
    }

    @Override
    public R queryYearElements(QualityYearElementsDto qualityYearManagerDto) {
        List<QualityYearElementsDto> lists=qualityYearManagerDao.queryYearElement(qualityYearManagerDto);
        for (QualityYearElementsDto yearElement:lists) {
            int sums=qualityYearManagerDao.querySchedule(yearElement.getCode(),yearElement.getCompanyCode(),yearElement.getYear());
            int num=qualityYearManagerDao.querySchdules(yearElement.getCode(),yearElement.getCompanyCode(),yearElement.getYear());
            int num1=sums-num;
            if(yearElement.getCode().length()!=QHSEMSETREE_MAX_HEIGHT*QHSEMSETREE_CODE_BITS)//树的最大编码
                yearElement.setSchedule(num1+"/"+sums);
        }
        R ok = R.ok();
        ok.put("data", treeUtil.getQualityYearElementTree(lists));
        return ok;
    }


    @Transactional
    @Override
    public R addYearElement(QualityYearElementsDto yearElementsDto) {
        try {
            String[] codes = yearElementsDto.getCodes().split(";");
            /*List<YearElementsDto> list = new ArrayList<>();*/
            Integer tableId = yearElementsDto.getQualityCompanyYearManagerSysElementTableID();
            String companyCode = yearElementsDto.getCompanyCode();
            String companyName = yearElementsDto.getCompanyName();
            String year = yearElementsDto.getYear();
            Integer len = qualityManagerSysElementDao.findMaxLen();
            //get the table's elements status and code
            Map<String, String> elementCodeAndConfigStatusMap = getElementCodeAndConfigStatusMap(tableId);
            Map<String,String> elementsFromClients=new HashMap<>(),
                    needToReopenElement=new HashMap<>(),
                    needToAddElement=new HashMap<>(),
                    openedMap = new HashMap<>(),
                    stoppedMap = new HashMap<>(),
                    elementNeedToStop=new HashMap<>(),
                    elementsFromClients1=new HashMap<>();
            Map<String,QualityYearElementsDto> map1=new HashMap<>();
            //covert codes to Map<code,configStatus>
            for (String code:codes){
                elementsFromClients.put(code,"启用");
            }
            for (Map.Entry<String, String> code : elementsFromClients.entrySet()) {
                List<QualityYearElementsDto> temp = qualityManagerSysElementDao.queryElementsByCode(code.getKey());
                for (int i = 0; i < temp.size(); i++) {
                    temp.get(i).setQualityCompanyYearManagerSysElementTableID(tableId);
                    temp.get(i).setCompanyCode(companyCode);
                    temp.get(i).setCompanyName(companyName);
                    temp.get(i).setYear(year);
                    temp.get(i).setConfigStatus("启用");
                    map1.put(temp.get(i).getCode(),temp.get(i));
                    elementsFromClients1.put(temp.get(i).getCode(),"启用");
                }
            }
            //the following algorithm is very inefficient.
            //please do some optimization.
            //commit  by Coco 2020-7-30 11:34 PM
            if (elementCodeAndConfigStatusMap.size()!=0){
                for (Map.Entry<String, String> entry : elementCodeAndConfigStatusMap.entrySet()) {
                    //find elements that have been stopped
                    if (entry.getValue().equals("停用")) {
                        stoppedMap.put(entry.getKey(), entry.getValue());
                    }
                    //find elements that  in open status
                    if(entry.getValue().equals("启用")){
                        openedMap.put(entry.getKey(), entry.getValue());
                    }
                }}
            //find elements need to stop
            if (openedMap.size()!=0){
                for (Map.Entry<String,String> entry:openedMap.entrySet()){
                    if (elementsFromClients1.containsKey(entry.getKey())==false){
                        elementNeedToStop.put(entry.getKey(),"停用");
                    }
                }
            }
            //find elements need to reopen and add
            for (Map.Entry<String,String> entry : elementsFromClients1.entrySet()){
                if (elementCodeAndConfigStatusMap.size()==0){
                    needToAddElement=elementsFromClients1;
                    break;
                }
                if (elementCodeAndConfigStatusMap.containsKey(entry.getKey())==false){
                    needToAddElement.put(entry.getKey(),entry.getValue());
                }
                if (stoppedMap.containsKey(entry.getKey())){
                    needToReopenElement.put(entry.getKey(),entry.getValue());
                }
            }

            int leafCnt=0;
            int size = needToAddElement.size();
            int result = 0;
            if (size!=0) {
                for (Map.Entry<String, String> code : needToAddElement.entrySet()) {
                    QualityYearElementsDto yearElementsDto1=new QualityYearElementsDto();
                    yearElementsDto1.setQualityCompanyYearManagerSysElementTableID(tableId);
                    yearElementsDto1.setCompanyCode(companyCode);
                    yearElementsDto1.setCompanyName(companyName);
                    yearElementsDto1.setYear(year);
                    if (map1.containsKey(code.getKey())){
                        yearElementsDto1.setName(map1.get(code.getKey()).getName());
                        yearElementsDto1.setFormula(map1.get(code.getKey()).getFormula());
                        yearElementsDto1.setAuditMode(map1.get(code.getKey()).getAuditMode());
                        yearElementsDto1.setContent(map1.get(code.getKey()).getContent());
                        yearElementsDto1.setInitialScore(map1.get(code.getKey()).getInitialScore());
                        yearElementsDto1.setTotalCount(map1.get(code.getKey()).getTotalCount());
                    }
                    if (len.equals(code.getKey().length())){
                        leafCnt++;
                        yearElementsDto1.setStatus("未提供");
                        yearElementsDto1.setFileCheckStatus("未审核");
                    }
                    yearElementsDto1.setConfigStatus("启用");
                    yearElementsDto1.setCode(code.getKey());
                    //add new element
                    qualityManagerSysElementDao.addYearElement(yearElementsDto1);
                }
            }

            //update Manager Sys Element's configStatus
            //when some elements need to stop.
            if (elementNeedToStop.size()!=0){

                for (Map.Entry<String,String> entry:elementNeedToStop.entrySet()) {
                    qualityManagerSysElementDao.updateConfigStatus(entry.getKey(), tableId, entry.getValue());

                }
            }
            //when some elements need to reopen
            if (needToReopenElement.size()!=0){
                for (Map.Entry<String,String> entry:needToReopenElement.entrySet()){
                    leafCnt++;
                    qualityManagerSysElementDao.updateConfigStatus(entry.getKey(),tableId,entry.getValue());
                }
            }
            //将叶子结点总数放入缓存中。
            jedisClient.set("QT"+tableId,String.valueOf(leafCnt));
            if (result < 0)
                throw new WLHSException("新增失败");
        }catch (Exception e) {
            e.printStackTrace();
            throw new WLHSException("新增失败");
        }
        return R.ok();
    }

    private Map<String,String> getElementCodeAndConfigStatusMap(int tableId){
        Map<String,String> map=new HashMap<>();
        List<ElementAndConfigStatusDto> elementAndConfigStatusDto = qualityManagerSysElementDao.selectCodeAndConfigStatusByTableId(tableId);
        Iterator<ElementAndConfigStatusDto> iterator = elementAndConfigStatusDto.iterator();
        while (iterator.hasNext()){
            ElementAndConfigStatusDto next = iterator.next();
            map.put(next.getElementCode(),next.getConfigStatus());
        }
        return map;
    }
}
