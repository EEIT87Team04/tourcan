package com.tourcan.att.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.google.gson.JsonObject;
import com.tourcan.att.model.AttService;
import com.tourcan.att.model.AttVO;

@WebServlet("/att/TestAttServlet")
public class TestAttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TestAttServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		//-------------------------------------------------------
				
//				if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
		String str = request.getParameter("attno");
		String att_name = request.getParameter("attname");
		PrintWriter out = response.getWriter();
		if(str!=null){
			List<String> errorMsgs1 = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs1);

					
			//***************************1.接收請求參數 - 輸入格式的錯誤處理**********************//*

						if (str == null || (str.trim()).length() == 0) {
							errorMsgs1.add("請輸入員工編號");
						}
						Integer memno = null;
						try {
							memno = new Integer(str);
						} catch (Exception e) {
							errorMsgs1.add("員工編號格式不正確");
						}
						
						
						//***************************2.開始查詢資料*****************************************//*
						
						AttService asv =new AttService();
						AttVO attVO= asv.getOne(memno);
						
						
						
						
						if (attVO == null) {
							errorMsgs1.add("查無資料");
						}
				
						
				//***************************3.查詢完成,準備轉交(Send the Success view)*************//*
						JsonObject jsob =new JsonObject();
						JSONArray list = new JSONArray();
						request.setAttribute("attVO", attVO); // 資料庫取出的attVO物件,存入req
						jsob.addProperty("id", attVO.getAtt_id());
						jsob.addProperty("addr", attVO.getAtt_addr());
						System.out.println(jsob);
						response.getWriter().println(jsob.toString());;
						
						out.println(jsob);
//						String url = "/result/listOneAtt.jsp";
//						RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//						successView.forward(request, response);

				
					
					
					
					
					
		}else if(att_name!=null){
			List<String> errorMsgs1 = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs1);
			try {
						//***************************1.接收請求參數 - 輸入格式的錯誤處理**********************//*
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
											
					
		
				}
	
		


	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
