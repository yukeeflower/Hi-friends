package com.ch.dao;

import com.ch.model.Message;
import com.ch.model.Userinfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:beans.xml"})
public class MessageDAOTest {

    @Autowired
    private MessageDAO messageDAO;

    @Test
    public void getMessageById() throws Exception {
        Message message = messageDAO.getMessageById(1);
        System.out.println(message.toString());
    }

}