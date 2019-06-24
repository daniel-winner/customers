package com.crm.customers.entity;

import java.util.List;

import org.springframework.data.domain.Page;

public class ReturnObject {
	
	private Long total;
	
	@SuppressWarnings("rawtypes")
	private List rows;
	
	private int code=0;
	
	private String msg;
	
	private Object obj;

	@SuppressWarnings("rawtypes")
	public void setPage(Page page){
		this.total=page.getTotalElements();
		this.rows=page.getContent();
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public void setRows(List rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "ReturnObject [total=" + total + ", rows=" + rows + ", code="
				+ code + ", msg=" + msg + ", obj=" + obj + "]";
	}
	
}
