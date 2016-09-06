package com.tourcan.resp.controller;

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
import com.tourcan.resp.model.RespService;
import com.tourcan.resp.model.RespVO;



@WebServlet("/resp/RespServlet")
public class RespServlet  extends HttpServlet{
  	private static final long serialVersionUID =1L;
  	
  	public RespServlet(){
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
	
		String thstr = req.getParameter("resp_id");
			if(thstr!=null){
				Integer rsno =null;
			try {
				rsno =new Integer(req.getParameter("resp_id"));
				 System.out.println(rsno);
				} catch (Exception e) {
					err.append("respId", "編號只能為整數");
					resp.getWriter().println(err.toString());
//					System.out.println(e.getMessage());
				}
				// ***************************2.開始查詢資料*****************************************//*

				if(rsno!=null){
				RespService rsv =new RespService();
				RespVO thVO = rsv.findById(rsno);
//				System.out.println(thVO);
				if(thVO !=null){
				try{
				Gson gson  = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
				String themeG = gson.toJson(thVO);
//				System.out.println(themeG);
				resp.getWriter().println(themeG);
				}catch (Exception e) {
					err.append("respId", "無此編號");
					resp.getWriter().println(err.toString());
				}				
				} else {				
				err.append("respId", "無此編號");
				resp.getWriter().println(err.toString());
			    }
				}else{
				err.append("respId", "無此編號2");

				}
			return;			
		}
			
			String th_name = req.getParameter("resp_topic");
//			System.out.println(th_name);
		  if (th_name != null) {
			try {
				try {
					if (th_name == null || (th_name.trim()).length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					err.append("respTopic", "無此Resp");
				}
				// ***************************2.開始查詢資料*****************************************//*
				RespService asv = new RespService();
				List<RespVO> avo = asv.findByTopic(th_name);
				
				Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
				String jsonG = gson.toJson(avo);
				if(jsonG.length()<3){
//				System.out.println(jsonG.length()<3);
				// response.getWriter().write(jsonG);
						err.append("respTopic", "無此Resp");
						resp.getWriter().println(err.toString());
				}else{
					resp.getWriter().println(jsonG.toString());
					
				}
				// ***************************其他可能的錯誤處理*************************************//*
			} catch (Exception e) {
				err.append("respTopic", "respTopic search error");
				resp.getWriter().println(err.toString());
			}
		  }
//		} else if (thstr == null && th_name == null) {
//			RespService asv = new RespService();
//			List<ThemeVO> avo = asv.getAll();
//			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//			String jsonG = gson.toJson(avo);
//			System.out.println(jsonG);
//			resp.getWriter().println(jsonG.toString());
//		}
		
	}
  		

  	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
  		req.setCharacterEncoding("UTF-8");
  		resp.setCharacterEncoding("UTF-8");
  		resp.setContentType("application/json");
		BufferedReader br = req.getReader();
		JSONObject checkR =new  JSONObject();
		Timestamp resptime =null;
		Integer memId = null;
		Integer themeid=null;
		RespVO respVO=null;
		try{
			respVO = new Gson().fromJson(br,RespVO.class);
			
			String respTopic= respVO.getResp_topic();
//			System.out.println(themeTopic);
			if(respTopic==null||respTopic.trim().isEmpty()||respTopic.trim().length()==0){
			checkR.append("resp_topic","plz into respTopic");
			}
			String resparticle = respVO.getResp_article();
//			System.out.println("themearticle:"+themearticle);
			if(resparticle==null||resparticle.trim().isEmpty()||resparticle.trim().length()==0){
			checkR.append("resp_article", "plz into resparticle");
			}
			resptime=new Timestamp(System.currentTimeMillis());
//			System.out.println("themetime:"+themetime);
			respVO.setResp_time(resptime);
			themeid=respVO.getTheme_id();
			memId= respVO.getMem_id();//抓出已建立的id
			if(checkR.length()>0){
				throw new Exception();
			}else{
				RespService srv =new RespService();
				srv.insert(respVO);    //(respTopic,  resparticle, resptime, memId);
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
		Timestamp resptime =null;
		Integer respid=null;
		Integer themeid=null;
		Integer memId = null;
		RespVO respVO=null;
		JSONObject checkR =new  JSONObject();
		try{
			respVO = new Gson().fromJson(br,RespVO.class);
			
			String respTopic= respVO.getResp_topic();
//			System.out.println(themeTopic);
			if(respTopic==null||respTopic.trim().isEmpty()||respTopic.trim().length()==0){
			checkR.append("theme_topic","plz into topic");
			}
			String resparticle = respVO.getResp_article();
//			System.out.println("themearticle:"+themearticle);
			if(resparticle==null||resparticle.trim().isEmpty()||resparticle.trim().length()==0){
			checkR.append("theme_article", "plz into article");
			}
//			System.out.println("themetime:"+themetime);
			
			resptime=new Timestamp(System.currentTimeMillis());
			respVO.setResp_time(resptime);
			themeid=respVO.getTheme_id();
			respid=respVO.getResp_id();
			memId= respVO.getMem_id();//抓出已建立的id
			if(checkR.length()>0){
				throw new Exception();
			}else{
				RespService srv =new RespService();
				srv.update(respVO);
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
  		
		String respstr = req.getParameter("resp_id");
		
		if(respstr!=null){
			Integer rsno =null;
			try {
				 rsno =new Integer(req.getParameter("resp_id"));
				 System.out.println(rsno);
				} catch (Exception e) {
					err.append("respInt", "編號只能為整數");
	//				resp.getWriter().println(err.toString());
	//				System.out.println(e.getMessage());
				}
			
			if(rsno!=null){
				RespService tsv =new RespService();
				try{
				 tsv.delete(rsno);
					}catch (Exception e) {
					err.append("respId", "無此編號");
	//				resp.getWriter().println(err.toString());
					}											
				}else{
				err.append("respId", "2無此編號");
	
				}				
			} 
		
		PrintWriter out = resp.getWriter();
		out.println(err.append("respSu","success"));
	}

}