package com.wlhse.entity;



/**
 * Author:melon
 * Origin:2020/8/8
 **/

public class ElementInputFileInfo {
    private Integer id;//要素新旧文件id
    private Integer QHSE_CompanyYearManagerSysElementEvidence_ID;//要素证据id
    private String elementOriginFileName;
    private String newElementFileName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQHSE_CompanyYearManagerSysElementEvidence_ID() {
        return QHSE_CompanyYearManagerSysElementEvidence_ID;
    }

    public void setQHSE_CompanyYearManagerSysElementEvidence_ID(Integer QHSE_CompanyYearManagerSysElementEvidence_ID) {
        this.QHSE_CompanyYearManagerSysElementEvidence_ID = QHSE_CompanyYearManagerSysElementEvidence_ID;
    }

    public String getElementOriginFileName() {
        return elementOriginFileName;
    }

    public void setElementOriginFileName(String elementOriginFileName) {
        this.elementOriginFileName = elementOriginFileName;
    }

    public String getNewElementFileName() {
        return newElementFileName;
    }

    public void setNewElementFileName(String newElementFileName) {
        this.newElementFileName = newElementFileName;
    }

    @Override
    public String toString() {
        return "ElementInputFileInfo{" +
                "id=" + id +
                ", QHSE_CompanyYearManagerSysElementEvidence_ID=" + QHSE_CompanyYearManagerSysElementEvidence_ID +
                ", elementOriginFileName='" + elementOriginFileName + '\'' +
                ", newElementFileName='" + newElementFileName + '\'' +
                '}';
    }
}
