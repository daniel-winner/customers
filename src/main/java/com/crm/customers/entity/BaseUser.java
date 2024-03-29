package com.crm.customers.entity;

import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="base_user")
public class BaseUser {
	@Column(name="password")
	private String password;
	@Column(name="update_time")
	private Date updateTime;
	@Column(name="create_time")
	private Date createTime;
	@Column(name="phone_num")
	private String phoneNum;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="email")
	private String email;
	@Column(name="user_no")
	private Integer userNo;
	@Column(name="username")
	private String username;
	@Column(name="name")
	private String name;
	@Column(name="status")
	private Integer status;

	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public String getPhoneNum(){
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
	}
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public Integer getUserNo(){
		return userNo;
	}
	public void setUserNo(Integer userNo){
		this.userNo = userNo;
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BaseUser{" +
				"password='" + password + '\'' +
				", updateTime=" + updateTime +
				", createTime=" + createTime +
				", phoneNum='" + phoneNum + '\'' +
				", id=" + id +
				", email='" + email + '\'' +
				", userNo=" + userNo +
				", username='" + username + '\'' +
				", name='" + name + '\'' +
				", status=" + status +
				'}';
	}
}