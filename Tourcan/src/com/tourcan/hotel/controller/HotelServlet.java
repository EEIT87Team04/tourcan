package com.tourcan.hotel.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tourcan.hotel.model.HotelDAO;
import com.tourcan.hotel.model.HotelHibernateDAO;
import com.tourcan.hotel.model.HotelVO;

@WebServlet("/HotelServlet/*")
public class HotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ApplicationContext context;

	@Override
	public void init() throws ServletException {
		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		HotelDAO dao = context.getBean(HotelHibernateDAO.class);

		String[] parameters;
		String name = "";
		Integer id = 0;
		if (request.getPathInfo() != null && (parameters = request.getPathInfo().split("/")).length > 0
				&& parameters[1].length() > 0) {
			try {
				id = Integer.parseInt(parameters[1]);
			} catch (NumberFormatException e) {
				name = parameters[1].trim();
			}
		}
		if (id > 0) {
			response.getWriter().println(context.getBean(Gson.class).toJson(dao.findById(id)));
			// when 404 ??
		} else if (!name.equalsIgnoreCase("")) {
			response.getWriter().println(context.getBean(Gson.class).toJson(dao.findByName(name)));
		} else {
			response.getWriter().println(context.getBean(Gson.class).toJson(dao.getAll()));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		HotelVO vo = null;
		JSONObject err = new JSONObject();

		// mapping json to vo
		try {
			vo = context.getBean(Gson.class).fromJson(request.getReader(), HotelVO.class);
		} catch (JsonSyntaxException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(new JSONObject().put("result", "error: JsonSyntaxException.").toString());
			e.printStackTrace();
			return;
		}

		// checking input
		// hotel_name
		if (vo.getHotel_name() == null) {
			err.put("hotelName", "can't be empty.");
		} else {
			if (vo.getHotel_name().trim().length() == 0)
				err.put("hotelName", "can't be empty.");
			if (vo.getHotel_name().trim().length() > 60)
				err.put("hotelName", "too long.");
		}

		// // hotel_id
		// if (vo.getHotel_id() == null || vo.getHotel_id() <= 0)
		// err.put("hotelId", "must provide.");
		if (vo.getHotel_id() != null)
			err.put("hotelId", "not allowed for insert.");

		// region_id
		if (vo.getRegion_id() == null || vo.getRegion_id() < 0 || vo.getRegion_id() == 0)
			err.put("regionId", "must provide.");

		// phone_addr
		if (vo.getHotel_addr() == null) {
			err.put("hotelAddr", "can't be empty.");
		} else {
			if (vo.getHotel_addr().trim().length() == 0)
				err.put("hotelAddr", "can't be empty.");
			if (vo.getHotel_addr().trim().length() >= 60)
				err.put("hotelAddr", "too long.");
		}

		// hotel_price
		if (vo.getHotel_price() == null) {
			vo.setHotel_price(-1.0);
		} else {
			if (vo.getHotel_price() <= -1)
				err.put("hotelPrice", "invalid price range.");
		}

		// hotel_phone
		if (vo.getHotel_phone() == null) {
			vo.setHotel_phone("");
		} else {
			if (vo.getHotel_phone().trim().length() >= 20)
				err.put("hotelPhone", "too long.");
		}

		// hotel_class
		if (vo.getHotel_class() == null) {
			vo.setHotel_class(-1);
		} else {
			if (vo.getHotel_class() <= -1 || vo.getHotel_class() > 10)
				err.put("hotelClass", "invalid ranking.");
		}

		// hotel_url
		if (vo.getHotel_url() == null) {
			vo.setHotel_url("");
		} else {
			if (vo.getHotel_phone().trim().length() >= 60)
				err.put("hotelUrl", "too long.");
		}

		// hotel_lat
		if (vo.getHotel_lat() == null) {
			err.put("hotelLat", "invalid coordinate format.");
		} else {
			if (vo.getHotel_lat() > 90 || vo.getHotel_lat() < -90)
				err.put("hotelLat", "invalid coordinate format.");
		}

		// hotel_lng
		if (vo.getHotel_lng() == null) {
			err.put("hotelLat", "invalid coordinate format.");
		} else {
			if (vo.getHotel_lng() > 180 || vo.getHotel_lng() < -180)
				err.put("hotelLat", "invalid coordinate format.");
		}

		// update database
		if (err.length() > 0) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter()
					.println(new JSONObject().put("result", "validation-error").put("message", err).toString());
		} else {
			try {
				context.getBean(HotelHibernateDAO.class).insert(vo);
				response.setStatus(HttpServletResponse.SC_CREATED);
				response.getWriter().println(new JSONObject().put("result", "success").toString());
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().println(new JSONObject().put("result", "error: insert unsuccessful.").toString());
				e.printStackTrace();
			}
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		HotelVO vo = null;
		JSONObject err = new JSONObject();

		// mapping json to vo
		try {
			vo = context.getBean(Gson.class).fromJson(request.getReader(), HotelVO.class);
		} catch (JsonSyntaxException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(new JSONObject().put("result", "error: JsonSyntaxException.").toString());
			e.printStackTrace();
			return;
		}

		// checking input
		// hotel_name
		if (vo.getHotel_name() == null) {
			err.put("hotelName", "can't be empty.");
		} else {
			if (vo.getHotel_name().trim().length() == 0)
				err.put("hotelName", "can't be empty.");
			if (vo.getHotel_name().trim().length() > 60)
				err.put("hotelName", "too long.");
		}

		// hotel_id
		if (vo.getHotel_id() == null || vo.getHotel_id() <= 0)
			err.put("hotelId", "must provide.");

		// region_id
		if (vo.getRegion_id() == null || vo.getRegion_id() < 0 || vo.getRegion_id() == 0)
			err.put("regionId", "must provide.");

		// phone_addr
		if (vo.getHotel_addr() == null) {
			err.put("hotelAddr", "can't be empty.");
		} else {
			if (vo.getHotel_addr().trim().length() == 0)
				err.put("hotelAddr", "can't be empty.");
			if (vo.getHotel_addr().trim().length() >= 60)
				err.put("hotelAddr", "too long.");
		}

		// hotel_price
		if (vo.getHotel_price() == null) {
			vo.setHotel_price(-1.0);
		} else {
			if (vo.getHotel_price() <= -1)
				err.put("hotelPrice", "invalid price range.");
		}

		// hotel_phone
		if (vo.getHotel_phone() == null) {
			vo.setHotel_phone("");
		} else {
			if (vo.getHotel_phone().trim().length() >= 20)
				err.put("hotelPhone", "too long.");
		}

		// hotel_class
		if (vo.getHotel_class() == null) {
			vo.setHotel_class(-1);
		} else {
			if (vo.getHotel_class() <= -1 || vo.getHotel_class() > 10)
				err.put("hotelClass", "invalid ranking.");
		}

		// hotel_url
		if (vo.getHotel_url() == null) {
			vo.setHotel_url("");
		} else {
			if (vo.getHotel_phone().trim().length() >= 60)
				err.put("hotelUrl", "too long.");
		}

		// hotel_lat
		if (vo.getHotel_lat() == null) {
			err.put("hotelLat", "invalid coordinate format.");
		} else {
			if (vo.getHotel_lat() > 90 || vo.getHotel_lat() < -90)
				err.put("hotelLat", "invalid coordinate format.");
		}

		// hotel_lng
		if (vo.getHotel_lng() == null) {
			err.put("hotelLat", "invalid coordinate format.");
		} else {
			if (vo.getHotel_lng() > 180 || vo.getHotel_lng() < -180)
				err.put("hotelLat", "invalid coordinate format.");
		}

		// update database
		if (err.length() > 0) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter()
					.println(new JSONObject().put("result", "validation-error").put("message", err).toString());
		} else {
			try {
				context.getBean(HotelHibernateDAO.class).update(vo);
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println(new JSONObject().put("result", "success").toString());
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().println(new JSONObject().put("result", "error: update unsuccessful.").toString());
				e.printStackTrace();
			}
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		String[] parameters;
		HotelVO vo = null;
		Integer id = 0;
		if (request.getPathInfo() != null && (parameters = request.getPathInfo().split("/")).length > 0
				&& parameters[1].length() > 0) {
			try {
				id = Integer.parseInt(parameters[1]);
			} catch (NumberFormatException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter()
						.println(new JSONObject().put("result", "error: number format exception.").toString());
				e.printStackTrace();
				return;
			}
			if (id <= 0) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().println(new JSONObject().put("result", "error: id invalid.").toString());
				return;
			}
			if ((context.getBean(HotelHibernateDAO.class).findById(id)) == null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println(new JSONObject().put("result", "error: id not exist.").toString());
				return;
			}
			vo = context.getBean(HotelVO.class);
			vo.setHotel_id(id);
			try {
				context.getBean(HotelHibernateDAO.class).delete(vo);
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println(new JSONObject().put("result", "success").toString());
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().println(new JSONObject().put("result", "error: delete unsuccessful.").toString());
				e.printStackTrace();
				return;
			}
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(new JSONObject().put("result", "error: id invalid.").toString());
		}
	}
}
