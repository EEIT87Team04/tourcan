package com.tourcan.eat.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.tika.Tika;

import com.tourcan.att.model.AttVO;
import com.tourcan.eat.model.EatDAO;
import com.tourcan.photo.model.PhotoDAO_interface;
import com.tourcan.photo.model.PhotoVO;
import com.tourcan.util.ApplicationContextUtils;

@Path("/")
public class EatService {
	@Context
	HttpServletRequest request;

	@Context
	HttpServletResponse response;

	EatDAO dao = (EatDAO) ApplicationContextUtils.getContext().getBean("eatDAO");
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
			request.setAttribute("pageName", "美食");
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
			request.setAttribute("pageName", "美食");
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
			request.setAttribute("pageName", "美食");
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
			List<String> imgs = new ArrayList<String>();
			
			for (AttVO avo : vos) {
//				System.out.println(request.getPathInfo());
				List<PhotoVO> pvo;
				if ((pvo = pdao.findByAttId(avo.getAtt_id())) != null && pvo.size() > 0) {
					imgs.add(request.getContextPath() + request.getServletPath() + "/" + avo.getAtt_id() + "/photos/"
							+ pvo.get(0).getPhoto_id());
				}else{
					imgs.add(request.getContextPath() + "/images/noInfo.jpg");
				}
			}
			// 200 OK
			request.setAttribute("imgs", imgs);
			request.setAttribute("attVO", vos);
			request.setAttribute("pageName", "美食");
			request.getRequestDispatcher("/WEB-INF/att/fs_List.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
