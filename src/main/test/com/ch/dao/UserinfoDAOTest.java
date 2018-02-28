package com.ch.dao;

import com.ch.model.Userinfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


/**
 * Created by apple on 2018/2/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:beans.xml"})
public class UserinfoDAOTest {

    @Autowired
    private UserinfoDAO userinfoDAO;

    @Test
    public void getUserinfo() throws Exception {
        Userinfo userinfo = userinfoDAO.getUserinfoById(1);
        System.out.println(userinfo.getNickname());
    }

    @Test
    public void getUserinfoByUsername(){
        Userinfo userinfo = userinfoDAO.getUserinfoByUsername("piao");
        System.out.println(userinfo.toString());
    }

    @Test
    public void updatePasswordById(){
        Userinfo userinfo = userinfoDAO.getUserinfoById(1);
        System.out.println(userinfo.toString());
        int i = userinfoDAO.updatePasswordById(1,"123456",new Date());
        System.out.println(i);
        userinfo = userinfoDAO.getUserinfoById(1);
        System.out.println(userinfo.toString());
    }

    @Test
    public void insertUser(){
        Userinfo userinfo = new Userinfo();
        userinfo.setUsername("zhang");
        userinfo.setPassword("123456");
        userinfo.setSalt("123456");
        userinfo.setEmail("56454564@163.com");
        userinfo.setMoney(1000);
        userinfo.setCreateTime(new Date());
        userinfo.setLastModify(new Date());
        System.out.println(userinfoDAO.insertUser(userinfo));
    }

    @Test
    public void updateUserinfo(){
        Userinfo userinfo = new Userinfo();
        userinfo.setNickname("筱橙子丶");
        System.out.println(userinfoDAO.updateUserinfoSelective(1,"筱橙子丶",null));
    }

}