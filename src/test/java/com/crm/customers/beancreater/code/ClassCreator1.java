package com.crm.customers.beancreater.code;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;



public class ClassCreator1{
	public static final String LINESEPARATOR = System.getProperty("line.separator");
	public static final String DAOPACKAGE = "##daopackage##";;
	public static final String DAOCLASS = "##daoclass##";
	public static final String DAOCLASSINSTANCE = "##daoclassinstance##";
	public static final String BEANPACKAGE = "##beanpackage##";
	public static final String BEANCLASS = "##beanclass##";
	public static final String SERVICEPACKAGE = "##servicepackage##";
	public static final String SERVICECLASS = "##serviceclass##";

	public static final String TABLENAME = "##tablename##";

	private String classname;
	private String tablename;
	private String packagename;
	//private String id;
	
	public ClassCreator1(TableBean tableBean){
		this.packagename = tableBean.getPackagename();
		this.classname = tableBean.getClassname();
		//this.id = tableBean.getId();
		this.tablename = tableBean.getTablename();
		if (StringUtils.isEmpty(this.classname)) this.classname = tablename.substring(0, 1).toUpperCase() + tablename.substring(1);
	}
	
	private Map<String, BeanProperty> map = new HashMap<String, BeanProperty>();
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public void addProperty(String name, String classname) {
		BeanProperty beanProperty = BeanProperty.toBeanProperty(name, classname);
		map.put(name, beanProperty);
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public int execute(Connection conn) throws SQLException {
		DatabaseMetaData dbmd=conn.getMetaData();
	    ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[] { "TABLE" });
	    boolean flag=false;
	    while (resultSet.next()) {
	    	String tableName=resultSet.getString("TABLE_NAME");
	    	if(tableName.equals(tablename)){
	    		flag=true;
	    		//ResultSet rs =getConnection.getMetaData().getColumns(null, getXMLConfig.getSchema(),tableName.toUpperCase(), "%");//其他数据库不需要这个方法的，直接传null，这个是oracle和db2这么用
	    		ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
	    		System.out.println("表名："+tableName+"\t\n表字段信息：");
	    		while(rs.next()){
	    			System.out.println("字段名："+rs.getString("COLUMN_NAME")+"\t字段数据类型："+rs.getString("TYPE_NAME")+"\t字段默认值："+rs.getString("COLUMN_DEF")+"\t字段注释："+rs.getString("REMARKS"));
	    			addProperty(rs.getString("COLUMN_NAME").toLowerCase(), getJavaType(rs.getString("TYPE_NAME")));
	    			if("BIGINT".equals(rs.getString("TYPE_NAME"))&&"0".equals(rs.getString("COLUMN_DEF"))){
	    				//BIGINT 默认值是0的，我们通常认为是 money字段
	    				addProperty(rs.getString("COLUMN_NAME").toLowerCase(), "com.gps.entity.system.Money");
	    			}
	    		}
				//System.out.println("生成成功！");
	    	}
	    }
	    if(!flag){
	    	System.err.println(" Not found table : "+tablename + " Application exited !");
	    	System.exit(-1);
	    }
	    /* old function
	    String sql = "select * from " + tablename + " limit 1";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(sql); 
		ResultSetMetaData data = rs.getMetaData();
		for (int i = 1; i <= data.getColumnCount(); i++){
			System.out.println(data.getColumnTypeName(i));
			addProperty(data.getColumnName(i).toLowerCase(), data.getColumnClassName(i));
			if (data.isAutoIncrement(i)) {//是否自增长
				//if (StringUtils.isEmpty(id)) id = data.getColumnName(i).toLowerCase();
			}
		}
		*/
		return 0;
	}
	public static String getJavaType(String dbType){
		String returnType="";
		if("BIGINT".equals(dbType)){
			returnType="java.lang.Long";
		}else if("VARCHAR".equals(dbType)){
			returnType="java.lang.String";
		}else if("INT".equals(dbType)){
			returnType="java.lang.Integer";
		}else if("DATETIME".equals(dbType)){
			returnType="java.sql.Timestamp";
		}else if("TIMESTAMP".equals(dbType)){
			returnType="java.sql.Timestamp";
		}else if("TEXT".equals(dbType)){
			returnType="java.lang.String";
		}else if("DECIMAL".equals(dbType)){
			returnType="java.lang.Double";
		}
		Assert.hasText(returnType, "不支持的db类型转换！dbType："+dbType);
		//see more http://blog.csdn.net/yangyinbo/article/details/6212394
		return returnType;
	}
	
