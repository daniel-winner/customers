package com.crm.customers.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.type.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 原生sql分页支持，普通版
 * @author Fans99
 * @since 2017.1.23
 * @version 1.0
 */
public interface MapDao{
	
	/**
	 * 返回long形值
	 * @param sql
	 * @param param
	 * @return
	 */
	public long findForLong(String sql, Map<String, Object> param);
	
	/**
	 * 执行sql，并返回影响的行数
	 * @param sql
	 * @param param
	 * @return
	 */
	public int executeSql(String sql, Map<String, Object> param);
	
	//无参数
	/**
	 * @param sql
	 * @param countSql
	 * @param clazz
	 * @param pageable 分页参数
	 * @param scalar 需要显示的字段已经类型，为空是全部显示，次参数用于默写特殊情况，比如id类型不符
	 * @return
	 */
	public Page<Map<String, Object>> findMapPage(String sql, Pageable pageable);

	public List<Map<String, Object>> findMapList(String sql);
	
	public Page<Map<String, Object>> findMapPage(String sql, String countSql, Pageable pageable);
	
	public List<Map<String, Object>> findMapList(String sql, String countSql);
	
	/**
	 * @param sql
	 * @param countSql
	 * @param clazz
	 * @param pageable 分页参数
	 * @param scalar 需要显示的字段已经类型，为空是全部显示，次参数用于默写特殊情况，比如id类型不符
	 * @return
	 */
	public Page<Map<String, Object>> findLinkedMapPage(String sql, Pageable pageable);
	
	public List<Map<String, Object>> findLinkedMapList(String sql);
	
	public Page<Map<String, Object>> findLinkedMapPage(String sql, String countSql, Pageable pageable);
	
	public List<Map<String, Object>> findLinkedMapList(String sql, String countSql);
	
	/**
	 * @param sql
	 * @param countSql
	 * @param clazz
	 * @param pageable 分页参数
	 * @param scalar 需要显示的字段已经类型，为空是全部显示，次参数用于默写特殊情况，比如id类型不符
	 * @return
	 */
	public Page<?> findBeanPage(String sql, Class<?> clazz, Pageable pageable);
	
	public List<?> findBeanList(String sql, Class<?> clazz);
	
	public Page<?> findBeanPage(String sql, String countSql, Class<?> clazz, Pageable pageable);
	
	public List<?> findBeanList(String sql, String countSql, Class<?> clazz);
	
	public Page<?> findBeanPage(String sql, Class<?> clazz, Pageable pageable, Map<String, Type> scalar);
	
	public List<?> findBeanList(String sql, Class<?> clazz, Map<String, Type> scalar);
	
	public Page<?> findBeanPage(String sql, String countSql, Class<?> clazz, Pageable pageable, Map<String, Type> scalar);
	
	public List<?> findBeanList(String sql, String countSql, Class<?> clazz, Map<String, Type> scalar);
	
	
	
	
	
	
	
	//list参数
	/**
	 * @param sql
	 * @param countSql
	 * @param param 参数，list类型
	 * @param clazz
	 * @param pageable 分页参数
	 * @param scalar 需要显示的字段已经类型，为空是全部显示，次参数用于默写特殊情况，比如id类型不符
	 * @return
	 */
	public Page<Map<String, Object>> findMapPage(String sql, List<Object> param, Pageable pageable);
	
	public List<Map<String, Object>> findMapList(String sql, List<Object> param);
	
	public Page<Map<String, Object>> findMapPage(String sql, String countSql, List<Object> param, Pageable pageable);
	
	public List<Map<String, Object>> findMapList(String sql, String countSql, List<Object> param);
	
	/**
	 * @param sql
	 * @param countSql
	 * @param param 参数，list类型
	 * @param clazz
	 * @param pageable 分页参数
	 * @param scalar 需要显示的字段已经类型，为空是全部显示，次参数用于默写特殊情况，比如id类型不符
	 * @return
	 */
	public Page<Map<String, Object>> findLinkedMapPage(String sql, List<Object> param, Pageable pageable);
	
	public List<Map<String, Object>> findLinkedMapList(String sql, List<Object> param);
	
	public Page<Map<String, Object>> findLinkedMapPage(String sql, String countSql, List<Object> param, Pageable pageable);
	
