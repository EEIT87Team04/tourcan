package com.tourcan.hotel.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.tika.Tika;

import com.tourcan.hotel.model.HotelDAO;
import com.tourcan.hotel.model.HotelVO;
import com.tourcan.photo.model.PhotoDAO_interface;
import com.tourcan.photo.model.PhotoVO;
import com.tourcan.util.ApplicationContextUtils;

@Path("/")
public class HotelService {

	@Context
	HttpServletRequest request;

	@Context
	HttpServletResponse response;

	HotelDAO dao = (HotelDAO) ApplicationContextUtils.getContext().getBean("hotelDAO");
	PhotoDAO_interface pdao = (PhotoDAO_interface) ApplicationContextUtils.getContext().getBean("photoDAO");

	@GET
	@Path("{id: [0-9]+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryById(@PathParam("id") Integer id) {
		HotelVO vo;
		if ((vo = dao.findById(id)) == null) {
			// 404 Not found
			HashMap<String, String> msg = new HashMap<String, String>();
			msg.put("result", "failure");
			msg.put("error", "id not exist.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			// 200 OK
			return Response.status(Status.OK).entity(vo).build();
		}
	}

	@GET
	@Path("{id: [0-9]+}")
	@Produces({ MediaType.TEXT_HTML })
	public void queryById_static(@PathParam("id") Integer id) {
		response.setCharacterEncoding("UTF-8");
		try {
			HotelVO vo = dao.findById(id);
			// 200 OK
			request.setAttribute("hotelVO", vo);
			ArrayList<String> imgs = new ArrayList<String>();
			for (PhotoVO pvo : pdao.findByHotleId(id))
				imgs.add(request.getContextPath() + request.getServletPath() + request.getPathInfo() + "/photos/"
						+ pvo.getPhoto_id());
			request.setAttribute("imgs", imgs);
			request.setAttribute("pageName", "飯店");
			request.getRequestDispatcher("/WEB-INF/hotel/fs_listOneAtt.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GET
	@Path("{id: [0-9]+}/photos")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryPhotosById(@PathParam("id") Integer id) {
		HashMap<String, Object> msg = new HashMap<String, Object>();
		if (dao.findById(id) == null) {
			// 404 Not found
			msg.put("result", "failure");
			msg.put("error", "id not exist.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			// 200 OK
			ArrayList<String> imgPath = new ArrayList<String>();
			for (PhotoVO pvo : pdao.findByHotleId(id))
				imgPath.add(request.getContextPath() + request.getServletPath() + request.getPathInfo() + "/"
						+ pvo.getPhoto_id());
			msg.put("result", "success");
			msg.put("imgs", imgPath);
			return Response.status(Status.OK).entity(msg).build();
		}
	}

	@GET
	@Path("{id: [0-9]+}/photos/{pid: [0-9]+}")
	public void queryPhotoById(@PathParam("id") Integer id, @PathParam("pid") Integer pid) {
		HotelVO vo;
		PhotoVO pvo;
		try {
			OutputStream out = response.getOutputStream();
			if ((vo = dao.findById(id)) == null || (pvo = pdao.findById(pid)) == null
					|| vo.getHotel_id() != pvo.getHotel_id()) {
				// 404 Not found
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else if (vo.getHotel_id() != pvo.getHotel_id()) {
				// 404 Not found
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				// 200 OK
				byte[] photo = pvo.getPhoto_file();
				response.setStatus(HttpServletResponse.SC_OK);
				response.setHeader("Content-Type",
						ApplicationContextUtils.getContext().getBean(Tika.class).detect(photo));
				response.setHeader("Content-Transfer-Encoding", "binary");
				response.setHeader("Content-Disposition", "inline");
				response.setContentLength(photo.length);
				out.write(photo);
			}
		} catch (IOException e) {
			// 500 Internal server error
			e.printStackTrace();
		}
	}

	@GET
	@Path("/name/{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryByName(@PathParam("name") String name) {
		List<HotelVO> vos;
		if ((vos = dao.findByName(name)) == null) {
			// 404 Not found
			HashMap<String, String> msg = new HashMap<String, String>();
			msg.put("result", "failure");
			msg.put("error", "no matched entry exist.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			// 200 OK
			return Response.status(Status.OK).entity(vos).build();
		}
	}

	@GET
	@Path("/name/{name}")
	@Produces({ MediaType.TEXT_HTML })
	public void queryByName_static(@PathParam("name") String name) {
		response.setCharacterEncoding("UTF-8");
		try {
			List<HotelVO> vos = dao.findByName(name);
			// 200 OK
			request.setAttribute("hotelVO", vos);
			request.setAttribute("pageName", "飯店");
			request.getRequestDispatcher("/WEB-INF/hotel/fs_List.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GET
	@Path("/region/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryByRegion(@PathParam("id") Integer region_id) {
		List<HotelVO> vos;
		if ((vos = dao.findByRegionId(region_id)) == null) {
			// 404 Not found
			HashMap<String, String> msg = new HashMap<String, String>();
			msg.put("result", "failure");
			msg.put("error", "no matched entry exist.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			// 200 OK
			return Response.status(Status.OK).entity(vos).build();
		}
	}

	@GET
	@Path("/region/{id}")
	@Produces({ MediaType.TEXT_HTML })
	public void queryByRegion_static(@PathParam("id") Integer region_id) {
		response.setCharacterEncoding("UTF-8");
		try {
			List<HotelVO> vos = dao.findByRegionId(region_id);
			// 200 OK
			request.setAttribute("hotelVO", vos);
			request.setAttribute("pageName", "飯店");
			request.getRequestDispatcher("/WEB-INF/hotel/fs_List.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryAll() {
		List<HotelVO> vos = dao.getAll();
		if ((vos = dao.getAll()) == null) {
			// 404 Not found
			HashMap<String, String> msg = new HashMap<String, String>();
			msg.put("result", "failure");
			msg.put("error", "no data.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			// 200 OK
			return Response.status(Status.OK).entity(vos).build();
		}
	}

	@GET
	@Produces({ MediaType.TEXT_HTML })
	public void queryAll_static() {
		response.setCharacterEncoding("UTF-8");
		try {
			List<HotelVO> vos = dao.getAll();
			List<String> imgs = new ArrayList<String>();
				
			for (HotelVO hvo : vos) {
//				System.out.println(request.getPathInfo());

				List<PhotoVO> pvo;
				if ((pvo = pdao.findByHotleId(hvo.getHotel_id())) != null && pvo.size() > 0) {
					imgs.add(request.getContextPath() + request.getServletPath() + "/" + hvo.getHotel_id() + "/photos/"
							+ pvo.get(0).getPhoto_id());
				}else{
					imgs.add(request.getContextPath() + "/images/noInfo.jpg");
				}
			}
			// 200 OK
			request.setAttribute("imgs", imgs);
			request.setAttribute("hotelVO", vos);
			request.setAttribute("pageName", "飯店");
			request.getRequestDispatcher("/WEB-INF/hotel/fs_List.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response insertHotel(HotelVO vo) {
		HashMap<String, Object> msg = null;
		// for general message, such as OK, failure
		HashMap<String, String> err = null;
		// for column-specific message, such as hotel_name is invalid.

		if (vo.getHotel_id() != null) {
			if ((dao.findById(vo.getHotel_id())) == null) {
				// 404 Not found
				msg = new HashMap<String, Object>();
				msg.put("result", "failure");
				msg.put("error", "id not exist.");
				return Response.status(Status.NOT_FOUND).entity(msg).build();
			} else {
				// 409 Conflict
				msg = new HashMap<String, Object>();
				msg.put("result", "failure");
				msg.put("error", "id already exist.");
				return Response.status(Status.CONFLICT).entity(msg).build();
			}
		}

		err = new HashMap<String, String>();

		// hotel_name
		if (vo.getHotel_name() == null) {
			err.put("hotel_name", "can't be empty.");
		} else {
			if (vo.getHotel_name().trim().length() == 0)
				err.put("hotel_name", "can't be empty.");
			if (vo.getHotel_name().trim().length() > 60)
				err.put("hotel_name", "too long.");
		}

		// region_id
		if (vo.getRegionVO() == null || vo.getRegionVO().getRegion_id() < 0 || vo.getRegionVO().getRegion_id() == 0)
			err.put("region_id", "must provide.");

		// phone_addr
		if (vo.getHotel_addr() == null) {
			err.put("hotel_addr", "can't be empty.");
		} else {
			if (vo.getHotel_addr().trim().length() == 0)
				err.put("hotel_addr", "can't be empty.");
			if (vo.getHotel_addr().trim().length() >= 60)
				err.put("hotel_addr", "too long.");
		}

		// hotel_price
		if (vo.getHotel_price() == null) {
			vo.setHotel_price(-1.0);
		} else {
			if (vo.getHotel_price() <= -1)
				err.put("hotel_price", "invalid price range.");
		}

		// hotel_phone
		if (vo.getHotel_phone() == null) {
			vo.setHotel_phone("");
		} else {
			if (vo.getHotel_phone().trim().length() >= 20)
				err.put("hotel_phone", "too long.");
		}

		// hotel_class
		if (vo.getHotel_class() == null) {
			vo.setHotel_class(-1);
		} else {
			if (vo.getHotel_class() <= -1 || vo.getHotel_class() > 10)
				err.put("hotel_class", "invalid ranking.");
		}

		// hotel_url
		if (vo.getHotel_url() == null) {
			vo.setHotel_url("");
		} else {
			if (vo.getHotel_phone().trim().length() >= 60)
				err.put("hotel_url", "too long.");
		}

		// hotel_lat
		if (vo.getHotel_lat() == null) {
			err.put("hotel_lat", "invalid coordinate format.");
		} else {
			if (vo.getHotel_lat() > 90 || vo.getHotel_lat() < -90)
				err.put("hotel_lat", "invalid coordinate format.");
		}

		// hotel_lng
		if (vo.getHotel_lng() == null) {
			err.put("hotel_lng", "invalid coordinate format.");
		} else {
			if (vo.getHotel_lng() > 180 || vo.getHotel_lng() < -180)
				err.put("hotel_lng", "invalid coordinate format.");
		}

		// update database
		if (err.size() > 0) {
			// 400 Bad request
			msg = new HashMap<String, Object>();
			msg.put("result", "validation-error");
			msg.put("error", "invalid user input.");
			msg.put("validation", err);
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		} else {
			try {
				dao.insert(vo);
				// 201 Created
				msg = new HashMap<String, Object>();
				msg.put("result", "success");
				msg.put("hotel_id", vo.getHotel_id());
				return Response.status(Status.CREATED).entity(msg).build();
			} catch (Exception e) {
				e.printStackTrace();
				// 500 Internal server error
				msg = new HashMap<String, Object>();
				msg.put("result", "failure");
				msg.put("error", "insert unsuccessful.");
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(msg).build();
			}
		}
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateHotel(HotelVO vo) {
		HashMap<String, String> msg = null;
		// for general message, such as OK, failure
		HashMap<String, String> err = new HashMap<String, String>();
		// for column-specific message, such as hotel_name is invalid.

		// checking input
		// hotel_id
		if (vo.getHotel_id() == null) {
			err.put("hotel_id", "must provide.");
		} else {
			if ((dao.findById(vo.getHotel_id())) == null) {
				// 404 Not found
				msg = new HashMap<String, String>();
				msg.put("result", "failure");
				msg.put("error", "id not exist.");
				return Response.status(Status.NOT_FOUND).entity(msg).build();
			}
		}

		// hotel_name
		if (vo.getHotel_name() == null) {
			err.put("hotel_name", "can't be empty.");
		} else {
			if (vo.getHotel_name().trim().length() == 0)
				err.put("hotel_name", "can't be empty.");
			if (vo.getHotel_name().trim().length() > 60)
				err.put("hotel_name", "too long.");
		}

		// region_id
		if (vo.getRegionVO() == null || vo.getRegionVO().getRegion_id() < 0 || vo.getRegionVO().getRegion_id() == 0)
			err.put("region_id", "must provide.");

		// phone_addr
		if (vo.getHotel_addr() == null) {
			err.put("hotel_addr", "can't be empty.");
		} else {
			if (vo.getHotel_addr().trim().length() == 0)
				err.put("hotel_addr", "can't be empty.");
			if (vo.getHotel_addr().trim().length() >= 60)
				err.put("hotel_addr", "too long.");
		}

		// hotel_price
		if (vo.getHotel_price() == null) {
			vo.setHotel_price(-1.0);
		} else {
			if (vo.getHotel_price() <= -1)
				err.put("hotel_price", "invalid price range.");
		}

		// hotel_phone
		if (vo.getHotel_phone() == null) {
			vo.setHotel_phone("");
		} else {
			if (vo.getHotel_phone().trim().length() >= 20)
				err.put("hotel_phone", "too long.");
		}

		// hotel_class
		if (vo.getHotel_class() == null) {
			vo.setHotel_class(-1);
		} else {
			if (vo.getHotel_class() <= -1 || vo.getHotel_class() > 10)
				err.put("hotel_class", "invalid ranking.");
		}

		// hotel_url
		if (vo.getHotel_url() == null) {
			vo.setHotel_url("");
		} else {
			if (vo.getHotel_phone().trim().length() >= 60)
				err.put("hotel_url", "too long.");
		}

		// hotel_lat
		if (vo.getHotel_lat() == null) {
			err.put("hotel_lat", "invalid coordinate format.");
		} else {
			if (vo.getHotel_lat() > 90 || vo.getHotel_lat() < -90)
				err.put("hotel_lat", "invalid coordinate format.");
		}

		// hotel_lng
		if (vo.getHotel_lng() == null) {
			err.put("hotel_lng", "invalid coordinate format.");
		} else {
			if (vo.getHotel_lng() > 180 || vo.getHotel_lng() < -180)
				err.put("hotel_lng", "invalid coordinate format.");
		}

		// update database
		if (err.size() > 0) {
			// 400 Bad request
			msg = new HashMap<String, String>();
			msg.put("result", "validation-error");
			msg.put("error", "invalid user input.");
			msg.put("validation", err.toString());
			return Response.status(Status.BAD_REQUEST).entity(msg).build();
		} else {
			try {
				dao.update(vo);
				// 200 OK
				msg = new HashMap<String, String>();
				msg.put("result", "success");
				return Response.status(Status.OK).entity(msg).build();
			} catch (Exception e) {
				e.printStackTrace();
				// 500 Internal server error
				msg = new HashMap<String, String>();
				msg.put("result", "failure");
				msg.put("error", "update unsuccessful.");
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(msg).build();
			}
		}
	}

	@DELETE
	@Path("{id: [0-9]+}") // Be careful, just writing "{id}" will NOT work.
							// Maybe conflict with GET ??
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteHotel(@PathParam("id") Integer id) {
		HashMap<String, String> msg = null;
		HotelVO vo;
		if ((vo = dao.findById(id)) == null) {
			// 404 Not found
			msg = new HashMap<String, String>();
			msg.put("result", "failure");
			msg.put("error", "id not exist.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			vo = ApplicationContextUtils.getContext().getBean(HotelVO.class);
			vo.setHotel_id(id);
			try {
				List<PhotoVO> photos = pdao.findByHotleId(id);
				for (PhotoVO photoVO : photos) {
					pdao.delete(photoVO.getPhoto_id());
				}
				dao.delete(vo);
				// 200 OK
				msg = new HashMap<String, String>();
				msg.put("result", "success");
				return Response.status(Status.OK).entity(msg).build();
			} catch (Exception e) {
				e.printStackTrace();
				// 500 Internal server error
				msg = new HashMap<String, String>();
				msg.put("result", "failure");
				msg.put("error", "delete unsuccessful.");
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(msg).build();
			}
		}
	}
}