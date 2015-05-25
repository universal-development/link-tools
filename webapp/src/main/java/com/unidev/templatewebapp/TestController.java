package com.unidev.templatewebapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @RequestMapping("/")
    public ModelAndView test() {

        return new ModelAndView("test");
    }

}