	public List<Map<String, Object>> findLinkedMapList(String sql, String countSql, List<Object> param);
	
	/**
	 * @param sql
	 * @param countSql
	 * @param param 参数，list类型
	 * @param clazz
	 * @param pageable 分页参数
	 * @param scalar 需要显示的字段已经类型，为空是全部显示，次参数用于默写特殊情况，比如id类型不符
	 * @return
	 */
	public Page<?> findBeanPage(String sql, List<Object> param, Class<?> clazz, Pageable pageable);
	
	public List<?> findBeanList(String sql, List<Object> param, Class<?> clazz);
	
	public Page<?> findBeanPage(String sql, String countSql, List<Object> param, Class<?> clazz, Pageable pageable);
	
	public List<?> findBeanList(String sql, String countSql, List<Object> param, Class<?> clazz);
	
	public Page<?> findBeanPage(String sql, List<Object> param, Class<?> clazz, Pageable pageable, Map<String, Type> scalar);
	
	public List<?> findBeanList(String sql, List<Object> param, Class<?> clazz, Map<String, Type> scalar);
	
	public Page<?> findBeanPage(String sql, String countSql, List<Object> param, Class<?> clazz, Pageable pageable, Map<String, Type> scalar);
	
	public List<?> findBeanList(String sql, String countSql, List<Object> param, Class<?> clazz, Map<String, Type> scalar);
	
	
	
	
	
	
	
	//map参数
	/**
	 * @param sql
	 * @param countSql
	 * @param param 参数，map类型
	 * @param clazz
	 * @param pageable 分页参数
	 * @param scalar 需要显示的字段已经类型，为空是全部显示，次参数用于默写特殊情况，比如id类型不符
	 * @return
	 */
	public Page<Map<String, Object>> findMapPage(String sql, Map<String, Object> param, Pageable pageable);
	
	public List<Map<String, Object>> findMapList(String sql, Map<String, Object> param);
	
	public Page<Map<String, Object>> findMapPage(String sql, String countSql, Map<String, Object> param, Pageable pageable);
	
	public List<Map<String, Object>> findMapList(String sql, String countSql, Map<String, Object> param);
	
	/**
	 * @param sql
	 * @param countSql
	 * @param param 参数，map类型
	 * @param clazz
	 * @param pageable 分页参数
	 * @param scalar 需要显示的字段已经类型，为空是全部显示，次参数用于默写特殊情况，比如id类型不符
	 * @return
	 */
	public Page<Map<String, Object>> findLinkedMapPage(String sql, Map<String, Object> param, Pageable pageable);
	
	public List<Map<String, Object>> findLinkedMapList(String sql, Map<String, Object> param);
	
	public Page<Map<String, Object>> findLinkedMapPage(String sql, String countSql, Map<String, Object> param, Pageable pageable);
	
	public List<Map<String, Object>> findLinkedMapList(String sql, String countSql, Map<String, Object> param);
	
	/**
	 * @param sql
	 * @param countSql
	 * @param param 参数，map类型
	 * @param clazz
	 * @param pageable 分页参数
	 * @param scalar 需要显示的字段已经类型，为空是全部显示，次参数用于默写特殊情况，比如id类型不符
	 * @return
	 */
	public Page<?> findBeanPage(String sql, Map<String, Object> param, Class<?> clazz, Pageable pageable);
	
	public List<?> findBeanList(String sql, Map<String, Object> param, Class<?> clazz);
	
	public Page<?> findBeanPage(String sql, String countSql, Map<String, Object> param, Class<?> clazz, Pageable pageable);
	
	public List<?> findBeanList(String sql, String countSql, Map<String, Object> param, Class<?> clazz);
	
	public Page<?> findBeanPage(String sql, Map<String, Object> param, Class<?> clazz, Pageable pageable, Map<String, Type> scalar);
	
	public List<?> findBeanList(String sql, Map<String, Object> param, Class<?> clazz, Map<String, Type> scalar);
	
	public Page<?> findBeanPage(String sql, String countSql, Map<String, Object> param, Class<?> clazz, Pageable pageable, Map<String, Type> scalar);
	
	public List<?> findBeanList(String sql, String countSql, Map<String, Object> param, Class<?> clazz, Map<String, Type> scalar);
	
}
