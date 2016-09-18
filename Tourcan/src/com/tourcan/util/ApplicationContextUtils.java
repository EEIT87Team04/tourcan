package com.tourcan.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtils implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
//		System.out.println("Context injected: " + applicationContext);
	}

	public static ApplicationContext getContext() {
//		System.out.println("Context called: " + applicationContext);
		return applicationContext;
	}

}
