package com.crm.customers.beancreater;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;

import com.crm.customers.CustomersApplication;
import com.crm.customers.beancreater.code.ClassCreator1;
import com.crm.customers.beancreater.code.GeneralCreator;
import com.crm.customers.beancreater.code.TableBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



/**
 * 生成bean、dao、service
 * @author Fans99
 * @since 2017.02.21
 */
@RunWith(SpringJUnit4ClassRunner.class)  
//@SpringApplicationConfiguration(classes = ApplicationStart.class)  
@SpringBootTest(classes= CustomersApplication.class)
@WebAppConfiguration
public class LJxCodeCreator {

	@Resource
	JdbcTemplate jdbcTemplate;
	
	@Test
    public void testShow() {
		TableBean tableBean = new TableBean();
		tableBean.setPackagename("com.crm.customer.entity");
		tableBean.setTablename("role");
		tableBean.setClassname("Role");
		
		ClassCreator1 classCreator = new ClassCreator1(tableBean);
		try {
			classCreator.execute(jdbcTemplate.getDataSource().getConnection());
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
		//classCreator.createDaoClass();
		//classCreator.createBeanClass();
		//classCreator.createServiceClass();
		String path = "D:/codes/";
		try {
			Map<String, String> params = classCreator.createDaoClassFromFile();
			GeneralCreator.create(path + "TemplateDAO.tpl", "utf-8", path + classCreator.getDaoclass() + ".java", params);
			params = classCreator.createServiceClassFromFile();
			GeneralCreator.create(path + "TemplateService.tpl", "utf-8", path + classCreator.getServiceclass() + ".java", params);
			GeneralCreator.create(path + "TemplateServiceImpl.tpl", "utf-8", path + classCreator.getServiceclass() + "Impl.java", params);
			String str = classCreator.createBeanClass();
			GeneralCreator.writeFile(path + classCreator.getBeanclass() + ".java", "utf-8", str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finish Create  files !!!");
		System.exit(0);
    }
	
	
}
