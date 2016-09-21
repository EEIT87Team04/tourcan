package com.tourcan.att.controller;

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

import com.tourcan.att.model.AttDAO_interface;
import com.tourcan.att.model.AttVO;
import com.tourcan.photo.model.PhotoDAO_interface;
import com.tourcan.photo.model.PhotoVO;
import com.tourcan.region.model.RegionVO;
import com.tourcan.util.ApplicationContextUtils;

@Path("/")
public class AttRestService {

	@Context
	HttpServletRequest request;

	@Context
	HttpServletResponse response;

	AttDAO_interface dao = (AttDAO_interface) ApplicationContextUtils.getContext().getBean("attDAO");
	PhotoDAO_interface pdao = (PhotoDAO_interface) ApplicationContextUtils.getContext().getBean("photoDAO");

	@GET
	@Path("{id: [0-9]+}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryById(@PathParam("id") Integer id) {
		AttVO vo;
		if ((vo = dao.findById(id)) == null) {
			// 404 Not found
			HashMap<String, Object> msg = new HashMap<String, Object>();
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
			AttVO vo = dao.findById(id);
			// 200 OK
			request.setAttribute("attVO", vo);
			ArrayList<String> imgs = new ArrayList<String>();
			for (PhotoVO pvo : pdao.findByAttId(id))
				imgs.add(request.getContextPath() + request.getServletPath() + request.getPathInfo() + "/photos/"
						+ pvo.getPhoto_id());
			request.setAttribute("imgs", imgs);
			request.getRequestDispatcher("/WEB-INF/att/fs_listOneAtt.jsp").forward(request, response);
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
			for (PhotoVO pvo : pdao.findByAttId(id))
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
		AttVO vo;
		PhotoVO pvo;
		try {
			OutputStream out = response.getOutputStream();
			if ((vo = dao.findById(id)) == null || (pvo = pdao.findById(pid)) == null
					|| vo.getAtt_id() != pvo.getAtt_id()) {
				// 404 Not found
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else if (vo.getAtt_id() != pvo.getAtt_id()) {
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
		List<AttVO> vos;
		if ((vos = dao.findByName(name)) == null) {
			// 404 Not found
			HashMap<String, Object> msg = new HashMap<String, Object>();
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
			List<AttVO> vos = dao.findByName(name);
			// 200 OK
			request.setAttribute("attVO", vos);
			request.getRequestDispatcher("/WEB-INF/att/fs_List.jsp").forward(request, response);
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
		List<AttVO> vos;
		if ((vos = dao.findByRegionId(region_id)) == null) {
			// 404 Not found
			HashMap<String, Object> msg = new HashMap<String, Object>();
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
			List<AttVO> vos = dao.findByRegionId(region_id);
			// 200 OK
			request.setAttribute("attVO", vos);
			request.getRequestDispatcher("/WEB-INF/att/fs_List.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response queryAll() {
		List<AttVO> vos;
		if ((vos = dao.getAll()) == null) {
			// 404 Not found
			HashMap<String, Object> msg = new HashMap<String, Object>();
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
			List<AttVO> vos = dao.getAll();
			// 200 OK
			request.setAttribute("attVO", vos);
			request.getRequestDispatcher("/WEB-INF/att/fs_List.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response insertAtt(AttVO vo) {
		HashMap<String, Object> msg = null;
		// for general message, such as OK, failure
		HashMap<String, String> err = null;
		// for column-specific message, such as att_name is invalid.

		if (vo.getAtt_id() != null) {
			if ((dao.findById(vo.getAtt_id())) == null) {
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

		String attName = vo.getAtt_name();
		if (attName == null || attName.trim().isEmpty() || attName.trim().length() >= 50)
			err.put("att_name", "請輸入景點名稱。");

		RegionVO regionVO = vo.getRegionVO();
		if (regionVO == null || regionVO.getRegion_id() == 0)
			err.put("region_id", "請選擇地區代號。");

		String attAddr = vo.getAtt_addr();
		if (attAddr == null || attAddr.trim().isEmpty())
			err.put("att_addr", "請輸入景點地址。");

		Boolean attEat = vo.getAtt_eat();
		if (attEat == null)
			err.put("att_eat", "無效的吃貨屬性。");

		String attIntro = vo.getAtt_intro();
		if (attIntro == null || attIntro.trim().isEmpty())
			err.put("att_intro", "請輸入景點介紹。");

		String appOpen = vo.getAtt_open();
		if (appOpen == null || appOpen.trim().isEmpty())
			err.put("att_open", "請輸入開放時間。");

		Double attPrice = vo.getAtt_price();
		if (attPrice == null || attPrice < 0)
			err.put("att_price", "請輸入最低消費金額。");

		Integer attStaytime = vo.getAtt_staytime();
		if (attStaytime == null || attStaytime < -1)
			err.put("att_staytime", "請輸入滯留時間。");

		String attUrl = vo.getAtt_url();
		if (attUrl == null || attUrl.trim().isEmpty())
			err.put("att_url", "無效的景點網址。");

		Double attLat = vo.getAtt_lat();
		if (attLat == null || attLat < -90 || attLat > 90)
			err.put("att_lat", "移動座標以設定經緯度。");

		Double attLng = vo.getAtt_lng();
		if (attLng == null || attLng < -180 || attLng > 180)
			err.put("att_lng", "移動座標以設定經緯度。");

		String attPhone = vo.getAtt_phone();
		if (attPhone == null || attPhone.trim().isEmpty() || attPhone.trim().length() >= 50) {
			String errMsg = "請勿空白。";
			err.put("att_phone", errMsg);
		} else if (!attPhone.matches("[0-9]{7,}")) {
			String errMsg = "請確認您的電話格式。";
			err.put("att_phone", errMsg);
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
				msg.put("att_id", vo.getAtt_id());
				msg.put("result", "success");
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
	public Response updateAtt(AttVO vo) {
		HashMap<String, String> msg = null;
		// for general message, such as OK, failure
		HashMap<String, String> err = new HashMap<String, String>();
		// for column-specific message, such as att_name is invalid.

		// checking input
		// att_id
		if (vo.getAtt_id() == null) {
			err.put("att_id", "must provide.");
		} else {
			if ((dao.findById(vo.getAtt_id())) == null) {
				// 404 Not found
				msg = new HashMap<String, String>();
				msg.put("result", "failure");
				msg.put("error", "id not exist.");
				return Response.status(Status.NOT_FOUND).entity(msg).build();
			}
		}

		String attName = vo.getAtt_name();
		if (attName == null || attName.trim().isEmpty() || attName.trim().length() >= 50)
			err.put("att_name", "請輸入景點名稱。");

		RegionVO regionVO = vo.getRegionVO();
		if (regionVO == null || regionVO.getRegion_id() == 0)
			err.put("region_id", "請選擇地區代號。");

		String attAddr = vo.getAtt_addr();
		if (attAddr == null || attAddr.trim().isEmpty())
			err.put("att_addr", "請輸入景點地址。");

		Boolean attEat = vo.getAtt_eat();
		if (attEat == null)
			err.put("att_eat", "無效的吃貨屬性。");

		String attIntro = vo.getAtt_intro();
		if (attIntro == null || attIntro.trim().isEmpty())
			err.put("att_intro", "請輸入景點介紹。");

		String appOpen = vo.getAtt_open();
		if (appOpen == null || appOpen.trim().isEmpty())
			err.put("att_open", "請輸入開放時間。");

		Double attPrice = vo.getAtt_price();
		if (attPrice == null || attPrice < 0)
			err.put("att_price", "請輸入最低消費金額。");

		Integer attStaytime = vo.getAtt_staytime();
		if (attStaytime == null || attStaytime < -1)
			err.put("att_staytime", "請輸入滯留時間。");

		String attUrl = vo.getAtt_url();
		if (attUrl == null || attUrl.trim().isEmpty())
			err.put("att_url", "無效的景點網址。");

		Double attLat = vo.getAtt_lat();
		if (attLat == null || attLat < -90 || attLat > 90)
			err.put("att_lat", "移動座標以設定經緯度。");

		Double attLng = vo.getAtt_lng();
		if (attLng == null || attLng < -180 || attLng > 180)
			err.put("att_lng", "移動座標以設定經緯度。");

		String attPhone = vo.getAtt_phone();
		if (attPhone == null || attPhone.trim().isEmpty() || attPhone.trim().length() >= 50) {
			String errMsg = "請勿空白。";
			err.put("att_phone", errMsg);
		} else if (!attPhone.matches("[0-9]{7,}")) {
			String errMsg = "請確認您的電話格式。";
			err.put("att_phone", errMsg);
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
	public Response deleteAtt(@PathParam("id") Integer id) {
		HashMap<String, String> msg = null;
		AttVO vo;
		if ((vo = dao.findById(id)) == null) {
			// 404 Not found
			msg = new HashMap<String, String>();
			msg.put("result", "failure");
			msg.put("error", "id not exist.");
			return Response.status(Status.NOT_FOUND).entity(msg).build();
		} else {
			vo = ApplicationContextUtils.getContext().getBean(AttVO.class);
			vo.setAtt_id(id);
			try {
				dao.delete(id);
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