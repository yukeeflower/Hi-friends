package com.ch.dao;

import com.ch.model.Remark;
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
public class RemarkDAOTest {

    @Autowired
    private RemarkDAO remarkDAO;

    @Test
    public void getRemarkById() throws Exception {
        Remark remark = remarkDAO.selectRemarkById(1);
        System.out.println(remark.toString());
    }

}