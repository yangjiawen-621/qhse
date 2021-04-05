package com.wlhse.entity;

public class Message {

    //消息的id
    private int id;
    private int senderId;
    private int receiverId;

    private String tittle;
    private String body;
    private String source;
    //消息发发送时间
    private String time;
    //状态，已读|未读
    private String status;
    //阅读时间
    private String readTime;

    //消息是否隐藏，隐藏1 不隐藏0
    private int deleted;

    public Message() {
    }

    public Message(int id, int senderId, int receiverId, String tittle, String body, String source, String time, String status, String readTime, int deleted) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.tittle = tittle;
        this.body = body;
        this.source = source;
        this.time = time;
        this.status = status;
        this.readTime = readTime;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
