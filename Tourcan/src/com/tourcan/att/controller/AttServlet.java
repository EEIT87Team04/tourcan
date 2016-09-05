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
import com.google.gson.JsonIOException;
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

		// ----------------INSERT----------------
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BufferedReader br = request.getReader();
		// StringBuffer sb = new StringBuffer(128);
		// String json;
		// while ((json = br.readLine()) != null)
		// sb.append(json);
		// json = sb.toString();
		// System.out.println(json);

		
		
		JSONObject checkResult = new JSONObject(); // checking result
		AttVO attVO = null;
		try {
			// JSONObject obj = new JSONObject(json); // received and parsed
			// JSON
			attVO = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(br, AttVO.class);

			String attName = attVO.getAtt_name();
			if (attName == null || attName.trim().isEmpty() || attName.trim().length() >= 50)
				checkResult.append("att_name", "請輸入景點名稱。");

			RegionVO regionVO = attVO.getRegionVO();
			if (regionVO == null||regionVO.getRegion_id()==0)
				checkResult.append("region_id", "請選擇地區代號。");

			String attAddr = attVO.getAtt_addr();
			if (attAddr == null || attAddr.trim().isEmpty())
				checkResult.append("att_addr", "請輸入景點地址。");

			Boolean attEat = attVO.getAtt_eat();
			if (attEat == null)
				checkResult.append("att_eat", "無效的吃貨屬性。");

			String attIntro = attVO.getAtt_intro();
			if (attIntro == null || attIntro.trim().isEmpty())
				checkResult.append("att_intro", "請輸入景點介紹。");

			String appOpen = attVO.getAtt_open();
			if (appOpen == null || appOpen.trim().isEmpty())
				checkResult.append("att_open", "請輸入開放時間。");

			Double attPrice = attVO.getAtt_price();
			if (attPrice == null || attPrice < 0)
				checkResult.append("att_price", "請輸入最低消費金額。");

			Integer attStaytime = attVO.getAtt_staytime();
			if (attStaytime == null || attStaytime < -1)
				checkResult.append("att_staytime", "請輸入滯留時間。");

			String attUrl = attVO.getAtt_url();
			if (attUrl == null || attUrl.trim().isEmpty())
				checkResult.append("att_url", "無效的景點網址。");

			Double attLat = attVO.getAtt_lat();
			if (attLat == null || attLat < -90 || attLat > 90)
				checkResult.append("att_lat", "移動座標以設定經緯度。");

			Double attLng = attVO.getAtt_lng();
			if (attLng == null || attLng < -180 || attLng > 180)
				checkResult.append("att_lng", "移動座標以設定經緯度。");

			String attPhone = attVO.getAtt_phone();
			if (attPhone == null || attPhone.trim().isEmpty() || attPhone.trim().length() >= 50) {
				String errMsg = "請勿空白。";
				checkResult.append("att_phone", errMsg);
			}
			if (!attPhone.matches("[0-9]{7,}")) {
				String errMsg = "請確認您的電話格式。";
				checkResult.append("att_phone", errMsg);
			}

			if (checkResult.length() > 0) {
				throw new Exception();
			} else {
				AttService srv = new AttService();
				srv.insert(attVO);
				checkResult.append("result", "新增成功");
				response.getWriter().println(checkResult.toString());
			}

		} catch (Exception e) {
			checkResult.append("result", "新增失敗。");
			response.getWriter().println(checkResult.toString());
			// e.printStackTrace();
		}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
