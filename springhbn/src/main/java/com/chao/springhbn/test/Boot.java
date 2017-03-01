package com.chao.springhbn.test;

import com.chao.springhbn.dao.UserDao;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Boot {

	  private static ApplicationContext applicationContext;
//	  private static final String CONFIGPATH= "applicationContext.xml";
	  private static final String CONFIGPATH= "applicationContext_spring.xml";
	  private static Logger logger = Logger.getLogger(Boot.class);

	  public static void main(final String[] args) throws Exception {
		  applicationContext = new ClassPathXmlApplicationContext(CONFIGPATH);

//		  SessionFactory sessionFactory = (SessionFactory) applicationContext.getBean("sessionFactory");
//		  Statistics statistics = sessionFactory.getStatistics();
//		  logger.debug(statistics.getConnectCount());

		  UserDao userDao = (UserDao) applicationContext.getBean("userDao");
		  logger.debug(userDao.getUser(1));
		  logger.debug(userDao.findUserByName("11"));
//		  logger.debug(userDao.getUserNum());
		  logger.debug(userDao.geUserNum2());

//		  Configuration cfr = new Configuration().configure();
//		  SchemaExport export = new SchemaExport(cfr);
//		  export.create(true, true);
	  }
}