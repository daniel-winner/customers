package com.crm.customers.entity;

import java.lang.String;
import java.lang.Integer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu")
public class Menu {
	@Column(name="menu_name")
	private String menuName;
	@Column(name="menu_url")
	private String menuUrl;
	@Column(name="icon")
	private String icon;
	@Column(name="order_num")
	private Integer orderNum;
	@Id
	@Column(name="menu_id")
	private Integer menuId;
	@Column(name="status")
	private Integer status;

	public String getMenuName(){
		return menuName;
	}
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}
	public String getMenuUrl(){
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl){
		this.menuUrl = menuUrl;
	}
	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public Integer getOrderNum(){
		return orderNum;
	}
	public void setOrderNum(Integer orderNum){
		this.orderNum = orderNum;
	}
	public Integer getMenuId(){
		return menuId;
	}
	public void setMenuId(Integer menuId){
		this.menuId = menuId;
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
		+ "menuName=" + getMenuName()
		+ ",menuUrl=" + getMenuUrl()
		+ ",icon=" + getIcon()
		+ ",orderNum=" + getOrderNum()
		+ ",menuId=" + getMenuId()
		+ ",status=" + getStatus()
		+ "]";
	}
}