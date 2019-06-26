package com.crm.customers.controller;

import com.alibaba.fastjson.JSONObject;
import com.crm.customers.dao.BaseUserDao;
import com.crm.customers.dao.ReturnObject;
import com.crm.customers.entity.BaseUser;
import com.crm.customers.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private BaseUserDao baseUserDao;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginIn() {
        return "base/login";
    }
    @RequestMapping(value = "login_err", method = RequestMethod.GET)
    public String loginerr() {
        return "base/login_err";
    }

    @RequestMapping(value = "loginIn", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletResponse response) throws IOException {
//        log.info("username:{},password:{}",user.getUsername(),user.getPassword());
        ReturnObject returnObject = new ReturnObject();
        String username = getParameter("username");
        String password = getParameter("password");
        if (StringUtils.isNotBlank(username) || StringUtils.isNotBlank(password)) {
            String code = MD5Util.getMD5Code(username + password);
            BaseUser user = baseUserDao.findBaseUserByUsername(username);
            if (user != null) {
                if (code.equals(user.getPassword())) {
                    returnObject.setCode(200);
                    returnObject.setMsg("登录成功");
                    response.sendRedirect("admin");
//                    setSession(UUID.randomUUID().toString(),"");
                } else {
                    returnObject.setCode(500);
                    returnObject.setMsg("密码校验失败，请重试");
                    response.sendRedirect("login_err");
                }

            } else {
                returnObject.setCode(500);
                returnObject.setMsg("未查询到登录用户");
                response.sendRedirect("login_err");
            }
        }else{
            returnObject.setCode(500);
            returnObject.setMsg("登录账号或手机号为空");
            response.sendRedirect("login_err");
        }


        return returnObject;
    }
}
