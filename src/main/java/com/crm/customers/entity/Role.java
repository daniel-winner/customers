package com.crm.customers.entity;

import java.lang.String;
import java.lang.Integer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	@Id
	@Column(name="role_id")
	private Integer roleId;
	@Column(name="name")
	private String name;
	@Column(name="depart_id")
	private Integer departId;
	@Column(name="remake")
	private String remake;
	@Column(name="status")
	private Integer status;

	public Integer getRoleId(){
		return roleId;
	}
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public Integer getDepartId(){
		return departId;
	}
	public void setDepartId(Integer departId){
		this.departId = departId;
	}
	public String getRemake(){
		return remake;
	}
	public void setRemake(String remake){
		this.remake = remake;
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
		+ "roleId=" + getRoleId()
		+ ",name=" + getName()
		+ ",departId=" + getDepartId()
		+ ",remake=" + getRemake()
		+ ",status=" + getStatus()
		+ "]";
	}
}