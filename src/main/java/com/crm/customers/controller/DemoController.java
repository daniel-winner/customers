package com.crm.customers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @RequestMapping("hello")
    public String hello(){
        return  "hello";
    }

    @RequestMapping("admin")
    public String admin(){
        return  "base/admin";
    }
}
