package com.crm.customers.dao;

import com.crm.customers.entity.CustomerInterview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerInterviewDao extends JpaRepository<CustomerInterview,String> {

    @Query(value = "select * from customer_interview order by create_time desc limit :offset,:limit",nativeQuery = true)
    List<CustomerInterview> findLimitOrderByCreateTime(@Param("offset")Integer offerset,@Param("limit") Integer limit);

    @Query(value = "select * from customer_interview where cell_num =:cellNum order by create_time desc limit :offset,:limit",nativeQuery = true)
    List<CustomerInterview> findLimitOrderByCreateTime(@Param("cellNum")String cellNum,@Param("offset")Integer offerset,@Param("limit") Integer limit);

    int countByCalledNum(String cellNum);
}
