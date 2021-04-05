package com.wlhse.dto.inDto;

public class QHSEManageSysElementsDto {
    private Integer id;
    private String code;
    private String companyName;
    private String companyCode;
    private String year;
    private String status;
    private String elementTableName;

    @Override
    public String toString() {
        return "QHSEManageSysElementsDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", year='" + year + '\'' +
                ", status='" + status + '\'' +
                ", elementTableName='" + elementTableName + '\'' +
                '}';
    }

    public String getElementTableName() {
        return elementTableName;
    }

    public void setElementTableName(String elementTableName) {
        this.elementTableName = elementTableName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}
