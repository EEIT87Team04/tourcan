package com.tourcan.att.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tourcan.att.model.AttService;
import com.tourcan.att.model.AttVO;

@WebServlet("/att/AttServlet")
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

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json");
//		BufferedReader br = request.getReader();
//		StringBuffer sb = new StringBuffer(128);
//		String json;
//		while ((json = br.readLine()) != null)
//			sb.append(json);
//		json = sb.toString();
//		// System.out.println(json);
//
//		JSONObject err = new JSONObject(); // checking result
//		try {
//			JSONObject obj = new JSONObject(json); // received and parsed JSON
//			AttVO vo = new AttVO();
//
//			try {
//				String attName = obj.getString("attName");
//				if (attName == null || attName.trim().isEmpty() || attName.trim().length() >= 50) {
//					throw new Exception();
//				}
//				vo.setAtt_name(attName);
//			} catch (Exception e) {
//				err.append("attName", "無效的景點名稱。");
////				e.printStackTrace();
//			}
//
//			try {
//				Integer regionId = obj.getInt("regionId");
//				if (regionId == null || regionId < 0) {
//					throw new Exception();
//				}
//				vo.setRegion_id(regionId);
//			} catch (Exception e) {
//				err.append("regionId", "無效的地區代號。");
////				e.printStackTrace();
//			}
//
//			try {
//				String attAddr = obj.getString("attAddr");
//				if (attAddr == null || attAddr.trim().isEmpty()) {
//					throw new Exception();
//				}
//				vo.setAtt_addr(attAddr);
//			} catch (Exception e) {
//				err.append("attAddr", "無效的景點地址。");
////				e.printStackTrace();
//			}
//
//			try {
//				Boolean attEat = obj.getBoolean("attEat");
//				vo.setAtt_eat(attEat);
//			} catch (Exception e) {
//				err.append("attAddr", "無效的吃貨屬性。");
////				e.printStackTrace();
//			}
//
//			try {
//				String attIntro = obj.getString("attIntro");
//				if (attIntro == null || attIntro.trim().isEmpty()) {
//					throw new Exception();
//				}
//				vo.setAtt_intro(attIntro);
//			} catch (Exception e) {
//				err.append("attIntro", "無效的景點介紹。");
////				e.printStackTrace();
//			}
//
//			try {
//				String appOpen = obj.getString("attOpen");
//				if (appOpen == null || appOpen.trim().isEmpty()) {
//					throw new Exception();
//				}
//				vo.setAtt_open(appOpen);
//			} catch (Exception e) {
//				err.append("attOpen", "無效的開放資訊。");
////				e.printStackTrace();
//			}
//
//			try {
//				String attPhone = obj.getString("attPhone");
//				if (attPhone == null || attPhone.trim().isEmpty() || attPhone.trim().length() >= 50) {
//					throw new Exception();
//				}
//				vo.setAtt_phone(attPhone);
//			} catch (Exception e) {
//				err.append("attPhone", "無效的聯絡電話。");
////				e.printStackTrace();
//			}
//
//			try {
//				Double attPrice = obj.getDouble("attPrice");
//				if (attPrice == null || attPrice < 0) {
//					throw new Exception();
//				}
//				vo.setAtt_price(attPrice);
//			} catch (Exception e) {
//				err.append("attPrice", "無效的基本消費。");
////				e.printStackTrace();
//			}
//
//			try {
//				Integer attStaytime = obj.getInt("attStaytime");
//				if (attStaytime == null || attStaytime < -1) {
//					throw new Exception();
//				}
//				vo.setAtt_staytime(attStaytime);
//			} catch (Exception e) {
//				err.append("attStaytime", "無效的滯留時間。");
////				e.printStackTrace();
//			}
//
//			try {
//				String attUrl = obj.getString("attUrl");
//				if (attUrl == null || attUrl.trim().isEmpty()) {
//					throw new Exception();
//				}
//				vo.setAtt_url(attUrl);
//			} catch (Exception e) {
//				err.append("attUrl", "無效的景點網址。");
////				e.printStackTrace();
//			}
//
//			try {
//				Double attLat = obj.getDouble("attLat");
//				if (attLat == null || attLat < -90 || attLat > 90) {
//					throw new Exception();
//				}
//				vo.setAtt_lat(attLat);
//			} catch (Exception e) {
//				err.append("attLat", "無效的緯度。");
////				e.printStackTrace();
//			}
//
//			try {
//				Double attLng = obj.getDouble("attLng");
//				if (attLng == null || attLng < -180 || attLng > 180) {
//					throw new Exception();
//				}
//				vo.setAtt_lng(attLng);
//			} catch (Exception e) {
//				err.append("attLng", "無效的經度。");
////				e.printStackTrace();
//			}
//
//			if (err.length() > 0) {
//				throw new Exception();
//			} else {
//				AttDAO dao = new AttDAO();
//				dao.insert(vo);
//			}
//		} catch (Exception e) {
//			err.append("result", "新增失敗。");
////			e.printStackTrace();
//		}
//		
//		response.getWriter().println(err.toString());
		
		
		
