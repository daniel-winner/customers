package com.crm.customers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.customers.entity.BaseUser;

public interface BaseUserService {

	public BaseUser findByUuid(String uuid);

	public BaseUser findOne(long id);
	
	public BaseUser findOne(BaseUser entity);
	
//	public BaseUser findOne(BaseUser entity, Pageable pageable);
	
	public void save(BaseUser entity);
	
	public int updateNotNull(BaseUser entity);
	
	public void delete(long id);
	
	public int deleteByUuid(String uuid);
	
	public Page<BaseUser> findAll(Pageable pageable);
	
	public List<BaseUser> findAll();
	
	public List<BaseUser> findAll(BaseUser entity);
	
	public Page<Map<String, Object>> findPage(Pageable pageable, Map<String, Object> parameter);
	
	public List<BaseUser> findAll(BaseUser entity, Pageable pageable);
	
	public Page<BaseUser> findEntityPage(BaseUser entity, Pageable pageable);

	public void addUserErrorCountByUsername(String username);
}
