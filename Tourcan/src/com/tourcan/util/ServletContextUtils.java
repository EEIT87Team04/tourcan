package com.tourcan.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tourcan.admin.model.AdminDAO;
import com.tourcan.admin.model.AdminFakeDAO;

public class ServletContextUtils implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		AdminDAO dao = new AdminFakeDAO();
		// System.out.println("contextPath = " + sc.getContextPath());
		sc.setAttribute("contextPath", sc.getContextPath());
		sc.setAttribute("admin", dao.findById(0));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
