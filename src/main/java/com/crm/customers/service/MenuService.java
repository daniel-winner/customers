package com.crm.customers.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.customers.entity.Menu;

public interface MenuService {

	public Menu findByUuid(String uuid);

	public Menu findOne(long id);
	
	public Menu findOne(Menu entity);
	
	public Menu findOne(Menu entity, Pageable pageable);
	
	public void save(Menu entity);
	
	public int updateNotNull(Menu entity);
	
	public void delete(long id);
	
	public int deleteByUuid(String uuid);
	
	public Page<Menu> findAll(Pageable pageable);
	
	public List<Menu> findAll();
	
	public List<Menu> findAll(Menu entity);
	
	public Page<Map<String, Object>> findPage(Pageable pageable, Map<String, Object> parameter);
	
	public List<Menu> findAll(Menu entity, Pageable pageable);
	
	public Page<Menu> findEntityPage(Menu entity, Pageable pageable);

}
