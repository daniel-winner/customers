package com.crm.customers.dao;

import com.crm.customers.entity.CustomerInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerInterviewDao extends JpaRepository<CustomerInterview,String> {

    @Query(value = "select * from customer_interview order by create_time limit :offset,:limit",nativeQuery = true)
    List<CustomerInterview> findAllOrderByCreateTime(@Param("offset")Integer offerset,@Param("limit") Integer limit);
}
