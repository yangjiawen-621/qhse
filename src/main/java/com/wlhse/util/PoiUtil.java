package com.wlhse.util;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

@Component
public class PoiUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private Workbook wb;

    public Workbook createWorkbook(String filePath) {
        FileInputStream fis=null;
        try {
            if (null != filePath) {
                fis = new FileInputStream(filePath);
                if (filePath.endsWith(".xls")) {
                    //2003版本的excel，用.xls结尾
                    wb = new HSSFWorkbook(fis);//得到工作簿
                } else if (filePath.endsWith(".xlsx")) {
                    //2007版本的excel，用.xlsx结尾
                    wb = new XSSFWorkbook(fis);//得到工作簿
                }
                return wb;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            if (fis!= null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}