package com.ch.dao;

import com.ch.model.Favor;
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
public class FavorDAOTest {

    @Autowired
    private FavorDAO favorDAO;

    @Test
    public void getFavorById() throws Exception {
        Favor favor = favorDAO.selectFavorById(1);
        System.out.println(favor.toString());
    }

}