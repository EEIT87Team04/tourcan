package com.tourcan.mem.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tourcan.mem.model.MemVO;

/**
 * This filter is used for checking whether user logged in or not, by validate
 * uid exist in session or not. A uid is only available by providing a valid
 * token in exchange.
 */
@WebFilter(description = "Check if user logged in or not.", urlPatterns = { "/*" })
public class MemAuthorizationFilter implements Filter {

	private static final Map<String, String[]> blacklist = new HashMap<String, String[]>();
	private static final Set<String> restful = new HashSet<String>();
	static {
		blacklist.put("/att", new String[] { "POST", "PUT", "DELETE" });
		restful.add("/att");

		blacklist.put("/HotelServlet", new String[] { "POST", "PUT", "DELETE" });
		restful.add("/HotelServlet");

		blacklist.put("/hotels", new String[] { "POST", "PUT", "DELETE" });
		restful.add("/hotels");

		blacklist.put("/members", new String[] { "GET", "POST", "PUT", "DELETE" });
		restful.add("/members");

		blacklist.put("/ems", new String[] { "GET", "POST", "PUT", "DELETE" });
		blacklist.put("/attImport.html", new String[] { "GET", "POST", "PUT", "DELETE" });
		blacklist.put("/att/insert.jsp", new String[] { "GET", "POST", "PUT", "DELETE" });
		blacklist.put("/mem", new String[] { "GET", "POST", "PUT", "DELETE" });
		blacklist.put("/hotelManage.jsp", new String[] { "GET", "POST", "PUT", "DELETE" });
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		MemVO vo = (MemVO) session.getAttribute("vo");

		Boolean isAllowed = true;
		for (Entry<String, String[]> path : blacklist.entrySet()) {
			if (req.getServletPath().startsWith(path.getKey())) {
				for (String method : path.getValue()) {
					if (req.getMethod().equalsIgnoreCase(method)) {
						isAllowed = false;
					}
				}
			}
		}

		Boolean isRestful = false;
		for (String e : restful) {
			if (req.getServletPath().startsWith(e)) {
				isRestful = false;
			}
		}

		if (req.getAttribute("pass") != null) {
			isAllowed = true;
			req.removeAttribute("pass");
		}

		if (!isAllowed) {
			// URL in blacklist, this area need login.
			if (vo == null) {
				// uid not provided. the user hasn't signed in.
				// System.out.println("filtered");
				if (isRestful) {
					// 401 for RESTful API
					resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User hadn't logged in.");
					return;
				} else {
					// 302 for others
					session.setAttribute("redirect-after-login", req.getServletPath());
					resp.setHeader("error-message", "User hadn't logged in.");
					resp.sendRedirect(req.getContextPath() + "/login.jsp");
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
