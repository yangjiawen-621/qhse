package com.wlhse.dto;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
public class EmployeeManagementDto extends BaseGetDto {

    //员工ID
    private Integer employeeID;

    private String employeeCode;//员工编号

    private String name;
    //二级单位，即companyCode的前4位
    private String topFourCompanyCode;

    private String topFourCompanyName;

    //底层单位
    private String companyCode;

    private String companyName;

    private String birthday;

    //员工状态
    private String jobTime;
    //员工组

    private String empGroup;
    //职位(市场化用工|合同化员工) --> 改为科室

    private String position;
    //岗位(安全监督|测井工|驾驶员)

    private String station;

    //SYS_USER表中的用户名
    private String uName;

    //SYS_USER表中的状态
    private String status;

    //sys_user_role表里的rolecode
    private String roleCode;

    //学历
    private String education;

    //性别
    private String sex;

    //Email
    private String email;

    //电话
    private String tel;

    //旧密码
    private String oldPassword;

    //新密码
    private String newPassword;

    //角色
    private String roleName;

    //员工类别
    private String category;

    private String hash;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopFourCompanyCode() {
        return topFourCompanyCode;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    //取companyCode的前4位
    public void setTopFourCompanyCode(String topFourCompanyCode) {
        this.topFourCompanyCode = topFourCompanyCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getJobTime() {
        return jobTime;
    }

    public void setJobTime(String jobTime) {
        this.jobTime = jobTime;
    }

    public String getEmpGroup() {
        return empGroup;
    }

    public void setEmpGroup(String empGroup) {
        this.empGroup = empGroup;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTopFourCompanyName() {
        return topFourCompanyName;
    }

    public void setTopFourCompanyName(String topFourCompanyName) {
        this.topFourCompanyName = topFourCompanyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
