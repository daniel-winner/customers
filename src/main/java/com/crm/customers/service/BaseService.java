package com.crm.customers.service;

import java.util.Map;

import javax.annotation.Resource;

import com.crm.customers.entity.ReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.Assert;


public abstract class BaseService {

	private final static Logger log = LoggerFactory.getLogger(BaseService.class); 
	
	/** 编程事务模板 */
	@Resource
	protected TransactionTemplate transactionTemplate;

	protected ReturnObject commonProcess(final String processBizName, final Map<String, Object> parameter, final ProcessInvokeService processInvokeService){
		log.info("进入 {} commonProcess processBizName={} parameter={} ", this.getClass().getName(),processBizName,parameter);
		ReturnObject ro=new ReturnObject();
		ro=transactionTemplate.execute(new TransactionCallback<ReturnObject>() {
			@Override
			public ReturnObject doInTransaction(TransactionStatus status) {
				ReturnObject ro=new ReturnObject();
				try{
					Assert.notNull(processInvokeService, "processInvokeService为空，报异常了");
					
					processInvokeService.process(parameter);
					
					//ro.setObj("这里是事务执行正常了！！！");
				}catch(Exception e){
					log.error(e.getLocalizedMessage(), e);
					status.setRollbackOnly();
					//result.setYrdResultEnum(YrdResultEnum.DATABASE_EXCEPTION);
					ro.setMsg(e.getLocalizedMessage());
					ro.setCode(-1);
				}
				return ro;
			}
		});
		return ro;
	}

}
