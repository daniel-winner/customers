package com.crm.customers.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseController {

	private final static Logger baseLog = LoggerFactory.getLogger(BaseController.class);
	
	public final static String USER_ID="userId";
	
	public final static String USER_NAME="userName";
	
	public final static String DOWNLOAD_SQL="sql";
	
	public final static String DOWNLOAD_PARAMETER="parameter";
	
	public final static String DOWNLOAD_ERROR_FILENAME="download_error.txt";
	
	public final static int SUCCESS=200;
	
	public final static int FAIL=500;
	
	public final static int MAX_PAGE_SIZE=100;//每页不能大于条数
	
	protected int getEnumInt(String code) {
		return Integer.valueOf(code);
	}
	
	/** 是否POST提交*/
	protected Boolean isPosted() {
		return "POST".equals(getRequest().getMethod());
	}
	
	/** request 中取得参数*/
	protected String getParameter(String name) {
		return getRequest().getParameter(name);
	}
	
	/** request 中取得参数数组*/
	protected String[] getParameters(String name) {
		return getRequest().getParameterValues(name);
	}
	
	protected String getSessionString(String name) {
		//return (String)getRequest().getSession().getAttribute(name);
		return getRequest().getSession().getAttribute(name)==null?null:getRequest().getSession().getAttribute(name).toString();
	}
	
	protected Object getSession(String name) {
		return getRequest().getSession().getAttribute(name);
	}
	
	protected void setSessionString(String name,String obj) {
		setSession(name,obj);
	}
	
	protected void setSession(String name,Object obj) {		
		getRequest().getSession().setAttribute(name,obj);
	}

	protected String getCookie(String name) {		
		Cookie[] cookies = getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie.getValue();
			}
		}
		return null;
	}

	protected void setCookie(String name, String value) {
		getResponse().addCookie(new Cookie(name, value));
	}
	
	protected HttpSession getSession() {
		return getRequest().getSession();
	}
	
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	public static HttpServletResponse getResponse(){
		HttpServletResponse resp = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return resp;
    }
	
	public static void printJson(HttpServletResponse response, Object obj){
		ObjectMapper g = new ObjectMapper();
		try {
			String json=g.writeValueAsString(obj);
			//response.setContentType("text/json");//ie不支持啊
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	/** 获取分页参数 */
	public Pageable getPageable() {
		//Order order1 = new Order(Direction.DESC,"id");
	    //Order order2 = new Order(Direction.ASC,"name");
	    //Sort sort = new Sort(order1,order2);
		Order order = new Order(Direction.valueOf(getParameter("desc")==null?"DESC":getParameter("desc") ),getParameter("sidx")==null?"id":getParameter("sidx"));
		Sort sort = new Sort(order);
	    //Integer page = Integer.valueOf(getParameter("page")==null?"1":getParameter("page")) - 1;
	    //Integer size = Integer.valueOf(getParameter("rows")==null?"10":getParameter("rows"));
		Integer page = Integer.valueOf(StringUtils.isEmpty(getParameter("page"))?"1":getParameter("page")) - 1;
	    Integer size = Integer.valueOf(StringUtils.isEmpty(getParameter("rows"))?"10":getParameter("rows"));
		if(size>MAX_PAGE_SIZE){
			baseLog.info("获取到分页条数为{}，超过最高条数设置，已经自动更新为最高条数{}",size,MAX_PAGE_SIZE);
			size=MAX_PAGE_SIZE;
		}
	    return new PageRequest(page, size,sort);
	}
	
	public Map<String, Object> getQueryConditions() {
		Map<String, Object> queryConditions=new HashMap<String, Object>();
		Enumeration<String> enums=getRequest().getParameterNames();  
		while (enums.hasMoreElements()){
			String paramName=(String)enums.nextElement();
			if(paramName!=null&&paramName.startsWith("queryConditions.")){
				String paramValue = getRequest().getParameter(paramName); 
				if(paramValue!=null&&!"".equals(paramValue.trim())){
					queryConditions.put(paramName.substring(16,paramName.length()), paramValue);
				}
			}
		}
		return queryConditions;
	}
	
}
