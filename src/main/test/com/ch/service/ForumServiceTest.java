package com.ch.service;

import com.ch.model.Forum;
import com.ch.model.ViewObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/3/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:beans.xml"})
public class ForumServiceTest {

    @Autowired
    private ForumService forumService;
    @Autowired
    private UserinfoService userinfoService;

    @Test
    public void getLatestForums() throws Exception {
    }

}