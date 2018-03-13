package com.ch.controller;

import com.ch.model.*;
import com.ch.service.ForumService;
import com.ch.service.RemarkService;
import com.ch.service.UserinfoService;
import com.ch.utils.HifriendsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by apple on 2018/3/4.
 */
@Controller
@RequestMapping(value = "/forum")
public class ForumController {

    private static final Logger logger = LoggerFactory.getLogger(ForumController.class);

    @Autowired
    private ForumService forumService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private RemarkService remarkService;
    @Autowired
    private HostHolder hostHolder;
//


//    @RequestMapping(value = "/listForums")
//    public ModelAndView listForums(){
//        ModelAndView mv = new ModelAndView("home");
//        Map<String,Object>map = forumService.getLatestForums();
//        if (!map.containsKey("msg")){
//            mv.addObject("forumList",map.get("list"));
//        }
//        return mv;
//    }

    @RequestMapping(value = "/toAddForumPage", method = RequestMethod.GET)
    public ModelAndView toAddForumPage() {
        ModelAndView mv = new ModelAndView("write");
        return mv;
    }

    @RequestMapping(value = "/{forumId}", method = RequestMethod.GET)
    public ModelAndView getForumDetail(@PathVariable("forumId") int forumId) {
        ViewObject vo = new ViewObject();
        Forum forum = forumService.getForumDetail(forumId);
        System.out.println(forum.getPictures());
        if (forum.getPictures()!=null||!forum.getPictures().equals("")){
            String[] pics = forum.getPictures().split(";");
            String img ="";
            for (int i = 0;i<pics.length;i++){
                img +=  "<img  src=\"  " + pics[i]+ " \"><br/>";
            }
            forum.setContent(forum.getContent()+img);
        }
        Userinfo user = (Userinfo) userinfoService.getUserinfo(forum.getUserId()).get("userinfo");
        List<Remark> remarkList = remarkService.getAllRemarkByForumId(forumId);
        List<ViewObject> remarks = new ArrayList<>();
        for (Remark remark : remarkList) {
            ViewObject remarkVo = new ViewObject();
            Userinfo userinfo = (Userinfo) userinfoService.getUserinfo(remark.getUserId()).get("userinfo");
            remarkVo.set("user", userinfo);
            remarkVo.set("remark", remark);
            remarks.add(remarkVo);
        }
        vo.set("forum", forum);
        vo.set("user", user);
        ModelAndView mv = new ModelAndView("article");
        mv.addObject("vo", vo);
        mv.addObject("remarks", remarks);
        return mv;
    }

    @RequestMapping(value = "/addForum", method = RequestMethod.POST)
    @ResponseBody
    public String addForum( String title, String content, String tags,String pics) {
        try {
            if (hostHolder.getUsers() !=null){
                if (forumService.addForum(hostHolder.getUsers().getId(), title, content, tags,pics) > 0) {
                    return HifriendsUtil.getJSONString(200,"success");
                }
            }
        } catch (Exception e) {
            logger.error("发帖失败" + e.getMessage());
            e.printStackTrace();
        }
        return HifriendsUtil.getJSONString(500,"error");
    }

    @RequestMapping(value = "uploadForumPics",method = RequestMethod.POST)
    @ResponseBody
    public String uploadForumPics(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        if (hostHolder.getUsers()!=null){
            String path = request.getSession().getServletContext().getRealPath("/WEB-INF/forumpics/");
            String url = HifriendsUtil.saveImage(file,path, UUID.randomUUID().toString().substring(0,9)+".png","forumpics",request.getContextPath());
            if (!url.equals("error")){
//                try {
//                    forumService.updateForumPics(forumId,url+";");
//                }catch (Exception e){
//                    logger.error("上传图片失败"+e.getMessage());
//                    e.printStackTrace();
//                }
                return HifriendsUtil.getJSONString(200,url);
            }
            return HifriendsUtil.getJSONString(400,"服务器繁忙，请稍后再试");
        }
        return HifriendsUtil.getJSONString(500,"用户验证错误");
    }
}
