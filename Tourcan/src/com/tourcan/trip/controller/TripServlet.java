package com.tourcan.trip.controller;

import java.io.BufferedReader;
import java.io.IOException;
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

@WebServlet("/trip/TripServlet")
public class TripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TripServlet() {
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
			Integer tripId = null;
			TripVO tripVO = null;

			try {
				String id = request.getParameter("tripId");
				// System.out.println(id);
				if (id == null || id.trim().length() == 0) {
					checkResult.append("checkResult", "請輸入行程ID");
				} else {
					try {
						tripId = new Integer(id);
					} catch (Exception e) {
						// e.printStackTrace();
						checkResult.append("checkResult", "行程ID格式不正確");
					}
				}
				TripService tripSvc = new TripService();
				tripVO = tripSvc.findById(tripId);
				if (tripVO != null) {
					JSONObject obj = new JSONObject(tripVO);
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

		// ----------------findByName----------------

		String tripName = request.getParameter("tripName");
		List<TripVO> TripVO = null;

		if (method.equals("getByName")) {
			JSONObject checkResult = new JSONObject();
			try {
				if (tripName == null || tripName.trim().length() == 0)
					checkResult.append("result", "請輸入行程名稱");

				if (checkResult.length() > 0) {
					throw new Exception();
				} else {
					TripService tripSvc = new TripService();
					TripVO = tripSvc.findByName(tripName);
					if (TripVO.size() != 0) {
						Gson gson = new Gson();
						String jsonG = gson.toJson(TripVO);
						// System.out.println(jsonG);
						response.getWriter().println(jsonG);
					} else {
						checkResult.append("result", "查無資料");
						response.getWriter().println(checkResult.toString());
					}
				}
				// ***************************其他可能的錯誤處理*************************************//*
			} catch (Exception e) {
				checkResult.append("result", "查詢失敗");
				System.out.println("err=" + checkResult);
				response.getWriter().println(checkResult.toString());
			}
		}

		// ----------------getAll----------------

		if (method.equals("getAll")) {
			JSONObject checkResult = new JSONObject();
			try {
				TripService tripSvc = new TripService();
				TripVO = tripSvc.getAll();
				if (TripVO.size() != 0) {
					Gson gson = new Gson();
					String jsonG = gson.toJson(TripVO);
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

		String tripName = null;
		Timestamp tripCtime = null;
		Integer tripPrice = null;
		Integer memId = null;

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

			// 抓出建立當下時間
			tripCtime = new Timestamp(System.currentTimeMillis());

			tripPrice = tripVO.getTrip_price();
			if (tripPrice == null || tripPrice < 0)
				checkResult.append("trip_price", "預算金額錯誤。");

			memId = tripVO.getMem_id(); // 抓出建立會員Id 且 不能修改

			if (checkResult.length() > 0) {
				throw new Exception();
			} else {
				TripService srv = new TripService();
				srv.insertTrip(tripName, tripCtime, tripPrice, memId);
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
		Integer memId = null;

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

			memId = tripVO.getMem_id(); // 抓出會員Id 且 不能修改
			System.out.println(tripName + "," + tripId + "," + tripCtime + "," + tripPrice + "," + memId);
			if (checkResult.length() > 0) {
				throw new Exception();
			} else {
				TripService srv = new TripService();
				srv.updateTrip(tripName, tripId, tripCtime, tripPrice, memId);
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
