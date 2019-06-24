package com.crm.customers.dao;

import com.crm.customers.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDao extends JpaRepository<Menu, Long> {

}
