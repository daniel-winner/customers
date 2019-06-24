package com.crm.customers.dao;

import com.crm.customers.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseUserDao extends JpaRepository<BaseUser, Long> {

    BaseUser findBaseUserByUsername (String username);

}
