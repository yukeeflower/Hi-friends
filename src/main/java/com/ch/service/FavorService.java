package com.ch.service;

import com.ch.dao.FavorDAO;
import com.ch.dao.ForumDAO;
import com.ch.dao.UserinfoDAO;
import com.ch.model.Favor;
import com.ch.model.Forum;
import java.util.List;

import com.ch.model.ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by apple on 2018/3/25.
 */
@Service
public class FavorService {
    @Autowired
    private FavorDAO favorDAO;
    @Autowired
    private ForumDAO forumDAO;
    @Autowired
    private UserinfoDAO userinfoDAO;

    public int addFavor(int userId,int forumId){
        Favor favor = new Favor();
        favor.setUserId(userId);
        favor.setForumId(forumId);
        favor.setCreateTime(new Date());
        return favorDAO.insertFavor(favor);
    }

    public List<ViewObject> getMyFavors(int userId){
        List<ViewObject>list =  new ArrayList<>();
        List<Favor> favors = favorDAO.getFavorsByUserId(userId);
        for (Favor favor : favors){
            Forum forum = forumDAO.selectForumById(favor.getForumId());
            if (forum != null){
                ViewObject vo = new ViewObject();
                if (forum.getContent().length() > 101) {
                    forum.setContent(forum.getContent().substring(0, 100) + "...");
                }
                forum.setCreateTime(favor.getCreateTime());
                vo.set("forum", forum);
                System.out.println(forum.getCreateTime());
                vo.set("user",userinfoDAO.selectById(forum.getUserId()));
                list.add(vo);
            }
        }
        return list;
    }

    public boolean checkFavorIsExist(int forumId,int userId){
        Favor favor = favorDAO.getFavorByForumIdAndUserId(forumId,userId);
        if (favor != null){
            return true;
        }
        return false;

    }
}
