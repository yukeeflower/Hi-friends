package com.ch.model;

import java.util.Date;

/**
 * Created by apple on 2018/2/28.
 */
public class Favor {

    private int id;

    private int userId;

    private int forumId;

    private Date createTime;

    public Favor(){}

    public Favor(int id, int userId, int forumId, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.forumId = forumId;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Favor{" +
                "id=" + id +
                ", userId=" + userId +
                ", forumId=" + forumId +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
