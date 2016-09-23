package com.tourcan.trip.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
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
import com.tourcan.trip.model.TripService;
import com.tourcan.trip.model.TripVO;
import com.tourcan.tripitem.model.TripitemService;

@WebServlet("/trip/TripServlet")
public class TripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TripServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String method = request.getParameter("method");

		if(method.equals("findByID")){
			String id = request.getParameter("tripId");
			try{
			Integer tripno =null;
			try{
			tripno =new Integer(id);
			}catch (Exception e) {
				throw new Exception();
			}
			TripService trsv = new TripService();
			TripVO tripVO =trsv.findById(tripno);
			request.setAttribute("tripVO",tripVO);
			System.out.println(tripVO.getTrip_id());
			String url = "/trip/???????.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
			}catch (Exception e) {
				RequestDispatcher failureView = request.getRequestDispatcher("/trip/listOneFromMemTrip.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		
		if(method.equals("findByMemuid")){
			JSONObject chResult = new JSONObject();
			List<TripVO> tripVO = null;
			try{
				String uid = request.getParameter("mem_uid");
				if(uid==null||uid.trim().length()==0)
					chResult.append("chResult", "error");
				if(chResult.length()>0){
					throw new Exception();
				}else{
				TripService tsv =new TripService();
				tripVO =  tsv.findByMemuid(uid);
				if(tripVO.size()!=0){
					Gson gson =new Gson();
					String strjson = gson.toJson(tripVO);
					response.getWriter().println(strjson);
				}else{
					chResult.append("chResult", "sreach null");
					response.getWriter().println(chResult.toString());
				}
				}	
			}catch (Exception e) {
				chResult.append("chResult", "sreach error");
				response.getWriter().println(chResult.toString());
				
			}
			
		}
		// ----------------findById----------------
		
		if (method.equals("getOneById")) {
			JSONObject checkResult = new JSONObject();
			Integer tripId = null;
			TripVO tripVO = null;

			try {
				String id = request.getParameter("trip_id");
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
		List<TripVO> tripVO = null;

		if (method.equals("getByName")) {
			JSONObject checkResult = new JSONObject();
			try {
				if (tripName == null || tripName.trim().length() == 0)
					checkResult.append("result", "請輸入行程名稱");

				if (checkResult.length() > 0) {
					throw new Exception();
				} else {
					TripService tripSvc = new TripService();
					tripVO = tripSvc.findByName(tripName);
					if (tripVO.size() != 0) {
						Gson gson = new Gson();
						String jsonG = gson.toJson(tripVO);
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
				tripVO = tripSvc.getAll();
				if (tripVO.size() != 0) {
					Gson gson = new Gson();
					String jsonG = gson.toJson(tripVO);
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

			// 抓出建立當下時間
			tripCtime = new Timestamp(System.currentTimeMillis());
			tripVO.setTrip_ctime(tripCtime);

			try{
				tripPrice = tripVO.getTrip_price();
			}catch(Exception e){
			}

			memUid ="h9nbaY43OGRODXAGp2XMMhskW9r1"; // 抓出建立會員Id 且 不能修改
			tripVO.setMem_uid(memUid);

			if (checkResult.length() > 0) {
				throw new Exception();
			} else {
				TripService srv = new TripService();
				srv.insertTrip(tripVO);
				checkResult.append("trip_id", tripVO.getTrip_id());
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
		StringBuffer sb = new StringBuffer(128);
		String json;
		while ((json = br.readLine()) != null)
			sb.append(json);
		json = sb.toString();
		System.out.println(json);

		String tripName = null;
		Integer tripId = null;
		Timestamp tripCtime = null;
		Integer tripPrice = null;
		String memUid = null;

		JSONObject checkResult = new JSONObject(); // checking result
		try {
			JSONObject obj = new JSONObject(json); // received and parsed JSON
			
			tripName = obj.getString("trip_name"); 

			tripId = obj.getInt("trip_id");

			// 抓出修改當下時間
			tripCtime =new Timestamp(System.currentTimeMillis());

			tripPrice =obj.getInt("trip_price"); 

			memUid = obj.getString("mem_uid"); // 抓出會員Id 且 不能修改
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
		String id = request.getParameter("tripId");
		try {
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
			TripitemService itsvc =new TripitemService();
			itsvc.deleteForTripID(tripId);
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
