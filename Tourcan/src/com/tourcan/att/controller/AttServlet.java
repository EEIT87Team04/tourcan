package com.tourcan.att.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.tourcan.att.model.AttDAO;
import com.tourcan.att.model.AttService;
import com.tourcan.att.model.AttVO;
import com.tourcan.region.model.RegionVO;

@WebServlet("/AttServlet")
public class AttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AttServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer(128);
		String json;
		while ((json = br.readLine()) != null)
			sb.append(json);
		json = sb.toString();

		JSONObject err = new JSONObject();

		// Query by att_id
		Integer attId = null;
		try {
			attId = new Integer(request.getParameter("att_id"));
		} catch (Exception e) {
			err.append("attId", "編號只能為整數");
			response.getWriter().println(err.toString());
			// e.printStackTrace();
		}

		if (attId != null) {
			AttService asv = new AttService();
			AttVO attVO = asv.getOneMem(attId);

			if (attVO != null) {
				try {
					Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
					String attVOGson = gson.toJson(attVO);
					response.getWriter().println(attVOGson);
				} catch (Exception e) {
					err.append("attId", "無此編號");
					response.getWriter().println(err.toString());
					// e.printStackTrace();
				}
			} else {
				err.append("attId", "無此編號");
				response.getWriter().println(err.toString());
			}

		} else {
			err.append("attId", "無此編號");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer(128);
		String json;
		while ((json = br.readLine()) != null)
			sb.append(json);
		json = sb.toString();

		JSONObject err = new JSONObject();

		Integer attId = null;
		String attName = null;
		Integer regionId = null;
		String attAddr = null;
		Boolean attEat = null;
		String attIntro = null;
		String appOpen = null;
		String attPhone = null;
		Double attPrice = null;
		Integer attStaytime = null;
		String attUrl = null;
		Double attLat = null;
		Double attLng = null;

		// update

		try {
			JSONObject obj = new JSONObject(json); // received and parsed JSON

			try {
				attId = obj.getInt("attId");
				if (attId == null || attId < 0) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attId", ""); // do not need error in update
				// e.printStackTrace();
			}

			try {
				attName = obj.getString("attName");
				if (attName == null || attName.trim().isEmpty() || attName.trim().length() >= 50) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attName", "無效的景點名稱。");
				// e.printStackTrace();
			}

			try {
				regionId = obj.getInt("regionId");
				if (regionId == null || regionId < 0) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("regionId", "無效的地區代號。");
				// e.printStackTrace();
			}

			try {
				attAddr = obj.getString("attAddr");
				if (attAddr == null || attAddr.trim().isEmpty()) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attAddr", "無效的景點地址。");
				// e.printStackTrace();
			}

			try {
				attEat = obj.getBoolean("attEat");

			} catch (Exception e) {
				err.append("attAddr", "無效的吃貨屬性。");
				// e.printStackTrace();
			}

			try {
				attIntro = obj.getString("attIntro");
				if (attIntro == null || attIntro.trim().isEmpty()) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attIntro", "無效的景點介紹。");
				// e.printStackTrace();
			}

			try {
				appOpen = obj.getString("attOpen");
				if (appOpen == null || appOpen.trim().isEmpty()) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attOpen", "無效的開放資訊。");
				// e.printStackTrace();
			}

			try {
				attPhone = obj.getString("attPhone");
				if (attPhone == null || attPhone.trim().isEmpty() || attPhone.trim().length() >= 50) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attPhone", "無效的聯絡電話。");
				// e.printStackTrace();
			}

			try {
				attPrice = obj.getDouble("attPrice");
				if (attPrice == null || attPrice < 0) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attPrice", "無效的基本消費。");
				// e.printStackTrace();
			}

			try {
				attStaytime = obj.getInt("attStaytime");
				if (attStaytime == null || attStaytime < -1) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attStaytime", "無效的滯留時間。");
				// e.printStackTrace();
			}

			try {
				attUrl = obj.getString("attUrl");
				if (attUrl == null || attUrl.trim().isEmpty()) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attUrl", "無效的景點網址。");
				// e.printStackTrace();
			}

			try {
				attLat = obj.getDouble("attLat");
				if (attLat == null || attLat < -90 || attLat > 90) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attLat", "無效的緯度。");
				// e.printStackTrace();
			}

			try {
				attLng = obj.getDouble("attLng");
				if (attLng == null || attLng < -180 || attLng > 180) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attLng", "無效的經度。");
				// e.printStackTrace();
			}

			if (err.length() > 0) {
				throw new Exception();
			} else {

				AttService srv = new AttService();

				srv.update(attName, regionId, attAddr, attEat, attIntro, appOpen, attPhone, attPrice, attStaytime,
						attUrl, attLat, attLng, attId);
				err.append("result", "修改成功");
				response.getWriter().println(err.toString());
			}
		} catch (Exception e) {
			err.append("result", "修改失敗");
			response.getWriter().println(err.toString());
			// e.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
