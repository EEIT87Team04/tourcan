package com.tourcan.hotel.controller;

import java.io.IOException;
import java.util.List;

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

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");

		String[] parameters;
		if (req.getPathInfo() != null && (parameters = req.getPathInfo().split("/")).length > 0
				&& parameters[1].length() > 0) {
			req.setAttribute("pathParam", parameters);
			if (parameters[1].trim().length() > 0) {
				try {
					req.setAttribute("queryId", Integer.parseInt(parameters[1].trim()));
				} catch (NumberFormatException e) {
					req.setAttribute("queryString", parameters[1].trim());
				}
			}
		}

		super.service(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HotelDAO dao = context.getBean(HotelHibernateDAO.class);

		String name = (String) request.getAttribute("queryString");
		Integer id = (Integer) request.getAttribute("queryId");

		if (id != null) {
			// Query by Id
			HotelVO vo = context.getBean(HotelVO.class);
			if ((vo = dao.findById(id)) == null) {
				// 404 Not found
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				// 200 OK
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println(context.getBean(Gson.class).toJson(vo));
			}
		} else if (name != null) {
			// Query by String/Name
			List<HotelVO> vos = dao.findByName(name);
			if (vos == null || vos.size() == 0) {
				// 404 Not found
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				// 200 OK
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println(context.getBean(Gson.class).toJson(vos));
			}
		} else {
			// Query all
			List<HotelVO> vos = dao.getAll();
			if (vos == null || vos.size() == 0) {
				// 404 Not found
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				// 200 OK
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println(context.getBean(Gson.class).toJson(vos));
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Query that had either Id or String.
		if (request.getAttribute("queryString") != null || request.getAttribute("queryId") != null) {
			// 405 Method not allowed
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return;
		}

		HotelVO vo = null;
		JSONObject err = new JSONObject();

		// mapping json to vo
		try {
			vo = context.getBean(Gson.class).fromJson(request.getReader(), HotelVO.class);
		} catch (JsonSyntaxException e) {
			// 400 Bad request
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(new JSONObject().put("result", "error: JsonSyntaxException.").toString());
			e.printStackTrace();
			return;
		}

		// checking input
		// hotel_id
		if (vo.getHotel_id() != null) {
			if ((context.getBean(HotelHibernateDAO.class).findById(vo.getHotel_id())) == null) {
				// 404 Not found
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				// 409 Conflict
				response.setStatus(HttpServletResponse.SC_CONFLICT);
			}
			return;
		}

		// hotel_name
		if (vo.getHotel_name() == null) {
			err.put("hotelName", "can't be empty.");
		} else {
			if (vo.getHotel_name().trim().length() == 0)
				err.put("hotelName", "can't be empty.");
			if (vo.getHotel_name().trim().length() > 60)
				err.put("hotelName", "too long.");
		}

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
			// 400 Bad request
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter()
					.println(new JSONObject().put("result", "validation-error").put("message", err).toString());
		} else {
			try {
				context.getBean(HotelHibernateDAO.class).insert(vo);
				// 201 Created
				response.setStatus(HttpServletResponse.SC_CREATED);
				response.getWriter().println(new JSONObject().put("result", "success").toString());
			} catch (Exception e) {
				// 500 Internal server error
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().println(new JSONObject().put("result", "error: insert unsuccessful.").toString());
				e.printStackTrace();
			}
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Query that had either Id or String.
		if (request.getAttribute("queryString") != null) {
			// 400 Bad request
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		HotelVO vo = null;
		JSONObject err = new JSONObject();

		// mapping json to vo
		try {
			vo = context.getBean(Gson.class).fromJson(request.getReader(), HotelVO.class);
		} catch (JsonSyntaxException e) {
			// 400 Bad request
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(new JSONObject().put("result", "error: JsonSyntaxException.").toString());
			e.printStackTrace();
			return;
		}

		// checking input
		// hotel_id
		if (request.getAttribute("queryId") != null && (Integer) request.getAttribute("queryId") != vo.getHotel_id()) {
			// 400 Bad request
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println(new JSONObject().put("result", "error: resource Id not match.").toString());
			return;
		}
		if (vo.getHotel_id() == null) {
			err.put("hotelId", "must provide.");
		} else {
			if ((context.getBean(HotelHibernateDAO.class).findById(vo.getHotel_id())) == null) {
				// 404 Not found
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		}

		// hotel_name
		if (vo.getHotel_name() == null) {
			err.put("hotelName", "can't be empty.");
		} else {
			if (vo.getHotel_name().trim().length() == 0)
				err.put("hotelName", "can't be empty.");
			if (vo.getHotel_name().trim().length() > 60)
				err.put("hotelName", "too long.");
		}

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
			// 400 Bad request
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter()
					.println(new JSONObject().put("result", "validation-error").put("message", err).toString());
		} else {
			try {
				context.getBean(HotelHibernateDAO.class).update(vo);
				// 200 OK
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println(new JSONObject().put("result", "success").toString());
			} catch (Exception e) {
				// 500 Internal server error
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().println(new JSONObject().put("result", "error: update unsuccessful.").toString());
				e.printStackTrace();
			}
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HotelDAO dao = context.getBean(HotelHibernateDAO.class);
		HotelVO vo = null;
		String name = (String) request.getAttribute("queryString");
		Integer id = (Integer) request.getAttribute("queryId");

		if (id != null) {
			if ((vo = dao.findById(id)) == null) {
				// 404 Not found
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.getWriter().println(new JSONObject().put("result", "error: id not exist.").toString());
				return;
			} else {
				vo = context.getBean(HotelVO.class);
				vo.setHotel_id(id);
				try {
					dao.delete(vo);
					// 200 OK
					response.setStatus(HttpServletResponse.SC_OK);
					response.getWriter().println(new JSONObject().put("result", "success").toString());
				} catch (Exception e) {
					// 500 Internal server error
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					response.getWriter()
							.println(new JSONObject().put("result", "error: delete unsuccessful.").toString());
					e.printStackTrace();
					return;
				}
			}
		} else if (name != null) {
			// 400 Bad request
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			// 405 Method not allowed
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}
}
