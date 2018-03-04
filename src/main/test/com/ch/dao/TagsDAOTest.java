package com.ch.dao;

import com.ch.model.Tags;
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
public class TagsDAOTest {

    @Autowired
    private TagsDAO tagsDAO;

    @Test
    public void getTagById() throws Exception {
        Tags tags = tagsDAO.selectTagById(1);
        System.out.println(tags.toString());
    }

}