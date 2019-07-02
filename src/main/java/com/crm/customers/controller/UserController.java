package com.crm.customers.controller;

import com.crm.customers.entity.BaseUser;
import com.crm.customers.entity.ReturnObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

    @RequestMapping("/userBean")
    @ResponseBody
    public ReturnObject userMassage(){
        ReturnObject returnObject = new ReturnObject();

        BaseUser user = (BaseUser)getSession(USER_SESSION);
        BaseUser session = new BaseUser();
        if(session!=null){
            session.setName(user.getName());
            session.setUsername(user.getUsername());
            session.setEmail(user.getEmail());
            session.setPhoneNum(user.getPhoneNum());
        }
        returnObject.setObj(session);
        returnObject.setCode(SUCCESS);
        return returnObject;

    }
}
