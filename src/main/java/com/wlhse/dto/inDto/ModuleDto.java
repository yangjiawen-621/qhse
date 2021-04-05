package com.wlhse.dto.inDto;

import org.springframework.stereotype.Component;

@Component
public class ModuleDto {
    private String parentCode;
    private Integer length;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
