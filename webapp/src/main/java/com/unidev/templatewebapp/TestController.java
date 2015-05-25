package com.unidev.templatewebapp;

import com.unidev.templatecore.Core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @Autowired
    private Core core;

    @RequestMapping("/")
    public ModelAndView test() {

        core.invokeMe();

        return new ModelAndView("test");
    }

}
