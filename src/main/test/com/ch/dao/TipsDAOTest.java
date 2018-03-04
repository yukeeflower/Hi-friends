package com.ch.dao;

import com.ch.model.Tips;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by apple on 2018/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:beans.xml"})
public class TipsDAOTest {

    @Autowired
    private TipsDAO tipsDAO;

    @Test
    public void getTipById() throws Exception {
        Tips tips = tipsDAO.selectTipById(1);
        System.out.println(tips.toString());
    }

}