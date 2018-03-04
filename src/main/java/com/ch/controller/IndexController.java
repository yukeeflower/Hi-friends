package com.ch.controller;

import com.ch.model.Forum;
import com.ch.model.Userinfo;
import com.ch.model.ViewObject;
import com.ch.service.ForumService;
import com.ch.service.UserinfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2018/3/4.
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private ForumService forumService;
    @Autowired
    private UserinfoService userinfoService;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public ModelAndView index() {
        try {
            List<ViewObject> vos = new ArrayList<>();
            ModelAndView mv = new ModelAndView("home");
            Map<String, Object> map = forumService.getLatestForums();
            if (!map.containsKey("msg")) {
                List<Forum> list = (List<Forum>) map.get("forumList");
                for (Forum forum : list) {
                    ViewObject vo = new ViewObject();
                    forum.setContent(forum.getContent().substring(0, 100) + "...");
                    vo.set("forum", forum);
                    Map<String, Object> userMap = userinfoService.getUserinfo(forum.getUserId());
                    if (!userMap.containsKey("msg")) {
                        vo.set("user", userMap.get("userinfo"));
                    } else {
                        vo.set("user", "匿名用户");
                    }
                    vos.add(vo);
                }
                mv.addObject("vos", vos);
            } else {
                mv.addObject("msg", map.get("msg"));
            }
            return mv;
        }
        catch (Exception e){
            logger.error("进入首页异常"+e.getMessage());
            return new ModelAndView("home");
        }

    }

//    @RequestMapping(value = "/listForums")
//    public ModelAndView listForums(){
//        ModelAndView mv = new ModelAndView("home");
//        Map<String,Object> map = forumService.getLatestForums();
//        if (!map.containsKey("msg")){
//            mv.addObject("forumList",map.get("list"));
//        }
//        return mv;
//    }

}
