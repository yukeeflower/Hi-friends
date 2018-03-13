package com.ch.service;

import com.ch.controller.UserinfoController;
import com.ch.dao.RemarkDAO;
import com.ch.model.Remark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by apple on 2018/3/7.
 */
@Service
public class RemarkService {

    private static final Logger logger = LoggerFactory.getLogger(RemarkService.class);

    @Autowired
    private RemarkDAO remarkDAO;

    public List<Remark>getAllRemarkByForumId(int forumId){
        return remarkDAO.selectRemarkByTypeId(Remark.REMARK_TYPE_FORUM,forumId);
    }

    public void addRemark(String content,int entityType,int entityId,int userId)throws Exception{
            Remark remark = new Remark();
            remark.setUserId(userId);
            remark.setContent(content);
            remark.setEntityType(entityType);
            remark.setEntityId(entityId);
            remark.setCreateTime(new Date());
            remarkDAO.insertRemark(remark);
    }
}
