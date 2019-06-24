package com.crm.customers.entity;

import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import java.lang.String;
import java.lang.Integer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {
	@Column(name="fid")
	private Integer fid;
	@Column(name="department_name")
	private String departmentName;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="order_num")
	private Integer orderNum;
	@Column(name="status")
	private Integer status;

	public Integer getFid(){
		return fid;
	}
	public void setFid(Integer fid){
		this.fid = fid;
	}
	public String getDepartmentName(){
		return departmentName;
	}
	public void setDepartmentName(String departmentName){
		this.departmentName = departmentName;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getOrderNum(){
		return orderNum;
	}
	public void setOrderNum(Integer orderNum){
		this.orderNum = orderNum;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}

	@Override
	public String toString(){
		return "["
		+ "fid=" + getFid()
		+ ",departmentName=" + getDepartmentName()
		+ ",id=" + getId()
		+ ",orderNum=" + getOrderNum()
		+ ",status=" + getStatus()
		+ "]";
	}
}