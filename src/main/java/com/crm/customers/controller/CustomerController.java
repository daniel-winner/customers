package com.crm.customers.controller;

import com.alibaba.fastjson.JSONObject;
import com.crm.customers.dao.CustomerInterviewDao;
import com.crm.customers.dao.ReturnObject;
import com.crm.customers.entity.CustomerInterview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Author dxf
 * 公海库 客户回访
 */
@Controller
@RequestMapping("/crm/customer")
@Slf4j
public class CustomerController extends BaseController{


    @Autowired
    private CustomerInterviewDao customerInterviewDao;

    @RequestMapping("/interviewlist")
    @ResponseBody
    public String getList(){
        Enumeration<String> names = getRequest().getParameterNames();
        log.info(names.toString());
        String page = getParameter("page");
        String limit = getParameter("limit");
        ReturnObject returnObject = new ReturnObject();
        JSONObject jsonObject = new JSONObject();

        returnObject.setCode(0);
        returnObject.setMsg("1111");
        List<CustomerInterview> all = customerInterviewDao.findAllOrderByCreateTime(0, Integer.parseInt(limit));
        returnObject.setCount(all.size());
        jsonObject.put("status",0);
        jsonObject.put("message","返回数据");
        jsonObject.put("total",all.size());
        jsonObject.put("data",all);

        return jsonObject.toString();
    }


}
