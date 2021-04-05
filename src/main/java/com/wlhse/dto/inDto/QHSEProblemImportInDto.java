package com.wlhse.dto.inDto;

import com.wlhse.dto.getDto.BaseGetDto;
import org.springframework.stereotype.Component;

@Component
public class QHSEProblemImportInDto extends BaseGetDto {
    private Integer problemID;
    private String checkClassCode;//检查类别code
    private Integer checkPersonID; //检查人员ID
    private String companyCode;//受检单位ID
    private String checkDate1;
    private String checkDate2;
    private String isOrderBy = "false";
    private String url;


    public Integer getProblemID() { return problemID; }
    public void setProblemID(Integer problemID) {
        this.problemID = problemID;
    }

    public String getCheckClassCode() {
        return checkClassCode;
    }
    public void setCheckClassCode(String checkClassCode) { this.checkClassCode = checkClassCode; }

    public Integer getCheckPersonID() {
        return checkPersonID;
    }
    public void setCheckPersonID(Integer checkPersonID) {
        this.checkPersonID = checkPersonID;
    }

    public String getCompanyCode() {
        return companyCode;
    }
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCheckDate1() {
        return checkDate1;
    }
    public void setCheckDate1(String checkDate1) {
        this.checkDate1 = checkDate1;
    }

    public String getCheckDate2() {
        return checkDate2;
    }
    public void setCheckDate2(String checkDate2) {
        this.checkDate2 = checkDate2;
    }

    public String getIsOrderBy() {
        return isOrderBy;
    }
    public void setIsOrderBy(String isOrderBy) {
        this.isOrderBy = isOrderBy;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
