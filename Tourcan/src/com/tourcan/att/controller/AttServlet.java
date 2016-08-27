package com.tourcan.att.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.tourcan.att.model.AttService;

@WebServlet("/att/AttServlet")
public class AttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AttServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("method").equals("delete")) {
			doDelete(request, response);
		}

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
