package com.wlhse.util.PoiCheckItemV2;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 导入树形excel
 * @Author: hg
 * @CreateDate: 2019/6/4 14:37
 */
public abstract class PoiTreeUtil<T> {

    protected Poi poi;

    protected String path;

    protected Class<T> c;

    //存储code
    protected List<String> codes;

    protected List<T> result;

    //数据库数据
    protected HashMap<String, String> value;

    public PoiTreeUtil(Poi poi, String path, Class<T> c) {
        this.poi = poi;
        this.path = path;
        this.c = c;
        codes = new ArrayList<>();
        value = new HashMap<>();
        result = new ArrayList<>();
    }

    //可以通过增加位数添加其它
    public List<String> getTreeRoot(int row, int column) {
        Workbook wb = poi.getWorkbook(path);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            if (null != wb.getSheetAt(i).getRow(row).getCell(column)) {
                Cell cell = wb.getSheetAt(i).getRow(row).getCell(column);
                String value = poi.getCellValue(cell, cell.getCellType());
                list.add(value);
            }
        }
        return list;
    }

    /**
     * @Description:
     * @Param: [strings 一级节点, list 数据库pojo, step第一级]
     * @return: java.util.Map<java.lang.String, java.lang.String>
     * @Author: hg
     * @Date: 2019/6/5
     */
//    public Map<String, String> treeDict(List<String> strings, List<CheckItemPojo> list, int step) {
//        Map<String, String> map = new HashMap<>();
//        for (int i = 0; i < list.size(); i++) {
//            map.put(list.get(i).getCheckItemName(), list.get(i).getCheckItemCode());
//            codes.add(list.get(i).getCheckItemCode());
//        }
//        String code = "";
//        if (list.size() == 0) {
//            for (int i = 0; i < step; i++) {
//                code += "0";
//            }
//            setDefault(code);
//            checkDefault(code, map, step);
//        } else
//            code = CodeUtil.maxCode(codes, step);
//        for (String str : strings) {
//            if (!map.containsKey(str)) {
//                code = CodeUtil.codeInc(code);
//                map.put(str, code);
//                //添加code
//                codes.add(code);
//                //添加list
//                result.add(getT(code, str));
//                //检查其它项
//                checkDefault(code, map, step);
//            }
//        }
//        return map;
//    }

    public void poiTree(int startRow, int startColumn, int endColumn, Map<String, String> map, List<String> roots, int step) {
        Workbook wb = poi.getWorkbook(path);
        String[] cache = new String[endColumn - startColumn + 1];
        for (int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++) {
            Sheet sheet = wb.getSheetAt(sheetNum);
            String root = roots.get(sheetNum);
            //一级节点
            cache[0] = map.get(root);
            for (int rowNum = startRow; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                for (int i = startColumn; i < endColumn; i++) {
                    if (null == row.getCell(i))
                        continue;
                    //获取当前单元格值
                    String value = poi.getCellValue(row.getCell(i), row.getCell(i).getCellType());
                    //当前单元格无值 || 字典中存在 continue
                    if ("".equals(value) || (map.containsKey(value) && map.get(value).indexOf(cache[0]) == 0))
                        continue;
                    String code = getCode(value, cache, i - startColumn, step);
                    result.add(getT(code, value));
                    map.put(value, code);
                }
            }
        }
    }

    protected abstract String getCode(String value, String[] cache, int offset, int step);

    protected abstract void checkDefault(String str, Map<String, String> map, int step);

    protected abstract void setDefault(String code);

    public List<T> getResult() {
        return result;
    }

    //请参考Spring Boot版 这个已经被改乱了,请见谅!
    protected T getT(String code, String str) {
        value.put("checkItemCode", code);
        value.put("checkItemName", str);

        T t = null;
        try {
            t = c.newInstance();
            BeanUtils.populate(t, value);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }
}
