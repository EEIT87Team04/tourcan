package com.tourcan.hotel.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.tourcan.hotel.model.HotelDAO;
import com.tourcan.hotel.model.HotelVO;
import com.tourcan.util.ApplicationContextUtils;

//@SuppressWarnings("unused")
@Path("hotels")
public class HotelService {
	HotelDAO dao = (HotelDAO) ApplicationContextUtils.getContext().getBean("hotelDAO");

	@GET
	@Path("{id: [0-9]+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryById(@PathParam("id") Integer id) {
		HotelVO vo;
		if ((vo = dao.findById(id)) == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.ok(vo).build();
		}
	}

	@GET
	@Path("{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryByName(@PathParam("name") String name) {
		List<HotelVO> vos;
		if ((vos = dao.findByName(name)) == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.ok(vos).build();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryAll() {
		List<HotelVO> vos = dao.getAll();
		if ((vos = dao.getAll()) == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			return Response.ok(vos).build();
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response insertHotel(HotelVO vo) {

		if (vo.getHotel_id() != null) {
			if ((dao.findById(vo.getHotel_id())) == null) {
				// 404 Not found
				return Response.status(Status.NOT_FOUND).build();
			} else {
				// 409 Conflict
				return Response.status(Status.CONFLICT).build();
			}
		}

		JSONObject err = new JSONObject();

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
			err.put("hotelLng", "invalid coordinate format.");
		} else {
			if (vo.getHotel_lng() > 180 || vo.getHotel_lng() < -180)
				err.put("hotelLng", "invalid coordinate format.");
		}

		// update database
		if (err.length() > 0) {
			// 400 Bad request
			return Response.status(Status.BAD_REQUEST)
					.entity(new JSONObject().put("result", "validation-error").put("message", err).toString()).build();
		} else {
			try {
				dao.insert(vo);
				// 201 Created
				return Response.status(Status.CREATED).build();
				// response.setStatus(HttpServletResponse.SC_CREATED);
				// response.getWriter().println(new JSONObject().put("result",
				// "success").toString());
			} catch (Exception e) {
				e.printStackTrace();
				// 500 Internal server error
				return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(new JSONObject().put("result", "error: insert unsuccessful.").toString()).build();
				// response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				// response.getWriter().println(new JSONObject().put("result",
				// "error: insert unsuccessful.").toString());
			}
		}
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateHotel(HotelVO vo) {

		JSONObject err = new JSONObject();

		// checking input
		// hotel_id
		if (vo.getHotel_id() == null) {
			err.put("hotelId", "must provide.");
		} else {
			if ((dao.findById(vo.getHotel_id())) == null) {
				// 404 Not found
				return Response.status(Status.NOT_FOUND).build();
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
			return Response.status(Status.BAD_REQUEST)
					.entity(new JSONObject().put("result", "validation-error").put("message", err).toString()).build();
		} else {
			try {
				dao.update(vo);
				// 200 OK
				return Response.ok(new JSONObject().put("result", "success").toString()).build();
			} catch (Exception e) {
				e.printStackTrace();
				// 500 Internal server error
				return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(new JSONObject().put("result", "error: update unsuccessful.").toString()).build();
			}
		}
	}

	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteHotel(@PathParam("id") Integer id) {
		HotelVO vo;
		if ((vo = dao.findById(id)) == null) {
			return Response.status(Status.NOT_FOUND).build();
		} else {
			vo = ApplicationContextUtils.getContext().getBean(HotelVO.class);
			vo.setHotel_id(id);
			try {
				dao.delete(vo);
				// 200 OK
				return Response.ok(new JSONObject().put("result", "success")).build();
			} catch (Exception e) {
				e.printStackTrace();
				// 500 Internal server error
				return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(new JSONObject().put("result", "error: delete unsuccessful.")).build();
			}
		}
	}
}