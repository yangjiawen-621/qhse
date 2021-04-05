package com.wlhse.dto;

import org.springframework.stereotype.Component;

@Component
public class ProblemMessageDto {
    private String orderNumber;
    private Integer publishID;
    private String checkItemName1;
    private String checkItemName2;
    private String checkItemName3;
    private String companyCode;
    private Integer checkPersonId;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getPublishID() {
        return publishID;
    }

    public void setPublishID(Integer publishID) {
        this.publishID = publishID;
    }

    public String getCheckItemName1() {
        return checkItemName1;
    }

    public void setCheckItemName1(String checkItemName1) {
        this.checkItemName1 = checkItemName1;
    }

    public String getCheckItemName2() {
        return checkItemName2;
    }

    public void setCheckItemName2(String checkItemName2) {
        this.checkItemName2 = checkItemName2;
    }

    public String getCheckItemName3() {
        return checkItemName3;
    }

    public void setCheckItemName3(String checkItemName3) {
        this.checkItemName3 = checkItemName3;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getCheckPersonId() {
        return checkPersonId;
    }

    public void setCheckPersonId(Integer checkPersonId) {
        this.checkPersonId = checkPersonId;
    }
}
