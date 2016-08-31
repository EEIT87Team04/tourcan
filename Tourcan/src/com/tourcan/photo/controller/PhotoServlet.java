package com.tourcan.photo.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.tourcan.photo.model.PhotoService;
import com.tourcan.photo.model.PhotoVO;

@WebServlet("/PhotoServlet")
@MultipartConfig
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhotoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		System.out.println(request.getContentType());
		
		
		JSONObject checkResult = new JSONObject();

		try {
			Part photoFile = request.getPart("photo");
			byte[] photo_file = new byte[(int) photoFile.getSize()];
			InputStream is = photoFile.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bis.read(photo_file);

			PhotoService src = new PhotoService();

			PhotoVO photoVO = src.insert(photo_file);

			if (photoVO != null) {
				checkResult.append("result", "新增成功");
				response.getWriter().println(checkResult.toString());
			}

		} catch (Exception e) {
			checkResult.append("result", "新增失敗");
			response.getWriter().println(checkResult.toString());
			e.printStackTrace();
		}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
