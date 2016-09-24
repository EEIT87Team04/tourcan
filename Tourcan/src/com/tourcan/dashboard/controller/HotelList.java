package com.tourcan.dashboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tourcan.hotel.model.HotelDAO;
import com.tourcan.region.model.RegionDAO;
import com.tourcan.util.ApplicationContextUtils;

@WebServlet({ "/adridores/hotel" })
public class HotelList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("hotelVO", ((HotelDAO) ApplicationContextUtils.getContext().getBean("hotelDAO")).getAll());
		req.setAttribute("regionVO", ((RegionDAO) ApplicationContextUtils.getContext().getBean("regionDAO")).getAll());
		req.getRequestDispatcher("/WEB-INF/adridores/hotel.jsp").forward(req, resp);
	}

}
