package com.tourcan.theme.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.tourcan.mem.controller.MemService;
import com.tourcan.resp.model.RespService;
import com.tourcan.resp.model.RespVO;
import com.tourcan.theme.model.ThemeService;
import com.tourcan.theme.model.ThemeVO;

@WebServlet("/articles/ThemeServlet")
public class ThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThemeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
//		BufferedReader br = req.getReader();
//		StringBuffer sb = new StringBuffer(128);
//		String json;
//		while ((json = br.readLine()) != null)
//			sb.append(json);
//		json = sb.toString();
//		System.out.println(json);
		String method = req.getParameter("method");
		JSONObject err = new JSONObject();
		String thstr = req.getParameter("theme_id");
		String muid =req.getParameter("mem_uid");
		// ----------------Query one by attId----------------
		if(method.equals("getOne_For_Display")){
//			System.out.println(thstr);
//			System.out.println("1"+thstr);
			try {
				Integer memno = null;
				try {
					memno = new Integer(thstr);
//					System.out.println("2"+thstr);
				} catch (Exception e) {
					throw new Exception();
				}
				// Send the use back to the form, if there were errors
				Response r = new MemService().queryByUid(muid);
				
				RespService rsv = new RespService();
				List<RespVO> resVO1 = rsv.findByThID(memno);
//				MemService msv =new MemService();
				// System.out.println("resVO1="+resVO1);
				ThemeService tsv = new ThemeService();
				ThemeVO themeVO1 = tsv.findById(memno);

				req.setAttribute("data",r.getEntity());
				req.setAttribute("list", resVO1);
				req.setAttribute("themeVO", themeVO1); 
				String url = "/articles/listOneTheme.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
																				
				successView.forward(req, resp);

			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/articles/list.jsp");
				failureView.forward(req, resp);
			}
			
	//****************************findforMemUID***********************************
		}else if(method.equals("findByMemuid")){
			try{
			ThemeService asv = new ThemeService();
			List<ThemeVO> avo = asv.findByMemuid(muid);
			Gson gson = new Gson();
			String jsonG = gson.toJson(avo);
			if (jsonG.length() < 3) {
				err.append("Memuid", "error");
				resp.getWriter().println(err.toString());
			} else {
				resp.getWriter().println(jsonG.toString());

			}
			// ***************************其他可能的錯誤處理*************************************//*
		} catch (Exception e) {
			err.append("Memuid", "搜尋失敗 請連絡客服或等會再試");
			System.out.println(e.toString());
			resp.getWriter().println(err.toString());
		}
			
		//*******************Find for topicName*****************************	
		}else if(method.equals("getForName")){
			System.out.println("no1=" + thstr);
			String th_name = req.getParameter("theme_topic");
			
		if (th_name != null) {
			try {
				try {
					if (th_name == null || (th_name.trim()).length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					err.append("themeTopic", "無此主題");
				}
				// ***************************2.開始查詢資料*****************************************//*
				ThemeService asv = new ThemeService();
				List<ThemeVO> avo = asv.findByTopic(th_name);

				Gson gson = new Gson();
				String jsonG = gson.toJson(avo);
				if (jsonG.length() < 3) {
					// System.out.println(jsonG.length()<3);
					// response.getWriter().write(jsonG);
					err.append("themeTopic", "無此主題");
					resp.getWriter().println(err.toString());
				} else {
					resp.getWriter().println(jsonG.toString());

				}
				// ***************************其他可能的錯誤處理*************************************//*
			} catch (Exception e) {
				err.append("themeTopic", "搜尋失敗 請連絡客服或等會再試");
				resp.getWriter().println(err.toString());
			}

		}
		} else  {
			ThemeService asv = new ThemeService();
			List<ThemeVO> avo = asv.getAll();
			Gson gson = new Gson();
			String jsonG = gson.toJson(avo);
			System.out.println(jsonG);
			resp.getWriter().println(jsonG.toString());
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// --------------------------------getOne_For_Display----------------------------------
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		// if(action==null){
		// System.out.println("11=");

		// resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		BufferedReader br = req.getReader();
		StringBuffer sb = new StringBuffer(128);
//		String n1 = null;
		String strj;
		while ((strj = br.readLine()) != null)
			sb.append(strj);
		strj = sb.toString();

			JSONObject checkR = new JSONObject();
			Timestamp themetime = null;
			String memUid = null;
			ThemeVO themeVO = null;
			// System.out.println("3333"+strj);
			try {
				themeVO = new Gson().fromJson(strj, ThemeVO.class);
				// System.out.println("22="+themeVO);
				String themeTopic = themeVO.getTheme_topic();
				// System.out.println("33="+themeTopic);
				if (themeTopic == null || themeTopic.trim().isEmpty() || themeTopic.trim().length() == 0) {
					checkR.append("theme_topic", "請輸入主題名稱");
				}
				String themearticle = themeVO.getTheme_article();
				// System.out.println("themearticle:"+themearticle);
				if (themearticle == null || themearticle.trim().isEmpty() || themearticle.trim().length() == 0) {
					checkR.append("theme_article", "請輸入內容");
				}
				memUid = themeVO.getMem_uid();// 抓出已建立的id
				themetime = new Timestamp(System.currentTimeMillis()+8*60*60*1000);
				themeVO.setTheme_time(themetime);
				Integer catlog=1;
				themeVO.setTheme_catalog(catlog);
				if (checkR.length() > 0) {
					throw new Exception();
				} else {
					ThemeService srv = new ThemeService();
					srv.insert(themeVO);
					checkR.append("result", "success");
					resp.getWriter().println(checkR.toString());
				}
			} catch (Exception e) {
				checkR.append("result", "faill");
				resp.getWriter().println(checkR.toString());
				e.printStackTrace();
			}
//		}
		// }

		// if ("getOne_For_Display".equals(action)) String action =
		// req.getParameter("action");

		// System.out.println(action);

	}

	@SuppressWarnings("unused")
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		BufferedReader br = req.getReader();
		Timestamp themetime = null;
		Integer themeid = null;
		String memUid = null;
		ThemeVO themeVO = null;
		JSONObject checkR = new JSONObject();
		try {
			themeVO = new Gson().fromJson(br, ThemeVO.class);

			String themeTopic = themeVO.getTheme_topic();
			// System.out.println(themeTopic);
			if (themeTopic == null || themeTopic.trim().isEmpty() || themeTopic.trim().length() == 0) {
				checkR.append("theme_topic", "請輸入主題名稱");
			}
			String themearticle = themeVO.getTheme_article();
			// System.out.println("themearticle:"+themearticle);
			if (themearticle == null || themearticle.trim().isEmpty() || themearticle.trim().length() == 0) {
				checkR.append("theme_article", "請輸入內容");
			}
			// System.out.println("themetime:"+themetime);
//			Integer themecatalog = themeVO.getTheme_catalog();
//			if (themecatalog == null) {
//				checkR.append("theme_catalog", "plz into catalog");
//			}
			themetime = new Timestamp(System.currentTimeMillis()+8*60*60*1000);
			themeVO.setTheme_time(themetime);
			themeid = themeVO.getTheme_id();
			memUid = themeVO.getMem_uid();// 抓出已建立的id
			Integer catlog=1;
			themeVO.setTheme_catalog(catlog);
			if (checkR.length() > 0) {
				throw new Exception();
			} else {
				ThemeService srv = new ThemeService();
				srv.update(themeVO);
				// srv.insert(themeTopic, themecatalog, themearticle, themetime,
				// memId);
				checkR.append("result", "修改成功");
				resp.getWriter().println(checkR.toString());
			}
		} catch (Exception e) {
			checkR.append("result", "修改失敗");
			resp.getWriter().println(checkR.toString());
			e.printStackTrace();
		}

	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		JSONObject err = new JSONObject();

		String thstr = req.getParameter("theme_id");

		if (thstr != null) {
			Integer thno = null;
			try {
				thno = new Integer(req.getParameter("theme_id"));
				System.out.println(thno);
			} catch (Exception e) {
				err.append("themeInt", "編號只能為整數");
				// resp.getWriter().println(err.toString());
				// System.out.println(e.getMessage());
			}

			if (thno != null) {
				RespService rsv =new RespService();
				RespVO res =new RespVO();
				rsv.deleteByThemeID(thno);
				
				ThemeService tsv = new ThemeService();
				try {
					tsv.delete(thno);
				} catch (Exception e) {
					err.append("themeId", "無此編號");
					// resp.getWriter().println(err.toString());
				}
			} else {
				err.append("themeId", "2無此編號");

			}
		}

		PrintWriter out = resp.getWriter();
		out.println(err.append("themeSu", "success"));
	}

}