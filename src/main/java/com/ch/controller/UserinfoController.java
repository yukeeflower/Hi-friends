package com.ch.controller;

import com.ch.model.HostHolder;
import com.ch.model.Token;
import com.ch.model.Userinfo;
import com.ch.service.TokenService;
import com.ch.service.UserinfoService;
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
import java.util.Map;

/**
 * Created by apple on 2018/3/4.
 */
@Controller
@RequestMapping("user")
public class UserinfoController {
    private static final Logger logger = LoggerFactory.getLogger(UserinfoController.class);
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(value = "/{userId}",method = RequestMethod.GET)
    public ModelAndView toMyCenter(@PathVariable("userId")int userId,HttpServletRequest request){
        ModelAndView modelAndView;
        if (checkIsLogin(request)){
            Map<String,Object> map = userinfoService.getUserinfo(userId);
            if (!map.containsKey("msg")){
                modelAndView = new ModelAndView("center");
                modelAndView.addObject("user",(Userinfo)map.get("userinfo"));
                return modelAndView;
            }
        }
        modelAndView = new ModelAndView("home");
        modelAndView.addObject("msg","用户验证错误");
        return modelAndView;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(String username, String password, @RequestParam(value = "next",required = false)String next,HttpServletResponse response) {
        try {
            ModelAndView mov = new ModelAndView();
            Map<String, Object> map = userinfoService.login(username, password);
            if (map.containsKey("ticket")) {
                mov.addObject("userId", map.get("userId"));
                mov.addObject("nickname", map.get("nickname"));
                Cookie cookie = new Cookie("ticket",map.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
//                if (StringUtils.isNotBlank("next")){
//                    return "redirect:"+next;
//                }
                
                mov.setViewName("home");
                return mov;
            } else {
                mov.setViewName("login");
                mov.addObject("msg", map.get("msg"));
                return mov;
            }
        }
        catch (Exception e){
            logger.error("登录异常"+e.getMessage());
            return new ModelAndView("login");
        }
    }


    @RequestMapping(value = "/uploadPhoto",method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request,
                         @RequestParam("file")MultipartFile file,
                         @RequestParam("token")String token)throws Exception{
        if (!file.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("/WEB-INF/images/");
            Token token1 = tokenService.getTokenByTicket(token);
            String filename = token1.getUserId()+".png";
            userinfoService.updatePhoto(token1.getUserId(),"images/"+filename);
            File filePath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping(value = "{userId}/updateUserinfo",method = RequestMethod.POST)
    public ModelAndView updateUserinfo(@PathVariable("userId")int userId, String nickname,String email){
        ModelAndView mv = new ModelAndView("center");
        Userinfo userinfo = userinfoService.updateUserinfo(userId,nickname,email);
        if (userinfo != null){
            mv.addObject("user",userinfo);
        }else {
            mv.addObject("msg","更新用户失败");
        }
        return mv;
    }

    @RequestMapping()
    @ResponseBody
    public String checkOldPassword(String oldPass){
        return "";
    }

    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public ModelAndView updatePassword(int userId,String password){
        userinfoService.updatePassword(userId,password);
        ModelAndView mv = new ModelAndView("center");
        Userinfo userinfo = (Userinfo)userinfoService.getUserinfo(userId).get("userinfo");
        if (userinfo != null){
            mv.addObject("user",userinfo);
        }else {
            mv.addObject("msg","更新用户失败");
        }
        return mv;
    }


    private boolean checkIsLogin(HttpServletRequest request){
        String ticket = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("ticket")){
                ticket = cookie.getValue();
                break;
            }
        }
        if (!ticket.equals("")){
            return tokenService.checkIsExpired(ticket);
        }else {
            return false;
        }
    }
}
