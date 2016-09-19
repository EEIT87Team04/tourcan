package com.tourcan.eat.controller;

import java.io.IOException;
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

import com.tourcan.att.model.AttVO;
import com.tourcan.eat.model.EatDAO;
import com.tourcan.util.ApplicationContextUtils;

@Path("/")
public class EatService {
	@Context
	HttpServletRequest request;

	@Context
	HttpServletResponse response;

	EatDAO dao = (EatDAO) ApplicationContextUtils.getContext().getBean("eatDAO");

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
			request.getRequestDispatcher("/WEB-INF/att/fs_listOneAtt.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

}
