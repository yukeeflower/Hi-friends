package com.ch.service;

import com.ch.dao.ForumDAO;
import com.ch.model.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2018/3/4.
 */
@Service
public class ForumService {
    @Autowired
    private ForumDAO forumDAO;

    public Map<String,Object> getLatestForums(){
        Map<String,Object>map = new HashMap<>();
        List<Forum> list = forumDAO.selectLatestForums();
        if (list.size()!=0){
            map.put("forumList",list);
        }else {
            map.put("msg","暂时没有帖子");
        }
        return map;
    }
}
