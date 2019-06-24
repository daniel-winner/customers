package com.crm.customers.beancreater.code;

public class BeanProperty {
	private String name;
	private String getMethod;
	private String setMethod;
	private String classname;
	
	
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGetMethod() {
		return getMethod;
	}
	public void setGetMethod(String getMethod) {
		this.getMethod = getMethod;
	}
	public String getSetMethod() {
		return setMethod;
	}
	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}
	
	public static BeanProperty toBeanProperty(String name, String classname){
		BeanProperty beanProperty = new BeanProperty();
		final String TYPE = "java.lang.Boolean";
		final String TIMESTAMP_TYPE="java.sql.Timestamp";
		beanProperty.setName(name);
		String beanName=ClassCreator1.underline2upper(name);
		beanProperty.setClassname(classname);
		if(classname.equals(TIMESTAMP_TYPE)){
			beanProperty.setClassname("java.util.Date");
		}
		if (classname.equals(TYPE)){
			//beanProperty.setGetMethod("is" + name.substring(0, 1).toUpperCase() + name.substring(1));
			beanProperty.setGetMethod("is" + beanName.substring(0, 1).toUpperCase() + beanName.substring(1));
		}else {
			//beanProperty.setGetMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
			beanProperty.setGetMethod("get" + beanName.substring(0, 1).toUpperCase() + beanName.substring(1));
		}
		//beanProperty.setSetMethod("set"  + name.substring(0, 1).toUpperCase() + name.substring(1));
		beanProperty.setSetMethod("set"  + beanName.substring(0, 1).toUpperCase() + beanName.substring(1));
		return beanProperty;
	}
	public static void main(String[] args){
		BeanProperty beanProperty = toBeanProperty("projectid", "java.lang.Boolean");
		System.out.println(beanProperty.getGetMethod());
	}
}
