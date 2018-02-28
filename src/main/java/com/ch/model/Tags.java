package com.ch.model;

import java.util.Date;

/**
 * Created by apple on 2018/2/28.
 */
public class Tags {

    private int id;

    private String tagName;

    private Date createTime;

    private int adminId;

    public Tags() {
    }

    public Tags(int id, String tagName, Date createTime, int adminId) {
        this.id = id;
        this.tagName = tagName;
        this.createTime = createTime;
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", createTime=" + createTime +
                ", adminId=" + adminId +
                '}';
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
