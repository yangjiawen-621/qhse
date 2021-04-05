package com.wlhse.util.PoiCheckItemV2;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicReference;

public class PoiImplV1 implements Poi {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getCellValue(Cell rowCell, int rowCellType) {
        String value = "";
        if (null == rowCell)
            return value;
        switch (rowCellType) {
            case Cell.CELL_TYPE_STRING:
                value = rowCell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                String dataFormat = rowCell.getCellStyle().getDataFormatString();
                AtomicReference<Boolean> isDate = new AtomicReference<>(false);
                if (DateUtil.isCellDateFormatted(rowCell)) {
                    value = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getJavaDate(rowCell.getNumericCellValue()));
                } else if (DateUtil.isCellInternalDateFormatted(rowCell)) {
                    value = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getJavaDate(rowCell.getNumericCellValue()));
                } else if (isDate.get()) {
                    value = new SimpleDateFormat("yyyy-MM-dd").format(rowCell.getDateCellValue());
                } else if (dataFormat == null) {
                    value = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getJavaDate(rowCell.getNumericCellValue()));
                } else {
                    if (dataFormat != null) {
                        value = String.valueOf(rowCell.getNumericCellValue());
                    } else {
                        if (rowCell.getCellStyle().getDataFormatString().contains("$")) {
                            value = "$" + rowCell.getNumericCellValue();
                        } else if (rowCell.getCellStyle().getDataFormatString().contains("￥")) {
                            value = "￥" + rowCell.getNumericCellValue();
                        } else if (rowCell.getCellStyle().getDataFormatString().contains("¥")) {
                            value = "¥" + rowCell.getNumericCellValue();
                        } else if (rowCell.getCellStyle().getDataFormatString().contains("€")) {
                            value = "€" + String.valueOf(rowCell.getNumericCellValue());
                        } else {
                            value = String.valueOf(rowCell.getNumericCellValue());
                        }
                    }
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(rowCell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                value = ErrorEval.getText(rowCell.getErrorCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = rowCell.getCellFormula();
                break;
        }
        return value;
    }

    @Override
    public Workbook getWorkbook(String filePath) {
        Workbook wb = null;
        try {
            if (null != filePath) {
                FileInputStream fis = new FileInputStream(filePath);
                if (filePath.endsWith(".xls")) {
                    wb = new HSSFWorkbook(fis);
                } else if (filePath.endsWith(".xlsx")) {
                    wb = new XSSFWorkbook(fis);
                }
                return wb;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
