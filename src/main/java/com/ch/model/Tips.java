package com.ch.model;

import java.util.Date;

/**
 * Created by apple on 2018/2/28.
 */
public class Tips {

    private int id;

    private int payerId;

    private int money;

    private int receiverId;

    private Date createTime;

    public Tips() {
    }

    public Tips(int id, int payerId, int money, int receiverId, Date createTime) {
        this.id = id;
        this.payerId = payerId;
        this.money = money;
        this.receiverId = receiverId;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Tips{" +
                "id=" + id +
                ", payerId=" + payerId +
                ", money=" + money +
                ", receiverId=" + receiverId +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

}
