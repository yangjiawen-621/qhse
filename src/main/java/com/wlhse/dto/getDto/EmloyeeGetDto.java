package com.wlhse.dto.getDto;


import org.springframework.stereotype.Component;

@Component
public class EmloyeeGetDto extends BaseGetDto {
    private String name;

    private String companyCode;

    private String inputType="problem_write";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
