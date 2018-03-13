package com.ch.model;

import java.util.Date;

/**
 * Created by apple on 2018/2/28.
 */
public class Remark {

    public static final int REMARK_TYPE_FORUM = 1;
    public static final int REMARK_TYPE_USER = 2;

    private int id;

    private int userId;

    private String content;

    private int entityType;

    private int entityId;

    private Date createTime;

    public Remark() {
    }


    public Remark(int id, int userId, String content, int entityType, int entityId, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.entityType = entityType;
        this.entityId = entityId;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Remark{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", entityType=" + entityType +
                ", entityId=" + entityId +
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEntityType() {
        return entityType;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
