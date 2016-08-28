package com.tourcan.att.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
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
		String attstr = request.getParameter("attno");
		String att_name = request.getParameter("attname");
//		PrintWriter out = response.getWriter();
		if(attstr!=null){
		
			JSONObject err = new JSONObject(); // checking result
	
			//***************************1.接收請求參數 - 輸入格式的錯誤處理**********************//*
			try{
				JsonObject jsob =new JsonObject();
					
						Integer attno = null;
						try {
							attno = new Integer(attstr);
							if(attstr==null||attstr.trim().length()==0){
								throw new Exception();
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
							err.append("errmsg", "attno error");

						}
						
						
						//***************************2.開始查詢資料*****************************************//*
						
						AttService asv =new AttService();
						AttVO attVO= asv.getOne(attno);
			
						
				//***************************3.查詢完成,準備轉交(Send the Success view)*************//*
//						JSONArray list = new JSONArray();
						request.setAttribute("attVO", attVO); // 資料庫取出的attVO物件,存入req
						jsob.addProperty("id", attVO.getAtt_id());
						jsob.addProperty("name", attVO.getAtt_name());
						jsob.addProperty("addr", attVO.getAtt_addr());
						jsob.addProperty("intro", attVO.getAtt_intro());
						jsob.addProperty("open", attVO.getAtt_open());
						jsob.addProperty("phone", attVO.getAtt_phone());
						jsob.addProperty("url", attVO.getAtt_url());
						jsob.addProperty("price", attVO.getAtt_price());
						jsob.addProperty("staytime", attVO.getAtt_staytime());
						jsob.addProperty("region_id", attVO.getRegionVO().getRegion_id());
						jsob.addProperty("att_lat", attVO.getAtt_lat());
						jsob.addProperty("att_lng", attVO.getAtt_lng());
						System.out.println(jsob);
						
						response.getWriter().println(jsob.toString());;
						
//						out.println(jsob);
//						String url = "/result/listOneAtt.jsp";
//						RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//						successView.forward(request, response);

		}catch (Exception e) {
//			System.out.println(e.getMessage());

			err.append("errmsg", "search error");
			response.getWriter().println(err.toString());
		}
//		}
														
					
		}else if(att_name!=null){
			JSONObject err = new JSONObject();
				//***************************1.接收請求參數 - 輸入格式的錯誤處理**********************//*
			try{
				try{
				if (att_name == null || (att_name.trim()).length() == 0) {
					throw new Exception();
					}
				}catch (Exception e) {
					err.append("errname", "attname error");
				}
					//System.out.println("s1="+att_name);
					// Send the use back to the form, if there were errors
						
					//***************************2.開始查詢資料*****************************************//*
							
				AttService asv =new AttService();				
				List<AttVO> avo = asv.getAllByName(att_name);
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			//指定要給的值  避免gson.toJson() throws StackOverflowError			
											
				//***************************3.查詢完成,準備轉交(Send the Success view)*************//*
					 
					String jsonG = gson.toJson(avo);
					System.out.println(jsonG);
					           
					//response.getWriter().write(jsonG);
					response.getWriter().println(jsonG.toString());;
					//***************************其他可能的錯誤處理*************************************//*
			}catch (Exception e) {
				err.append("errmsg", "search error");
				response.getWriter().println(err.toString());
			}	
		
		}else if(attstr==null&&att_name==null){
			AttService asv =new AttService();				
			List<AttVO> avo = asv.getAll();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String jsonG = gson.toJson(avo);
			System.out.println(jsonG);
			
			response.getWriter().println(jsonG.toString());;

		}
		
		
	}
	
		


	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
