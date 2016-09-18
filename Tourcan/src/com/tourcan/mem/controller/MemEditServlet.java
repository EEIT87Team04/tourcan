package com.tourcan.mem.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;

import com.tourcan.mem.model.MemVO;

@WebServlet("/mem/edituser")
public class MemEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// present update interface
		if (req.getParameter("uid") != null) {
			// request specific member data
			Response r = new MemService().queryByUid(req.getParameter("uid"));
			if (r.getStatusInfo().equals(Status.OK)) {
				req.setAttribute("data", r.getEntity());
			} else {
				resp.sendRedirect(req.getContextPath() + "/mem/listuser");
			}
		}
		req.setAttribute("listener", "/mem/edituser");
		req.getRequestDispatcher("/WEB-INF/mem/edituser.jsp").forward(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		MemVO vo_new = new MemVO(), vo_orig = (MemVO) session.getAttribute("vo");

		// TODO check new_mem_uid.equal(old_mem_uid)

		ConvertUtilsBean conv = new ConvertUtilsBean();
		DateConverter dconv = new DateConverter(null);
		dconv.setPattern("yyyy-MM-dd");
		conv.register(dconv, Date.class);
		try {
			new BeanUtilsBean(conv).populate(vo_new, req.getParameterMap());

			if (vo_new.getMem_uid().equals(vo_orig.getMem_uid())) {
				// modify user it self.
				Response i = new MemService().updateMem(vo_new);
				if (i.getStatusInfo().equals(Status.OK)) {
					// vo updated, refresh vo in session.
					session.setAttribute("vo", vo_new);
					// then redirect somewhere
					String path = "/";
					if (session.getAttribute("redirect-after-login") != null) {
						path = (String) session.getAttribute("redirect-after-login");
						session.removeAttribute("redirect-after-login");
					}
					resp.sendRedirect(req.getContextPath() + path);
				} else if (i.getStatusInfo().equals(Status.BAD_REQUEST)) {
					// store vo in request scope for el pre-fill in
					req.setAttribute("data", vo_new);
					req.setAttribute("msg", i.getEntity());
					req.getRequestDispatcher("/WEB-INF/mem/edituser.jsp").forward(req, resp);
				} else if (i.getStatusInfo().equals(Status.NOT_FOUND)) {
					// this should NOT happen.
					System.out.println("Error occured.");
					for (Entry<String, String> j : ((Map<String, String>) i.getEntity()).entrySet()) {
						System.out.println(j.getKey() + "\t: " + j.getValue());
					}
				} else {
					// TODO handle invalid input.
					System.out.println("Error occured.");
					for (Entry<String, String> j : ((Map<String, String>) i.getEntity()).entrySet()) {
						System.out.println(j.getKey() + "\t: " + j.getValue());
					}
				}
			} else {
				// modify other user
				Response i = new MemService().updateMem(vo_new);
				if (i.getStatusInfo().equals(Status.OK)) {
					// vo updated
					// then redirect somewhere
					String path = "/";
					if (session.getAttribute("redirect-after-login") != null) {
						path = (String) session.getAttribute("redirect-after-login");
						session.removeAttribute("redirect-after-login");
					}
					resp.sendRedirect(req.getContextPath() + path);
				} else if (i.getStatusInfo().equals(Status.BAD_REQUEST)) {
					// store vo in request scope for el pre-fill in
					req.setAttribute("data", vo_new);
					req.setAttribute("msg", i.getEntity());
					req.getRequestDispatcher("/WEB-INF/mem/edituser.jsp").forward(req, resp);
				} else if (i.getStatusInfo().equals(Status.NOT_FOUND)) {
					// this should NOT happen.
					System.out.println("Error occured.");
					for (Entry<String, String> j : ((Map<String, String>) i.getEntity()).entrySet()) {
						System.out.println(j.getKey() + "\t: " + j.getValue());
					}
				} else {
					// TODO handle invalid input.
					System.out.println("Error occured.");
					for (Entry<String, String> j : ((Map<String, String>) i.getEntity()).entrySet()) {
						System.out.println(j.getKey() + "\t: " + j.getValue());
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
