package com.tourcan.att.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tourcan.att.model.AttDAO;
import com.tourcan.att.model.AttService;
import com.tourcan.att.model.AttVO;
import com.tourcan.region.model.RegionDAO;
import com.tourcan.region.model.RegionDAO_interface;
import com.tourcan.region.model.RegionHibernateDAO;
import com.tourcan.region.model.RegionVO;

@WebServlet("/AttServlet")
public class AttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AttServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//----------------INSERT----------------
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer(128);
		String json;
		while ((json = br.readLine()) != null)
			sb.append(json);
		json = sb.toString();
		 System.out.println(json);

		JSONObject err = new JSONObject(); // checking result
		
		String attName = null;
		Integer regionId = null;
		RegionVO regionVO = null;
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
		
		
		try {
			JSONObject obj = new JSONObject(json); // received and parsed JSON

			
			
			try {
				attName = obj.getString("att_name");
				if (attName == null || attName.trim().isEmpty() || attName.trim().length() >= 50) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("att_name", "無效的景點名稱。");
//				e.printStackTrace();
			}

			try {
				regionId = obj.getInt("region_id");
//				obj.remove("region_id");
				if (regionId == null || regionId < 0) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("region_id", "無效的地區代號。");
				e.printStackTrace();
			}

			try {
				attAddr = obj.getString("att_addr");
				if (attAddr == null || attAddr.trim().isEmpty()) {
					throw new Exception();
				}
			
			} catch (Exception e) {
				err.append("att_addr", "無效的景點地址。");
//				e.printStackTrace();
			}

			try {
				attEat = obj.getBoolean("att_eat");
				
			} catch (Exception e) {
				err.append("att_eat", "無效的吃貨屬性。");
//				e.printStackTrace();
			}

			try {
				attIntro = obj.getString("att_intro");
				if (attIntro == null || attIntro.trim().isEmpty()) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("att_intro", "無效的景點介紹。");
//				e.printStackTrace();
			}

			try {
				appOpen = obj.getString("att_open");
				if (appOpen == null || appOpen.trim().isEmpty()) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("att_open", "無效的開放資訊。");
//				e.printStackTrace();
			}

			try {
				attPhone = obj.getString("att_phone");
				if (attPhone == null || attPhone.trim().isEmpty() || attPhone.trim().length() >= 50) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("att_phone", "無效的聯絡電話。");
//				e.printStackTrace();
			}

			try {
				attPrice = obj.getDouble("att_price");
				if (attPrice == null || attPrice < 0) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("att_price", "無效的基本消費。");
//				e.printStackTrace();
			}

			try {
				attStaytime = obj.getInt("att_staytime");
				if (attStaytime == null || attStaytime < -1) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("att_staytime", "無效的滯留時間。");
//				e.printStackTrace();
			}

			try {
				attUrl = obj.getString("att_url");
				if (attUrl == null || attUrl.trim().isEmpty()) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("att_url", "無效的景點網址。");
//				e.printStackTrace();
			}

			try {
				attLat = obj.getDouble("att_lat");
				if (attLat == null || attLat < -90 || attLat > 90) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("att_lat", "無效的緯度。");
//				e.printStackTrace();
			}

			try {
				attLng = obj.getDouble("att_lng");
				if (attLng == null || attLng < -180 || attLng > 180) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("att_lng", "無效的經度。");
//				e.printStackTrace();
			}

			if (err.length() > 0) {
				throw new Exception();
			} else {
				
				AttService srv = new AttService();
				RegionDAO rdao = new RegionHibernateDAO();
				regionVO = rdao.findById(regionId);
				
				String regJson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(regionVO);
				
				JSONObject regobj = new JSONObject(regJson);
				
				obj.put("regionVO", regobj);
				System.out.println(obj.toString());
				
				AttVO attVO = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(obj.toString(), AttVO.class);
				System.out.println(attVO.getRegionVO());
				srv.insert(attVO);
				err.append("result", "新增成功");
				response.getWriter().println(err.toString());
			}
		} catch (Exception e) {
			err.append("result", "新增失敗。");
			response.getWriter().println(err.toString());
//			e.printStackTrace();
		}
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
