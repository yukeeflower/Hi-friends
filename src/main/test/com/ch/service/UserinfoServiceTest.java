package com.ch.service;

import com.ch.model.Userinfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/3/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:beans.xml"})
public class UserinfoServiceTest {

    @Autowired
    UserinfoService userinfoService;

    @Test
    public void login() throws Exception {
        Map<String,Object> map =  userinfoService.login("cheng","1232");
        System.out.println(map);
    }

    @Test
    public void regist()throws Exception{
        Userinfo userinfo = new Userinfo();
        userinfo.setUsername("li");
        userinfo.setPassword("123456");
        userinfo.setNickname("风中的筱橙子丶");
        userinfo.setMoney(0);
        userinfoService.regist("li","123456","风中的筱橙子丶");
    }


}