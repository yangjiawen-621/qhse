package com.wlhse.dto.outDto;

public class FactorOutDto {

    private Integer id;

    private Integer factorID;

    private String factorCode;

    private String name;

    private String rigth;

    private String factorHseCode;

    private String factorObserverCode;

    private String factorSourceCode;

    private String factorDepartmentCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFactorID() {
        return factorID;
    }

    public void setFactorID(Integer factorID) {
        this.factorID = factorID;
    }

    public String getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRigth() {
        return rigth;
    }

    public void setRigth(String rigth) {
        this.rigth = rigth;
    }

    public String getFactorHseCode() {
        return factorHseCode;
    }

    public void setFactorHseCode(String factorHseCode) {
        this.factorHseCode = factorHseCode;
    }

    public String getFactorObserverCode() {
        return factorObserverCode;
    }

    public void setFactorObserverCode(String factorObserverCode) {
        this.factorObserverCode = factorObserverCode;
    }

    public String getFactorSourceCode() {
        return factorSourceCode;
    }

    public void setFactorSourceCode(String factorSourceCode) {
        this.factorSourceCode = factorSourceCode;
    }

    public String getFactorDepartmentCode() {
        return factorDepartmentCode;
    }

    public void setFactorDepartmentCode(String factorDepartmentCode) {
        this.factorDepartmentCode = factorDepartmentCode;
    }

    @Override
    public String toString() {
        return "FactorOutDto{" +
                "id=" + id +
                ", factorID=" + factorID +
                ", factorCode='" + factorCode + '\'' +
                ", name='" + name + '\'' +
                ", right='" + rigth + '\'' +
                ", factorHseCode='" + factorHseCode + '\'' +
                ", factorObserverCode='" + factorObserverCode + '\'' +
                ", factorSourceCode='" + factorSourceCode + '\'' +
                ", factorDepartmentCode='" + factorDepartmentCode + '\'' +
                '}';
    }

}
