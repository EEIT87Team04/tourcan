package com.tourcan.admin.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tourcan.admin.model.AdminVO;

@WebFilter(description = "Check if user logged in or not.", urlPatterns = { "/adridores/*" })
public class AdminAuthorizationFilter implements Filter {

	static {
		// TODO complete this blacklist
		// blacklist.put("/att", new String[] { "POST", "PUT", "DELETE" });
		// restful.add("/att");
		//
		// blacklist.put("/HotelServlet", new String[] { "POST", "PUT", "DELETE"
		// });
		// restful.add("/HotelServlet");
		//
		// blacklist.put("/hotels", new String[] { "POST", "PUT", "DELETE" });
		// restful.add("/hotels");
		//
		// blacklist.put("/members", new String[] { "GET", "POST", "PUT",
		// "DELETE" });
		// restful.add("/members");
		//
		// blacklist.put("/ems", new String[] { "GET", "POST", "PUT", "DELETE"
		// });
		// blacklist.put("/attImport.html", new String[] { "GET", "POST", "PUT",
		// "DELETE" });
		// blacklist.put("/att/insert.jsp", new String[] { "GET", "POST", "PUT",
		// "DELETE" });
		// blacklist.put("/mem", new String[] { "GET", "POST", "PUT", "DELETE"
		// });
		// blacklist.put("/hotelManage.jsp", new String[] { "GET", "POST",
		// "PUT", "DELETE" });
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		AdminVO vo = (AdminVO) req.getSession().getAttribute("admin");

		if (vo == null && !req.getServletPath().startsWith("/adridores/login")) {
			resp.sendRedirect(req.getContextPath() + "/adridores/login");
		} else if (vo != null && req.getServletPath().startsWith("/adridores/login")) {
			resp.sendRedirect(req.getContextPath() + "/adridores/att");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
