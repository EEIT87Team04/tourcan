package com.tourcan.dashboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tourcan.mem.model.MemDAO;
import com.tourcan.region.model.RegionDAO;
import com.tourcan.util.ApplicationContextUtils;

@WebServlet({ "/adridores/mem" })
public class MemList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("memVO", ((MemDAO) ApplicationContextUtils.getContext().getBean("memDAO")).getAll());
		req.setAttribute("regionVO", ((RegionDAO) ApplicationContextUtils.getContext().getBean("regionDAO")).getAll());
		req.getRequestDispatcher("/WEB-INF/adridores/mem.jsp").forward(req, resp);
	}

}
