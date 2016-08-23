package com.tourcan.att.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

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
		
		
//------------------------------------------------------------------------		
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
				//***************************2.開始查詢資料*****************************************//*
		
				AttService asv =new AttService();				
				List<AttVO> avo = asv.getAllByName(att_name);
				
				if (avo == null) {
					errorMsgs1.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs1.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/query_One_Att.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
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
		
		
/***************************** 刪除一筆資料 ************************************************************************/

		
		if ("delete".equals(action)) { // 來自delete_att.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
			try {
				String id = request.getParameter("att_id");
				if (id == null || (id.trim()).length() == 0) {
					errorMsgs.add("請輸入景點ID");
				}
				// 驗證有誤，提示錯誤訊息
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher rd = request.getRequestDispatcher("/att/delete_att.jsp");
					rd.forward(request, response);
					return;// 程式中斷
				}

				Integer att_id = null;
				try {
					att_id = new Integer(id);
				} catch (Exception e) {
					errorMsgs.add("景點ID格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher rd = request.getRequestDispatcher("/att/delete_att.jsp");
					rd.forward(request, response);
					return;// 程式中斷
				}

				/*************************** 2.開始刪除單筆資料 *****************************************/
				AttService attSvc = new AttService();
				try {
					attSvc.deleteAtt(att_id);
				} catch (Exception e) {
					errorMsgs.add("查無資料");
					RequestDispatcher rd = request.getRequestDispatcher("/att/delete_att.jsp");
					rd.forward(request, response);
					return;// 程式中斷
				}

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				RequestDispatcher rd = request.getRequestDispatcher("/success/deleteSuccess.jsp"); // 成功轉交
				rd.forward(request, response);
				return;// 程式中斷

				/*************************** 其他可能的錯誤處理 **************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/att/delete_att.jsp");
				rd.forward(request, response);
			}
		}
		
		
		
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
//					AttVO vo = new AttVO();

					try {
						attName = obj.getString("attName");
						if (attName == null || attName.trim().isEmpty() || attName.trim().length() >= 50) {
							throw new Exception();
						}

					} catch (Exception e) {
						err.append("attName", "無效的景點名稱。");
//						e.printStackTrace();
					}

					try {
						regionId = obj.getInt("regionId");
						if (regionId == null || regionId < 0) {
							throw new Exception();
						}

					} catch (Exception e) {
						err.append("regionId", "無效的地區代號。");
//						e.printStackTrace();
					}

					try {
						attAddr = obj.getString("attAddr");
						if (attAddr == null || attAddr.trim().isEmpty()) {
							throw new Exception();
						}
					
					} catch (Exception e) {
						err.append("attAddr", "無效的景點地址。");
//						e.printStackTrace();
					}

					try {
						attEat = obj.getBoolean("attEat");
						
					} catch (Exception e) {
						err.append("attAddr", "無效的吃貨屬性。");
//						e.printStackTrace();
					}

					try {
						attIntro = obj.getString("attIntro");
						if (attIntro == null || attIntro.trim().isEmpty()) {
							throw new Exception();
						}
						
					} catch (Exception e) {
						err.append("attIntro", "無效的景點介紹。");
//						e.printStackTrace();
					}

					try {
						appOpen = obj.getString("attOpen");
						if (appOpen == null || appOpen.trim().isEmpty()) {
							throw new Exception();
						}
						
					} catch (Exception e) {
						err.append("attOpen", "無效的開放資訊。");
//						e.printStackTrace();
					}

					try {
						attPhone = obj.getString("attPhone");
						if (attPhone == null || attPhone.trim().isEmpty() || attPhone.trim().length() >= 50) {
							throw new Exception();
						}
						
					} catch (Exception e) {
						err.append("attPhone", "無效的聯絡電話。");
//						e.printStackTrace();
					}

					try {
						attPrice = obj.getDouble("attPrice");
						if (attPrice == null || attPrice < 0) {
							throw new Exception();
						}
						
					} catch (Exception e) {
						err.append("attPrice", "無效的基本消費。");
//						e.printStackTrace();
					}

					try {
						attStaytime = obj.getInt("attStaytime");
						if (attStaytime == null || attStaytime < -1) {
							throw new Exception();
						}
						
					} catch (Exception e) {
						err.append("attStaytime", "無效的滯留時間。");
//						e.printStackTrace();
					}

					try {
						attUrl = obj.getString("attUrl");
						if (attUrl == null || attUrl.trim().isEmpty()) {
							throw new Exception();
						}
						
					} catch (Exception e) {
						err.append("attUrl", "無效的景點網址。");
//						e.printStackTrace();
					}

					try {
						attLat = obj.getDouble("attLat");
						if (attLat == null || attLat < -90 || attLat > 90) {
							throw new Exception();
						}
						
					} catch (Exception e) {
						err.append("attLat", "無效的緯度。");
//						e.printStackTrace();
					}

					try {
						attLng = obj.getDouble("attLng");
						if (attLng == null || attLng < -180 || attLng > 180) {
							throw new Exception();
						}
						
					} catch (Exception e) {
						err.append("attLng", "無效的經度。");
//						e.printStackTrace();
					}

					if (err.length() > 0) {
						throw new Exception();
					} else {
//						AttDAO dao = new AttDAO();
//						dao.insert(vo);
						
						AttService srv = new AttService();
						srv.insert(attName, regionId, attAddr, attEat, attIntro, appOpen, attPhone, attPrice, attStaytime, attUrl, attLat, attLng);
						
					}
				} catch (Exception e) {
					err.append("result", "新增失敗。");
//					e.printStackTrace();
				}
				
				response.getWriter().println(err.toString());
		
		
		
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
