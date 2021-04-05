package com.wlhse.util.PoiCheckItemV2;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

public interface Poi {
    String getCellValue(Cell rowCell, int rowCellType);

    Workbook getWorkbook(String filePath);
}
