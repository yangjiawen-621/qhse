package com.wlhse.dto.inDto;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class RegulationInDto extends BaseGetDto {
    private String	typeCode;
    private String	regName;
    private String	publishComCode;

    public String getRegNameCode() {
        return regNameCode;
    }

    public void setRegNameCode(String regNameCode) {
        this.regNameCode = regNameCode;
    }

    private String	beginDate1;
    private String	beginDate2;
    private String url;
    private String	regNameCode;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getPublishComCode() {
        return publishComCode;
    }

    public void setPublishComCode(String publishComCode) {
        this.publishComCode = publishComCode;
    }

    public String getBeginDate1() {
        return beginDate1;
    }

    public void setBeginDate1(String beginDate1) {
        this.beginDate1 = beginDate1;
    }

    public String getBeginDate2() {
        return beginDate2;
    }

    public void setBeginDate2(String beginDate2) {
        this.beginDate2 = beginDate2;
    }

}

