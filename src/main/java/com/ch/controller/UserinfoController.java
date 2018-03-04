package com.ch.controller;

import com.ch.model.Token;
import com.ch.service.TokenService;
import com.ch.service.UserinfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/user")
public class UserinfoController {
    private static final Logger logger = LoggerFactory.getLogger(UserinfoController.class);
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(String username, String password, @RequestParam(value = "next",required = false)String next,HttpServletResponse response) {
        try {
            ModelAndView mov = new ModelAndView();
            Map<String, Object> map = userinfoService.login(username, password);
            if (map.containsKey("ticket")) {
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

}
