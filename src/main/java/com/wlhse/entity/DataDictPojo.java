package com.wlhse.entity;

import org.springframework.stereotype.Component;

@Component
public class DataDictPojo {
    private Integer id;
    private String dictCode;
    private String name;
    private Integer serialNO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSerialNO() {
        return serialNO;
    }

    public void setSerialNO(Integer serialNO) {
        this.serialNO = serialNO;
    }

}
