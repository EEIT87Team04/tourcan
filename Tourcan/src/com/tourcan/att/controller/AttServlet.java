package com.tourcan.att.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tourcan.att.model.AttService;
import com.tourcan.att.model.AttVO;
import com.tourcan.photo.model.PhotoService;
import com.tourcan.region.model.RegionVO;

@WebServlet("/front_Att/AttServlet")
public class AttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AttServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ----------------Query one by attId----------------
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject err = new JSONObject();
//		response.setContentType("application/json");
//		BufferedReader br = request.getReader();
//		StringBuffer sb = new StringBuffer(128);
//		String json;
//		while ((json = br.readLine()) != null)
//			sb.append(json);
//		json = sb.toString();
		String action=request.getParameter("action");
		String ats =request.getParameter("att_id");
		if(action.equals("getOne_For_Display")){
			HashMap<String, String> errMsgs = new HashMap<>();
			request.setAttribute("errorMessage", errMsgs);
		try{
			if(ats==null||ats.trim().length()==10){
				errMsgs.put("id", "plz into id");
			}
			if(!errMsgs.isEmpty()){
				RequestDispatcher failureView = request.getRequestDispatcher("/front_Att/fs_List.jsp");
				failureView.forward(request, response);
				return;
			}
			Integer memno = null;
			try {
				memno = new Integer(ats);
			} catch (Exception e) {
				errMsgs.put("id","ID error");
			}
			// Send the use back to the form, if there were errors
			if (!errMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/front_Att/fs_List.jsp");
				failureView.forward(request, response);
				return;
			}
			PhotoService rsv = new PhotoService();
			List Photoj = (List) rsv.getByAttId(memno);
			// System.out.println("resVO1="+resVO1);
			AttService asv = new AttService();
			AttVO attVO = asv.getOne(memno);
			if (attVO == null) {
				errMsgs.put("id","No ID");
			}
			// Send the use back to the form, if there were errors
			if (!errMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/front_Att/fs_List.jsp");
				failureView.forward(request, response);
				return;
			}

			// ***************************3.�d�ߧ���,�ǳ����(Send the
			// Success view)*************//*
			request.setAttribute("Photoj", Photoj);
			request.setAttribute("attVO", attVO); // 
			String url = "/front_Att/fs_listOneAtt.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // ���\���
																			// listOneEmp.jsp
			successView.forward(request, response);
			// ***************************��L�i�઺���~�B�z*************************************//*
		} catch (Exception e) {
			errMsgs.put("id","search error");
			RequestDispatcher failureView = request.getRequestDispatcher("/front_Att/fs_List.jsp");
			failureView.forward(request, response);
		}
		
	}
			
			
			
		
//
//		// Query by att_id
//		 String attIdStr=request.getParameter("att_id");
//		 if(attIdStr != null){
//		 Integer attId = null;
//		 try {
//		 attId = new Integer(request.getParameter("att_id"));
//		 } catch (Exception e) {
//		 err.append("attId", "編號只能為整數");
//		 response.getWriter().println(err.toString());
//		 // e.printStackTrace();
//		 }
//		
//		 if (attId != null) {
//		 AttService asv = new AttService();
//		 AttVO attVO = asv.getOneMem(attId);
//		
//		 if (attVO != null) {
//		 try {
//		 Gson gson = new
//		 GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//		 String attVOGson = gson.toJson(attVO);
//		 response.getWriter().println(attVOGson);
//		 } catch (Exception e) {
//		 err.append("attId", "無此編號");
//		 response.getWriter().println(err.toString());
//		 // e.printStackTrace();
//		 }
//		 } else {
//		 err.append("attId", "無此編號");
//		 response.getWriter().println(err.toString());
//		 }
//		
//		 } else {
//		 err.append("attId", "無此編號");
//		 }
//		 return;
//		 }
		 
		// ----------------Query one by attname----------------
		String att_name = request.getParameter("attname");
		if (att_name != null) {
			// ***************************1.接收請求參數 -
			// 輸入格式的錯誤處理**********************//*
			try {
				try {
					if (att_name == null || (att_name.trim()).length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					err.append("errname", "attname error");
				}
				// System.out.println("s1="+att_name);
				// Send the use back to the form, if there were errors
				// ***************************2.開始查詢資料*****************************************//*
				AttService asv = new AttService();
				// ----------------Query one by attname----------------
				List<AttVO> avo = asv.getAllByName(att_name);
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				// 指定要給的值 避免gson.toJson() throws StackOverflowError

				// ***************************3.查詢完成,準備轉交(Send the Success
				String jsonG = gson.toJson(avo);
				System.out.println(jsonG);
				// response.getWriter().write(jsonG);
				response.getWriter().println(jsonG.toString());
				
				// ***************************其他可能的錯誤處理*************************************//*
			} catch (Exception e) {
				err.append("errmsg", "search error");
				response.getWriter().println(err.toString());
			}
		} else if ( att_name == null) {
			AttService asv = new AttService();
			List<AttVO> avo = asv.getAll();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String jsonG = gson.toJson(avo);
			System.out.println(jsonG);
			response.getWriter().println(jsonG.toString());
//			return;
		}
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
			if (regionVO == null || regionVO.getRegion_id() == 0)
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		Integer attId = null;

		/***************************** 刪除一筆資料 ************************************************************************/

		Map<String, String> errorMsgs = new HashMap<>();
		/****************************
		 * 1.接收請求參數 - 輸入格式的錯誤處理
		 **********************/
		try {
			String id = request.getParameter("attId");
			if (id == null || (id.trim()).length() == 0) {
				errorMsgs.put("errMsg", "請輸入景點ID");
			} else {

				try {
					attId = new Integer(id);
				} catch (Exception e) {
					errorMsgs.put("errMsg", "景點ID格式不正確");
				}
			}

			/*************************** 2.開始刪除單筆資料 *****************************************/
			AttService attSvc = new AttService();
			try {
				attSvc.deleteAtt(attId);
			} catch (Exception e) {
				errorMsgs.put("errMsg", "查無資料");
			}

			/*************************** 其他可能的錯誤處理 **************************************/
		} catch (Exception e) {
			errorMsgs.put("errMsg", "無法取得資料:" + e.getMessage());
		}
		JSONObject json = new JSONObject(errorMsgs);
		PrintWriter out = response.getWriter();
		out.println(json);
	}

}
