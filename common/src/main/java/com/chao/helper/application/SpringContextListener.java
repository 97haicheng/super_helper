package com.chao.helper.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class SpringContextListener implements ServletContextListener {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static ApplicationContext applicationContext;
	 
	public static ApplicationContext getAppContext(){
		return applicationContext;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				logger.debug("deregistering jdbc driver {}", driver);
			} catch (SQLException e) {
				logger.error("Error deregistering driver {}", driver, e);
			}
		}
	}
}
