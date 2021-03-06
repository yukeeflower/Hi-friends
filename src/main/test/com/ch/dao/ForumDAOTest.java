package com.ch.dao;

import com.ch.model.Forum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:beans.xml"})
public class ForumDAOTest {

    @Autowired
    private ForumDAO forumDAO;

    @Test
    public void getForumById() throws Exception {
        Forum forum = forumDAO.selectForumById(1);
        System.out.println(forum.toString());
    }

    @Test
    public void getLatestForums() throws Exception {
        List<Forum> forum = forumDAO.selectLatestForums();
        System.out.println(forum.size());
    }

}