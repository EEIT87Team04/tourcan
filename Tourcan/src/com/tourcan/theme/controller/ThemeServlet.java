package com.tourcan.theme.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ThemeServlet")
public class ThemeServlet  extends HttpServlet{
  	private static final long serialVersionUID =1L;
  	
  	public ThemeServlet(){
  		super();	
  	}
  	
  	
  	protected void doGPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
  		req.setCharacterEncoding("UTF-8");
  		resp.setCharacterEncoding("UTF-8");
  		resp.setContentType("application/json");
  		

  	}
  	
  	protected void doGet(HttpServletRequest req ,HttpServletResponse resp) throws ServletException,IOException  {
  				 		
  	}
}