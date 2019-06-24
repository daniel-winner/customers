package com.crm.customers.controller;

import com.crm.customers.dao.CustomerInterviewDao;
import com.crm.customers.dao.ReturnObject;
import com.crm.customers.entity.CustomerInterview;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/crm/customer")
@Slf4j
public class CustomerController {


    @Autowired
    private CustomerInterviewDao customerInterviewDao;

    @RequestMapping("/interviewlist")
    @ResponseBody
    public Object getList(){
        ReturnObject returnObject = new ReturnObject();
        returnObject.setCode(0);
        log.info("=========================================");
        List<CustomerInterview> all = customerInterviewDao.findAllOrderByCreateTime(0, 10);
        all.forEach(CustomerInterview-> log.info(CustomerInterview.toString()));
        returnObject.setData(all);

        return returnObject;
    }

    public static List<String> getObject(){
        List<String> arrayList = new ArrayList<>();
        for (int i =0 ;i<10 ;i++){
            arrayList.add("value_"+i);
        }
        return arrayList;
    }
}
