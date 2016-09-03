package com.tourcan.theme.controller;

import java.io.BufferedReader;
import java.io.IOException;
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
import com.tourcan.att.model.AttService;
import com.tourcan.att.model.AttVO;
import com.tourcan.theme.model.ThemeService;
import com.tourcan.theme.model.ThemeVO;

@WebServlet("/theme/ThemeServlet")
public class ThemeServlet  extends HttpServlet{
  	private static final long serialVersionUID =1L;
  	
  	public ThemeServlet(){
  		super();	
  	}
  	
  	protected void doGet(HttpServletRequest req ,HttpServletResponse resp) throws ServletException,IOException  {
  		req.setCharacterEncoding("UTF-8");
  		resp.setCharacterEncoding("UTF-8");
  		resp.setContentType("application/json");  		
  		String thstr = req.getParameter("themeID");
		String th_name = req.getParameter("themeTopice");
		// PrintWriter out = response.getWriter();
		BufferedReader br = req.getReader();
		StringBuffer sb = new StringBuffer(128);
		String json;
		while ((json = br.readLine()) != null)
			sb.append(json);
		json = sb.toString();
		JSONObject err = new JSONObject(); 
// ----------------Query one by attId----------------	
	
			if(thstr!=null){
				Integer thno =null;
			try {
				 thno =new Integer(req.getParameter("themeID"));				
				} catch (Exception e) {
					err.append("themeId", "編號只能為整數");
					System.out.println(e.getMessage());
				}
				// ***************************2.開始查詢資料*****************************************//*

				if(thno!=null){
				ThemeService tsv =new ThemeService();
				ThemeVO thVO = tsv.findById(thno);
				if(thVO !=null){
				try{
				Gson gson  = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
				String themeG = gson.toJson(thVO);
				System.out.println(themeG);
				resp.getWriter().println(themeG);
				}catch (Exception e) {
					err.append("themeId", "error");
					resp.getWriter().println(err.toString());
				}				
				} else {				
				err.append("themeId", "search error");
				resp.getWriter().println(err.toString());
			    }
				}else{
				err.append("themeId", "search error");

				}
			return;			
		}
			

		  if (th_name != null) {
			try {
				try {
					if (th_name == null || (th_name.trim()).length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					err.append("errname", "attname error");
				}
				// System.out.println("s1="+att_name);
				// ***************************2.開始查詢資料*****************************************//*
				ThemeService asv = new ThemeService();
				List<ThemeVO> avo = asv.findByTopic(th_name);
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				// 指定要給的值 避免gson.toJson() throws StackOverflowError

				// ***************************3.查詢完成,準備轉交(Send the Success
				String jsonG = gson.toJson(avo);
				System.out.println(jsonG);
				// response.getWriter().write(jsonG);
				resp.getWriter().println(jsonG.toString());
				;
				// ***************************其他可能的錯誤處理*************************************//*
			} catch (Exception e) {
				err.append("errmsg", "search error");
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
  		
  	
  	
  	protected void doGPost(HttpServletRequest req, HttpServletResponse resp)
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
			themeVO = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().fromJson(br,ThemeVO.class);
			
			String themeTopic= themeVO.getTheme_topic();
			if(themeTopic==null||themeTopic.trim().isEmpty()||themeTopic.trim().length()==0){
			checkR.append("theme_topic","plz into topic");
			}
			String themearticle = themeVO.getTheme_article();
			if(themearticle==null||themearticle.trim().isEmpty()||themearticle.trim().length()==0){
			checkR.append("theme_article", "plz into topic");
			}
			themetime=new Timestamp(System.currentTimeMillis());
			System.out.println(themetime);
			Integer themecatalog =themeVO.getMem_id();
			if(themecatalog==null){
				checkR.append("theme_catalog", "plz into catalog");	
			}
			memId= themeVO.getMem_id();//抓出已建立的id
			if(checkR.length()>0){
				throw new Exception();
			}else{
				ThemeService srv =new ThemeService();
				srv.insert(themearticle, themeTopic, themecatalog, themetime, memId);
				checkR.append("result", "success");
				resp.getWriter().println(checkR.toString());
			}
		}catch (Exception e) {
			checkR.append("result", "faill");
			resp.getWriter().println(checkR.toString());
			e.printStackTrace();
		}

  	}
  	
}