	private Map<String, String> getParamMap(){
		Map<String, String> mapParam = new HashMap<String, String>();
		mapParam.put(DAOPACKAGE, getDaopackage());
		mapParam.put(DAOCLASS, getDaoclass());
		mapParam.put(DAOCLASSINSTANCE, getDaoclass().substring(0,1).toLowerCase()+getDaoclass().substring(1));
		mapParam.put(BEANPACKAGE, getBeanpackage());
		mapParam.put(BEANCLASS, getBeanclass());
		mapParam.put(SERVICEPACKAGE, getServicepackage());
		mapParam.put(SERVICECLASS, getServiceclass());
		mapParam.put(TABLENAME, tablename);
		return mapParam;
	}
	public Map<String, String> createDaoClassFromFile(){
		Map<String, String> mapParam = getParamMap();
		
		StringBuilder sb = new StringBuilder();
		for (String key: map.keySet()){
			BeanProperty beanProperty = map.get(key);
			if (getShortClass(beanProperty.getClassname()).equals("String")){
				sb.append("\t\t\t").append("if (StringUtils.isNotEmpty(bean." + beanProperty.getGetMethod() + "())) {").append(LINESEPARATOR);
			}else {
				sb.append("\t\t\t").append("if (bean." + beanProperty.getGetMethod() + "() != null) {").append(LINESEPARATOR);
			}
			sb.append("\t\t\t\t").append("sqlWhere.connect(\" and \", \"" + beanProperty.getName() + "=?\");").append(LINESEPARATOR);
			sb.append("\t\t\t\t").append("params.add(bean." + beanProperty.getGetMethod() + "());").append(LINESEPARATOR);
			sb.append("\t\t\t}").append(LINESEPARATOR);
		}
		mapParam.put("##listquery##", sb.toString());
		return mapParam;
	}
	public Map<String, String> createServiceClassFromFile(){
		Map<String, String> mapParam = getParamMap();
		return mapParam;
	}	

	public void createDaoClass(){
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(getDaopackage()).append(";").append(LINESEPARATOR).append(LINESEPARATOR);
		
		sb.append("import java.util.ArrayList;").append(LINESEPARATOR);
		sb.append("import java.util.List;").append(LINESEPARATOR);
		sb.append("import org.util.dao.BaseDAO;").append(LINESEPARATOR);
		sb.append("import ").append(getBeanpackage()).append(".").append(getBeanclass()).append(";").append(LINESEPARATOR);
		sb.append("import org.util.dao.SQLWhere;").append(LINESEPARATOR);
		sb.append("import java.util.HashMap;").append(LINESEPARATOR);
		sb.append("import java.util.Map;").append(LINESEPARATOR);
		sb.append("import org.apache.commons.lang3.StringUtils;").append(LINESEPARATOR);

		
		sb.append(LINESEPARATOR).append("public class ").append(getDaoclass()).append(" {").append(LINESEPARATOR);
		sb.append("\t").append("public static int saveList(List<" +getBeanclass()+ "> list){").append(LINESEPARATOR);
		sb.append("\t\treturn BaseDAO.saveList(list);").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR).append(LINESEPARATOR);
		
