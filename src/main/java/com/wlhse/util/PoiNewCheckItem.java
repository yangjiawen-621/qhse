//package com.wlhse.util;
//
//import com.wlhse.dao.CheckItemDao;
//import com.wlhse.dao.CheckItemStardardDao;
//import com.wlhse.entity.CheckItemPojo;
//import com.wlhse.entity.CheckItemStardardPojo;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.Iterator;
//import java.util.List;
//
//
//@Component
//@Transactional(rollbackFor = Exception.class)
//public class PoiNewCheckItem {
//    @Resource
//    private CheckItemDao checkItemDao;
//
//    @Resource
//    private SortCodeUtil sortCodeUtil;
//
//    @Resource
//    private CheckItemPojo checkItemPojo;
//
//    @Resource
//    private CheckItemStardardPojo checkItemStardardPojo;
//
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Resource
//    private CheckItemStardardDao checkItemStardardDao;
//
//    private String insertCheckItem(String parentcode, String sonName) {
//        String sonCode = checkItemDao.parentGetString(parentcode, parentcode.length(), sonName, parentcode.length() + 2);
//        if (sonCode == null) {
//            String tempCode = checkItemDao.getMaxString(parentcode, parentcode.length(), parentcode.length() + 2);
//            if (tempCode == null) {
//                sonCode = parentcode + "01";
//            } else {
//                sonCode = sortCodeUtil.getMaxCodeString(tempCode);
//            }
//            setcheckItemPojoAll(sonCode, sonName);
//        }
//        return sonCode;
//    }
//
//    private void setcheckItemPojoAll(String code, String name) {
//        checkItemPojo.setCheckItemCode(code);
//        checkItemPojo.setCheckItemName(name);
//        checkItemPojo.setStatus("启用");
//        checkItemDao.addCheckItemPojo(checkItemPojo);
//    }
//
//    private void setDefalutCheckItem() {
//        List<CheckItemPojo> checkItemPojoDefault = checkItemDao.getCheckItemPojoDefault(DictCode.CHECK_ITEM_LEVEL_1);
//        for (CheckItemPojo checkItemPojo : checkItemPojoDefault) {
//            String checkItemLevel1Code = checkItemPojo.getCheckItemCode();
//            setSingleCheckItemUtil(checkItemLevel1Code);
//        }
//        List<CheckItemPojo> checkItemPojoDefault1 = checkItemDao.getCheckItemPojoDefault(DictCode.CHECK_ITEM_LEVEL_2);
//        for (CheckItemPojo checkItemPojo : checkItemPojoDefault1) {
//            setLevelTwoDefaultCheckItem(checkItemPojo.getCheckItemCode());
//        }
//        List<CheckItemPojo> checkItemPojoDefault2 = checkItemDao.getCheckItemPojoDefault(DictCode.CHECK_ITEM_LEVEL_3);
//        for (CheckItemPojo checkItemPojo : checkItemPojoDefault2) {
//            setLevelThreeDefaultCheckItem(checkItemPojo.getCheckItemCode());
//        }
//    }
//
//    public void setSingleCheckItemUtil(String checkItemLevel1Code) {
//        String checkItemLevel2Code = checkItemLevel1Code + "00";
//        String checkItemLevel3Code = checkItemLevel1Code + "0000";
//        String checkItemLevel4Code = checkItemLevel1Code + "000000";
//
//        checkItemPojo.setStatus("启用");
//        checkItemPojo.setCheckItemName("其它");
//        if (checkItemDao.getCheckItemPojoDefaultCount(checkItemLevel2Code) <= 0) {
//            checkItemPojo.setCheckItemCode(checkItemLevel2Code);
//            checkItemDao.addCheckItemPojo(checkItemPojo);
//        }
//        if (checkItemDao.getCheckItemPojoDefaultCount(checkItemLevel3Code) <= 0) {
//            checkItemPojo.setCheckItemCode(checkItemLevel3Code);
//            checkItemDao.addCheckItemPojo(checkItemPojo);
//        }
//        if (checkItemDao.getCheckItemPojoDefaultCount(checkItemLevel4Code) <= 0) {
//            checkItemPojo.setCheckItemCode(checkItemLevel4Code);
//            checkItemDao.addCheckItemPojo(checkItemPojo);
//        }
//    }
//
//    public void setLevelTwoDefaultCheckItem(String checkItemCode) {
//        checkItemPojo.setStatus("启用");
//        checkItemPojo.setCheckItemName("其它");
//        String checkItemLevel3Code = checkItemCode + "00";
//        String checkItemLevel4Code = checkItemCode + "0000";
//        if (checkItemDao.getCheckItemPojoDefaultCount(checkItemLevel3Code) <= 0) {
//            checkItemPojo.setCheckItemCode(checkItemLevel3Code);
//            checkItemDao.addCheckItemPojo(checkItemPojo);
//        }
//        if (checkItemDao.getCheckItemPojoDefaultCount(checkItemLevel4Code) <= 0) {
//            checkItemPojo.setCheckItemCode(checkItemLevel4Code);
//            checkItemDao.addCheckItemPojo(checkItemPojo);
//        }
//    }
//
//    public void setLevelThreeDefaultCheckItem(String checkItemCode) {
//        checkItemPojo.setStatus("启用");
//        checkItemPojo.setCheckItemName("其它");
//        String checkItemLevel4Code = checkItemCode + "00";
//        if (checkItemDao.getCheckItemPojoDefaultCount(checkItemLevel4Code) <= 0) {
//            checkItemPojo.setCheckItemCode(checkItemLevel4Code);
//            checkItemDao.addCheckItemPojo(checkItemPojo);
//        }
//    }
//
//    @Transactional
//    public void excelCheckItem(Workbook wb) {
//        try {
//            if (wb != null) {
//                Sheet sheet;
//                Row row;
//                for (int sheetnum = 0; sheetnum < wb.getNumberOfSheets(); sheetnum++) {
//                    sheet = wb.getSheetAt(sheetnum);
//                    String code1 = "";
//                    String code2 = "";
//                    String code3 = "";
//                    String tableName = sheet.getRow(0).getCell(0).toString();
//                    String tableCode = checkItemDao.queryByCheckItemNameReturnStr(tableName);
//                    if (null == tableCode) {
//                        String tempCode = checkItemDao.getMaxString("", 0, 4);
//                        if (tempCode == null) {
//                            tableCode = "0001";
//                        } else {
//                            tableCode = sortCodeUtil.getMaxCodeString(tempCode);
//                        }
//                        setcheckItemPojoAll(tableCode, tableName);
//                    }
//                    for (int j = 2; j < sheet.getPhysicalNumberOfRows(); j++) {
//                        row = sheet.getRow(j);
//                        int columnNum = row.getPhysicalNumberOfCells();
//                        Iterator<Cell> cell = row.iterator();
//                        int flag = 0;
//                        while (cell.hasNext()) {
//                            if (flag == columnNum) {
//                                break;
//                            } else {
//                                String myString = cell.next().toString();
//                                while (myString.equals("")) {
//                                    myString = cell.next().toString();
//                                    flag++;
//                                }
//                                switch (flag) {
//                                    case 0:
//                                        code1 = insertCheckItem(tableCode, myString);
//                                        break;
//                                    case 1:
//                                        code2 = insertCheckItem(code1, myString);
//                                        break;
//                                    case 2:
//                                        code3 = insertCheckItem(code2, myString);
//                                        break;
//                                    case 3:
//                                        checkItemStardardPojo.setStatus("启用");
//                                        checkItemStardardPojo.setSerialNo(checkItemDao.getMaxSeria(code3) + 1);
//                                        checkItemStardardPojo.setContent(myString);
//                                        checkItemStardardPojo.setCheckItemCode(code3);
//                                        checkItemStardardDao.addCheckItemStardardPojo(checkItemStardardPojo);
//                                        break;
//                                }
//                                flag++;
//                            }
//                        }
//                    }
//                }
//            }
//            //检查数据库是否存在其他
//            setDefalutCheckItem();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//
//    }
//}
