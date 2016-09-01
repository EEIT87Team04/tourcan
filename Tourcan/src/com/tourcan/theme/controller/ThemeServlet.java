package com.tourcan.theme.controller;

import java.io.IOException;
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
import com.google.gson.JsonObject;
import com.tourcan.att.model.AttService;
import com.tourcan.att.model.AttVO;
import com.tourcan.theme.model.ThemeService;
import com.tourcan.theme.model.ThemeVO;

@WebServlet("/ThemeServlet")
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
		if (thstr != null) {

			JSONObject err = new JSONObject(); // checking result

			// ***************************1.接收請求參數 -
			// 輸入格式的錯誤處理**********************//*
			try {
				JsonObject jsob = new JsonObject();

				Integer thno = null;
				try {
					thno = new Integer(thstr);
					if (thstr == null || thstr.trim().length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					err.append("errmsg", "attno error");

				}
				// ***************************2.開始查詢資料*****************************************//*


				ThemeService tsv =new ThemeService();
				ThemeVO thVO = tsv.findById(thno);
				
				Gson gson  = GsonBuilderUtils.gsonBuilderWithBase64EncodedByteArrays().create();
				String jsonG = gson.toJson(thVO);
				System.out.println(jsonG);

				resp.getWriter().println(jsonG.toString());
				;

			} catch (Exception e) {
				// System.out.println(e.getMessage());

				err.append("errmsg", "search error");
				resp.getWriter().println(err.toString());
			}
			// }

		} else if (th_name != null) {
			JSONObject err = new JSONObject();
			// ***************************1.接收請求參數 -
			// 輸入格式的錯誤處理**********************//*
			try {
				try {
					if (th_name == null || (th_name.trim()).length() == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					err.append("errname", "attname error");
				}
				// System.out.println("s1="+att_name);
				// Send the use back to the form, if there were errors
				// ***************************2.開始查詢資料*****************************************//*
				AttService asv = new AttService();
				List<AttVO> avo = asv.getAllByName(th_name);
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
			AttService asv = new AttService();
			List<AttVO> avo = asv.getAll();
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
  		

  	}
  	
}