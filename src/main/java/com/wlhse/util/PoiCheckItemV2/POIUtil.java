package com.wlhse.util.PoiCheckItemV2;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.CopyOnWriteArrayList;


public class POIUtil<T> {
//    private Workbook wb;

    private String[] property;

    private Class<T> c;

    private int startRow;

    private int startColumn;

    //是否需要hash
    private boolean isHash = false;

    //存放hash集合
    private HashSet<Integer> hashSet;

    private CopyOnWriteArrayList beanList;

    private Poi poi;

    private String path;

    public CopyOnWriteArrayList getBeanList() {
        return beanList;
    }

    public POIUtil(String[] property, Class<T> c, int startRow, int startColumn, Poi poi, String path) {
        this.property = property;
        this.c = c;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.beanList = new CopyOnWriteArrayList();
        this.path = path;
        this.poi = poi;
    }

    public String getPath() {
        return path;
    }

    /**
     * @Description:
     * @Param: [property 类的属性字符串数组, c class对下你给, filePath excel地址,
     * startRow 开始行, startColumn 开始列, hashSet 不需要必填，hash校验]
     * @return:
     * @Author: hg
     * @Date: 2019/6/4
     */
    public POIUtil(String[] property, Class<T> c, int startRow, int startColumn, Poi poi, String path, HashSet<Integer> hashSet) {
        this.property = property;
        this.c = c;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.hashSet = hashSet;
        this.isHash = true;
        this.beanList = new CopyOnWriteArrayList();
        this.path = path;
        this.poi = poi;
    }

    /**
     * @Description:
     * @Param: [startSheet sheet开始, endSheet sheet结束]
     * @return: void
     * @Author: hg
     * @Date: 2019/6/4
     */
    public void getBeanList(int startSheet, int endSheet) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        HashMap<String, String> valueMap = new HashMap<>();
        Workbook wb = poi.getWorkbook(path);
        for (int sheetNum = startSheet; sheetNum < endSheet; sheetNum++) {
            Sheet sheet = wb.getSheetAt(sheetNum);
            for (int rowNum = startRow; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                for (int i = 0, columnNum = startColumn; i < property.length; i++, columnNum++) {
                    if (null == row.getCell(columnNum))
                        continue;
                    valueMap.put(property[i], poi.getCellValue(row.getCell(columnNum), row.getCell(columnNum).getCellType()));
                }
                //这里使用clone效率更高
                T t = c.newInstance();
                BeanUtils.populate(t, valueMap);
                beanList.add(t);
            }
        }
    }
}