//-------------------------------------------------------

		String action = request.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs1 = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs1);

			try {
	//***************************1.接收請求參數 - 輸入格式的錯誤處理**********************//*
				String str = request.getParameter("attno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs1.add("請輸入員工編號");
				}
		// Send the use back to the form, if there were errors
				if (!errorMsgs1.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/att/query_One_Att.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				Integer memno = null;
				try {
					memno = new Integer(str);
				} catch (Exception e) {
					errorMsgs1.add("員工編號格式不正確");
				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs1.isEmpty()) {
//					RequestDispatcher failureView = request
//							.getRequestDispatcher("/query_One_Att.jsp");
//					failureView.forward(request, response);
//					return;//程式中斷
//				}
				
				//***************************2.開始查詢資料*****************************************//*
		
				AttService asv =new AttService();
				AttVO attVO= asv.getOne(memno);
				if (attVO == null) {
					errorMsgs1.add("查無資料");
				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs1.isEmpty()) {
//					RequestDispatcher failureView = request
//							.getRequestDispatcher("/query_One_Att.jsp");
//					failureView.forward(request, response);
//					return;//程式中斷
//				}
				
		//***************************3.查詢完成,準備轉交(Send the Success view)*************//*
				request.setAttribute("attVO", attVO); // 資料庫取出的empVO物件,存入req
				String url = "/result/listOneAtt.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				//***************************其他可能的錯誤處理*************************************//*
			} catch (Exception e) {
				errorMsgs1.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/att/query_One_Att.jsp");
				failureView.forward(request, response);
			}
		}
		
		
		if ("getOne_For_Name".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs1 = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs1);

			try {
	//***************************1.接收請求參數 - 輸入格式的錯誤處理**********************//*
				String att_name = request.getParameter("attname");
				if (att_name == null || (att_name.trim()).length() == 0) {
					errorMsgs1.add("請輸入attname");
				}
				System.out.println("s1="+att_name);
		// Send the use back to the form, if there were errors
				if (!errorMsgs1.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/att/query_One_Att.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
//				Integer memno = null;
//				try {
//					memno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs1.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs1.isEmpty()) {
//					RequestDispatcher failureView = request
//							.getRequestDispatcher("/query_One_Att.jsp");
//					failureView.forward(request, response);
//					return;//程式中斷
//				}
				
				//***************************2.開始查詢資料*****************************************//*
		
				AttService asv =new AttService();
//				Integer attVO= asv.getId(str);
//				AttVO avo =asv.getOne(attVO);
				
				List<AttVO> avo = asv.getAllByName(att_name);
				
				if (avo == null) {
					errorMsgs1.add("查無資料");
				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs1.isEmpty()) {
//					RequestDispatcher failureView = request
//							.getRequestDispatcher("/query_One_Att.jsp");
//					failureView.forward(request, response);
//					return;//程式中斷
//				}
				
		//***************************3.查詢完成,準備轉交(Send the Success view)*************//*
				request.setAttribute("list", avo); // 資料庫取出的empVO物件,存入req
				String url = "/att/query_All_att_for_name.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);

				//***************************其他可能的錯誤處理*************************************//*
			} catch (Exception e) {
				errorMsgs1.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/att/query_One_Att.jsp");
				failureView.forward(request, response);
			}
		}
		
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
