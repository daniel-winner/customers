package com.crm.customers.controller;

import com.alibaba.fastjson.JSONObject;
import com.crm.customers.dao.CustomerInterviewDao;
import com.crm.customers.dao.ReturnObject;
import com.crm.customers.entity.BaseUser;
import com.crm.customers.entity.CustomerInterview;
import com.crm.customers.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        String page = getParameter("page");
        String limit = getParameter("limit");
        log.info(page+":"+limit);
        JSONObject jsonObject = new JSONObject();
        List<CustomerInterview> all = customerInterviewDao.findAllOrderByCreateTime( Integer.parseInt(page), Integer.parseInt(limit));
        jsonObject.put("status",0);
        jsonObject.put("message","返回数据");
        jsonObject.put("total",customerInterviewDao.count());
        jsonObject.put("data",all);

        return jsonObject.toString();
    }
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public ReturnObject save(CustomerInterview customer) {
        ReturnObject returnObject = new ReturnObject();

        if(customer!=null){
            try {
                if(StringUtils.isNotBlank(customer.getCustomerInterviewId())){
                    Optional<CustomerInterview> customerInterview = customerInterviewDao.findById(customer.getCustomerInterviewId());
//                    CustomerInterview interview = customerInterview.isPresent() ? customerInterview.get() : null;
                    if (customerInterview.isPresent()){
                        returnObject.setCode(0);
                        returnObject.setMsg("数据已存在，请重试");
                        return returnObject;
                    }
                }else {
                    BaseUser session = (BaseUser)getSession(USER_SESSION);
                    customer.setCustomerInterviewId(UUIDUtils.getUUID());
                    customer.setUserId(session.getId());
                    customer.setCreateTime(new Date());
                    customer.setUsername(session.getUsername());
                    customerInterviewDao.save(customer);
                    returnObject.setCode(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return returnObject;
    }


}
