package com.wlhse.dto.outDto;

import org.springframework.stereotype.Component;

@Component
public class UserOutDto {
    private int userId;
    private String userName;
    private String token;
    private String status;
    private int employeeId;
    private String companyName;
    private String companyCode;
    private String employeeName;
    private String group;
    private String openId;



    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    @Override
    public String toString() {
        return "UserOutDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                ", status='" + status + '\'' +
                ", employeeId=" + employeeId +
                ", companyName='" + companyName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", group='" + group + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
