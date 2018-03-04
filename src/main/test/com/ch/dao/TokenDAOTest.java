package com.ch.dao;

import com.ch.model.Token;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/3/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:beans.xml"})
public class TokenDAOTest {

    @Autowired
    private TokenDAO tokenDAO;

    @Test
    public void selectTokenById() throws Exception {
        System.out.println(tokenDAO.selectTokenById(1));
    }

    @Test
    public void selectByToken() throws Exception {
        System.out.println(tokenDAO.selectByToken("73ff311"));
    }

    @Test
    public void insertToken() throws Exception {
        Token token = new Token();
        token.setId(1);
        token.setTicket(UUID.randomUUID().toString().substring(0,5)+"11");
        token.setExpired(new Date(new Date().getTime() + 7*24*60*60*1000));
        token.setUserId(1);
        token.setStatus(0);
        System.out.println(tokenDAO.insertToken(token));
    }

}