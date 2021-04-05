package com.wlhse.dto.outDto;

import org.springframework.stereotype.Component;

@Component
public class CompanyOutDto {
    private String parentCode;
    private String parentName;
    private String sonCode;
    private String sonName;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getSonCode() {
        return sonCode;
    }

    public void setSonCode(String sonCode) {
        this.sonCode = sonCode;
    }

    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

}
