package ##servicepackage##.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shangyong.cs.dao.MapDao;
import com.shangyong.cs.service.BaseService;
import ##beanpackage##.##beanclass##;
import ##daopackage##.##daoclass##;
import ##servicepackage##.##serviceclass##;

@Service
public class ##serviceclass##Impl extends BaseService implements ##serviceclass## {

	private final static Logger log = LoggerFactory.getLogger(##serviceclass##Impl.class);

	@Resource
	private ##daoclass## ##daoclassinstance##;

	@Resource
	private MapDao mapDao;

	@Override
	public ##beanclass## findByUuid(String uuid) {
		return ##daoclassinstance##.findByUuid(uuid);
	}

	@Override
	public ##beanclass## findOne(long id) {
		return ##daoclassinstance##.findOne(id);
	}
	
	@Override
	public ##beanclass## findOne(##beanclass## entity) {
		return ##daoclassinstance##.findOne(entity);
	}

	@Override
	public ##beanclass## findOne(##beanclass## entity, Pageable pageable) { 
		return ##daoclassinstance##.findOne(entity, pageable);
	}

	@Override
	public void save(##beanclass## entity) {
		##daoclassinstance##.save(entity);
	}

	@Override
	public void delete(long id) {
		##daoclassinstance##.delete(id);
	}
	
	@Override
	public int updateNotNull(##beanclass## entity) {
		return ##daoclassinstance##.updateNotNull(entity);
	}

	@Override
	public int deleteByUuid(String uuid) {
		return ##daoclassinstance##.deleteByUuid(uuid);
	}

	@Override
	public List<##beanclass##> findAll() {		
		Iterator<##beanclass##> iter=##daoclassinstance##.findAll().iterator();
		List<##beanclass##> ls=new ArrayList<##beanclass##>();
		while(iter.hasNext()){
			ls.add(iter.next());
		}
		return ls.size()==0?null:ls;
	}

	@Override
	public Page<##beanclass##> findAll(Pageable pageable) {
		return ##daoclassinstance##.findAll(pageable);
	}
	
	@Override
	public List<##beanclass##> findAll(##beanclass## entity) {
		return ##daoclassinstance##.findAll(entity);
	}
	
	@Override
	public List<##beanclass##> findAll(##beanclass## entity, Pageable pageable) {
		return ##daoclassinstance##.findAll(entity, pageable);
	}

	@Override
	public Page<##beanclass##> findEntityPage(##beanclass## entity,
			Pageable pageable) {
		return ##daoclassinstance##.findEntityPage(entity, pageable);
	}

	@Override
	public Page<Map<String, Object>> findPage(Pageable pageable,
			Map<String, Object> parameter) {
		StringBuilder sb=new StringBuilder("select t.* from ##tablename## t where 1=1 ");
		Map<String, Object> params=new HashMap<String, Object>();
		if(parameter!=null){
			if(!StringUtils.isEmpty(parameter.get("type"))){
				sb.append(" and t.type=:type ");
				params.put("type", parameter.get("type"));
			}
			if(!StringUtils.isEmpty(parameter.get("name"))){
				sb.append(" and t.name like concat('%',:name,'%') ");
				params.put("name", parameter.get("name"));
			}
		}
		return mapDao.findMapPage(sb.toString(), params, pageable);
	}
	
}
