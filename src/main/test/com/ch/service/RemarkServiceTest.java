package com.ch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/3/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:beans.xml"})
public class RemarkServiceTest {
    @Autowired
    private RemarkService remarkService;
    @Test
    public void getAllRemarkByForumId() throws Exception {

    }

    @Test
    public void addRemark() throws Exception {
        remarkService.addRemark("sadsa",1,1,1);
    }

}