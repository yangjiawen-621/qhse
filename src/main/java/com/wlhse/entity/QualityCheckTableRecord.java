package com.wlhse.entity;

/**
 * @Description TODO
 * @Author YangJiaWen
 * @Date 2021/4/3 11:57
 * @Version 1.0
 **/


public class QualityCheckTableRecord {

    private String checkListCode;


    private Integer QualityCheckID;

    public Integer getQualityCheckID() {
        return QualityCheckID;
    }

    public void setQualityCheckID(Integer qualityCheckID) {
        QualityCheckID = qualityCheckID;
    }

    public String getCheckListCode() {
        return checkListCode;
    }

    public void setCheckListCode(String checkListCode) {
        this.checkListCode = checkListCode;
    }

    public QualityCheckTableRecord() {

    }

}
