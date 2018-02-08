package com.ch.dao;

import com.ch.model.Userinfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

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
        Userinfo userinfo = userinfoDAO.getUserinfo(1);
        System.out.println(userinfo.getNickname());
    }

}