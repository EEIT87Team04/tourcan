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
		} else if (vo != null && req.getServletPath().equals("/adridores")) {
			resp.sendRedirect(req.getContextPath() + "/adridores/att");
		} else if (vo != null && req.getServletPath().equals("/adridores/")) {
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
