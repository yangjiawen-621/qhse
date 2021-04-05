package com.wlhse.entity;

import org.springframework.stereotype.Component;



@Component
public class UserPojo {
    private Integer sysUserID;  //用户标识
    private Integer employeeID;
    private String uName; //用户名
    private String pwd; //密码
    private String status;  //账户状态
    private String description; //描述
    private String roleCode;


    public Integer getSysUserID() {
        return sysUserID;
    }

    public void setSysUserID(Integer sysUserID) {
        this.sysUserID = sysUserID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
