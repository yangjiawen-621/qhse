package com.wlhse.dto;

import java.util.List;

public class QHSEQualityFillDto {
    private Integer id;
    private String year;
    private String companyCode;
    private List<String> parentNodeCode;//父节点
    private Integer diertaScore;//增加的份数
    private boolean flag=true;//标志是否是第一次修改分数
    private String code;
    private String keyWords;
    private Integer actualScore;
    private String existProblems;
    private String rank;
    private String pictureFile;
    private String videoFile;
    private String photoesurl;//照片地址
    private String videosurl;//视频地址


    public String getPhotoesurl() {
        return photoesurl;
    }

    public void setPhotoesurl(String photoesurl) {
        this.photoesurl = photoesurl;
    }

    public String getVideosurl() {
        return videosurl;
    }

    public void setVideosurl(String videosurl) {
        this.videosurl = videosurl;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getDiertaScore() {
        return diertaScore;
    }

    public void setDiertaScore(Integer diertaScore) {
        this.diertaScore = diertaScore;
    }

    public List<String> getParentNodeCode() {
        return parentNodeCode;
    }

    public void setParentNodeCode(List<String> parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public Integer getActualScore() {
        return actualScore;
    }

    public void setActualScore(Integer actualScore) {
        this.actualScore = actualScore;
    }

    public String getExistProblems() {
        return existProblems;
    }

    public void setExistProblems(String existProblems) {
        this.existProblems = existProblems;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(String pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public void setVideoFile(String videoFile) {
        this.videoFile = videoFile;
    }
}
