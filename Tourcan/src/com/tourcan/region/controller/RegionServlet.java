package com.tourcan.region.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tourcan.region.model.RegionDAO;
import com.tourcan.region.model.RegionHibernateDAO;

/**
 * Servlet implementation class RegionServlet
 */
@WebServlet("/RegionServlet")
public class RegionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		RegionDAO dao = context.getBean(RegionHibernateDAO.class);
		dao.getAll();
		PrintWriter out = response.getWriter();
//		System.out.println(new JSONArray(dao.getAll()).toString());
		out.println(new JSONArray(dao.getAll()).toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
