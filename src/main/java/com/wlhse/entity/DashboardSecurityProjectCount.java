package com.wlhse.entity;

/**
 * @Author tobing
 * @Date 2020/11/27 11:37
 * @Description
 */
public class DashboardSecurityProjectCount {

    private String companyCode;
    private String companyName;
    /**
     * 项目级别
     */
    private String projectLevel;
    /**
     * 项目级别统计
     */
    private String projectLevelCount;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectLevel() {
        return projectLevel;
    }

    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }

    public String getProjectLevelCount() {
        return projectLevelCount;
    }

    public void setProjectLevelCount(String projectLevelCount) {
        this.projectLevelCount = projectLevelCount;
    }
}
