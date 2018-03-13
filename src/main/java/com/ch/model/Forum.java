package com.ch.model;

import java.util.Date;

/**
 * Created by apple on 2018/2/28.
 */
public class Forum {

    private int id;

    private String title;

    private String content;

    private String pictures;

    private int userId;

    private Date createTime;

    private int scanNum;

    private int favorNum;

    private int remarkNum;

    private String tags;

    public Forum(){}

    public Forum(int id, String title, String content, String pictures, int userId, Date createTime, int scanNum, int favorNum, int remarkNum,String tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.pictures = pictures;
        this.userId = userId;
        this.createTime = createTime;
        this.scanNum = scanNum;
        this.favorNum = favorNum;
        this.remarkNum = remarkNum;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Forum{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pictures='" + pictures + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", scanNum=" + scanNum +
                ", favorNum=" + favorNum +
                ", remarkNum=" + remarkNum +
                ", tags=" + tags +
                '}';
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getScanNum() {
        return scanNum;
    }

    public void setScanNum(int scanNum) {
        this.scanNum = scanNum;
    }

    public int getFavorNum() {
        return favorNum;
    }

    public void setFavorNum(int favorNum) {
        this.favorNum = favorNum;
    }

    public int getRemarkNum() {
        return remarkNum;
    }

    public void setRemarkNum(int remarkNum) {
        this.remarkNum = remarkNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
