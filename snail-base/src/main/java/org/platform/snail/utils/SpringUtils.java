package org.platform.snail.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
	private static SpringUtils instance;
	private static ApplicationContext context;

	private SpringUtils() {
		context = new ClassPathXmlApplicationContext("/applicationContext.xml");
	}
	public synchronized static Object getBean(String beanName) {
		if(context==null){
			context = new ClassPathXmlApplicationContext("/applicationContext.xml");
		}
		Object obj = context.getBean(beanName);
		return obj;
	}

	public static ApplicationContext getContext() {
		return context;
	}
	public synchronized static SpringUtils getInstance() {
		if (instance == null) {
			instance = new SpringUtils();
		}
		return instance;
	}
}
