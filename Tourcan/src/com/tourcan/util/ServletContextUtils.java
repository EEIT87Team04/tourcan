package com.tourcan.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tourcan.admin.model.AdminDAO;
import com.tourcan.admin.model.AdminHibernateDAO;

public class ServletContextUtils implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		AdminDAO dao = new AdminHibernateDAO();
		// System.out.println("contextPath = " + sc.getContextPath());
		sc.setAttribute("contextPath", sc.getContextPath());
		sc.setAttribute("admin", dao.findById());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
