package com.wlhse.dto;

/**
 * Author:melon
 * Origin:2020/10/5
 **/
public class QualityFileInputInfoDto {
    private Integer id;
      private String originalFileName;
    private String newFileName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }
}
