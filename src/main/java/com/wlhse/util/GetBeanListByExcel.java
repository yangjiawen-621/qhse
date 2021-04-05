package com.wlhse.util;

import com.wlhse.dto.*;
import com.wlhse.exception.WLHSException;
import com.wlhse.util.state_code.CodeDict;
import com.wlhse.util.state_code.NR;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Component
public class GetBeanListByExcel {

    @Resource
    private PoiEmploy poiEmploy;


    @Resource
    private PoiReport poiReport;

    public <T> String getBeanList(Workbook wb, String[] cellKey, Class<T> c) throws Exception {
        //创建实体类对象容器
        List<T> beanList = new ArrayList<>();
        HashMap<String, String> valueMap = new HashMap<>();
        Sheet sheet = wb.getSheetAt(0);
        DataFormatter dataFormat=new DataFormatter();
        //遍历excel文件
        String value;
        for (int j = 2; j < sheet.getPhysicalNumberOfRows(); j++) {
            Row row = sheet.getRow(j);
            for (int i = 0; i < cellKey.length; i++) {
                value=dataFormat.formatCellValue(row.getCell(i));
                System.out.println(value);
                valueMap.put(cellKey[i], value);
                }
            T t = c.newInstance();
            //使用BeanUtils将封装的属性注入对象
            BeanUtils.populate(t, valueMap);
            //将对象添加至容器
            beanList.add(t);
        }
        if (beanList.size() > 0) {
//            beanList.remove(0);
            String duplicCode=PoiMSElement.isDuplicelements5((List<EmployeeExcelDto>) beanList);
            if (duplicCode== null) {
                poiEmploy.poiPoiEmploy((List<EmployeeExcelDto>) beanList);
                return NR.getPoiProblemReturn(CodeDict.SUCCESS, 0);
            }
            else{
                throw new WLHSException("有重复编码"+duplicCode);//提示有重复账号
            }
        } else {
            return NR.getPoiProblemReturn(CodeDict.POI_PROBLEM_UNKNOWN_ERROR, 0);
        }
    }

    public <T> String getReportsBeanList(Workbook wb, String[] cellKey, Class<T> c) throws Exception {
        //创建实体类对象容器
        List<ExcelUploadReportDto> beanList = new ArrayList<>();
        HashMap<String, String> reportValueMap = new HashMap<>();
        HashMap<String, String> sampleValueMap = new HashMap<>();
        Sheet sheet = wb.getSheetAt(0);
        int flag=0,idx=-1;//idx:report下标，flag：report中sample的下标
        String reportCode="";
        DataFormatter dataFormat=new DataFormatter();
        //遍历excel文件
        for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
            Row row = sheet.getRow(j);
            int count =0;
            //判断是否为报告行
            Cell celltest=row.getCell(2);
            String str;
            if(celltest==null){
                str=null;
            }else{
                str=dataFormat.formatCellValue(celltest).toString();
            }
            if(str==null||reportCode.equals(str)){//非报告行
                flag=0;
            }else{//报告行
                reportCode=str;
                flag=1;
            }
            for (int i = 0; i < cellKey.length; i++) {
                Cell cell = row.getCell(i);
                String a=dataFormat.formatCellValue(cell).toString();
                if (cell == null || a.length() == 0) {
                    count = count + 1;
                }

            }
            if(count == cellKey.length){//空行
                continue;
            }else {
                if(flag==1){
                    for (int i = 0; i < 30; i++) {//封装report
                        Cell cell = row.getCell(i);
                        String cellValue;
                        cellValue = dataFormat.formatCellValue(cell).toString();
                        reportValueMap.put(cellKey[i], cellValue);
                    }
                    ExcelUploadReportDto report = (ExcelUploadReportDto) c.newInstance();
                    //使用BeanUtils将封装的属性注入对象
                    BeanUtils.populate(report, reportValueMap);
                    //将对象添加至容器
                    beanList.add(report);
                    idx++;
                }
                for(int i=30;i<cellKey.length;i++){//封装sample
                    Cell cell = row.getCell(i);
                    String cellValue;
                    cellValue = dataFormat.formatCellValue(cell).toString();
                    sampleValueMap.put(cellKey[i], cellValue);
                }
                ExcelUploadSampleDto sample=new ExcelUploadSampleDto();
                //使用BeanUtils将封装的属性注入对象
                BeanUtils.populate(sample, sampleValueMap);
                sample.setReportCode(beanList.get(idx).getReportCode());
                beanList.get(idx).getSampleDtoList().add(sample);
            }
        }
        //判断报告中是否存在重复报告编号
        if(PoiReport.isDuplicReportCode(beanList)){
            return NR.getPoiReportsReturn(CodeDict.POI_ReportCodeDuplic_ERROR, "EXCEL中存在重复报告编码！");
        }

        if (beanList.size() > 0) {
            String duplicCode=poiReport.poiReports(beanList);
            if(duplicCode.equals("")){
                return NR.getPoiReportsReturn(CodeDict.SUCCESS, duplicCode);
            }else{
                duplicCode=duplicCode.substring(0,duplicCode.length()-1);
                return NR.getPoiReportsReturn(CodeDict.POI_ReportCodeDuplic_ERROR, duplicCode);
            }
        } else {
            return NR.getPoiReportsReturn(CodeDict.POI_Report_Insert_ERROR,"不能插入空表");
        }
    }

}
