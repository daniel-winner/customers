package com.crm.customers.dao;

import com.crm.customers.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department, Long> {

}
