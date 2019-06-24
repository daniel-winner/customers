package com.crm.customers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class IndexController {

    @RequestMapping("index")
    public ModelAndView toIndex(){
        ModelAndView view = new ModelAndView();
        view.setViewName("static/index");
        return view;
    }
}
