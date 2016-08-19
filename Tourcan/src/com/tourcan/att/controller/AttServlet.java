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

@WebServlet("/att/att.do")
public class AttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AttServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

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
				RequestDispatcher rd = request.getRequestDispatcher("/att/deleteSuccess.jsp"); // 成功轉交
				rd.forward(request, response);
				return;// 程式中斷

				/*************************** 其他可能的錯誤處理 **************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher rd = request.getRequestDispatcher("/att/delete_att.jsp");
				rd.forward(request, response);
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
