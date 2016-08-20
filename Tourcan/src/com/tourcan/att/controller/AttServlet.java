package com.tourcan.att.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tourcan.att.model.AttDAO;
import com.tourcan.att.model.AttService;
import com.tourcan.att.model.AttVO;

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
		// System.out.println(json);

		JSONObject err = new JSONObject(); // checking result
		
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
		
		
		try {
			JSONObject obj = new JSONObject(json); // received and parsed JSON
//			AttVO vo = new AttVO();

			try {
				attName = obj.getString("attName");
				if (attName == null || attName.trim().isEmpty() || attName.trim().length() >= 50) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("attName", "無效的景點名稱。");
//				e.printStackTrace();
			}

			try {
				regionId = obj.getInt("regionId");
				if (regionId == null || regionId < 0) {
					throw new Exception();
				}

			} catch (Exception e) {
				err.append("regionId", "無效的地區代號。");
//				e.printStackTrace();
			}

			try {
				attAddr = obj.getString("attAddr");
				if (attAddr == null || attAddr.trim().isEmpty()) {
					throw new Exception();
				}
			
			} catch (Exception e) {
				err.append("attAddr", "無效的景點地址。");
//				e.printStackTrace();
			}

			try {
				attEat = obj.getBoolean("attEat");
				
			} catch (Exception e) {
				err.append("attAddr", "無效的吃貨屬性。");
//				e.printStackTrace();
			}

			try {
				attIntro = obj.getString("attIntro");
				if (attIntro == null || attIntro.trim().isEmpty()) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("attIntro", "無效的景點介紹。");
//				e.printStackTrace();
			}

			try {
				appOpen = obj.getString("attOpen");
				if (appOpen == null || appOpen.trim().isEmpty()) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("attOpen", "無效的開放資訊。");
//				e.printStackTrace();
			}

			try {
				attPhone = obj.getString("attPhone");
				if (attPhone == null || attPhone.trim().isEmpty() || attPhone.trim().length() >= 50) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("attPhone", "無效的聯絡電話。");
//				e.printStackTrace();
			}

			try {
				attPrice = obj.getDouble("attPrice");
				if (attPrice == null || attPrice < 0) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("attPrice", "無效的基本消費。");
//				e.printStackTrace();
			}

			try {
				attStaytime = obj.getInt("attStaytime");
				if (attStaytime == null || attStaytime < -1) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("attStaytime", "無效的滯留時間。");
//				e.printStackTrace();
			}

			try {
				attUrl = obj.getString("attUrl");
				if (attUrl == null || attUrl.trim().isEmpty()) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("attUrl", "無效的景點網址。");
//				e.printStackTrace();
			}

			try {
				attLat = obj.getDouble("attLat");
				if (attLat == null || attLat < -90 || attLat > 90) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("attLat", "無效的緯度。");
//				e.printStackTrace();
			}

			try {
				attLng = obj.getDouble("attLng");
				if (attLng == null || attLng < -180 || attLng > 180) {
					throw new Exception();
				}
				
			} catch (Exception e) {
				err.append("attLng", "無效的經度。");
//				e.printStackTrace();
			}

			if (err.length() > 0) {
				throw new Exception();
			} else {
//				AttDAO dao = new AttDAO();
//				dao.insert(vo);
				
				AttService srv = new AttService();
				srv.insert(attName, regionId, attAddr, attEat, attIntro, appOpen, attPhone, attPrice, attStaytime, attUrl, attLat, attLng);
				
			}
		} catch (Exception e) {
			err.append("result", "新增失敗。");
//			e.printStackTrace();
		}
		
		response.getWriter().println(err.toString());

		// // test output
		// for (String key : JSONObject.getNames(obj)) {
		// System.out.println(key+"\t"+obj.getString(key));
		// for (String key2 : JSONObject.getNames(obj.getJSONObject(key1))) {
		// System.out.println(" " + key2);
		// }
		// }
		//
		// // test output
		// System.out.println("\n" + sb.toString());
		// Enumeration<String> headers = request.getHeaderNames();
		// while (headers.hasMoreElements()) {
		// String header = headers.nextElement();
		// System.out.println(header + "\t: " + request.getHeader(header));
		// }
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
