package com.tourcan.tripitem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.tourcan.trip.model.TripService;
import com.tourcan.trip.model.TripVO;
import com.tourcan.tripitem.model.TripitemService;
import com.tourcan.tripitem.model.TripitemVO;

@WebServlet("/tripitem/TripServlet")
public class TripitemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TripitemServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ----------------findById----------------
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String method = request.getParameter("method");

		if (method.equals("getOneById")) {
			JSONObject checkResult = new JSONObject();
			Integer tripitemSerial = null;
			TripitemVO tripitemVO = null;

			try {
				String id = request.getParameter("tripitemSerial");
				// System.out.println(id);
				if (id == null || id.trim().length() == 0) {
					checkResult.append("checkResult", "請輸入行程明細ID");
				} else {
					try {
						tripitemSerial = new Integer(id);
					} catch (Exception e) {
						// e.printStackTrace();
						checkResult.append("checkResult", "行程明細ID格式不正確");
					}
				}
				TripitemService tripitemSvc = new TripitemService();
				tripitemVO = tripitemSvc.findById(tripitemSerial);
				if (tripitemVO != null) {
					JSONObject obj = new JSONObject(tripitemVO);
					response.getWriter().println(obj.toString());
				} else {
					checkResult.append("checkResult", "查無資料");
					response.getWriter().println(checkResult.toString());
				}
			} catch (Exception e) {
				checkResult.append("false", "查詢失敗");
				response.getWriter().println(checkResult.toString());
			}
		}


		// ----------------getAll----------------

		List<TripitemVO> tripitemVO = null;

		if (method.equals("getAll")) {
			JSONObject checkResult = new JSONObject();
			try {
				TripitemService tripitemSvc = new TripitemService();
				tripitemVO = tripitemSvc.getAll();
				if (tripitemVO.size() != 0) {
					Gson gson = new Gson();
					String jsonG = gson.toJson(tripitemVO);
					// System.out.println(jsonG);
					response.getWriter().println(jsonG);
				}
				// ***************************其他可能的錯誤處理*************************************//*
			} catch (Exception e) {
				checkResult.append("result", "查詢失敗");
				System.out.println("err=" + checkResult);
				response.getWriter().println(checkResult.toString());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ----------------INSERT----------------
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BufferedReader br = request.getReader();

		String tripitemTraffic;
		String tripitemMemo;
		Integer tripitemStaytime;
		Timestamp tripitemBegin;
		Timestamp tripitemEnd;
		Integer tripId;
		Integer attId;
		Integer hotelId;

		JSONObject checkResult = new JSONObject(); // checking result
		TripitemVO tripitemVO = null;
		try {
			tripitemVO = new Gson().fromJson(br, TripitemVO.class);

			tripitemTraffic = tripitemVO.getTripitem_traffic();
			if (tripitemTraffic == null || tripitemTraffic.trim().isEmpty()) 
				checkResult.append("tripitem_traffic", "請輸入交通資訊。");
			
			tripitemMemo = tripitemVO.getTripitem_memo();
			if (tripitemMemo == null || tripitemMemo.trim().isEmpty()) 
				checkResult.append("tripitem_memo", "註記未輸入。");			
			
			tripitemStaytime=tripitemVO.getTripitem_staytime();
			if (tripitemStaytime == null || tripitemStaytime < -1)
				checkResult.append("tripitem_staytime", "請輸入預計停留時間。");
			
			String beginTime=(request.getParameter("begin_time")).replace("T", " ");
			tripitemBegin =Timestamp.valueOf(beginTime);
				
			tripitemEnd = new Timestamp(System.currentTimeMillis());

			
			
			
			
			
			tripName = tripVO.getTrip_name();
			if (tripName == null || tripName.trim().isEmpty()) {
				checkResult.append("trip_name", "請輸入旅遊名稱。");
			} else if (tripName.trim().length() >= 50) {
				checkResult.append("trip_name", "旅遊名稱不得超過50個字");
			}

			// 抓出建立當下時間
			tripCtime = new Timestamp(System.currentTimeMillis());

			tripPrice = tripVO.getTrip_price();
			if (tripPrice == null || tripPrice < 0)
				checkResult.append("trip_price", "預算金額錯誤。");

			memUid = tripVO.getMem_uid(); // 抓出建立會員Id 且 不能修改

			if (checkResult.length() > 0) {
				throw new Exception();
			} else {
				TripService srv = new TripService();
				srv.insertTrip(tripName, tripCtime, tripPrice, memUid);
				checkResult.append("result", "新增成功");
				response.getWriter().println(checkResult.toString());
			}
		} catch (Exception e) {
			checkResult.append("result", "新增失敗。");
			response.getWriter().println(checkResult.toString());
			e.printStackTrace();
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ----------------UPDATE----------------
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BufferedReader br = request.getReader();

		String tripName = null;
		Integer tripId = null;
		Timestamp tripCtime = null;
		Integer tripPrice = null;
		String memUid = null;

		JSONObject checkResult = new JSONObject(); // checking result
		TripVO tripVO = null;
		try {
			tripVO = new Gson().fromJson(br, TripVO.class);

			tripName = tripVO.getTrip_name();
			if (tripName == null || tripName.trim().isEmpty()) {
				checkResult.append("trip_name", "請輸入旅遊名稱。");
			} else if (tripName.trim().length() >= 50) {
				checkResult.append("trip_name", "旅遊名稱不得超過50個字");
			}

			tripId = new Integer(request.getParameter("trip_id")); // 不能修改Id

			// 抓出修改當下時間
			tripCtime = new Timestamp(System.currentTimeMillis());

			tripPrice = tripVO.getTrip_price();
			if (tripPrice == null || tripPrice < 0)
				checkResult.append("trip_price", "總預算金額錯誤。");

			memUid = tripVO.getMem_uid(); // 抓出會員Id 且 不能修改
//			System.out.println(tripName + "," + tripId + "," + tripCtime + "," + tripPrice + "," + memUid);
			if (checkResult.length() > 0) {
				throw new Exception();
			} else {
				TripService srv = new TripService();
				srv.updateTrip(tripName, tripId, tripCtime, tripPrice, memUid);
				checkResult.append("result", "更新成功");
				response.getWriter().println(checkResult.toString());
			}
		} catch (Exception e) {
			checkResult.append("result", "更新失敗。");
			response.getWriter().println(checkResult.toString());
			// e.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ----------------DELETE----------------
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		Integer tripId = null;

		/***************************** 刪除一筆資料 ************************************************************************/

		Map<String, String> errorMsgs = new HashMap<>();
		/****************************
		 * 1.接收請求參數 - 輸入格式的錯誤處理
		 **********************/
		try {
			String id = request.getParameter("tripId");
			System.out.println(id);
			if (id == null || (id.trim()).length() == 0) {
				errorMsgs.put("errMsg", "請輸入行程ID");
			} else {
				try {
					tripId = new Integer(id);
				} catch (Exception e) {
					errorMsgs.put("errMsg", "行程ID格式不正確");
				}
			}

			/*************************** 2.開始刪除單筆資料 *****************************************/
			TripService tripSvc = new TripService();
			try {
				tripSvc.deleteTrip(tripId);
			} catch (Exception e) {
				errorMsgs.put("errMsg", "查無資料");
			}

			/*************************** 其他可能的錯誤處理 **************************************/
		} catch (Exception e) {
			errorMsgs.put("errMsg", "無法取得資料:" + e.getMessage());
		}
		JSONObject json = new JSONObject(errorMsgs);
		response.getWriter().println(json.toString());
	}
}

