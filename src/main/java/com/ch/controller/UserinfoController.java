package com.ch.controller;

import com.ch.service.UserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by apple on 2018/3/4.
 */
@Controller
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("home");
        return mv;
    }

    @RequestMapping(value = "/toLoginPage",method = RequestMethod.GET)
    public ModelAndView toLoginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(String username, String password) {
        ModelAndView mov = new ModelAndView();
        Map<String, Object> map = userinfoService.login(username, password);
        if (!map.containsKey("msg")) {
            mov.addObject("nickname", map.get("nickname"));
            mov.addObject("token", map.get("token"));
            mov.setViewName("home");
            return mov;
        } else {
            mov.setViewName("login");
            mov.addObject("msg", map.get("msg"));
            return mov;
        }
    }
}
