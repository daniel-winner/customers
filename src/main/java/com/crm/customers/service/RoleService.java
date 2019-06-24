package com.crm.customers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.customers.entity.Role;

public interface RoleService {

	public Role findByUuid(String uuid);

	public Role findOne(long id);
	
	public Role findOne(Role entity);
	
	public Role findOne(Role entity, Pageable pageable);
	
	public void save(Role entity);
	
	public int updateNotNull(Role entity);
	
	public void delete(long id);
	
	public int deleteByUuid(String uuid);
	
	public Page<Role> findAll(Pageable pageable);
	
	public List<Role> findAll();
	
	public List<Role> findAll(Role entity);
	
	public Page<Map<String, Object>> findPage(Pageable pageable, Map<String, Object> parameter);
	
	public List<Role> findAll(Role entity, Pageable pageable);
	
	public Page<Role> findEntityPage(Role entity, Pageable pageable);

}
