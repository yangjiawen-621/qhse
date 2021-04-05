package com.wlhse.dto.inDto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Description:
 * Author:Coco
 * create:2020-08-04 5:59 PM
 **/
public class FilePropagationFileInfo {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long propagationId;
    private String filePath;
    private String originName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPropagationId() {
        return propagationId;
    }

    public void setPropagationId(Long propagationId) {
        this.propagationId = propagationId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    @Override
    public String toString() {
        return "FilePropagationFileInfo{" +
                "id=" + id +
                ", propagationId=" + propagationId +
                ", filePath='" + filePath + '\'' +
                ", originName='" + originName + '\'' +
                '}';
    }
}
