package com.wlhse.dto.outDto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactorOutDto2 {

    private Integer id;

    private Integer factorID;

    private String factorCode;

    private String name;

    private String right;

    private String factorHseCode;

    private String factorObserverCode;

    private String factorSourceCode;

    private String factorDepartmentCode;

    private List<FactorOutDto2> childNode;

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

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
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

    public List<FactorOutDto2> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<FactorOutDto2> childNode) {
        this.childNode = childNode;
    }

    @Override
    public String toString() {
        return "FactorOutDto2{" +
                "id=" + id +
                ", factorID=" + factorID +
                ", factorCode='" + factorCode + '\'' +
                ", name='" + name + '\'' +
                ", right='" + right + '\'' +
                ", factorHseCode='" + factorHseCode + '\'' +
                ", factorObserverCode='" + factorObserverCode + '\'' +
                ", factorSourceCode='" + factorSourceCode + '\'' +
                ", factorDepartmentCode='" + factorDepartmentCode + '\'' +
                ", childNode=" + childNode +
                '}';
    }
}
