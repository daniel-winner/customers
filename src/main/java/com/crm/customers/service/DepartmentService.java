package com.crm.customers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.customers.entity.Department;

public interface DepartmentService {

	public Department findByUuid(String uuid);

	public Department findOne(long id);
	
	public Department findOne(Department entity);
	
	public Department findOne(Department entity, Pageable pageable);
	
	public void save(Department entity);
	
	public int updateNotNull(Department entity);
	
	public void delete(long id);
	
	public int deleteByUuid(String uuid);
	
	public Page<Department> findAll(Pageable pageable);
	
	public List<Department> findAll();
	
	public List<Department> findAll(Department entity);
	
	public Page<Map<String, Object>> findPage(Pageable pageable, Map<String, Object> parameter);
	
	public List<Department> findAll(Department entity, Pageable pageable);
	
	public Page<Department> findEntityPage(Department entity, Pageable pageable);

}
