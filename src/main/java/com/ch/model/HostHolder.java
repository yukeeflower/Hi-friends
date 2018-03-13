package com.ch.model;

import org.springframework.stereotype.Component;

/**
 * Created by apple on 2018/3/5.
 */
@Component
public class HostHolder {
    private ThreadLocal<Userinfo>users = new ThreadLocal<>();

    public void setUsers(Userinfo userinfo){
        users.set(userinfo);
    }

    public Userinfo getUsers(){
        return users.get();
    }

    public void clearUsers(){
        users.remove();
    }

}
