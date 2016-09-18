package com.tourcan.tripitem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Time;
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
import com.tourcan.tripitem.model.TripitemService;
import com.tourcan.tripitem.model.TripitemVO;

//import org.hibernate.ejb.criteria.expression.function.SubstringFunction;

@WebServlet("/tripitem/TripitemServlet")
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
			Integer tripitemId = null;
			TripitemVO tripitemVO = null;

			try {
				String id = request.getParameter("tripitemId");
//				 System.out.println(id);
				if (id == null || id.trim().length() == 0) {
					checkResult.append("checkResult", "請輸入行程明細ID");
				} else {
					try {
						tripitemId = new Integer(id);
					} catch (Exception e) {
						// e.printStackTrace();
						checkResult.append("checkResult", "行程明細ID格式不正確");
					}
				}
//				System.out.println("tripitemId="+tripitemId);
				TripitemService tripitemSvc = new TripitemService();
				tripitemVO = tripitemSvc.findById(tripitemId);
				System.out.println(tripitemVO);
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
		StringBuffer sb = new StringBuffer(128);
		String json;
		while ((json = br.readLine()) != null)
			sb.append(json);
		json = sb.toString();
		System.out.println(json);

		Integer tripId=null;
		Integer tripitemSerial=null;
		Integer attId=null;
		Integer hotelId=null;
		Integer tripitemStaytime=null;
		Time tripitemBegin = null;
		Time tripitemEnd=null;
		String tripitemTraffic=null;
		String tripitemMemo=null;

		JSONObject checkResult = new JSONObject(); // checking result

		try {
			JSONObject obj = new JSONObject(json); // received and parsed JSON
			
			tripId = obj.getInt("trip_id");

			attId = obj.getInt("att_id");
			
			hotelId = obj.getInt("hotel_id");
			
			tripitemSerial = obj.getInt("tripitem_serial");

			try {
				tripitemStaytime = obj.getInt("tripitem_staytime");
				if (tripitemStaytime == null || tripitemStaytime < 0) {
					throw new Exception();

				}
			} catch (Exception e) {
				checkResult.append("tripitem_staytime", "請輸入逗留時間"); // do not need error in update
				// e.printStackTrace();
			}
			
			try {
				String Begin = obj.getString("tripitem_begin");
				if (Begin == null || Begin.trim().isEmpty()) {
					throw new Exception();
				}else{
					tripitemBegin=Time.valueOf(Begin+":00");
					obj.remove("tripitem_begin");
//					obj.put("tripitem_begin", tripitemBegin);
				}
			} catch (Exception e) {
				checkResult.append("tripitem_begin", "請輸入完整時間。");
				 e.printStackTrace();
			}
			
			tripitemEnd=new Time(tripitemBegin.getTime()+tripitemStaytime*60*1000);
			obj.remove("tripitem_end");
//			obj.append("tripitem_end", tripitemEnd);

			try {
				tripitemTraffic = obj.getString("tripitem_traffic");
				if (tripitemTraffic == null || tripitemTraffic.trim().isEmpty()) {
					throw new Exception();
				}
			} catch (Exception e) {
				checkResult.append("tripitem_traffic", "請輸入交通資訊。");
				// e.printStackTrace();
			}
			try {
				tripitemMemo = obj.getString("tripitem_memo");
			} catch (Exception e) {
//				e.printStackTrace();
			}

				
				if (checkResult.length() > 0) {
					throw new Exception();
				} else {
					TripitemService tripitemSvc = new TripitemService();
					TripitemVO tripitemVO = new Gson().fromJson(obj.toString(), TripitemVO.class);
					tripitemVO.setTripitem_begin(tripitemBegin);
					tripitemVO.setTripitem_end(tripitemEnd);
					
					tripitemSvc.insert(tripitemVO); 
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

		Integer tripitemId=null;
		Integer tripId=null;
		Integer tripitemSerial=null;
		Integer attId=null;
		Integer hotelId=null;
		Integer tripitemStaytime=null;
		Time tripitemBegin = null;
		Time tripitemEnd=null;
		String tripitemTraffic=null;
		String tripitemMemo=null;

		JSONObject checkResult = new JSONObject(); // checking result

		try {
			JSONObject obj = new JSONObject(json); // received and parsed JSON
			
			tripitemId = new Integer(request.getParameter("tripitem_id"));
			
			tripId = obj.getInt("trip_id");

			attId = obj.getInt("att_id");
			
			hotelId = obj.getInt("hotel_id");
			
			tripitemSerial = obj.getInt("tripitem_serial");

			try {
				tripitemStaytime = obj.getInt("tripitem_staytime");
				if (tripitemStaytime == null || tripitemStaytime < 0) {
					throw new Exception();

				}
			} catch (Exception e) {
				checkResult.append("tripitem_staytime", "請輸入逗留時間"); // do not need error in update
				// e.printStackTrace();
			}
			
			try {
				String Begin = obj.getString("tripitem_begin").substring(0, 5);
				if (Begin == null || Begin.trim().isEmpty()) {
					throw new Exception();
				}else{
					tripitemBegin=Time.valueOf(Begin+":00");
					obj.remove("tripitem_begin");
//					obj.put("tripitem_begin", tripitemBegin);
				}
			} catch (Exception e) {	
				checkResult.append("tripitem_begin", "請修改初始時間。");
				 e.printStackTrace();
			}
			
			tripitemEnd=new Time(tripitemBegin.getTime()+tripitemStaytime*60*1000);
			obj.remove("tripitem_end");
//			obj.append("tripitem_end", tripitemEnd);

			try {
				tripitemTraffic = obj.getString("tripitem_traffic");
				if (tripitemTraffic == null || tripitemTraffic.trim().isEmpty()) {
					throw new Exception();
				}
			} catch (Exception e) {
				checkResult.append("tripitem_traffic", "請輸入交通資訊。");
				// e.printStackTrace();
			}
			try {
				tripitemMemo = obj.getString("tripitem_memo");
			} catch (Exception e) {
//				e.printStackTrace();
			}

				
				if (checkResult.length() > 0) {
					throw new Exception();
				} else {
					TripitemService tripitemSvc = new TripitemService();
					TripitemVO tripitemVO = new Gson().fromJson(obj.toString(), TripitemVO.class);
					tripitemVO.setTripitem_begin(tripitemBegin);
					tripitemVO.setTripitem_end(tripitemEnd);
					
					tripitemSvc.insert(tripitemVO); 
					checkResult.append("result", "更新成功");
					response.getWriter().println(checkResult.toString());
				}
				
		} catch (Exception e) {
			checkResult.append("result", "新增失敗。");
			response.getWriter().println(checkResult.toString());
			e.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ----------------DELETE----------------
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		Integer tripitemId = null;

		/***************************** 刪除一筆資料 ************************************************************************/

		Map<String, String> errorMsgs = new HashMap<>();
		/****************************
		 * 1.接收請求參數 - 輸入格式的錯誤處理
		 **********************/
		try {
			String id = request.getParameter("tripitemId");
			System.out.println(id);
			if (id == null || (id.trim()).length() == 0) {
				errorMsgs.put("errMsg", "請輸入行程明細ID");
			} else {
				try {
					tripitemId = new Integer(id);
				} catch (Exception e) {
					errorMsgs.put("errMsg", "行程明細ID格式不正確");
				}
			}

			/*************************** 2.開始刪除單筆資料 *****************************************/
			TripitemService tripitemSvc = new TripitemService();
			try {
				tripitemSvc.delete(tripitemId);
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
