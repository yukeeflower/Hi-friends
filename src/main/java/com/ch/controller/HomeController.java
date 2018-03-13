package com.ch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by apple on 2018/3/4.
 */
@Controller
@RequestMapping("/home")
public class HomeController {


    @RequestMapping(value = "/toLoginPage",method = RequestMethod.GET)
    public String toLoginPage(Model model, @RequestParam(value = "next",required = false)String next) {
        model.addAttribute("next",next);
        return "login";
    }


    @RequestMapping(value = "/toMyHome",method = RequestMethod.GET)
    public String toMyHome(){
        return "redirect:/";
    }

}
