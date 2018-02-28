package com.ch.model;

import java.util.Date;

/**
 * Created by apple on 2018/2/28.
 */
public class Message {

    private int id;

    private int fromId;

    private int toId;

    private String content;

    private Date createTime;

    private int hasRead;

    public Message() {
    }

    public Message(int id, int fromId, int toId, String content, Date createTime, int hasRead) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.content = content;
        this.createTime = createTime;
        this.hasRead = hasRead;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", hasRead=" + hasRead +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getHasRead() {
        return hasRead;
    }

    public void setHasRead(int hasRead) {
        this.hasRead = hasRead;
    }
}
