package com.wlhse.test;

import com.wlhse.dao.*;
import com.wlhse.dto.*;
import com.wlhse.dto.inDto.QSHEMSElementInDto;
import com.wlhse.dto.inDto.QualityManagerSysElementInDto;
import com.wlhse.dto.outDto.QhseElementsOutDto;
import com.wlhse.dto.outDto.QualityManagerSysElementOutDto;
import com.wlhse.entity.QhseElementsPojo;
import com.wlhse.entity.QualityCheckTableRecord;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.CheckTableRecordService;
import com.wlhse.service.impl.CheckTableRecordServiceImpl;
import com.wlhse.util.R;
import com.wlhse.util.SortCodeUtil;
import com.wlhse.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/*.xml"})
@Slf4j
public class Test {


    @Resource
    private TreeUtil treeUtil;

    @Resource
    private SortCodeUtil sortCodeUtil;
    @Resource
    private CheckListDao checkListDao;

    @Resource
    private QHSEManageSysElementsDao qHSEManageSysElementsDao;

    @Resource
    private QualityManagerSysElementDao qualityManagerSysElementDao;

    @Autowired
    private QualityCheckListDao qualityCheckListDao;

    @Resource
    private QualityCheckTableRecordDao qualityCheckTableRecordDao;


    @Resource
    private CheckTableRecordService tableRecordService;

    @Autowired
    private CheckTableRecordServiceImpl checkTableRecordService;

    @org.junit.Test
    public void test(){


        QualityCheckTableRecord q = new QualityCheckTableRecord();
        q.setCheckListCode("0001;00010001;00010002");
        q.setQualityCheckID(67);

        checkTableRecordService.saveQualityCheckTableRecord(q);

        QualityCheckTableRecord q1 = new QualityCheckTableRecord();
        q1.setCheckListCode("0001;00010001;00010003");
        q1.setQualityCheckID(67);
        checkTableRecordService.saveQualityCheckTableRecord(q1);

        //List<QualityCheckTableRecordDto> allQualityCheckTableRecord = new ArrayList<>();

//        QualityCheckDto qualityCheckDto = new QualityCheckDto();
//        qualityCheckDto.setCheckListCode("0001;00010001;00010002");
//        qualityCheckDto.setQualityCheckID(67);
//        String[] strings = codes.split(";");
//        for (String string : strings) {
//            QualityCheckTableRecordDto treeByCode1 = qualityCheckListDao.findTreeByCode1(string);
//            treeByCode1.setQualityCheckID(67);
//            treeByCode1.setCheckResult("未审核");
//            treeByCode1.setScore(0);
//            allQualityCheckTableRecord.add(treeByCode1);
//        }
//        for (QualityCheckTableRecordDto quality : allQualityCheckTableRecord) {
//            System.out.println(quality);
//        }
//
//        Integer integer = qualityCheckTableRecordDao.batchInsertTree(allQualityCheckTableRecord);
//        System.out.println(integer);

    }

    @org.junit.Test
    public void test1(){
        Integer QualityCheckID =67;
        List<String> checkedListCodeById = qualityCheckTableRecordDao.findCheckedListCodeById(QualityCheckID);
        System.out.println(checkedListCodeById);

    }


    //导入excel采用！！！！！
//    @org.junit.Test
//    public void test1() throws Exception {
//        //从本地将excel存入db
//        uploadService.uploadCheckList("F:\\java后台安装工具\\TomCat\\webapps\\CheckList\\cc9120ee-5ca1-44bb-bc09-dcb2f0c0de32.xls");
//    }

    @org.junit.Test
    public void test2() throws Exception {
//        String code=sortCodeUtil.getMaxCodeString("00010001");
        List<String> list = new ArrayList<>();
        list.add("0001");
        list.add("0003");
        list.add("0004");
        list.add("0002");
        System.out.println(sortCodeUtil.getMaxCode(list));
    }

    @org.junit.Test
    public void test3() throws Exception {
        List<String> list = checkListDao.querryAllCheckListCode();
        for (String t : list) {
            System.out.println(t);
        }
    }

    //---------读取审核要素原表excel算法，自动生成记录，自动生成编码，自动分级，自动统计分数，节点数，自动分解问题描述，分别写入数据库-----
    //---对excel有格式要求，运行直接写入数据库，仅限算法作者使用。
    @org.junit.Test
    public void readComplexExcel() throws Exception {
        Workbook workbook;
        String filePath = "E:\\qshe\\zxQHSE管理体系要素一体化、标准化(1).xlsx";
        FileInputStream fis = null;
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int endloc = sheet.getPhysicalNumberOfRows() - 1;//从0开始
        Map<String, Object> T1result = getFTNNode(sheet, 2, endloc, "", "",0);
        Map<Integer, int[]> T1RangeMap = (Map<Integer, int[]>) T1result.get("RangeMap");
        Map<Integer, String> T1CodeMap = (Map<Integer, String>) T1result.get("CodeMap");
        List<QSHEMSElementInDto> beanList1 = (List<QSHEMSElementInDto>) T1result.get("elementList");
        Map<Integer, String> NumberMap1 = (Map<Integer, String>) T1result.get("NumberMap");
        Map<String, String> problemDescriptionMap = new HashMap<>();

        for (Map.Entry<Integer, int[]> entry1 : T1RangeMap.entrySet())//第二层
        {
            //System.out.println(entry1.getKey()+"---"+entry1.getValue()[0]+"   "+entry1.getValue()[1]);
            Map<String, Object> T2result = getTNNode(sheet, entry1.getValue()[0], entry1.getValue()[1], T1CodeMap.get(entry1.getKey()),NumberMap1.get(entry1.getKey()), 1);
            Map<Integer, int[]> T2RangeMap = (Map<Integer, int[]>) T2result.get("RangeMap");
            Map<Integer, String> T2CodeMap = (Map<Integer, String>) T2result.get("CodeMap");
            List<QSHEMSElementInDto> beanList2 = (List<QSHEMSElementInDto>) T2result.get("elementList");
            Map<Integer, String> NumberMap2 = (Map<Integer, String>) T2result.get("NumberMap");
            beanList1.addAll(beanList2);

            for(Map.Entry<Integer,int[]> entry2:T2RangeMap.entrySet())//第三层
            {
                //System.out.println(entry2.getKey()+"---"+entry2.getValue()[0]+"   "+entry2.getValue()[1]);
                Map<String, Object> T3result=getTNNode(sheet,entry2.getValue()[0],entry2.getValue()[1],T2CodeMap.get(entry2.getKey()),NumberMap2.get(entry2.getKey()),2);
                Map<Integer, int[]> T3RangeMap=(Map<Integer, int[]>)T3result.get("RangeMap");
                Map<Integer, String> T3CodeMap=(Map<Integer, String>)T3result.get("CodeMap");
                List<QSHEMSElementInDto> beanList3=(List<QSHEMSElementInDto>)T3result.get("elementList");
                Map<Integer, String> NumberMap3 = (Map<Integer, String>) T3result.get("NumberMap");
                beanList1.addAll(beanList3);

                for(Map.Entry<Integer,int[]> entry3:T3RangeMap.entrySet())//第四层
                {
                    //System.out.println(entry3.getKey()+"---"+entry3.getValue()[0]+"   "+entry3.getValue()[1]);
                    Map<String, Object> T4result=getTNNode(sheet,entry3.getValue()[0],entry3.getValue()[1],T3CodeMap.get(entry3.getKey()),NumberMap3.get(entry3.getKey()),3);
                    Map<Integer, int[]> T4RangeMap=(Map<Integer, int[]>)T4result.get("RangeMap");
                    Map<Integer, String> T4CodeMap=(Map<Integer, String>)T4result.get("CodeMap");
                    List<QSHEMSElementInDto> beanList4=(List<QSHEMSElementInDto>)T4result.get("elementList");
                    Map<Integer, String> NumberMap4 = (Map<Integer, String>) T4result.get("NumberMap");
                    beanList1.addAll(beanList4);

                    for(Map.Entry<Integer,int[]> entry4:T4RangeMap.entrySet())//第五层
                    {
                        //System.out.println(entry4.getKey()+"---"+entry4.getValue()[0]+"   "+entry4.getValue()[1]);
                        Map<String, Object> T5result=getTNNode(sheet,entry4.getValue()[0],entry4.getValue()[1],T4CodeMap.get(entry4.getKey()),NumberMap4.get(entry4.getKey()),4);
                        Map<Integer, int[]> T5RangeMap=(Map<Integer, int[]>)T5result.get("RangeMap");
                        Map<Integer, String> T5CodeMap=(Map<Integer, String>)T5result.get("CodeMap");
                        List<QSHEMSElementInDto> beanList5=(List<QSHEMSElementInDto>)T5result.get("elementList");
                        Map<Integer, String> NumberMap5 = (Map<Integer, String>) T5result.get("NumberMap");
                        beanList1.addAll(beanList5);

                        for(Map.Entry<Integer,int[]> entry5:T5RangeMap.entrySet())//第六层
                        {
                            //System.out.println(entry5.getKey()+"---"+entry5.getValue()[0]+"   "+entry5.getValue()[1]);
                            Map<String, Object> T6result=getLastNode(sheet,entry5.getValue()[0],entry5.getValue()[1],T5CodeMap.get(entry5.getKey()),NumberMap5.get(entry5.getKey()),5);
                            List<QSHEMSElementInDto> beanList6=(List<QSHEMSElementInDto>)T6result.get("elementList");
                            Map<String, String> problemDescriptionMap1=(Map<String, String>)T6result.get("problemDescriptionMap");
                            beanList1.addAll(beanList6);
                            problemDescriptionMap.putAll(problemDescriptionMap1);
                        }
                    }
                }
            }
        }

        for(QSHEMSElementInDto ele:beanList1)
            System.out.println(ele);
        List<QhseElementsOutDto> TreeList=getQhseElementTree(beanList1);

        for(QhseElementsOutDto qshele:TreeList) {
            getScore(qshele);
            getCount(qshele);
        }
        List<QhseElementsOutDto> result=new ArrayList<>();
         for (QhseElementsOutDto ele1:TreeList)
            {
                //System.out.println(ele1.getName() + "--" + ele1.getCode());
                result.add(ele1);
                //qHSEManageSysElementsDao.addExcelQHSEElemenForInerPople(ele1);
                for (QhseElementsOutDto ele2 : ele1.getChildNode()) {
                    //System.out.println(ele2.getName() + "--" + ele2.getCode());
                    result.add(ele2);
                    //qHSEManageSysElementsDao.addExcelQHSEElemenForInerPople(ele2);
                    for (QhseElementsOutDto ele3 : ele2.getChildNode()) {
                        //System.out.println(ele3.getName() + "--" + ele3.getCode());
                        result.add(ele3);
                        //qHSEManageSysElementsDao.addExcelQHSEElemenForInerPople(ele3);
                        for (QhseElementsOutDto ele4 : ele3.getChildNode()) {
                            //System.out.println(ele4.getName() + "--" + ele4.getCode());
                            result.add(ele4);
                             //qHSEManageSysElementsDao.addExcelQHSEElemenForInerPople(ele4);
                            for (QhseElementsOutDto ele5 : ele4.getChildNode()) {
                               // System.out.println(ele5.getName() + "--" + ele5.getCode());
                                result.add(ele5);
                                 //qHSEManageSysElementsDao.addExcelQHSEElemenForInerPople(ele5);
                                for (QhseElementsOutDto ele6 : ele5.getChildNode()) {
                                   // System.out.println(ele6.getName() + "--" + ele6.getCode());
                                    result.add(ele6);
                                    //qHSEManageSysElementsDao.addExcelQHSEElemenForInerPople(ele6);
                                }//6
                            }//5
                        }//4
                    }//3
                }//2
            }
        for(QhseElementsOutDto e:result)
        {
            System.out.println(e.getName() + "--" + e.getCode());
        }
        System.out.println("等待：");
        //qHSEManageSysElementsDao.batchInsertRecord(result);
        //insertProblemDescription(problemDescriptionMap);

        workbook.close();
        System.out.println("----关闭close");
        fis.close();
        System.out.println("----关闭fis" + "成功了，666");
    }

    public Map<String, Object> getFTNNode(Sheet sheet, int start, int end, String parentCode,String parentNumber,int rowNUmber) throws Exception {
        DataFormatter dataFormat = new DataFormatter();
        Row row;
        String value;
        int[] a = new int[2];
        Pattern pattern = Pattern.compile("[0-9]*");
        Map<Integer, String> T1CodeMap = new TreeMap<>();
        Map<Integer, int[]> T1RangeMap = new TreeMap<>();
        Map<Integer, String> NumberMap = new TreeMap<>();

        List<QSHEMSElementInDto> beanList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        HashMap<String, String> QSHEMSElementValueMap = new HashMap<>();
        row = sheet.getRow(start);
        value=dataFormat.formatCellValue(row.getCell(rowNUmber));
        if (!(pattern.matcher(value.charAt(0) + "").matches()))
            start+=1;
        row = sheet.getRow(start);
        value=dataFormat.formatCellValue(row.getCell(rowNUmber));
        if (!(pattern.matcher(value.charAt(0) + "").matches()))
            start+=1;
        row = sheet.getRow(start);
        String f1Code = parentCode + "001";
        int number=1;
        String name=parentNumber+number;
        QSHEMSElementValueMap.put("code", f1Code);
        QSHEMSElementValueMap.put("name",name+" "+dataFormat.formatCellValue(row.getCell(rowNUmber)).split("\\.",2)[1]);
        QSHEMSElementValueMap.put("status", "启用");
        QSHEMSElementInDto qSHEMSElement = new QSHEMSElementInDto();
        BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
        //对象放进进容器
        beanList.add(qSHEMSElement);
        int id = 1;
        a[0] = start;
        if(start>=end) {
            a[1]=end;
            T1RangeMap.put(id, a);
        }
        NumberMap.put(id,name);
        T1CodeMap.put(id, f1Code);
        for (int i = start + 1; i <= end; i++) {
            row = sheet.getRow(i);
            value = dataFormat.formatCellValue(row.getCell(rowNUmber));
            if (value == null || "".equals(value) || " ".equals(value)) {
                if (i == end) {
                    a[1] = i;
                    T1RangeMap.put(id, a);
                }
                continue;
            }
            if (pattern.matcher(value.charAt(0) + "").matches()) {
                //生成code
                number++;
                name=parentNumber+number;
                f1Code = sortCodeUtil.getMaxCodeString(f1Code);
                //封装对象
                QSHEMSElementValueMap.put("code", f1Code);
                QSHEMSElementValueMap.put("name", name+" "+value.split("\\.",2)[1]);
                QSHEMSElementValueMap.put("status", "启用");
                qSHEMSElement = new QSHEMSElementInDto();
                BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
                beanList.add(qSHEMSElement);
                //子节点范围定位
                a[1] = i - 1;
                T1RangeMap.put(id, a);
                a = new int[2];
                a[0] = i;
                //存储code
                id++;
                T1CodeMap.put(id, f1Code);
                NumberMap.put(id,name);

            }
            if (i == end) {
                a[1] = i;
                T1RangeMap.put(id, a);
            }
        }
        result.put("CodeMap",T1CodeMap);
        result.put("RangeMap",T1RangeMap);
        result.put("elementList",beanList);
        result.put("NumberMap",NumberMap);
        return result;
    }

    public Map<String, Object> getTNNode(Sheet sheet, int start, int end, String parentCode,String parentNumber,int rowNUmber) throws Exception {
        DataFormatter dataFormat = new DataFormatter();
        Row row;
        String value;
        int[] a = new int[2];
        Pattern pattern = Pattern.compile("[0-9]*");
        Map<Integer, String> T1CodeMap = new TreeMap<>();
        Map<Integer, int[]> T1RangeMap = new TreeMap<>();
        Map<Integer, String> NumberMap = new TreeMap<>();

        List<QSHEMSElementInDto> beanList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        HashMap<String, String> QSHEMSElementValueMap = new HashMap<>();
        row = sheet.getRow(start);
        value=dataFormat.formatCellValue(row.getCell(rowNUmber));
        if (!(pattern.matcher(value.charAt(0) + "").matches()))
            start+=1;
        row = sheet.getRow(start);
        value=dataFormat.formatCellValue(row.getCell(rowNUmber));
        if (!(pattern.matcher(value.charAt(0) + "").matches()))
            start+=1;
        row = sheet.getRow(start);
        String f1Code = parentCode + "001";
        int number=1;
        String name=parentNumber+"."+number;
        QSHEMSElementValueMap.put("code", f1Code);
        QSHEMSElementValueMap.put("name",name+" "+dataFormat.formatCellValue(row.getCell(rowNUmber)).split("\\.",2)[1]);
        QSHEMSElementValueMap.put("status", "启用");
        QSHEMSElementInDto qSHEMSElement = new QSHEMSElementInDto();
        BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
        //对象放进进容器
        beanList.add(qSHEMSElement);
        int id = 1;
        a[0] = start;
        if(start>=end) {
            a[1]=end;
            T1RangeMap.put(id, a);
        }
        NumberMap.put(id,name);
        T1CodeMap.put(id, f1Code);
        for (int i = start + 1; i <= end; i++) {
            row = sheet.getRow(i);
            value = dataFormat.formatCellValue(row.getCell(rowNUmber));
            if (value == null || "".equals(value) || " ".equals(value)) {
                if (i == end) {
                    a[1] = i;
                    T1RangeMap.put(id, a);
                }
                continue;
            }
            if (pattern.matcher(value.charAt(0) + "").matches()) {
                //生成code
                number++;
                name=parentNumber+"."+number;
                f1Code = sortCodeUtil.getMaxCodeString(f1Code);
                //封装对象
                QSHEMSElementValueMap.put("code", f1Code);
                QSHEMSElementValueMap.put("name", name+" "+value.split("\\.",2)[1]);
                QSHEMSElementValueMap.put("status", "启用");
                qSHEMSElement = new QSHEMSElementInDto();
                BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
                beanList.add(qSHEMSElement);
                //子节点范围定位
                a[1] = i - 1;
                T1RangeMap.put(id, a);
                a = new int[2];
                a[0] = i;
                //存储code
                id++;
                T1CodeMap.put(id, f1Code);
                NumberMap.put(id,name);
            }
            if (i == end) {
                a[1] = i;
                T1RangeMap.put(id, a);
            }
        }
        result.put("CodeMap",T1CodeMap);
        result.put("RangeMap",T1RangeMap);
        result.put("elementList",beanList);
        result.put("NumberMap",NumberMap);
        return result;
    }

    public Map<String, Object> getLastNode(Sheet sheet, int start, int end, String parentCode,String parentNumber,  int rowNUmber) throws Exception {
        DataFormatter dataFormat = new DataFormatter();
        Row row;
        String value;
        Map<String, String> problemDescriptionMap = new HashMap<>();
        Pattern pattern = Pattern.compile("[0-9]*");
        List<QSHEMSElementInDto> beanList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        String f1Code = parentCode + "001";
        int number=1;
        String name=parentNumber+"."+number;
        row = sheet.getRow(start);
        HashMap<String, String> QSHEMSElementValueMap = new HashMap<>();
        QSHEMSElementValueMap.put("code", f1Code);
        String valueTemp=dataFormat.formatCellValue(row.getCell(rowNUmber)).replace("\n"," ").split("\\d\\-\\d",2)[1];
        QSHEMSElementValueMap.put("name", name+" "+valueTemp);
        QSHEMSElementValueMap.put("auditMode", dataFormat.formatCellValue(row.getCell(rowNUmber+1)));
        QSHEMSElementValueMap.put("initialScore", dataFormat.formatCellValue(row.getCell(rowNUmber+2)));
        String temp=dataFormat.formatCellValue(row.getCell(rowNUmber+3)).replace("\n","");
        QSHEMSElementValueMap.put("formula", temp);
        QSHEMSElementValueMap.put("totalCount", "1");
        QSHEMSElementValueMap.put("status", "启用");
        QSHEMSElementInDto qSHEMSElement = new QSHEMSElementInDto();
        BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
        String problemTemp=dataFormat.formatCellValue(row.getCell(rowNUmber+4)).replace("\n","");
        if(problemTemp!=null||!("".equals(problemTemp)))
        {
            if(problemTemp.startsWith("1")) {//检查是不是以序号1开头
              problemDescriptionMap.put(f1Code, problemTemp);
            }
        }
        else
            throw new Exception(f1Code+"的可能问题序号不是1开始");
        //对象放进进容器
        beanList.add(qSHEMSElement);
        for (int i = start + 1; i <= end; i++) {
            row = sheet.getRow(i);
            value = dataFormat.formatCellValue(row.getCell(rowNUmber)).replace("\n","");
            if (value == null || "".equals(value) || " ".equals(value)) {
                continue;
            }
            if (pattern.matcher(value.charAt(0) + "").matches()) {
                //生成code
                f1Code = sortCodeUtil.getMaxCodeString(f1Code);
                number++;
                name=parentNumber+"."+number;
                //封装对象
                QSHEMSElementValueMap.put("code", f1Code);
                QSHEMSElementValueMap.put("name", name+" "+value.split("\\d\\-\\d",2)[1]);
                QSHEMSElementValueMap.put("status", "启用");
                QSHEMSElementValueMap.put("auditMode", dataFormat.formatCellValue(row.getCell(rowNUmber+1)));
                QSHEMSElementValueMap.put("initialScore", dataFormat.formatCellValue(row.getCell(rowNUmber+2)));
                String temp2=dataFormat.formatCellValue(row.getCell(rowNUmber+3)).replace("\n","");
                QSHEMSElementValueMap.put("formula", temp2);
                qSHEMSElement = new QSHEMSElementInDto();
                BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
                beanList.add(qSHEMSElement);
                String problemTemp2=dataFormat.formatCellValue(row.getCell(rowNUmber+4)).replace("\n","");
                if(problemTemp2!=null||!("".equals(problemTemp2)))
                {
                    if(problemTemp2.startsWith("1")) {//检查是不是以序号1开头
                        problemDescriptionMap.put(f1Code, problemTemp2);
                    }
                }
                else
                    throw new Exception(f1Code+"的可能问题序号不是1开始");
            }
        }
        result.put("elementList",beanList);
        result.put("problemDescriptionMap",problemDescriptionMap);
        return result;
    }
    public List<QhseElementsOutDto> returnQhseElementList(Map<String, QhseElementsOutDto> map, List<Integer> code) {
        /*
        思想：创建一个list,然后遍历map里的节点,如果是一级节点，就放进list；
        如果不是一级节点，就去找他的父节点，找到后就把当前节点放进父节里的孩子list中，最后逐渐形成一棵树；
         */
        List<QhseElementsOutDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QhseElementsOutDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))//是一级节点，就放进list；
                result.add(entry.getValue());
            else {//如果不是一级节点，就去找他的父节点
                QhseElementsOutDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)//父节孩子为空
                    continue;
                if (null == treeDto.getChildNode()) {//父节孩子为空，建一个list放入
                    List<QhseElementsOutDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else////父节孩子不为空，直接放入
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }

    public List<QhseElementsOutDto> getQhseElementTree(List<QSHEMSElementInDto> qhseElementsPojos) {
        /*
        思想，把list里的对象逐个遍历；QhseElementsPojo赋值给QhseElementsOutDto，
        并把节点<code,QhseElementsOutDto>,放进map集合，方便后续操作；同时设一个code list记录节点长度；
         */
        Map<String, QhseElementsOutDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QSHEMSElementInDto pojo : qhseElementsPojos) {
            QhseElementsOutDto qhseElementsOutDto = new QhseElementsOutDto();
            qhseElementsOutDto.setAuditMode(pojo.getAuditMode());
            qhseElementsOutDto.setCode(pojo.getCode());
            qhseElementsOutDto.setTotalCount(pojo.getTotalCount());
            qhseElementsOutDto.setFormula(pojo.getFormula());
            qhseElementsOutDto.setInitialScore(pojo.getInitialScore());
            qhseElementsOutDto.setName(pojo.getName());
            qhseElementsOutDto.setStatus(pojo.getStatus());
            map1.put(qhseElementsOutDto.getCode(), qhseElementsOutDto);
            //同一层节点长度一样
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnQhseElementList(map1, code);
    }
   public Integer getScore(QhseElementsOutDto hseElementsOutDto)//递归求分数；
    {
        int score=0;
        if(hseElementsOutDto.getInitialScore()==null){
            if(hseElementsOutDto.getChildNode()==null)
                hseElementsOutDto.setInitialScore(0);
            else{
                for(QhseElementsOutDto ele:hseElementsOutDto.getChildNode()){
                    score+=getScore(ele);
                }
                hseElementsOutDto.setInitialScore(score);
            }
        }
        return hseElementsOutDto.getInitialScore();
    }
   public Integer getCount(QhseElementsOutDto hseElementsOutDto)//递归求分数；
    {
        int count=0;
        if(hseElementsOutDto.getTotalCount()==null){
            if(hseElementsOutDto.getChildNode()==null)
                hseElementsOutDto.setTotalCount(0);
            else{
                for(QhseElementsOutDto ele:hseElementsOutDto.getChildNode()){
                    count+=getCount(ele);
                }
                hseElementsOutDto.setTotalCount(count);
            }
        }
        return hseElementsOutDto.getTotalCount();
    }

    @Transactional
    public void insertProblemDescription(Map<String, String> problemDescription) {
        /*
        思想：算法升级，根据递增序号1，2，3，4....打断，能有效解决：1.占总数的1.5% 2注安占专职人员的比例等于20% 3注安占专职人员的比例在20%以下等格式
        但弊端是，序号必须是递增的，1，2，2，3就会打断失败。
         */
        String code;
        String description;
        //为了提高效率，直接把数据表先清空，然后再插入。使用事务管理防止插入失败造成数据丢失。
        for (Map.Entry<String, String> entry : problemDescription.entrySet()) {
            code = entry.getKey();
            description = entry.getValue();
            System.out.println(code+"----"+description);
            String[] s = description.split("1", 2);
            for (int i = 2; s[1].contains(String.valueOf(i)); i++) {
                description = s[1];
                s = description.split(String.valueOf(i), 2);
                //得到的s[0]即为插入的问题描述；
                //有".",就去除，没有”."就直接写入
                if (qHSEManageSysElementsDao.addProblemDescription(code, (s[0].startsWith(".") ? s[0].substring(1) : s[0])) <= 0)
                    throw new WLHSException("新增失败");
            }//插入最后个问题描述，有".",就去除，没有”."就直接写入
            if (qHSEManageSysElementsDao.addProblemDescription(code, (s[1].startsWith(".") ? s[1].substring(1) : s[1])) <= 0)
                throw new WLHSException("新增失败");
        }
    }









    //---------读取质量原表excel算法，自动生成记录，自动生成编码，自动分级，自动统计分数，节点数，自动分解问题描述，分别写入数据库-----
    //---对excel有格式要求，运行直接写入数据库，仅限算法作者使用。
    @org.junit.Test
    public void readQualityComplexExcel() throws Exception {
        Workbook workbook;
        String filePath = "E:\\qshe\\质量量化审核检查表上传.xlsx";
        FileInputStream fis = null;
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        int endloc = sheet.getPhysicalNumberOfRows() - 1;//从0开始
        Map<String, Object> T1result = getQualityTNNode(sheet, 1, endloc, "", 0);
        Map<Integer, int[]> T1RangeMap = (Map<Integer, int[]>) T1result.get("RangeMap");
        Map<Integer, String> T1CodeMap = (Map<Integer, String>) T1result.get("CodeMap");
        List<QualityManagerSysElementInDto> beanList1 = (List<QualityManagerSysElementInDto>) T1result.get("elementList");
        List<QualityManagerSysEleReviewTermsDto> allTermsList=new ArrayList<>();
        for (Map.Entry<Integer, int[]> entry1 : T1RangeMap.entrySet())//第二层
        {
            //System.out.println(entry1.getKey()+"---"+entry1.getValue()[0]+"   "+entry1.getValue()[1]);
            Map<String, Object> T2result = getQualityTNNode(sheet, entry1.getValue()[0], entry1.getValue()[1], T1CodeMap.get(entry1.getKey()), 1);
            Map<Integer, int[]> T2RangeMap = (Map<Integer, int[]>) T2result.get("RangeMap");
            Map<Integer, String> T2CodeMap = (Map<Integer, String>) T2result.get("CodeMap");
            List<QualityManagerSysElementInDto> beanList2 = (List<QualityManagerSysElementInDto>) T2result.get("elementList");
            beanList1.addAll(beanList2);

            for (Map.Entry<Integer, int[]> entry2 : T2RangeMap.entrySet())//第三层
            {
                //System.out.println(entry2.getKey()+"---"+entry2.getValue()[0]+"   "+entry2.getValue()[1]);
                Map<String, Object> T3result = getQualityTNNode(sheet, entry2.getValue()[0], entry2.getValue()[1], T2CodeMap.get(entry2.getKey()), 2);
                Map<Integer, int[]> T3RangeMap = (Map<Integer, int[]>) T3result.get("RangeMap");
                Map<Integer, String> T3CodeMap = (Map<Integer, String>) T3result.get("CodeMap");
                List<QualityManagerSysElementInDto> beanList3 = (List<QualityManagerSysElementInDto>) T3result.get("elementList");
                beanList1.addAll(beanList3);
                for (Map.Entry<Integer, int[]> entry3 : T3RangeMap.entrySet())//第四层
                {
                    //System.out.println(entry3.getKey()+"---"+entry3.getValue()[0]+"   "+entry3.getValue()[1]);
                    Map<String, Object> T4result = getLastQualityTNNode(sheet, entry3.getValue()[0], entry3.getValue()[1], T3CodeMap.get(entry3.getKey()), 3);
                    Map<Integer, int[]> T4RangeMap = (Map<Integer, int[]>) T4result.get("RangeMap");
                    Map<Integer, String> T4CodeMap = (Map<Integer, String>) T4result.get("CodeMap");
                    for (Map.Entry<Integer, int[]> entry4 : T4RangeMap.entrySet())//第五层
                    {
                        //System.out.println(entry4.getKey()+"---"+entry4.getValue()[0]+"   "+entry4.getValue()[1]);
                        Map<String, Object> T5result = getFinalLastQualityTNNode(sheet, entry4.getValue()[0], entry4.getValue()[1], T4CodeMap.get(entry4.getKey()), 3);
                        List<QualityManagerSysEleReviewTermsDto> termsList=(List<QualityManagerSysEleReviewTermsDto>) T5result.get("termsList");
                        List<QualityManagerSysElementInDto> beanList5 = (List<QualityManagerSysElementInDto>) T5result.get("elementList");
                        beanList1.addAll(beanList5);
                        allTermsList.addAll(termsList);
                    }
                }
            }
        }

        List<QualityManagerSysElementOutDto> TreeList=getQualityTree(beanList1);


        for(QualityManagerSysElementOutDto qshele:TreeList) {
            getQScore(qshele);
            getQCount(qshele);
        }
        for(QualityManagerSysElementOutDto ele:TreeList)
        {
            System.out.println(ele);
        }
        /*for (QualityManagerSysElementOutDto ele1:TreeList)
        {
            System.out.println(ele1.getName() + "--" + ele1.getCode());
            qualityManagerSysElementDao.addExcelQHSEElemenForInerPople(ele1);
            for (QualityManagerSysElementOutDto ele2 : ele1.getChildNode()) {
                System.out.println(ele2.getName() + "--" + ele2.getCode());
                qualityManagerSysElementDao.addExcelQHSEElemenForInerPople(ele2);
                for (QualityManagerSysElementOutDto ele3 : ele2.getChildNode()) {
                    System.out.println(ele3.getName() + "--" + ele3.getCode());
                    qualityManagerSysElementDao.addExcelQHSEElemenForInerPople(ele3);
                    for (QualityManagerSysElementOutDto ele4 : ele3.getChildNode()) {
                        System.out.println(ele4.getName() + "--" + ele4.getCode());
                        qualityManagerSysElementDao.addExcelQHSEElemenForInerPople(ele4);
                    }//4
                }//3
            }//2
        }*/
       // qualityManagerSysElementDao.batchInsertRecord(allTermsList);
        workbook.close();
        System.out.println("----关闭close");
        fis.close();
        System.out.println("----关闭fis" + "成功了，666");
    }
    public Map<String, Object> getQualityTNNode(Sheet sheet, int start, int end, String parentCode, int rowNUmber) throws Exception {
        DataFormatter dataFormat = new DataFormatter();
        Row row;
        String value;
        int[] a = new int[2];
        Map<Integer, String> T1CodeMap = new TreeMap<>();
        Map<Integer, int[]> T1RangeMap = new TreeMap<>();
        List<QualityManagerSysElementInDto> beanList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        HashMap<String, String> QSHEMSElementValueMap = new HashMap<>();
        row = sheet.getRow(start);
        value=dataFormat.formatCellValue(row.getCell(rowNUmber));
        if (value == null || "".equals(value) || " ".equals(value))
            start+=1;
        row = sheet.getRow(start);
        String f1Code = parentCode + "001";
        QSHEMSElementValueMap.put("code", f1Code);
        QSHEMSElementValueMap.put("name", dataFormat.formatCellValue(row.getCell(rowNUmber)));
        QSHEMSElementValueMap.put("status", "启用");
        QualityManagerSysElementInDto qSHEMSElement = new QualityManagerSysElementInDto();
        BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
        //对象放进进容器
        beanList.add(qSHEMSElement);
        int id = 1;
        a[0] = start;
        if(start>=end) {
            a[1]=end;
            T1RangeMap.put(id, a);
        }
        T1CodeMap.put(id, f1Code);
        for (int i = start + 1; i <= end; i++) {
            row = sheet.getRow(i);
            value = dataFormat.formatCellValue(row.getCell(rowNUmber));
            if (value == null || "".equals(value) || " ".equals(value)) {
                if (i == end) {
                    a[1] = i;
                    T1RangeMap.put(id, a);
                }
                continue;
            }else{
                //生成code
                f1Code = sortCodeUtil.getMaxCodeString(f1Code);
                //封装对象
                QSHEMSElementValueMap.put("code", f1Code);
                QSHEMSElementValueMap.put("name", value);
                QSHEMSElementValueMap.put("status", "启用");
                qSHEMSElement = new QualityManagerSysElementInDto();
                BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
                beanList.add(qSHEMSElement);
                //子节点范围定位
                a[1] = i - 1;
                T1RangeMap.put(id, a);
                a = new int[2];
                a[0] = i;
                //存储code
                id++;
                T1CodeMap.put(id, f1Code);
            }
            if (i == end) {
                a[1] = i;
                T1RangeMap.put(id, a);
            }
        }
        result.put("CodeMap",T1CodeMap);
        result.put("RangeMap",T1RangeMap);
        result.put("elementList",beanList);
        return result;
    }

    public Map<String, Object> getLastQualityTNNode(Sheet sheet, int start, int end, String parentCode, int rowNUmber) throws Exception {
        DataFormatter dataFormat = new DataFormatter();
        Row row;
        String value;
        int[] a = new int[2];
        Map<Integer, String> T1CodeMap = new TreeMap<>();
        Map<Integer, int[]> T1RangeMap = new TreeMap<>();
        List<QualityManagerSysElementInDto> beanList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        HashMap<String, String> QSHEMSElementValueMap = new HashMap<>();
        row = sheet.getRow(start);
        value=dataFormat.formatCellValue(row.getCell(rowNUmber));
        if (value == null || "".equals(value) || " ".equals(value))
            start+=1;
        row = sheet.getRow(start);
        String f1Code = parentCode + "001";
        QSHEMSElementValueMap.put("code", f1Code);
        QSHEMSElementValueMap.put("name", dataFormat.formatCellValue(row.getCell(rowNUmber)));
        QSHEMSElementValueMap.put("status", "启用");
        QSHEMSElementValueMap.put("initialScore", "5");
        QSHEMSElementValueMap.put("totalCount", "1");
        QSHEMSElementValueMap.put("content", dataFormat.formatCellValue(row.getCell(rowNUmber+4)));
        QSHEMSElementValueMap.put("auditMode", dataFormat.formatCellValue(row.getCell(rowNUmber+6)));
        QSHEMSElementValueMap.put("formula", dataFormat.formatCellValue(row.getCell(rowNUmber+7)));
        QualityManagerSysElementInDto qSHEMSElement = new QualityManagerSysElementInDto();
        BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
        //对象放进进容器
        beanList.add(qSHEMSElement);
        int id = 1;
        a[0] = start;
        if(start>=end) {
            a[1]=end;
            T1RangeMap.put(id, a);
        }
        T1CodeMap.put(id, f1Code);
        for (int i = start + 1; i <= end; i++) {
            row = sheet.getRow(i);
            value = dataFormat.formatCellValue(row.getCell(rowNUmber));
            if (value == null || "".equals(value) || " ".equals(value)) {
                if (i == end) {
                    a[1] = i;
                    T1RangeMap.put(id, a);
                }
                continue;
            }else{
                //生成code
                f1Code = sortCodeUtil.getMaxCodeString(f1Code);
                //封装对象
                QSHEMSElementValueMap.put("code", f1Code);
                QSHEMSElementValueMap.put("name", value);
                QSHEMSElementValueMap.put("status", "启用");
                QSHEMSElementValueMap.put("initialScore", "5");
                QSHEMSElementValueMap.put("totalCount", "1");
                QSHEMSElementValueMap.put("content", dataFormat.formatCellValue(row.getCell(rowNUmber+4)));
                QSHEMSElementValueMap.put("auditMode", dataFormat.formatCellValue(row.getCell(rowNUmber+6)));
                QSHEMSElementValueMap.put("formula", dataFormat.formatCellValue(row.getCell(rowNUmber+7)));
                qSHEMSElement = new QualityManagerSysElementInDto();
                BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
                beanList.add(qSHEMSElement);
                //子节点范围定位
                a[1] = i - 1;
                T1RangeMap.put(id, a);
                a = new int[2];
                a[0] = i;
                //存储code
                id++;
                T1CodeMap.put(id, f1Code);
            }
            if (i == end) {
                a[1] = i;
                T1RangeMap.put(id, a);
            }
        }
        result.put("CodeMap",T1CodeMap);
        result.put("RangeMap",T1RangeMap);
        result.put("elementList",beanList);
        return result;
    }
    public Map<String, Object> getFinalLastQualityTNNode(Sheet sheet, int start, int end, String parentCode, int rowNUmber) throws Exception {
        DataFormatter dataFormat = new DataFormatter();
        Row row;
        String value;
        List<QualityManagerSysElementInDto> beanList = new ArrayList<>();
        List<QualityManagerSysEleReviewTermsDto> termsList=new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        HashMap<String, String> QSHEMSElementValueMap = new HashMap<>();
        row = sheet.getRow(start);
        value=dataFormat.formatCellValue(row.getCell(rowNUmber));
        QSHEMSElementValueMap.put("code", parentCode);
        QSHEMSElementValueMap.put("name",value);
        QSHEMSElementValueMap.put("status", "启用");
        QSHEMSElementValueMap.put("initialScore", "5");
        QSHEMSElementValueMap.put("totalCount", "1");
        QSHEMSElementValueMap.put("content", dataFormat.formatCellValue(row.getCell(rowNUmber+4)));
        QSHEMSElementValueMap.put("auditMode", dataFormat.formatCellValue(row.getCell(rowNUmber+6)));
        QSHEMSElementValueMap.put("formula", dataFormat.formatCellValue(row.getCell(rowNUmber+7)));
        String basis;
        String terms;
        String content;
        String scoreShow="";
        for (int i = start ; i <= end; i++){
            row = sheet.getRow(i);
            scoreShow+=dataFormat.formatCellValue(row.getCell(rowNUmber+5));
            basis= dataFormat.formatCellValue(row.getCell(rowNUmber+1));
            terms= dataFormat.formatCellValue(row.getCell(rowNUmber+2));
            content= dataFormat.formatCellValue(row.getCell(rowNUmber+3));
            if((basis==null||"".equals(basis))&&(terms==null||"".equals(terms))&&(content==null||"".equals(content))){
                continue;
            }else{
                QualityManagerSysEleReviewTermsDto ele=new QualityManagerSysEleReviewTermsDto();
                ele.setCode(parentCode);
                ele.setBasis(basis);
                ele.setTerms(terms);
                ele.setContent(content);
                termsList.add(ele);
            }
        }
        QSHEMSElementValueMap.put("scoreShows",scoreShow);
        QualityManagerSysElementInDto qSHEMSElement = new QualityManagerSysElementInDto();
        BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
        //对象放进进容器
        beanList.add(qSHEMSElement);
        result.put("elementList",beanList);
        result.put("termsList",termsList);
        return result;
    }

    public List<QualityManagerSysElementOutDto> returnQualityList(Map<String, QualityManagerSysElementOutDto> map, List<Integer> code) {
        /*
        思想：创建一个list,然后遍历map里的节点,如果是一级节点，就放进list；
        如果不是一级节点，就去找他的父节点，找到后就把当前节点放进父节里的孩子list中，最后逐渐形成一棵树；
         */
        List<QualityManagerSysElementOutDto> result = new ArrayList<>();
        Collections.sort(code);
        for (Map.Entry<String, QualityManagerSysElementOutDto> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.length() == code.get(0))//是一级节点，就放进list；
                result.add(entry.getValue());
            else {//如果不是一级节点，就去找他的父节点
                QualityManagerSysElementOutDto treeDto = map.get(key.substring(0, code.get(code.indexOf(key.length()) - 1)));
                if (null == treeDto)//父节孩子为空
                    continue;
                if (null == treeDto.getChildNode()) {//父节孩子为空，建一个list放入
                    List<QualityManagerSysElementOutDto> tmp = new ArrayList<>();
                    tmp.add(entry.getValue());
                    treeDto.setChildNode(tmp);
                } else////父节孩子不为空，直接放入
                    treeDto.getChildNode().add(entry.getValue());
            }
        }
        return result;
    }

    public List<QualityManagerSysElementOutDto> getQualityTree(List<QualityManagerSysElementInDto> qhseElementsPojos) {
        /*
        思想，把list里的对象逐个遍历；QhseElementsPojo赋值给QhseElementsOutDto，
        并把节点<code,QhseElementsOutDto>,放进map集合，方便后续操作；同时设一个code list记录节点长度；
         */
        Map<String, QualityManagerSysElementOutDto> map1 = new TreeMap<>();
        List<Integer> code = new ArrayList<>();
        for (QualityManagerSysElementInDto pojo : qhseElementsPojos) {
            QualityManagerSysElementOutDto qhseElementsOutDto = new QualityManagerSysElementOutDto();
            qhseElementsOutDto.setAuditMode(pojo.getAuditMode());
            qhseElementsOutDto.setCode(pojo.getCode());
            qhseElementsOutDto.setTotalCount(pojo.getTotalCount());
            qhseElementsOutDto.setFormula(pojo.getFormula());
            qhseElementsOutDto.setInitialScore(pojo.getInitialScore());
            qhseElementsOutDto.setName(pojo.getName());
            qhseElementsOutDto.setStatus(pojo.getStatus());
            qhseElementsOutDto.setScoreShows(pojo.getScoreShows());
            qhseElementsOutDto.setContent(pojo.getContent());
            map1.put(qhseElementsOutDto.getCode(), qhseElementsOutDto);
            //同一层节点长度一样
            if (code.indexOf(pojo.getCode().length()) == -1)
                code.add(pojo.getCode().length());
        }
        return returnQualityList(map1, code);
    }
    public Integer getQScore(QualityManagerSysElementOutDto hseElementsOutDto)//递归求分数；
    {
        int score=0;
        if(hseElementsOutDto.getInitialScore()==null){
            if(hseElementsOutDto.getChildNode()==null)
                hseElementsOutDto.setInitialScore(0);
            else{
                for(QualityManagerSysElementOutDto ele:hseElementsOutDto.getChildNode()){
                    score+=getQScore(ele);
                }
                hseElementsOutDto.setInitialScore(score);
            }
        }
        return hseElementsOutDto.getInitialScore();
    }
    public Integer getQCount(QualityManagerSysElementOutDto hseElementsOutDto)//递归求分数；
    {
        int count=0;
        if(hseElementsOutDto.getTotalCount()==null){
            if(hseElementsOutDto.getChildNode()==null)
                hseElementsOutDto.setTotalCount(0);
            else{
                for(QualityManagerSysElementOutDto ele:hseElementsOutDto.getChildNode()){
                    count+=getQCount(ele);
                }
                hseElementsOutDto.setTotalCount(count);
            }
        }
        return hseElementsOutDto.getTotalCount();
    }

    //---对excel有格式要求，运行直接写入数据库，仅限算法作者使用。
    @org.junit.Test
    public void readExcel() throws Exception {
        Workbook workbook;
        String filePath = "E:\\qshe\\检查表模板文件4.xlsx";
        String[] attributeArray={"表",//第一级的属性
                "表/*a5f46saad*/检查项",//第二级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容",//第三级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容/*a5f46saad*/检查要素1",//第四级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容/*a5f46saad*/检查要素1/*a5f46saad*/检查要素2",//第五级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容/*a5f46saad*/检查要素1/*a5f46saad*/检查要素2/*a5f46saad*/检查要素3",//第六级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容/*a5f46saad*/检查要素1/*a5f46saad*/检查要素2/*a5f46saad*/检查要素3/*a5f46saad*/检查要素4", //第七级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容/*a5f46saad*/检查要素1/*a5f46saad*/检查要素2/*a5f46saad*/检查要素3/*a5f46saad*/检查要素4/*a5f46saad*/检查要素5", //第八级的属性
        };
        FileInputStream fis = null;
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        String maxCode=qualityCheckListDao.findMaxCode();
        maxCode=sortCodeUtil.getMaxCodeString(maxCode);
        DataFormatter dataFormat=new DataFormatter();
        // 得到表名
        Row titleRow=sheet.getRow(0);
        String tableName=dataFormat.formatCellValue(titleRow.getCell(0));
        //得到类别、方式、依据
        int endloc = sheet.getPhysicalNumberOfRows() - 1;//从0开始
        Row row = sheet.getRow(1);
        String checkCategory=dataFormat.formatCellValue(row.getCell(0));
        String checkBasis=dataFormat.formatCellValue(row.getCell(1));
        String checkMethod=dataFormat.formatCellValue(row.getCell(2));
        QualityCheckListDto qualityCheckListDto=new QualityCheckListDto();
        qualityCheckListDto.setCheckListCode(maxCode);
        qualityCheckListDto.setCheckListName(tableName);
        qualityCheckListDto.setCheckCategory(checkCategory);
        qualityCheckListDto.setCheckBasis(checkBasis);
        qualityCheckListDto.setCheckMethod(checkMethod);
        qualityCheckListDto.setAttribute(attributeArray[0]);
        qualityCheckListDto.setIsChildNode("false");
        qualityCheckListDto.setStatus("启用");
        row = sheet.getRow(3);
        Integer level=Integer.valueOf(dataFormat.formatCellValue(row.getCell(0)));
        String isChild="false";
        int subLevel=1;//第二层
        if(subLevel+1==level){
            isChild="true";
        }
        Map<String, Object> T1result = getFNode(sheet, 4, endloc, maxCode, subLevel-1,attributeArray[subLevel],tableName,isChild);
        Map<Integer, int[]> T1RangeMap = (Map<Integer, int[]>) T1result.get("RangeMap");
        Map<Integer, String> T1CodeMap = (Map<Integer, String>) T1result.get("CodeMap");
        List<QualityCheckListDto> beanList1 = (List<QualityCheckListDto>) T1result.get("elementList");
        Map<Integer, String> T1NameMap =(Map<Integer, String>) T1result.get("T1NameMap");
        subLevel++;//第三层
        if(subLevel<level){
            String isChild2="false";
            if(subLevel+1==level){
                isChild2="true";
            }
            int subLevelTemp1=subLevel;

            subLevel++;
            for (Map.Entry<Integer, int[]> entry1 : T1RangeMap.entrySet()){
                System.out.println("第三层"+entry1.getKey()+"---"+entry1.getValue()[0]+"   "+entry1.getValue()[1]);
                Map<String, Object> T2result = getFNode(sheet, entry1.getValue()[0], entry1.getValue()[1], T1CodeMap.get(entry1.getKey()), subLevelTemp1-1,attributeArray[subLevelTemp1],
                T1NameMap.get(entry1.getKey()),isChild2 );
                List<QualityCheckListDto> beanList2 = (List<QualityCheckListDto>) T2result.get("elementList");
                Map<Integer, int[]> T2RangeMap = (Map<Integer, int[]>) T2result.get("RangeMap");
                Map<Integer, String> T2CodeMap = (Map<Integer, String>) T2result.get("CodeMap");
                Map<Integer, String> T2NameMap =(Map<Integer, String>) T2result.get("T1NameMap");
                beanList1.addAll(beanList2);
                if(subLevel<level){//第四层
                    String isChild3="false";
                    if(subLevel+1==level){
                        isChild3="true";
                    }
                    int subLevelTemp2=subLevel;
                    subLevel++;
                    for (Map.Entry<Integer, int[]> entry2 : T2RangeMap.entrySet()){
//                        System.out.println("第四层"+entry2.getKey()+"---"+entry2.getValue()[0]+"   "+entry2.getValue()[1]);
                        Map<String, Object> T3result = getFNode(sheet, entry2.getValue()[0], entry2.getValue()[1], T2CodeMap.get(entry2.getKey()), subLevelTemp2-1,attributeArray[subLevelTemp2],
                                T2NameMap.get(entry2.getKey()),isChild3);
                        List<QualityCheckListDto> beanList3 = (List<QualityCheckListDto>) T3result.get("elementList");
                        Map<Integer, int[]> T3RangeMap = (Map<Integer, int[]>) T3result.get("RangeMap");
                        Map<Integer, String> T3CodeMap = (Map<Integer, String>) T3result.get("CodeMap");
                        Map<Integer, String> T3NameMap =(Map<Integer, String>) T3result.get("T1NameMap");
                        beanList1.addAll(beanList3);
                        if(subLevel<level){//第五层
                            String isChild4="false";
                            if(subLevel+1==level){
                                isChild4="true";
                            }
                            int subLevelTemp3=subLevel;
                            subLevel++;
                            for (Map.Entry<Integer, int[]> entry3 : T3RangeMap.entrySet()){
                                //System.out.println(entry1.getKey()+"---"+entry3.getValue()[0]+"   "+entry1.getValue()[1]);
                                Map<String, Object> T4result = getFNode(sheet, entry3.getValue()[0], entry3.getValue()[1], T3CodeMap.get(entry3.getKey()), subLevelTemp3-1,attributeArray[subLevelTemp3],
                                        T3NameMap.get(entry3.getKey()),isChild4);
                                List<QualityCheckListDto> beanList4 = (List<QualityCheckListDto>) T4result.get("elementList");
                                Map<Integer, int[]> T4RangeMap = (Map<Integer, int[]>) T4result.get("RangeMap");
                                Map<Integer, String> T4CodeMap = (Map<Integer, String>) T4result.get("CodeMap");
                                Map<Integer, String> T4NameMap =(Map<Integer, String>) T4result.get("T1NameMap");
                                beanList1.addAll(beanList4);
                                if(subLevel<level){//第六层
                                    String isChild5="false";
                                    if(subLevel+1==level){
                                        isChild5="true";
                                    }
                                    int subLevelTemp4=subLevel;
                                    subLevel++;
                                    for (Map.Entry<Integer, int[]> entry4 : T4RangeMap.entrySet()){
                                        //System.out.println(entry1.getKey()+"---"+entry3.getValue()[0]+"   "+entry1.getValue()[1]);
                                        Map<String, Object> T5result = getFNode(sheet, entry4.getValue()[0], entry4.getValue()[1], T4CodeMap.get(entry4.getKey()), subLevelTemp4-1,attributeArray[subLevelTemp4],
                                                T4NameMap.get(entry4.getKey()),isChild5);
                                        List<QualityCheckListDto> beanList5 = (List<QualityCheckListDto>) T5result.get("elementList");
                                        Map<Integer, int[]> T5RangeMap = (Map<Integer, int[]>) T5result.get("RangeMap");
                                        Map<Integer, String> T5CodeMap = (Map<Integer, String>) T5result.get("CodeMap");
                                        Map<Integer, String> T5NameMap =(Map<Integer, String>) T5result.get("T1NameMap");
                                        beanList1.addAll(beanList5);
                                        if(subLevel<level){//第七层
                                            String isChild6="false";
                                            if(subLevel+1==level){
                                                isChild6="true";
                                            }
                                            int subLevelTemp5=subLevel;
                                            subLevel++;
                                            for (Map.Entry<Integer, int[]> entry5 : T5RangeMap.entrySet()){
                                                //System.out.println(entry1.getKey()+"---"+entry3.getValue()[0]+"   "+entry1.getValue()[1]);
                                                Map<String, Object> T6result = getFNode(sheet, entry5.getValue()[0], entry5.getValue()[1], T5CodeMap.get(entry5.getKey()), subLevelTemp5-1,attributeArray[subLevelTemp5],
                                                        T5NameMap.get(entry5.getKey()),isChild6);
                                                List<QualityCheckListDto> beanList6 = (List<QualityCheckListDto>) T6result.get("elementList");
                                                Map<Integer, int[]> T6RangeMap = (Map<Integer, int[]>) T6result.get("RangeMap");
                                                Map<Integer, String> T6CodeMap = (Map<Integer, String>) T6result.get("CodeMap");
                                                Map<Integer, String> T6NameMap =(Map<Integer, String>) T6result.get("T1NameMap");
                                                beanList1.addAll(beanList6);
                                            }
                                            subLevel--;}
                                       }
                                    subLevel--;}
                                }
                            subLevel--;}
                        }
                subLevel--;}
            }
        }
        beanList1.add(qualityCheckListDto);
        for(QualityCheckListDto x:beanList1){
            System.out.println(x);
        }
    }

    public Map<String, Object> getFNode(Sheet sheet, int start, int end, String parentCode, int rowNUmber,String att,String parentName,String isChild) throws Exception {
        DataFormatter dataFormat = new DataFormatter();
        Row row;
        String value;
        int[] a = new int[2];
        Map<Integer, String> T1CodeMap = new TreeMap<>();
        Map<Integer, int[]> T1RangeMap = new TreeMap<>();
        Map<Integer, String> T1NameMap = new TreeMap<>();
        List<QualityCheckListDto> beanList = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        HashMap<String, String> QSHEMSElementValueMap = new HashMap<>();
        row = sheet.getRow(start);

        String f1Code = parentCode + "0001";
        QSHEMSElementValueMap.put("checkListCode", f1Code);
        String name=dataFormat.formatCellValue(row.getCell(rowNUmber));
        QSHEMSElementValueMap.put("checkListName",name);
        if(rowNUmber==1){
            System.out.println(name);
        }
        QSHEMSElementValueMap.put("status", "启用");
        QSHEMSElementValueMap.put("attribute", att);
        QSHEMSElementValueMap.put("parentName", parentName);
        QSHEMSElementValueMap.put("isChildNode", isChild);
        QualityCheckListDto qualityCheckListDto = new QualityCheckListDto();
        BeanUtils.populate(qualityCheckListDto, QSHEMSElementValueMap);
        //对象放进进容器
        beanList.add(qualityCheckListDto);
        int id = 1;
        a[0] = start;
        if(start>=end) {
            a[1]=end;
            T1RangeMap.put(id, a);
        }
        T1CodeMap.put(id, f1Code);
        T1NameMap.put(id,parentName+"/*a5f46saad*/"+name);
        for (int i = start + 1; i <= end; i++) {
            row = sheet.getRow(i);
            value = dataFormat.formatCellValue(row.getCell(rowNUmber));
            if (value == null || "".equals(value) || " ".equals(value)) {
                if (i == end) {
                    a[1] = i;
                    T1RangeMap.put(id, a);
                }
                continue;
            }else{
                //生成code
                f1Code = sortCodeUtil.getMaxCodeString(f1Code);
                //封装对象
                name=dataFormat.formatCellValue(row.getCell(rowNUmber));
                QSHEMSElementValueMap.put("checkListCode", f1Code);
                QSHEMSElementValueMap.put("checkListName",name);
                QSHEMSElementValueMap.put("status", "启用");
                QSHEMSElementValueMap.put("attribute", att);
                QSHEMSElementValueMap.put("parentName", parentName);
                QSHEMSElementValueMap.put("isChildNode", isChild);
                QualityCheckListDto qualityCheckListDto1 = new QualityCheckListDto();
                BeanUtils.populate(qualityCheckListDto1, QSHEMSElementValueMap);
                beanList.add(qualityCheckListDto1);
                //子节点范围定位
                a[1] = i - 1;
                T1RangeMap.put(id, a);
                a = new int[2];
                a[0] = i;
                //存储code
                id++;
                T1CodeMap.put(id, f1Code);
                T1NameMap.put(id,parentName+"/*a5f46saad*/"+name);

            }
            if (i == end) {
                a[1] = i;
                T1RangeMap.put(id, a);
            }
        }
        result.put("CodeMap",T1CodeMap);
        result.put("RangeMap",T1RangeMap);
        result.put("elementList",beanList);
        result.put("T1NameMap",T1NameMap);
        return result;
    }
}

