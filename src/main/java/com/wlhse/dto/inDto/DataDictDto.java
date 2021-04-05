package com.wlhse.dto.inDto;

public class DataDictDto {
    private Integer id;
    private String name;
    private String parent;
    private Integer length;
    private String dataDictCode;

    public String getDataDictCode() {
        return dataDictCode;
    }

    public void setDataDictCode(String dataDictCode) {
        this.dataDictCode = dataDictCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
