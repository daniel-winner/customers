package com.crm.customers.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 基类
 * @author Fans99
 * @since 2017.2.16
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>{
	
	public T findByUuid(String uuid);
	
	public int deleteByUuid(String uuid);
	
	/** 更新非空字段 */
	public int updateNotNull(T entity);
	
	public T findOne(T entity);

	/** 可按字段排序 */
	public T findOne(T entity, Pageable pageable);
	
	public List<T> findAll(T entity);
	
	public List<T> findAll(T entity, Pageable pageable);
	
	public Page<T> findEntityPage(T entity, Pageable pageable);
	
}
