package ##servicepackage##;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ##beanpackage##.##beanclass##;

public interface ##serviceclass## {

	public ##beanclass## findByUuid(String uuid);

	public ##beanclass## findOne(long id);
	
	public ##beanclass## findOne(##beanclass## entity);
	
	public ##beanclass## findOne(##beanclass## entity, Pageable pageable);
	
	public void save(##beanclass## entity);
	
	public int updateNotNull(##beanclass## entity);
	
	public void delete(long id);
	
	public int deleteByUuid(String uuid);
	
	public Page<##beanclass##> findAll(Pageable pageable);
	
	public List<##beanclass##> findAll();
	
	public List<##beanclass##> findAll(##beanclass## entity);
	
	public Page<Map<String, Object>> findPage(Pageable pageable,Map<String, Object> parameter);
	
	public List<##beanclass##> findAll(##beanclass## entity, Pageable pageable);
	
	public Page<##beanclass##> findEntityPage(##beanclass## entity, Pageable pageable);

}