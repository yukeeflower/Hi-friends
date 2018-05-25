package com.ch.service;

import com.ch.dao.ForumDAO;
import com.ch.dao.UserinfoDAO;
import com.ch.model.Forum;
import com.ch.model.ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by apple on 2018/3/4.
 */
@Service
public class ForumService {
    @Autowired
    private ForumDAO forumDAO;
    @Autowired
    private UserinfoDAO userinfoDAO;
    public Map<String, Object> getLatestForums() {
        Map<String, Object> map = new HashMap<>();
        List<Forum> list = forumDAO.selectLatestForums();
        if (list.size() != 0) {
            map.put("forumList", list);
        } else {
            map.put("msg", "暂时没有帖子");
        }
        return map;
    }

    public Forum getForumDetail(int forumId) {
        return forumDAO.selectForumById(forumId);
    }

    public int addForum(int userId, String title, String content, String tags, String pics) throws Exception {
        Forum forum = new Forum();
        forum.setTitle(title);
        forum.setContent(content);
        forum.setUserId(userId);
        forum.setCreateTime(new Date());
        forum.setFavorNum(0);
        forum.setScanNum(0);
        forum.setRemarkNum(0);
        forum.setFavorNum(0);
        forum.setTags(tags);
        forum.setPictures(pics);
        return forumDAO.insertForum(forum);
    }

    public int updateForumPics(int forumId, String pics) throws Exception {
        return forumDAO.updateForumPics(pics, forumId);
    }

    public List<ViewObject> getForumsByUserId(int userId) {
        List<ViewObject> vos = new ArrayList<>();
        List<Forum> list = forumDAO.selectForumsByUserId(userId);
        for (Forum forum : list) {
            ViewObject vo = new ViewObject();
            if (forum.getContent().length() > 101) {
                forum.setContent(forum.getContent().substring(0, 100) + "...");
            }
            vo.set("forum", forum);
            vo.set("user",userinfoDAO.selectById(forum.getUserId()));
            vos.add(vo);
        }
        return vos;
    }

    public int addScanNum(int forumId){
        int num = forumDAO.selectForumById(forumId).getScanNum();
        num++;
        Forum forum = new Forum();
        forum.setId(forumId);
        forum.setScanNum(num);
        return forumDAO.updateForumSelective(forum);
    }

    public int addFavorNum(int forumId){
        int num = forumDAO.selectForumById(forumId).getFavorNum();
        num++;
        Forum forum = new Forum();
        forum.setId(forumId);
        forum.setFavorNum(num);
        return forumDAO.updateForumSelective(forum);
    }

    public int addRemarkNum(int forumId){
        int num = forumDAO.selectForumById(forumId).getRemarkNum();
        num++;
        Forum forum = new Forum();
        forum.setId(forumId);
        forum.setRemarkNum(num);
        return forumDAO.updateForumSelective(forum);
    }
}
