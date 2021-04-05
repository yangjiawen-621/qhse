//package com.wlhse.util.thread;
//
//import com.wlhse.dto.PoiProblemDto;
//import com.wlhse.util.GetBeanListByExcel;
//import org.apache.poi.ss.usermodel.Sheet;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.concurrent.CountDownLatch;
//
//public class PoiProblemsThread extends Thread {
//    private int startIdx;
//    private int endIdx;
//    private Sheet sheet;
//    private String[] strArray = {"orderNumber", "parentCompanyName", "companyName", "checkDate",
//            "checkType", "checkItemName1", "checkItemName2", "checkItemName3", "task",
//            "process", "description", "descriptionDetail", "recommendRectiMeasure", "responsePersonName",
//            "responsePersonParentCompanyName", "responsePersonCompanyName", "penalty", "lostScore"};
//    private CountDownLatch countDownLatch;
//    private GetBeanListByExcel getBeanListByExcel;
//    public PoiProblemsThread(int startIdx, int endIdx, Sheet sheet, GetBeanListByExcel getBeanListByExcel, CountDownLatch countDownLatch) {
//        this.startIdx = startIdx;
//        this.endIdx = endIdx;
//        this.sheet = sheet;
//        this.getBeanListByExcel = getBeanListByExcel;
//        this.countDownLatch = countDownLatch;
//    }
//    @Override
//    public void run() {
//        try {
//            if (sheet != null) {
//                getBeanListByExcel.getProblemBeanAndPoi(sheet, strArray, PoiProblemDto.class, startIdx, endIdx);
//            }
//        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
//            e.printStackTrace();
//        } finally {
//            countDownLatch.countDown();
//        }
//    }
//}
