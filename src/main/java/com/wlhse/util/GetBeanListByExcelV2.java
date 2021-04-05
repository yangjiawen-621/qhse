//package com.wlhse.util;
//
//import com.wlhse.entity.ProblemDescriptionPojo;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@Component
//public class GetBeanListByExcelV2<T> {
//    public List<T> getBeanList(Workbook wb, String[] cellKey, Class<T> c)
//    {
//        List<T> list=new ArrayList<>();
//        ProblemDescriptionPojo pojo=new ProblemDescriptionPojo();
//
//        //遍历sheet
//        for(int rowNum = 0; rowNum<=wb.getNumberOfSheets();rowNum++) {
//            Sheet sheet = wb.getSheetAt(rowNum);
//            //遍历行
//            for (int j = 2; j < sheet.getPhysicalNumberOfRows(); j++) {
//                Row row = sheet.getRow(j);
//                //遍历列
//                for (int i = 1; i < cellKey.length; i++)
//                {
//                   row.getCell(i).getStringCellValue();
//                }
//            }
//        }
//
//
//        return list;
//    }
//}
