package com.ch.controller;

import com.ch.model.*;
import com.ch.service.ForumService;
import com.ch.service.TokenService;
import com.ch.service.UserinfoService;
import com.ch.utils.HifriendsUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 2018/3/4.
 */
@Controller
@RequestMapping("/user")
public class UserinfoController {
    private static final Logger logger = LoggerFactory.getLogger(UserinfoController.class);
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private ForumService forumService;
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public String toMyCenter(@PathVariable("userId") int userId) {
        if (hostHolder.getUsers() != null && hostHolder.getUsers().getId() == userId) {
            return "center";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "/toCenterPage", method = RequestMethod.GET)
    public String toCenterPage(HttpServletRequest request) {
        return "redirect:/"+request.getContextPath()+"/user/" + hostHolder.getUsers().getId();
    }

    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
    public ModelAndView toMyHome(@PathVariable("userId") int userId) {
        if (hostHolder.getUsers() != null && hostHolder.getUsers().getId() == userId) {
            List<ViewObject>vos = forumService.getForumsByUserId(hostHolder.getUsers().getId());
            ModelAndView mv = new ModelAndView("myHome");
            mv.addObject("vos",vos);
            return mv;
        } else {
            return new ModelAndView("home");
        }
    }

    @RequestMapping(value = "/toMyHomePage", method = RequestMethod.GET)
    public String toMyHomePage(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:/"+request.getContextPath()+"/user/profile/" + hostHolder.getUsers().getId();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, String username, String password, @RequestParam(value = "next", required = false) String next, HttpServletResponse response,HttpServletRequest request) {
        try {
            Map<String, Object> map = userinfoService.login(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                if (StringUtils.isNotBlank(next)) {
                    if (next.contains("profile")) {
                        return "redirect:/"+request.getContextPath()+"/user/toMyHomePage";
                    } else if (!next.contains("profile") && next.contains("-1")) {
                        return "redirect:/"+request.getContextPath()+"/user/toCenterPage";
                    }
                    return "redirect:/"+request.getContextPath()+"/"+ next;
                }
                return "redirect:/";
            } else {
                model.addAttribute("msg", map.get("msg"));
                return "login";
            }

        } catch (Exception e) {
            logger.error("登陆异常" + e.getMessage());
            return "login";
        }
    }


    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (hostHolder.getUsers()!=null){
            String path = request.getSession().getServletContext().getRealPath("/WEB-INF/images/");
            String url = HifriendsUtil.saveImage(file,path,hostHolder.getUsers().getId()+".png","images",request.getContextPath());
            if (!url.equals("error")){
                userinfoService.updatePhoto(hostHolder.getUsers().getId(), url);
                return HifriendsUtil.getJSONString(200,url);
            }
            return HifriendsUtil.getJSONString(400,"服务器繁忙，请稍后再试");
        }
        return HifriendsUtil.getJSONString(500,"用户验证错误");
    }

    @RequestMapping(value = "{userId}/updateUserinfo", method = RequestMethod.POST)
    public ModelAndView updateUserinfo(@PathVariable("userId") int userId, String nickname, String email) {
        ModelAndView mv = new ModelAndView("center");
        Userinfo userinfo = userinfoService.updateUserinfo(userId, nickname, email);
        if (userinfo != null) {
            mv.addObject("user", userinfo);
        } else {
            mv.addObject("msg", "更新用户失败");
        }
        return mv;
    }

    @RequestMapping(value = "checkOldPass", method = RequestMethod.POST)
    @ResponseBody
    public String checkOldPassword(@RequestBody Map<String, Object> map) {
        String oldPass = "";
        if (map.containsKey("oldPass")) {
            oldPass = map.get("oldPass").toString();
        }
        if (hostHolder.getUsers() == null) {
            return HifriendsUtil.getJSONString(500, "用户验证错误");
        } else {
            if (userinfoService.checkOldPass(hostHolder.getUsers().getId(), oldPass)) {
                return HifriendsUtil.getJSONString(200, "原密码正确");
            }
        }
        return HifriendsUtil.getJSONString(400, "原密码错误");
    }

    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(@RequestBody Map<String, Object> map) {
        if (hostHolder.getUsers()==null){
            return  HifriendsUtil.getJSONString(500, "用户验证错误");
        }
        if (map.containsKey("oldPass") && map.containsKey("password")) {
            String oldPass = map.get("oldPass").toString();
            String password = map.get("password").toString();
            if (userinfoService.checkOldPass(hostHolder.getUsers().getId(), oldPass)) {
                if (userinfoService.updatePassword(hostHolder.getUsers().getId(), password) > 0) {
                    return HifriendsUtil.getJSONString(200, "更改成功");
                }
            }
            return  HifriendsUtil.getJSONString(400, "更改失败");
        }
        return  HifriendsUtil.getJSONString(401, "参数错误");
    }


//    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//    public ModelAndView updatePassword(String oldPass,String password) {
//        ModelAndView mv = new ModelAndView("center");
//        if (userinfoService.checkOldPass(hostHolder.getUsers().getId(),oldPass)){
//            if (userinfoService.updatePassword(hostHolder.getUsers().getId(), password)>0){
//                return mv;
//            }
//        }
//        return mv;
//    }


//    private boolean checkIsLogin(HttpServletRequest request) {
//        String ticket = "";
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("ticket")) {
//                ticket = cookie.getValue();
//                break;
//            }
//        }
//        if (!ticket.equals("")) {
//            return tokenService.checkIsExpired(ticket);
//        } else {
//            return false;
//        }
//    }
}
