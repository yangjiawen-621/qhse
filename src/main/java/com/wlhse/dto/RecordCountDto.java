package com.wlhse.dto;

/**
 * @Author tobing
 * @Date 2020/11/30 21:48
 * @Description
 */
public class RecordCountDto {
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 违章记录数
     */
    private String nums;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }
}
