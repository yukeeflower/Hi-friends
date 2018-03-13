package com.ch.dao;

import com.ch.model.Remark;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

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

    @Test
    public void getRemarkByEntityType() throws Exception {
        List<Remark> remark = remarkDAO.selectRemarkByTypeId(1,1);
        System.out.println(remark.toString());
    }
    @Test
    public void insertRemark() throws Exception {
        Remark remark = new Remark();
        remark.setUserId(1);
        remark.setContent("开讲啦数据的阿卡聚少离多按时 ");
        remark.setEntityType(Remark.REMARK_TYPE_FORUM);
        remark.setEntityId(3);
        remark.setCreateTime(new Date());
        int i = remarkDAO.insertRemark(remark);
        System.out.println(i);
    }

}