package com.wlhse.service.impl;

import com.wlhse.dao.*;
import com.wlhse.dto.*;
import com.wlhse.dto.inDto.FilePropagationFileInfo;
import com.wlhse.dto.inDto.QSHEMSElementInDto;
import com.wlhse.dto.inDto.QualityManagerSysElementInDto;
import com.wlhse.exception.WLHSException;
import com.wlhse.service.UploadService;
import com.wlhse.util.*;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UploadServiceImpl implements UploadService {



    @Resource
    private PoiUtil poiUtil;

    @Resource
    private GetBeanListByExcel getBeanListByExcel;

    @Resource
    private CheckListDao checkListDao;

    @Resource
    private QHSEManageSysElementsDao qHSEManageSysElementsDao;

    @Resource
    private QualityCheckListDao qualityCheckListDao;

    @Resource
    private QualityManagerSysElementDao qualityManagerSysElementDao;

    @Resource
    private SortCodeUtil sortCodeUtil;

    private final static String staus = "启用";

    @Resource
    FileDao fileDao;

    @Override
    @Async
    public String uploadEmployees(String path) throws Exception {
        String[] strArray = {"companyCode","companyName","employeeCode","name", "sex","birthday","jobTime","empGroup",
                "position","station","education", "email","tel","category","uName","roleCode"};
        Workbook workbook = poiUtil.createWorkbook(path);
        return getBeanListByExcel.getBeanList(workbook, strArray, EmployeeExcelDto.class);
    }

    @Override
    public String uploadReports(String path) throws Exception {
        String[] strArray = {"companyCode","companyName","reportCode", "reportType","reportPlanDate","reportCheckPersonID"
                ,"reportCheckPersonName","auditorIDs","auditorNames","auditorDate","approverIDs","approverNames","approverDate","fileDate","senderID"
                ,"senderName","sendDate","reportCount","seal1","seal2","seal3","seal4","seal5","seal6","note","sealPersonID"
                ,"sealPersonName","authID","authName","sealDate","sampleName","sampleNO","sampleModel","sampleCode"
                ,"entrustCompany","productCompany","customerCompany","arriveDate","checkDate","checkAddress","checkProject"
                ,"checkGuist","checkResult","sampleCheckPersonName"};
        Workbook workbook = poiUtil.createWorkbook(path);
        return getBeanListByExcel.getReportsBeanList(workbook, strArray, ExcelUploadReportDto.class);
    }

    /**
     * 该方法用于数据库新增checkList数据
     * @param path 传入文件的路径
     * @return 返回操作成功，失败，重复编码，excel为空等消息
     * @throws Exception
     */
    @Override
    public R uploadCheckList(String path) throws Exception {
        //String[] strArray = {"checkListCode","checkListName","attribute","parentName","isChildNode","status", "checkContent"};
        Workbook workbook = poiUtil.createWorkbook(path);
        Sheet sheet = workbook.getSheetAt(0);//获取指定表可以改成自动获取
        //获取EXCEL中CheckList的值
        List<CheckListDto> beanList = new ArrayList<>();
        DataFormatter dataFormat=new DataFormatter();
       // System.out.println(sheet.getPhysicalNumberOfRows());
        for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {//从第二行读
            HashMap<String, String> checkListValueMap = new HashMap<>();
            Row row = sheet.getRow(j);//按行取
            checkListValueMap.put("checkListCode",dataFormat.formatCellValue(row.getCell(0)));
            //System.out.println(dataFormat.formatCellValue(row.getCell(0)));
            checkListValueMap.put("checkListName",dataFormat.formatCellValue(row.getCell(1)));
            checkListValueMap.put("attribute",dataFormat.formatCellValue(row.getCell(2)));
            checkListValueMap.put("parentName",dataFormat.formatCellValue(row.getCell(3)));
            checkListValueMap.put("isChildNode",dataFormat.formatCellValue(row.getCell(4)));
            checkListValueMap.put("status",dataFormat.formatCellValue(row.getCell(5)));
            //使用BeanUtils将封装的属性注入对象
            CheckListDto checkListDto=new CheckListDto();
            BeanUtils.populate(checkListDto, checkListValueMap);
            beanList.add(checkListDto);
        }
        workbook.close();
        if (beanList.size() > 0) {
            String duplicateCodeCode=PoiMSElement.isDuplicelements2(beanList);//判断是否有重复编码
            if (duplicateCodeCode== null) {
                //优化，一次把所有code查询出来放进list，在list中查找code
                List<String> list=checkListDao.querryAllCheckListCode();
                for(CheckListDto ele:beanList) {
                    if(list.contains(ele.getCheckListCode())) {//-------编码存在则更新
                        if (checkListDao.updateCheckListByCode(ele) <= 0)
                            throw new WLHSException("更新失败");
                    }
                    else{//--------不存在则插入
                        if (checkListDao.addCheckList(ele) <= 0)
                            throw new WLHSException("新增失败");
                    }
                }
                return R.ok("文件上传成功");//导入数据库成功
            }
            else {
                throw new WLHSException("有重复编码"+duplicateCodeCode);//提示有重复编码
            }
        }
        else {
            throw new WLHSException("excel文件为空");//list为空，读取excel失败；
        }
    }
    /**
     * 该方法用于管理要素审核excel录入数据库
     * @param path 传入文件的路径
     * @return 返回操作成功，失败，重复编码，excel为空等消息
     * @throws Exception
     */

    @Override
    public R uploadQHSEManageSysElements(String path) throws Exception {
        /*
        思想：创建excel工具类对象，使用该对象对表格进行读写，读写的顺序为一行一行从左往右，每读一行，即一条记录，一个对象；
        然后把存放对象属性值的键值对MAP封装为对象，放进list中；
        然后对数据进行校验，包括是否为空表，读取失败，有重复编码；都没问题再写入，根据code有则更新，无则添加；
         */
        //创建一个字段数组，用于放入对象的map，一定要对应excel里的列顺序
        String[] fieldArray = {
                "code",//编码
                "name",//名字
                //"content",//内容
                "auditMode",//审核方式
                "initialScore",//分数
                "formula", //计算公式
                "problemDescription",//问题描述，插入另一个数据库
                "totalCount",//第五级叶子总数
                "status"//状态
        };
        Workbook workbook = poiUtil.createWorkbook(path);
        //得到第一张表
        Sheet sheet = workbook.getSheetAt(0);
        // 得到标题行
        // Row titleRow=sheet.getRow(0);
        //创建实体类对象容器,放入审核要素对象
        List<QSHEMSElementInDto> beanList = new ArrayList<>();
        //创建创建MAP,放入问题描述对象
        Map<String, String> problemDescriptionMap = new HashMap<>();
        //获取EXCEL中的值
        DataFormatter dataFormat=new DataFormatter();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {//类似二维数组的读取，外层为行，内层为列；从第2行开始读；
            HashMap<String, String> QSHEMSElementValueMap = new HashMap<>();
            Row row = sheet.getRow(i);
            String rcode=new String();
            for(int j=0;j<fieldArray.length;j++)//j可以j<titleRow.getLastCellNum()，但害怕excel错误添加,有多余的字段
            {
                String value=dataFormat.formatCellValue(row.getCell(j));
                if(j==0){//找到该条记录的code
                    rcode=value;
                    if(rcode==null||"".equals(value)||" ".equals(value))//检查code是否为空
                        throw new WLHSException("存在code为空或有空行");
                }
                if("problemDescription".equals(fieldArray[j])) {//问题描述，先放进map，等所有格式检查无误后，再统一插入；
                    if(value==null||"".equals(value)||" ".equals(value)||"  ".equals(value))//如果不是叶子节点，就为空，直接跳过
                        continue;
                    else {
                        if(value.startsWith("1")){//检查是不是以序号1开头
                            problemDescriptionMap.put(rcode,value);
                            continue;
                        }
                        else throw new WLHSException(rcode+"的可能问题序号不是1开始");
                    }
                }
                else {//当不为问题描述时，直接将属性键值对放入map
                    QSHEMSElementValueMap.put(fieldArray[j], value);//读取第i行第j列；
                }
            }
            /*QSHEMSElementValueMap.put("code", dataFormat.formatCellValue(row.getCell(0)));*/
            //使用BeanUtils将封装的属性注入对象
            QSHEMSElementInDto qSHEMSElement=new QSHEMSElementInDto();
            BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
            //对象放进进容器
            beanList.add(qSHEMSElement);
        }
        workbook.close();
        if (beanList.size() > 0)//开始检查装入容器是否成功
        {
            String duplicCode=PoiMSElement.isDuplicelements(beanList);//判断是否有重复编码
            if (duplicCode== null)//至此，所有格式检查完毕，开始导入
            { //先导入审核要素表
             //优化，一次把所有code查询出来放进list，在list中查找code
                List<String> list=qHSEManageSysElementsDao.queryAllCode();
                for(QSHEMSElementInDto ele:beanList) {
                    if(list.contains(ele.getCode())) {//-------编码存在则更新
                        if (qHSEManageSysElementsDao.updateExcelElement(ele) <= 0)
                            throw new WLHSException("更新失败");
                    }
                    else {//--------不存在则插入
                        if (qHSEManageSysElementsDao.addExcelQHSEElement(ele) <= 0)
                            throw new WLHSException("新增失败");
                    }
                }
                //再导入问题描述表
                insertProblemDescription(problemDescriptionMap);
                return R.ok("文件上传成功");//导入数据库成功
            }
            else throw new WLHSException("有重复编码："+duplicCode);//提示有重复编码
        }
        else throw new WLHSException("excel文件为空");//list为空，读取excel失败；
    }

    @Override
    public boolean insertFilePropagationFileRecord(FilePropagationFileInfo filePropagationFileInfo) {
        int i = fileDao.insertFilePropagationFile(filePropagationFileInfo);
        if (i!=0) return true;
        return false;
    }

    @Override
    public boolean insertAttachInfoDto(QualityCheckTableRecordAttachInfoDto qualityCheckTableRecordAttachInfoDto) {
        int i = fileDao.InsertQualityAttachInfo(qualityCheckTableRecordAttachInfoDto);
        if (i!=0) return true;
        return false;
    }

    @Transactional
    @Override
    public R uploadQualityCheck(String path) throws Exception {
        Workbook workbook = poiUtil.createWorkbook(path);
        Sheet sheet = workbook.getSheetAt(0);//获取指定表可以改成自动获取
        //获取EXCEL中CheckList的值
        List<QualityCheckListDto> beanList = new ArrayList<>();
        DataFormatter dataFormat=new DataFormatter();
        // System.out.println(sheet.getPhysicalNumberOfRows());
        for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {//从第二行读
            HashMap<String, String> checkListValueMap = new HashMap<>();
            Row row = sheet.getRow(j);//按行取
            checkListValueMap.put("checkListCode",dataFormat.formatCellValue(row.getCell(0)));
            checkListValueMap.put("checkListName",dataFormat.formatCellValue(row.getCell(1)));
            checkListValueMap.put("attribute",dataFormat.formatCellValue(row.getCell(2)));
            checkListValueMap.put("parentName",dataFormat.formatCellValue(row.getCell(3)));
            checkListValueMap.put("isChildNode",dataFormat.formatCellValue(row.getCell(4)));
            checkListValueMap.put("status",dataFormat.formatCellValue(row.getCell(5)));
            checkListValueMap.put("checkCategory",dataFormat.formatCellValue(row.getCell(6)));
            checkListValueMap.put("checkBasis",dataFormat.formatCellValue(row.getCell(7)));
            checkListValueMap.put("checkMethod",dataFormat.formatCellValue(row.getCell(8)));
            //使用BeanUtils将封装的属性注入对象
            QualityCheckListDto qualityCheckListDto=new QualityCheckListDto();
            BeanUtils.populate(qualityCheckListDto, checkListValueMap);
            //System.out.println(qualityCheckListDto);
            beanList.add(qualityCheckListDto);

        }
        workbook.close();
        if (beanList.size() > 0) {
            String duplicateCode=PoiMSElement.isDuplicelements3(beanList);//判断是否有重复编码
            if (duplicateCode== null) {
                //采用先删除库，再批量插入，增加速度。
                if(qualityCheckListDao.clearTable()<0)
                    throw new WLHSException("删除失败");
                if((qualityCheckListDao.batchInsertRecord(beanList))<0)
                    throw new WLHSException("插入失败");
                return R.ok("文件上传成功");//导入数据库成功
            }
            else {
                throw new WLHSException("有重复编码"+duplicateCode);//提示有重复编码
            }
        }
        else {
            throw new WLHSException("excel文件为空");//list为空，读取excel失败；
        }
    }

    @Transactional
    @Override
    public R uploadQualityManageSysElements(String path) throws Exception {
         /*
        思想：创建excel工具类对象，使用该对象对表格进行读写，读写的顺序为一行一行从左往右，每读一行，即一条记录，一个对象；
        然后把存放对象属性值的键值对MAP封装为对象，放进list中；
        然后对数据进行校验，包括是否为空表，读取失败，有重复编码；都没问题再写入，根据code有则更新，无则添加；
         */
        //创建一个字段数组，用于放入对象的map，一定要对应excel里的列顺序
        String[] fieldArray = {
                "code",//编码
                "name",//名字
                "content",//需提供审核证据
                "initialScore",//分数
                "ScoreShows",//评分说明
                "auditMode",//审核方式
                "formula", //部门
                "totalCount",//第五级叶子总数
                "status",//状态
                "reviewTerms",//审核条款
                "problemDescription",//问题描述，插入另一个数据库
        };
        Workbook workbook = poiUtil.createWorkbook(path);
        //得到第一张表
        Sheet sheet = workbook.getSheetAt(0);
        // 得到标题行
        // Row titleRow=sheet.getRow(0);
        //创建实体类对象容器,放入审核要素对象
        List<QualityManagerSysElementInDto> beanList = new ArrayList<>();
        //创建创建MAP,放入问题描述对象
        Map<String, String> problemDescriptionMap = new HashMap<>();
        Map<String, String> reviewTermsMap = new HashMap<>();
        //获取EXCEL中的值
        DataFormatter dataFormat=new DataFormatter();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {//类似二维数组的读取，外层为行，内层为列；从第2行开始读；
            HashMap<String, String> QSHEMSElementValueMap = new HashMap<>();
            Row row = sheet.getRow(i);
            String rcode=new String();
            for(int j=0;j<fieldArray.length;j++)//j可以j<titleRow.getLastCellNum()，但害怕excel错误添加,有多余的字段
            {
                String value=dataFormat.formatCellValue(row.getCell(j));
                if(j==0){//找到该条记录的code
                    rcode=value;
                    if(rcode==null||"".equals(value)||" ".equals(value))//检查code是否为空
                        throw new WLHSException("存在code为空或有空行");
                }
                if("problemDescription".equals(fieldArray[j])) {//问题描述，先放进map，等所有格式检查无误后，再统一插入；
                    if(value==null||"".equals(value)||" ".equals(value)||"  ".equals(value))//如果不是叶子节点，就为空，直接跳过
                        continue;
                    else {
                        if(value.startsWith("1")){//检查是不是以序号1开头
                            problemDescriptionMap.put(rcode,value);
                            continue;
                        }
                        else throw new WLHSException(rcode+"的可能问题序号不是1开始");
                    }
                }
                else if ("reviewTerms".equals(fieldArray[j])){
                    if(value==null||"".equals(value)||" ".equals(value)||"  ".equals(value))//如果不是叶子节点，就为空，直接跳过
                        continue;
                    else reviewTermsMap.put(rcode,value);
                }else {//当不为问题描述时，直接将属性键值对放入map
                    QSHEMSElementValueMap.put(fieldArray[j], value);//读取第i行第j列；
                }
            }
            /*QSHEMSElementValueMap.put("code", dataFormat.formatCellValue(row.getCell(0)));*/
            //使用BeanUtils将封装的属性注入对象
            QualityManagerSysElementInDto qSHEMSElement=new QualityManagerSysElementInDto();
            BeanUtils.populate(qSHEMSElement, QSHEMSElementValueMap);
            //对象放进进容器
            beanList.add(qSHEMSElement);
        }
        workbook.close();
        if (beanList.size() > 0)//开始检查装入容器是否成功
        {
            String duplicCode=PoiMSElement.isDuplicelements4(beanList);//判断是否有重复编码
            if (duplicCode== null)//至此，所有格式检查完毕，开始导入
            { //先导入审核要素表
                //优化，一次把所有code查询出来放进list，在list中查找code
                List<String> list=qualityManagerSysElementDao.queryAllCode();
                for(QualityManagerSysElementInDto ele:beanList) {
                    if(list.contains(ele.getCode())) {//-------编码存在则更新
                        if (qualityManagerSysElementDao.updateExcelElement(ele) <= 0)
                            throw new WLHSException("更新失败");
                    }
                    else {//--------不存在则插入
                        if (qualityManagerSysElementDao.addExcelQHSEElement(ele) <= 0)
                            throw new WLHSException("新增失败");
                    }
                }
                //再导入问题描述表
                insertQualityDescription(problemDescriptionMap);
                insertReviewTerms(reviewTermsMap);
                return R.ok("文件上传成功");//导入数据库成功
            }
            else throw new WLHSException("有重复编码："+duplicCode);//提示有重复编码
        }
        else throw new WLHSException("excel文件为空");//list为空，读取excel失败；
    }

    @Override
    public R addQualityExcel(String path) throws Exception {
        String maxCode=qualityCheckListDao.findMaxCode();
        maxCode=sortCodeUtil.getMaxCodeString(maxCode);
        Workbook workbook = poiUtil.createWorkbook(path);
        DataFormatter dataFormat=new DataFormatter();
        String[] attributeArray={"表",//第一级的属性
                "表/*a5f46saad*/检查项",//第二级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容",//第三级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容/*a5f46saad*/检查要素1",//第四级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容/*a5f46saad*/检查要素1/*a5f46saad*/检查要素2",//第五级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容/*a5f46saad*/检查要素1/*a5f46saad*/检查要素2/*a5f46saad*/检查要素3",//第六级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容/*a5f46saad*/检查要素1/*a5f46saad*/检查要素2/*a5f46saad*/检查要素3/*a5f46saad*/检查要素4", //第七级的属性
                "表/*a5f46saad*/检查项/*a5f46saad*/检查内容/*a5f46saad*/检查要素1/*a5f46saad*/检查要素2/*a5f46saad*/检查要素3/*a5f46saad*/检查要素4/*a5f46saad*/检查要素5", //第八级的属性
        };
        //得到第一张表
        Sheet sheet = workbook.getSheetAt(0);
        // 得到表名
        Row titleRow=sheet.getRow(0);
        String tableName=dataFormat.formatCellValue(titleRow.getCell(0));
        if(qualityCheckListDao.findTableName(tableName)!=null){
            throw new WLHSException("已存在此表或表名重复");
        }
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
        if(beanList1.size()<=0){
            throw new WLHSException("读取excel失败");
        }
        if((qualityCheckListDao.batchInsertRecord(beanList1))<0)
            throw new WLHSException("插入失败");
        return R.ok("文件上传成功");//导入数据库成功
    }

    /**
     * 该方法用于问题描述的插入
     * @param problemDescription Map<String, String>型，存放的是问题描述的code,problemDescription键值对；
     */
    @Transactional
    public void insertProblemDescription(Map<String, String> problemDescription)  {
        /*
        思想：算法升级，根据递增序号1，2，3，4....打断，能有效解决：1.占总数的1.5% 2注安占专职人员的比例等于20% 3注安占专职人员的比例在20%以下等格式
        但弊端是，序号必须是递增的，1，2，2，3就会打断失败。
         */
        String code;
        String description;
        //为了提高效率，直接把数据表先清空，然后再插入。使用事务管理防止插入失败造成数据丢失。
        qHSEManageSysElementsDao.deleteAllDescription();
        for(Map.Entry<String,String> entry: problemDescription.entrySet())
        {
            code=entry.getKey();
            description=entry.getValue();
            String[] s=description.split("1",2);
            for(int i=2;s[1].contains(String.valueOf(i));i++) {
                description=s[1];
                s=description.split(String.valueOf(i),2);
                //得到的s[0]即为插入的问题描述；
                //有".",就去除，没有”."就直接写入
                if(qHSEManageSysElementsDao.addProblemDescription(code,(s[0].startsWith(".")? s[0].substring(1):s[0]))<=0)
                    throw new WLHSException("新增失败");
            }//插入最后个问题描述，有".",就去除，没有”."就直接写入
            if(qHSEManageSysElementsDao.addProblemDescription(code, (s[1].startsWith(".")? s[1].substring(1):s[1]))<=0)
                throw new WLHSException("新增失败");
        }

    }

    @Transactional
    public void insertQualityDescription(Map<String, String> problemDescription)  {
        /*
        思想：算法升级，根据递增序号1，2，3，4....打断，能有效解决：1.占总数的1.5% 2注安占专职人员的比例等于20% 3注安占专职人员的比例在20%以下等格式
        但弊端是，序号必须是递增的，1，2，2，3就会打断失败。
         */
        String code;
        String description;
        //为了提高效率，直接把数据表先清空，然后再插入。使用事务管理防止插入失败造成数据丢失。
        qualityManagerSysElementDao.deleteAllDescription();
        for(Map.Entry<String,String> entry: problemDescription.entrySet())
        {
            code=entry.getKey();
            description=entry.getValue();
            String[] s=description.split("1",2);
            for(int i=2;s[1].contains(String.valueOf(i));i++) {
                description=s[1];
                s=description.split(String.valueOf(i),2);
                //得到的s[0]即为插入的问题描述；
                //有".",就去除，没有”."就直接写入
                if(qualityManagerSysElementDao.addProblemDescription(code,(s[0].startsWith(".")? s[0].substring(1):s[0]))<=0)
                    throw new WLHSException("新增失败");
            }//插入最后个问题描述，有".",就去除，没有”."就直接写入
            if(qualityManagerSysElementDao.addProblemDescription(code, (s[1].startsWith(".")? s[1].substring(1):s[1]))<=0)
                throw new WLHSException("新增失败");
        }
    }
    public void insertReviewTerms(Map<String, String> ReviewTerms) {
        String code;
        qualityManagerSysElementDao.deleteReviewTerms();
        List<QualityManagerSysEleReviewTermsDto> list=new ArrayList<>();
        for(Map.Entry<String,String> entry: ReviewTerms.entrySet())
        {
            code=entry.getKey();
            String Terms;
            String[] b;
            Terms=entry.getValue();
            String[] a= Terms.split("/\\*\\*/");
            for(String temp:a){
                b=temp.split("%");
                QualityManagerSysEleReviewTermsDto ele=new QualityManagerSysEleReviewTermsDto();
                ele.setBasis(b[0]);
                ele.setTerms(b[1]);
                ele.setContent(b[2]);
                ele.setCode(code);
                list.add(ele);
            }
        }
        if(qualityManagerSysElementDao.batchInsertRecord(list)<=0) {
            throw new WLHSException("新增失败");
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
