package com.tourcan.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tourcan.admin.model.AdminDAO;
import com.tourcan.admin.model.AdminHibernateDAO;
import com.tourcan.admin.model.AdminVO;

@WebServlet("/adridores/login")
public class AdminAuthService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/adridores/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminDAO dao = new AdminHibernateDAO();
		AdminVO vo = dao.findByAccount(req.getParameter("admin_account"));
		if (!vo.getAdmin_pwd().equals(req.getParameter("admin_pwd"))) {
			req.getRequestDispatcher("/WEB-INF/adridores/login.jsp").forward(req, resp);
		} else {
			req.getSession().setAttribute("admin", vo);
			resp.sendRedirect(req.getContextPath() + "/adridores/att");
		}
	}
}
