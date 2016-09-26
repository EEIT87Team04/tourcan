package com.tourcan.tripitem.controller;

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
		//---------------findByTripID--------------
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
			TripitemService tripitemSvc = new TripitemService();
			List<TripitemVO> tripitemVO =tripitemSvc.findByTripID(tripno);
			request.setAttribute("tripitemVO",tripitemVO);
			System.out.println(tripitemVO);
			String url = "/trip/updateTrip.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
			}catch (Exception e) {
				RequestDispatcher failureView = request.getRequestDispatcher("/trip/listOneFromMemTrip.jsp");
				failureView.forward(request, response);
			}
		}
		
		// ----------------findById----------------
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String method1 = request.getParameter("method");

		if (method1.equals("getOneById")) {
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

	@SuppressWarnings("unused")
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
		Timestamp tripitemBegin = null;
		Timestamp tripitemEnd=null;
		String tripitemTraffic=null;
		String tripitemMemo=null;
		Integer tripitemPrice=null;

		JSONObject checkResult = new JSONObject(); // checking result

		try {
			JSONObject obj = new JSONObject(json); // received and parsed JSON
			
			tripId = obj.getInt("trip_id");

			try {
				attId = obj.getInt("att_id");
			} catch (Exception e) {
//				e.printStackTrace();
			}
			
			try {
				hotelId = obj.getInt("hotel_id");
			} catch (Exception e) {
//				e.printStackTrace();
			}
			
			tripitemSerial = obj.getInt("tripitem_serial");

			tripitemStaytime = obj.getInt("tripitem_staytime");
			
			try {
				String Begin = obj.getString("tripitem_begin");
				if (Begin == null || Begin.trim().isEmpty()) {
					throw new Exception();
				}else{
					tripitemBegin=new Timestamp(Long.parseLong(Begin));
					obj.remove("tripitem_begin");
//					obj.put("tripitem_begin", tripitemBegin);
				}
			} catch (Exception e) {
				checkResult.append("tripitem_begin", "請輸入完整時間。");
				 e.printStackTrace();
			}
			
			try {
				String End = obj.getString("tripitem_end");
				if (End == null || End.trim().isEmpty()) {
					throw new Exception();
				}else{
					tripitemEnd=new Timestamp(Long.parseLong(End));
					obj.remove("tripitem_end");
//					obj.put("tripitem_end", tripitemEegin);
				}
			} catch (Exception e) {
				checkResult.append("tripitem_end", "請輸入完整時間。");
				 e.printStackTrace();
			}

			
			try{
				tripitemTraffic = obj.getString("tripitem_traffic");
			}catch(Exception e){
//				e.printStackTrace();			
			}
			
			try {
				tripitemMemo = obj.getString("tripitem_memo");
			} catch (Exception e) {
//				e.printStackTrace();
			}
			
			try {
				tripitemPrice=obj.getInt("tripitem_price");
			} catch (Exception e) {
				tripitemPrice=0;
//				e.printStackTrace();
			}
			

				if (checkResult.length() > 0) {
					throw new Exception();
				} else {
					TripitemService tripitemSvc = new TripitemService();
					TripitemVO tripitemVO = new Gson().fromJson(obj.toString(), TripitemVO.class);
					tripitemVO.setTripitem_begin(tripitemBegin);
					tripitemVO.setTripitem_end(tripitemEnd);
					tripitemVO.setTripitem_price(tripitemPrice);
					
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

	@SuppressWarnings("unused")
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
		Timestamp tripitemBegin = null;
		Timestamp tripitemEnd=null;
		String tripitemTraffic=null;
		String tripitemMemo=null;
		Integer tripitemPrice=null;
		

		JSONObject checkResult = new JSONObject(); // checking result

		try {
			JSONObject obj = new JSONObject(json); // received and parsed JSON
			
			tripitemId = new Integer(request.getParameter("tripitem_id"));
			
			tripId = obj.getInt("trip_id");

			try {
				attId = obj.getInt("att_id");
			} catch (Exception e) {
//				e.printStackTrace();
			}
			
			try {
				hotelId = obj.getInt("hotel_id");
			} catch (Exception e) {
//				e.printStackTrace();
			}
			
			tripitemSerial = obj.getInt("tripitem_serial");

			tripitemStaytime = obj.getInt("tripitem_staytime");
			
			try {
				String Begin = obj.getString("tripitem_begin");
				if (Begin == null || Begin.trim().isEmpty()) {
					throw new Exception();
				}else{
					tripitemBegin=new Timestamp(Long.parseLong(Begin));
					obj.remove("tripitem_begin");
//					obj.put("tripitem_begin", tripitemBegin);
				}
			} catch (Exception e) {
				checkResult.append("tripitem_begin", "請輸入完整時間。");
				 e.printStackTrace();
			}
			
			try {
				String End = obj.getString("tripitem_end");
				if (End == null || End.trim().isEmpty()) {
					throw new Exception();
				}else{
					tripitemEnd=new Timestamp(Long.parseLong(End));
					obj.remove("tripitem_end");
//					obj.put("tripitem_end", tripitemEegin);
				}
			} catch (Exception e) {
				checkResult.append("tripitem_end", "請輸入完整時間。");
				 e.printStackTrace();
			}

			tripitemTraffic = null;
			
			try {
				tripitemMemo = obj.getString("tripitem_memo");
			} catch (Exception e) {
//				e.printStackTrace();
			}
			
			try {
				tripitemPrice=obj.getInt("tripitem_price");
			} catch (Exception e) {
				tripitemPrice=0;
//				e.printStackTrace();
			}

				
				if (checkResult.length() > 0) {
					throw new Exception();
				} else {
					TripitemService tripitemSvc = new TripitemService();
					TripitemVO tripitemVO = new Gson().fromJson(obj.toString(), TripitemVO.class);
					tripitemVO.setTripitem_begin(tripitemBegin);
					tripitemVO.setTripitem_end(tripitemEnd);
					tripitemVO.setTripitem_price(tripitemPrice);
					
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
