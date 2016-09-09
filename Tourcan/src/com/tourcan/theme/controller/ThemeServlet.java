package com.tourcan.theme.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.converter.json.GsonBuilderUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tourcan.resp.model.RespService;
import com.tourcan.resp.model.RespVO;
import com.tourcan.theme.model.ThemeService;
import com.tourcan.theme.model.ThemeVO;

@WebServlet("/theme/ThemeServlet")
public class ThemeServlet  extends HttpServlet{
  	private static final long serialVersionUID =1L;
  	
  	public ThemeServlet(){
  		super();	
  	}
  	@Override
  	protected void doGet(HttpServletRequest req ,HttpServletResponse resp) throws ServletException,IOException  {

  		
  		
  		req.setCharacterEncoding("UTF-8");
  		resp.setCharacterEncoding("UTF-8");
  		resp.setContentType("application/json");  		
		BufferedReader br = req.getReader();
		StringBuffer sb = new StringBuffer(128);
		String json;
		while ((json = br.readLine()) != null)
			sb.append(json);
		json = sb.toString();
		System.out.println(json);
		JSONObject err = new JSONObject(); 
// ----------------Query one by attId----------------	
	
		String thstr = req.getParameter("theme_id");
		System.out.println("no1="+thstr);
		String th_name = req.getParameter("theme_topic");
			if(thstr!=null){
				Integer thno =null;
			try {
				 thno =new Integer(req.getParameter("theme_id"));
				} catch (Exception e) {
					err.append("themeId", "編號只能為整數");
					resp.getWriter().println(err.toString());
//					System.out.println(e.getMessage());
				}
				// ***************************2.開始查詢資料*****************************************//*

				if(thno!=null){
				ThemeService tsv =new ThemeService();
				ThemeVO thVO = tsv.findById(thno);
//				System.out.println(thVO);
				if(thVO !=null){
				try{
				Gson gson  = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
				String themeG = gson.toJson(thVO);
//				System.out.println(themeG);
				resp.getWriter().println(themeG);
				}catch (Exception e) {
					err.append("themeId", "無此編號");
					resp.getWriter().println(err.toString());
				}				
				} else {				
				err.append("themeId", "無此編號");
				resp.getWriter().println(err.toString());
			    }
				}else{
				err.append("themeId", "無此編號");

				}
			return;			
		}
			
//			System.out.println(th_name);
		  if (th_name != null) {
			try {
				try {
					if (th_name == null || (th_name.trim()).length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					err.append("themeTopic", "無此Topic");
				}
				// ***************************2.開始查詢資料*****************************************//*
				ThemeService asv = new ThemeService();
				List<ThemeVO> avo = asv.findByTopic(th_name);
				
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				String jsonG = gson.toJson(avo);
				if(jsonG.length()<3){
//				System.out.println(jsonG.length()<3);
				// response.getWriter().write(jsonG);
						err.append("themeTopic", "無此Topic");
						resp.getWriter().println(err.toString());
				}else{
					resp.getWriter().println(jsonG.toString());
					
				}
				// ***************************其他可能的錯誤處理*************************************//*
			} catch (Exception e) {
				err.append("themeTopic", "themeTopic search error");
				resp.getWriter().println(err.toString());
			}

		} else if (thstr == null && th_name == null) {
			ThemeService asv = new ThemeService();
			List<ThemeVO> avo = asv.getAll();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String jsonG = gson.toJson(avo);
			System.out.println(jsonG);
			resp.getWriter().println(jsonG.toString());
		}
		
	}
  		

  	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//--------------------------------getOne_For_Display----------------------------------
  		req.setCharacterEncoding("UTF-8");
//  		if(action==null){
//			System.out.println("11=");
			
//	  		resp.setCharacterEncoding("UTF-8");
	  		resp.setContentType("application/json");
			BufferedReader br = req.getReader();
			StringBuffer sb = new StringBuffer(128);
			String n1=null;
			String json;
			while ((json = br.readLine()) != null)
				sb.append(json);
			json = sb.toString();
			
			if(json.indexOf("getOne")>-1){
			System.out.println("000="+json);
			if(json.length()>36){
//			System.out.println(json.length());			
			System.out.println(json.substring(9, 11));
			n1=json.substring(9, 11);
			}else{
			System.out.println(json.substring(9, 10));
			n1=json.substring(9, 10);
			}
			System.out.println("n1:"+n1);
			 { 
				System.out.println("qqqqqqqqqq");
				List<String> errorMsgs1 = new LinkedList<String>();
//				req.setCharacterEncoding("UTF-8");
				req.setAttribute("errorMsgs", errorMsgs1);
				
				try {
					
					String str = n1;
					System.out.println("s_id="+str);
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs1.add("error");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs1.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/theme/showAllTheme.jsp");
						failureView.forward(req, resp);
						return;
					}
					
					Integer memno = null;
					try {
						memno = new Integer(str);
					} catch (Exception e) {
						errorMsgs1.add("error");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs1.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/theme/showAllTheme.jsp");
						failureView.forward(req, resp);
						return;
					}
					RespService rsv =new RespService();
					List<RespVO> resVO1=rsv.findByThID(memno);
//					System.out.println("resVO1="+resVO1);
					ThemeService tsv =new ThemeService();
					ThemeVO themeVO1= tsv.findById(memno);
					if (themeVO1 == null) {
						errorMsgs1.add("error");
					}
					// Send the use back to the form, if there were errors
					if (!errorMsgs1.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/theme/showAllTheme.jsp");
						failureView.forward(req, resp);
						return;//�{�����_
					}
					
					//***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************//*
					req.setAttribute("list", resVO1);
					req.setAttribute("themeVO", themeVO1); // ��Ʈw���X��empVO����,�s�Jreq
					String url = "/theme/listOneTheme.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
					successView.forward(req, resp);
					
					//***************************��L�i�઺���~�B�z*************************************//*
				} catch (Exception e) {
					errorMsgs1.add("error:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/theme/showAllTheme.jsp");
					failureView.forward(req, resp);
				}
			}  		  		
			
			
			}else{
			JSONObject checkR =new  JSONObject();
			Timestamp themetime =null;
			String memUid = null;
			ThemeVO themeVO=null;
			try{
				themeVO = new Gson().fromJson(br,ThemeVO.class);
				System.out.println("22="+themeVO);
				String themeTopic= themeVO.getTheme_topic();
				System.out.println("33="+themeTopic);
				if(themeTopic==null||themeTopic.trim().isEmpty()||themeTopic.trim().length()==0){
				checkR.append("theme_topic","plz into topic");
				}
				String themearticle = themeVO.getTheme_article();
//				System.out.println("themearticle:"+themearticle);
				if(themearticle==null||themearticle.trim().isEmpty()||themearticle.trim().length()==0){
				checkR.append("theme_article", "plz into article");
				}
				themetime=new Timestamp(System.currentTimeMillis());
//				System.out.println("themetime:"+themetime);
				Integer themecatalog =themeVO.getTheme_catalog();
				if(themecatalog==null){
					checkR.append("theme_catalog", "plz into catalog");	
				}
				memUid= themeVO.getMem_uid();//抓出已建立的id
				if(checkR.length()>0){
					throw new Exception();
				}else{
					ThemeService srv =new ThemeService();
					srv.insert(themeTopic, themecatalog, themearticle, themetime, memUid);
					checkR.append("result", "success");
					resp.getWriter().println(checkR.toString());
				}
			}catch (Exception e) {
				checkR.append("result", "faill");
				resp.getWriter().println(checkR.toString());
				e.printStackTrace();
			}
			}
//  		}
					
		
//			if ("getOne_For_Display".equals(action))  String action = req.getParameter("action"); 
			
//			System.out.println(action);
		
		
  	}
  	
  	@SuppressWarnings("unused")
	protected void doPut(HttpServletRequest req,HttpServletResponse resp)throws  ServletException, IOException {
  		req.setCharacterEncoding("UTF-8");
  		resp.setCharacterEncoding("UTF-8");
  		resp.setContentType("application/json");
		BufferedReader br = req.getReader();
		Timestamp themetime =null;
		Integer themeid=null;
		String memUid = null;
		ThemeVO themeVO=null;
		JSONObject checkR =new  JSONObject();
		try{
			themeVO = new Gson().fromJson(br,ThemeVO.class);
			
			String themeTopic= themeVO.getTheme_topic();
//			System.out.println(themeTopic);
			if(themeTopic==null||themeTopic.trim().isEmpty()||themeTopic.trim().length()==0){
			checkR.append("theme_topic","plz into topic");
			}
			String themearticle = themeVO.getTheme_article();
//			System.out.println("themearticle:"+themearticle);
			if(themearticle==null||themearticle.trim().isEmpty()||themearticle.trim().length()==0){
			checkR.append("theme_article", "plz into article");
			}
//			System.out.println("themetime:"+themetime);
			Integer themecatalog =themeVO.getTheme_catalog();
			if(themecatalog==null){
				checkR.append("theme_catalog", "plz into catalog");	
			}
			themetime=new Timestamp(System.currentTimeMillis());
			themeVO.setTheme_time(themetime);
			themeid=themeVO.getTheme_id();
			memUid= themeVO.getMem_uid();//抓出已建立的id
			if(checkR.length()>0){
				throw new Exception();
			}else{
				ThemeService srv =new ThemeService();
				srv.update(themeVO);
//				srv.insert(themeTopic, themecatalog, themearticle, themetime, memId);
				checkR.append("result", "修改成功");
				resp.getWriter().println(checkR.toString());
			}
		}catch (Exception e) {
			checkR.append("result", "修改失敗");
			resp.getWriter().println(checkR.toString());
			e.printStackTrace();
		}
  	
  	
  	}
  	
  	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
  		req.setCharacterEncoding("UTF-8");
  		resp.setCharacterEncoding("UTF-8");
  		resp.setContentType("application/json");
		JSONObject err = new JSONObject();
  		
		String thstr = req.getParameter("theme_id");
		
		if(thstr!=null){
			Integer thno =null;
			try {
				 thno =new Integer(req.getParameter("theme_id"));
				 System.out.println(thno);
				} catch (Exception e) {
					err.append("themeInt", "編號只能為整數");
	//				resp.getWriter().println(err.toString());
	//				System.out.println(e.getMessage());
				}
			
			if(thno!=null){
				ThemeService tsv =new ThemeService();
				try{
				 tsv.delete(thno);
					}catch (Exception e) {
					err.append("themeId", "無此編號");
	//				resp.getWriter().println(err.toString());
					}											
				}else{
				err.append("themeId", "2無此編號");
	
				}				
			} 
		
		PrintWriter out = resp.getWriter();
		out.println(err.append("themeSu","success"));
	}

}