		sb.append("\t").append("public static int updateList(List<" +getBeanclass()+ "> list){").append(LINESEPARATOR);
		sb.append("\t\treturn BaseDAO.updateList(list);").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR).append(LINESEPARATOR);

		sb.append("\t").append("public static int deleteList(List<" +getBeanclass()+ "> list){").append(LINESEPARATOR);
		sb.append("\t\treturn BaseDAO.deleteList(list);").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR).append(LINESEPARATOR);
		
		sb.append("\t").append("public static Map<String, Object> list(" + getBeanclass() +" bean, int offset, int size){").append(LINESEPARATOR);
		sb.append("\t\t").append("SQLWhere sqlWhere = new SQLWhere();").append(LINESEPARATOR);
		sb.append("\t\t").append("List<Object> params = new ArrayList<Object>();").append(LINESEPARATOR);
		
		for (String key: map.keySet()){
			BeanProperty beanProperty = map.get(key);
			if (getShortClass(beanProperty.getClassname()).equals("String")){
				sb.append("\t\t").append("if (StringUtils.isNotEmpty(bean." + beanProperty.getGetMethod() + "())) {").append(LINESEPARATOR);
			}else {
				sb.append("\t\t").append("if (bean." + beanProperty.getGetMethod() + "() != null) {").append(LINESEPARATOR);
			}
			sb.append("\t\t\t").append("sqlWhere.connect(\" and \", \"" + beanProperty.getName() + "=?\");").append(LINESEPARATOR);
			sb.append("\t\t\t").append("params.add(bean." + beanProperty.getGetMethod() + "());").append(LINESEPARATOR);
			sb.append("\t\t}").append(LINESEPARATOR);
		}
		sb.append("\t\t").append("StringBuilder sqlList = new StringBuilder(\"select * from " +tablename+ "\");").append(LINESEPARATOR);
		sb.append("\t\t").append("StringBuilder sqlCount = new StringBuilder(\"select count(*) from "+tablename+"\");").append(LINESEPARATOR);
		sb.append("\t\t").append("StringBuilder sb = sqlWhere.getSql();").append(LINESEPARATOR);
		sb.append("\t\t").append("if (sb.length() != 0){").append(LINESEPARATOR);
		sb.append("\t\t\t").append("sqlList.append(\" where \").append(sb.toString());").append(LINESEPARATOR);
		sb.append("\t\t\t").append("sqlCount.append(\" where \").append(sb.toString());").append(LINESEPARATOR);
		sb.append("\t\t").append("}").append(LINESEPARATOR);
		sb.append("\t\t").append("Number total = (Number)BaseDAO.queryOneRowOneCol(sqlCount.toString(), params.toArray());").append(LINESEPARATOR);
		sb.append("\t\t").append("sqlList.append(\" limit ?,?\");").append(LINESEPARATOR);
		sb.append("\t\t").append("params.add(offset);").append(LINESEPARATOR);
		sb.append("\t\t").append("params.add(size);").append(LINESEPARATOR);
		sb.append("\t\t").append("List<"+getBeanclass()+"> list = BaseDAO.queryForList("+getBeanclass()+".class, sqlList.toString(), params.toArray());").append(LINESEPARATOR);
		sb.append("\t\t").append("Map<String, Object> rtn = new HashMap<String, Object>();").append(LINESEPARATOR);
		sb.append("\t\t").append("rtn.put(\"total\", total);").append(LINESEPARATOR);
		sb.append("\t\t").append("rtn.put(\"list\", list);").append(LINESEPARATOR);
		sb.append("\t\t").append("return rtn;").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR);
		
		sb.append("}");
		System.out.println(sb.toString());
	}
	public String getDaopackage(){
		return packagename.substring(0,packagename.lastIndexOf(".")) + ".dao";
		//return packagename + ".dao";
	}
	public String getDaoclass(){
		return classname + "Dao";
		//return classname + "BaseDAO";
	}
	public String getBeanclass(){
		return classname;
		//return classname + "Bean";
	}
	public String getServiceclass(){
		return classname + "Service";
	}
	public String getBeanpackage(){
		return packagename;
		//return packagename + ".bean";
	}
	public String getServicepackage(){
		//return packagename + ".service";
		return packagename.substring(0,packagename.lastIndexOf(".")) + ".service";
	}
	public String createBeanClass(){
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(getBeanpackage()).append(";").append(LINESEPARATOR).append(LINESEPARATOR);
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder stringifyBuilder = new StringBuilder("\t\treturn \"[\"").append(LINESEPARATOR);
		Set<String> set = new HashSet<String>();
		boolean moneyFlag=false;
		for (String key: map.keySet()){
			BeanProperty beanProperty = map.get(key);
			if(beanProperty.getName().equalsIgnoreCase("id")){
				sb.append("import javax.persistence.Id;").append(LINESEPARATOR);
				sb.append("import javax.persistence.GenerationType;").append(LINESEPARATOR);
				sb.append("import javax.persistence.GeneratedValue;").append(LINESEPARATOR);
				sb2.append("\t@Id").append(LINESEPARATOR);
				sb2.append("\t@GeneratedValue(strategy = GenerationType.AUTO)").append(LINESEPARATOR);
			}else{
				if("Money".equals(getShortClass(beanProperty.getClassname()))){//money type
					moneyFlag=true;
					sb2.append("\t@Embedded").append(LINESEPARATOR);
					sb2.append("\t@AttributeOverrides({").append(LINESEPARATOR);
					sb2.append("\t@AttributeOverride(name=\"cent\",column=@Column(name=\""+beanProperty.getName()+"\"))").append(LINESEPARATOR);
					sb2.append("\t})").append(LINESEPARATOR);
					//sb2.append("\t").append("private ").append(getShortClass(beanProperty.getClassname())).append(" ").append(underline2upper(beanProperty.getName())).append(";").append(LINESEPARATOR);
				}else{
					sb2.append("\t@Column(name=\""+beanProperty.getName()+"\")").append(LINESEPARATOR);
				}
			}
			sb2.append("\t").append("private ").append(getShortClass(beanProperty.getClassname())).append(" ").append(underline2upper(beanProperty.getName())).append(";").append(LINESEPARATOR);
			set.add(beanProperty.getClassname());
			sb1.append("\t").append("public ").append(getShortClass(beanProperty.getClassname())).append(" ")
				.append(beanProperty.getGetMethod()).append("(){").append(LINESEPARATOR) ;
			sb1.append("\t\t").append("return ").append(underline2upper(beanProperty.getName())).append(";").append(LINESEPARATOR);
			sb1.append("\t}").append(LINESEPARATOR);

			sb1.append("\t").append("public void ")
				.append(beanProperty.getSetMethod()).append("(" + getShortClass(beanProperty.getClassname()) + " " + underline2upper(beanProperty.getName()) + "){").append(LINESEPARATOR) ;
			sb1.append("\t\t").append("this.").append(underline2upper(beanProperty.getName())).append(" = ").append(underline2upper(beanProperty.getName())).append(";").append(LINESEPARATOR);
			sb1.append("\t}").append(LINESEPARATOR);
			
			stringifyBuilder.append("\t\t+ \",").append(underline2upper(beanProperty.getName())).append("=\" + ").append(beanProperty.getGetMethod()).append("()").append(LINESEPARATOR);

		}
		if(moneyFlag){
			sb.append("import javax.persistence.AttributeOverride;").append(LINESEPARATOR);
			sb.append("import javax.persistence.Embedded;").append(LINESEPARATOR);
			sb.append("import javax.persistence.AttributeOverrides;").append(LINESEPARATOR);
		}
		stringifyBuilder.append("\t\t+ \"]\";").append(LINESEPARATOR);
		for (String key: set){
			sb.append("import ").append(key).append(";").append(LINESEPARATOR);
		}
		sb.append("import javax.persistence.Column;").append(LINESEPARATOR);
		sb.append("import javax.persistence.Entity;").append(LINESEPARATOR);
		sb.append("import javax.persistence.Table;").append(LINESEPARATOR);
//		sb.append("import org.hibernate.annotations.DynamicInsert;").append(LINESEPARATOR);
//		sb.append("import org.hibernate.annotations.DynamicUpdate;").append(LINESEPARATOR);
//		sb.append(LINESEPARATOR).append("@DynamicInsert");
//		sb.append(LINESEPARATOR).append("@DynamicUpdate");
		sb.append(LINESEPARATOR).append("@Entity");
		sb.append(LINESEPARATOR).append("@Table(name=\"" + tablename + "\")").append(LINESEPARATOR);
		//sb.append("public class ").append(getBeanclass()).append("   implements Cloneable {").append(LINESEPARATOR);
		sb.append("public class ").append(getBeanclass()).append(" {").append(LINESEPARATOR);
		sb.append(sb2).append(LINESEPARATOR);
		sb.append(sb1).append(LINESEPARATOR);
		
		/*
		sb.append("\tpublic ").append(getBeanclass()).append(" clone() {").append(LINESEPARATOR);
		sb.append("\t\t").append(getBeanclass()).append(" o = null;").append(LINESEPARATOR);
		sb.append("\t\t").append("try {").append(LINESEPARATOR);
		sb.append("\t\t\to = (").append(getBeanclass()).append(") super.clone();").append(LINESEPARATOR);
		sb.append("\t\t} catch (CloneNotSupportedException e) {").append(LINESEPARATOR);
		sb.append("\t\t\te.printStackTrace();").append(LINESEPARATOR);
		sb.append("\t\t}").append(LINESEPARATOR);
		sb.append("\t\treturn o;").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR).append(LINESEPARATOR);
		 */
		
		sb.append(getToStringMethod(stringifyBuilder));
		sb.append("}");
		return sb.toString();
	}
	
	private StringBuilder getToStringMethod(StringBuilder stringifyBuilder) {
		StringBuilder toStrMethod = new StringBuilder();
		toStrMethod.append("\t@Override").append(LINESEPARATOR).append("\tpublic String toString(){").append(LINESEPARATOR);

		int index = stringifyBuilder.indexOf(",");
		if(index > 0){
			stringifyBuilder.deleteCharAt(index);
		}
		
		toStrMethod.append(stringifyBuilder);
		toStrMethod.append("\t}").append(LINESEPARATOR);
		return toStrMethod;
	}
	
	private String getShortClass(String classname){
		String[] arr = classname.split("\\.");
		return arr[arr.length-1];
	}
	public static String underline2upper(String str){
		String[] arr = str.split("_");
		StringBuilder sb=new StringBuilder();
		for(String s:arr){
			sb.append(firstChar2upper(s));
		}
		return firstChar2lower(sb.toString());
	}
	private static String firstChar2upper(String str){
		if(str==null||str.length()==0){
			return null;
		}
		return str.substring(0,1).toUpperCase()+str.substring(1);
	}
	private static String firstChar2lower(String str){
		if(str==null||str.length()==0){
			return null;
		}
		return str.substring(0,1).toLowerCase()+str.substring(1);
	}
	
	public void createServiceClass(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("package ").append(getServicepackage()).append(";").append(LINESEPARATOR).append(LINESEPARATOR);
		sb.append("import java.util.List;").append(LINESEPARATOR);
		sb.append("import java.util.Map;").append(LINESEPARATOR);
		sb.append("import org.util.json.JsonUtil;").append(LINESEPARATOR);
		sb.append("import com.wx.bean.Constant;").append(LINESEPARATOR);
		sb.append("import com.wx.bean.RtnObject;").append(LINESEPARATOR);
		sb.append("import com.wx.bean.WebParam;").append(LINESEPARATOR);
		sb.append("import " + getBeanpackage() + "." + getBeanclass() + ";").append(LINESEPARATOR);
		sb.append("import " + getDaopackage() + "." + getDaoclass() + ";").append(LINESEPARATOR).append(LINESEPARATOR);

		sb.append("public class " + getServiceclass() + " {").append(LINESEPARATOR);
		sb.append("\tpublic static RtnObject save(WebParam param){").append(LINESEPARATOR);
		sb.append("\t\tList<" + getBeanclass() + "> list = getList(param);").append(LINESEPARATOR);
		sb.append("\t\tif (list == null) return errSubmitRtn();").append(LINESEPARATOR);
		sb.append("\t\tRtnObject rtnObject = new RtnObject();").append(LINESEPARATOR);
		sb.append("\t\tint rtn = " + getDaoclass() + ".saveList(list);").append(LINESEPARATOR);
		sb.append("\t\tif (rtn > 0){").append(LINESEPARATOR);
		sb.append("\t\t\trtnObject.setCode(Constant.CODE_SUCCESS);").append(LINESEPARATOR);
		sb.append("\t\t}else {").append(LINESEPARATOR);
		sb.append("\t\t\trtnObject.setCode(Constant.CODE_ERR_SERVER_EXCEPTION);").append(LINESEPARATOR);
		sb.append("\t\t}").append(LINESEPARATOR);
		sb.append("\t\t\treturn rtnObject;").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR);
		sb.append("\tpublic static RtnObject update(WebParam param){").append(LINESEPARATOR);
		sb.append("\t\tList<" + getBeanclass() + "> list = getList(param);").append(LINESEPARATOR);
		sb.append("\t\tif (list == null) return errSubmitRtn();").append(LINESEPARATOR);
		sb.append("\t\tRtnObject rtnObject = new RtnObject();").append(LINESEPARATOR);
		sb.append("\t\tint rtn = " + getDaoclass() + ".updateList(list);").append(LINESEPARATOR);
		sb.append("\t\tif (rtn > 0){").append(LINESEPARATOR);
		sb.append("\t\t\trtnObject.setCode(Constant.CODE_SUCCESS);").append(LINESEPARATOR);
		sb.append("\t\t}else {").append(LINESEPARATOR);
		sb.append("\t\t\trtnObject.setCode(Constant.CODE_ERR_SERVER_EXCEPTION);").append(LINESEPARATOR);
		sb.append("\t\t}").append(LINESEPARATOR);
		sb.append("\t\treturn rtnObject;").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR);
		sb.append("\tpublic static RtnObject delete(WebParam param){").append(LINESEPARATOR);
		sb.append("\t\tList<" + getBeanclass() + "> list = getList(param);").append(LINESEPARATOR);
		sb.append("\t\tif (list == null) return errSubmitRtn();").append(LINESEPARATOR);
		sb.append("\t\tRtnObject rtnObject = new RtnObject();").append(LINESEPARATOR);
		sb.append("\t\tint rtn = " + getDaoclass() + ".deleteList(list);").append(LINESEPARATOR);
		sb.append("\t\tif (rtn > 0){").append(LINESEPARATOR);
		sb.append("\t\t\trtnObject.setCode(Constant.CODE_SUCCESS);").append(LINESEPARATOR);
		sb.append("\t\t}else {").append(LINESEPARATOR);
		sb.append("\t\t\trtnObject.setCode(Constant.CODE_ERR_SERVER_EXCEPTION);").append(LINESEPARATOR);
		sb.append("\t\t}").append(LINESEPARATOR);
		sb.append("\t\treturn rtnObject;").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR);
		sb.append("\tpublic static RtnObject list(WebParam param){").append(LINESEPARATOR);
		sb.append("\t\tRtnObject rtnObject = new RtnObject();").append(LINESEPARATOR);
		sb.append("\t\ttry {").append(LINESEPARATOR);
		sb.append("\t\t\t" + getBeanclass() + " bean = JsonUtil.jsonToObj(" + getBeanclass() + ".class, param.getRequestParam().get(\"obj\"));").append(LINESEPARATOR);
		sb.append("\t\t\tint offset = JsonUtil.getIntValue(param.getRequestParam(), \"offset\", 0);").append(LINESEPARATOR);
		sb.append("\t\t\tint size = JsonUtil.getIntValue(param.getRequestParam(), \"size\", 5000);").append(LINESEPARATOR);
		sb.append("\t\t\tMap<String, Object> map = " + getDaoclass() + ".list(bean, offset, size);").append(LINESEPARATOR);
		sb.append("\t\t\trtnObject.setCode(Constant.CODE_SUCCESS);").append(LINESEPARATOR);
		sb.append("\t\t\trtnObject.setObj(map);").append(LINESEPARATOR);
		sb.append("\t\t} catch (Exception e) {").append(LINESEPARATOR);
		sb.append("\t\t\te.printStackTrace();").append(LINESEPARATOR);
		sb.append("\t\t\trtnObject.setCode(Constant.CODE_ERR_SERVER_EXCEPTION);").append(LINESEPARATOR);
		sb.append("\t\t}").append(LINESEPARATOR);
		sb.append("\t\treturn rtnObject;").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR);

		sb.append("\tprivate static RtnObject errSubmitRtn(){").append(LINESEPARATOR);
		sb.append("\t\tRtnObject rtnObject = new RtnObject();").append(LINESEPARATOR);
		sb.append("\t\trtnObject.setCode(Constant.CODE_ERR_SUBMIT_PARAM);").append(LINESEPARATOR);
		sb.append("\t\treturn rtnObject;").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR);
		sb.append("\tprivate static List<" + getBeanclass() + "> getList(WebParam param){").append(LINESEPARATOR);
		sb.append("\t\ttry {").append(LINESEPARATOR);
		sb.append("\t\t\treturn JsonUtil.jsonToObj(List.class, " + getBeanclass() + ".class, param.getRequestParam().get(\"list\"));").append(LINESEPARATOR);
		sb.append("\t\t} catch (Exception e) {").append(LINESEPARATOR);
		sb.append("\t\t\te.printStackTrace();").append(LINESEPARATOR);
		sb.append("\t\t}").append(LINESEPARATOR);
		sb.append("\t\treturn null;").append(LINESEPARATOR);
		sb.append("\t}").append(LINESEPARATOR);
		sb.append("}").append(LINESEPARATOR);
		System.out.println(sb.toString());
	}
	
}