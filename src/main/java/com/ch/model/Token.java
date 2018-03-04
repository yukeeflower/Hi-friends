package com.ch.model;

import java.util.Date;

/**
 * Created by apple on 2018/3/4.
 */
public class Token {

    private int id;

    private int userId;

    private String ticket;

    private Date expired;

    private int status;

    public Token() {
    }

    public Token(int id, int userId, String ticket, Date expired, int status) {
        this.id = id;
        this.userId = userId;
        this.ticket = ticket;
        this.expired = expired;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", userId=" + userId +
                ", ticket='" + ticket + '\'' +
                ", expired=" + expired +
                ", status=" + status +
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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
