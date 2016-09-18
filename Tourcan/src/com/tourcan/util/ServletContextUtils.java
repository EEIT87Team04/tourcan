package com.tourcan.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextUtils implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		// System.out.println("contextPath = " + sc.getContextPath());
		sc.setAttribute("contextPath", sc.getContextPath());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
