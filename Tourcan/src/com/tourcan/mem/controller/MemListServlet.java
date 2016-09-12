package com.tourcan.mem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.tourcan.mem.model.MemVO;
import com.tourcan.region.model.RegionDAO;
import com.tourcan.region.model.RegionHibernateDAO;
import com.tourcan.region.model.RegionVO;
import com.tourcan.util.ApplicationContextUtils;

@WebServlet("/mem/listuser")
public class MemListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		Response rm = new MemService().queryAll();
		List<MemVO> vos = null;
		RegionDAO regionDAO = ApplicationContextUtils.getContext().getBean(RegionHibernateDAO.class);
		List<RegionVO> regions = regionDAO.getAll();
		if (rm.getStatusInfo().equals(Status.OK)) {
			vos = (List<MemVO>) rm.getEntity();
			req.setAttribute("members", vos);
			req.setAttribute("regions", regions);
			req.getRequestDispatcher("/WEB-INF/mem/listuser.jsp").forward(req, resp);
		}
	}
}
