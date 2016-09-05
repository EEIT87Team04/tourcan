package com.tourcan.theme.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.converter.json.GsonBuilderUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
		// PrintWriter out = response.getWriter();
		BufferedReader br = req.getReader();
		StringBuffer sb = new StringBuffer(128);
		String json;
		while ((json = br.readLine()) != null)
			sb.append(json);
		json = sb.toString();
		JSONObject err = new JSONObject(); 
// ----------------Query one by attId----------------	
	
		String thstr = req.getParameter("theme_id");
			if(thstr!=null){
				Integer thno =null;
			try {
				 thno =new Integer(req.getParameter("theme_id"));
				 System.out.println(thno);
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
			
			String th_name = req.getParameter("theme_topic");
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
  		req.setCharacterEncoding("UTF-8");
  		resp.setCharacterEncoding("UTF-8");
  		resp.setContentType("application/json");
		BufferedReader br = req.getReader();
		JSONObject checkR =new  JSONObject();
		Timestamp themetime =null;
		Integer memId = null;
		ThemeVO themeVO=null;
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
			themetime=new Timestamp(System.currentTimeMillis());
//			System.out.println("themetime:"+themetime);
			Integer themecatalog =themeVO.getTheme_catalog();
			if(themecatalog==null){
				checkR.append("theme_catalog", "plz into catalog");	
			}
			memId= themeVO.getMem_id();//抓出已建立的id
			if(checkR.length()>0){
				throw new Exception();
			}else{
				ThemeService srv =new ThemeService();
				srv.insert(themeTopic, themecatalog, themearticle, themetime, memId);
				checkR.append("result", "success");
				resp.getWriter().println(checkR.toString());
			}
		}catch (Exception e) {
			checkR.append("result", "faill");
			resp.getWriter().println(checkR.toString());
			e.printStackTrace();
		}

  	}
  	
  	@SuppressWarnings("unused")
	protected void doPut(HttpServletRequest req,HttpServletResponse resp)throws  ServletException, IOException {
  		req.setCharacterEncoding("UTF-8");
  		resp.setCharacterEncoding("UTF-8");
  		resp.setContentType("application/json");
		BufferedReader br = req.getReader();
		Timestamp themetime =null;
		Integer themeid=null;
		Integer memId = null;
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
			memId= themeVO.getMem_id();//抓出已建立的id
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