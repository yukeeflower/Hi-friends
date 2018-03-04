package com.ch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by apple on 2018/3/4.
 */
@Controller
@RequestMapping("/home")
public class HomeController {


    @RequestMapping(value = "/toLoginPage",method = RequestMethod.GET)
    public ModelAndView toLoginPage() {
        return new ModelAndView("login");
    }


}
