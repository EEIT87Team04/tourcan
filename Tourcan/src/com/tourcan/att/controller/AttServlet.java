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
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String method =request.getParameter("method");
		String attstr = request.getParameter("attno");
		
		// PrintWriter out = response.getWriter();
//		if (attstr != null) {
//
//			JSONObject err = new JSONObject(); // checking result
//
//			// ***************************1.接收請求參數 -
//			// 輸入格式的錯誤處理**********************//*
//			try {
//				JsonObject jsob = new JsonObject();
//
//				Integer attno = null;
//				try {
//					attno = new Integer(attstr);
//					if (attstr == null || attstr.trim().length() == 0) {
//						throw new Exception();
//					}
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//					err.append("errmsg", "attno error");
//
//				}
//				// ***************************2.開始查詢資料*****************************************//*
//
//				AttService asv = new AttService();
//				AttVO attVO = asv.getOne(attno);
//
//				// ***************************3.查詢完成,準備轉交(Send the Success
//				// view)*************//*
//				// JSONArray list = new JSONArray();
////				request.setAttribute("attVO", attVO); // 資料庫取出的attVO物件,存入req
////				jsob.addProperty("id", attVO.getAtt_id());
////				jsob.addProperty("name", attVO.getAtt_name());
////				jsob.addProperty("addr", attVO.getAtt_addr());
////				jsob.addProperty("intro", attVO.getAtt_intro());
////				jsob.addProperty("open", attVO.getAtt_open());
////				jsob.addProperty("phone", attVO.getAtt_phone());
////				jsob.addProperty("url", attVO.getAtt_url());
////				jsob.addProperty("price", attVO.getAtt_price());
////				jsob.addProperty("staytime", attVO.getAtt_staytime());
////				jsob.addProperty("region_id", attVO.getRegionVO().getRegion_id());
////				jsob.addProperty("att_lat", attVO.getAtt_lat());
////				jsob.addProperty("att_lng", attVO.getAtt_lng());
////				System.out.println(jsob);
//
//				
//				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//				String jsonG = gson.toJson(attVO);
//				response.getWriter().println(jsonG.toString());
//				;
//
//				// out.println(jsob);
//				// String url = "/result/listOneAtt.jsp";
//				// RequestDispatcher successView =
//				// request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				// successView.forward(request, response);
//
//			} catch (Exception e) {
//				// System.out.println(e.getMessage());
//
//				err.append("errmsg", "search error");
//				response.getWriter().println(err.toString());
//			}
//			// }
//
//		} 
		 if (method.equals("getByName")) {
			JSONObject err = new JSONObject();
			// ***************************1.接收請求參數 -
			// 輸入格式的錯誤處理**********************//*
			try {
				String att_name = request.getParameter("attname");
				if (att_name == null || att_name.length()==0 ) {
//				try {
//						System.out.println("1235466");
//						err.append("errname", "attname error");
						throw new Exception();
//					}
//				} catch (Exception e) {
//					err.append("errname", "attname error");
				}
				 System.out.println("s1="+att_name);
				// Send the use back to the form, if there were errors
				// ***************************2.開始查詢資料*****************************************//*
				AttService asv = new AttService();
				List<AttVO> avo = asv.getAllByName(att_name);
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				// 指定要給的值 避免gson.toJson() throws StackOverflowError
//				System.out.println(gson);
				// ***************************3.查詢完成,準備轉交(Send the Success
				String jsonG = gson.toJson(avo);
				// response.getWriter().write(jsonG);
//				try{
//				if(jsonG.length()<3){
//					throw new Exception();
//				}
//				}catch (Exception e) {
//					System.out.println("123456");
//					err.append("errmsg", "error");
//					response.getWriter().println(err.toString());
//				}
				response.getWriter().println(jsonG);
//				err.append("errmsg", "success");
//				response.getWriter().println(err.toString());
				
				// ***************************其他可能的錯誤處理*************************************//*
			} catch (Exception e) {
				err.append("errmsg", "search error");
				response.getWriter().println(err.toString());
			}

		} else if (method.equals("getAll")) {
			AttService asv = new AttService();
			List<AttVO> avo = asv.getAll();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String jsonG = gson.toJson(avo);
			System.out.println(jsonG);
			response.getWriter().println(jsonG.toString());
		}
	}

}
