package com.tourcan.att.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.tourcan.att.model.AttDAO_JDBC;
import com.tourcan.att.model.AttService;
import com.tourcan.att.model.AttVO;

@WebServlet("/att/mem.do")
public class AttServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AttServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json");
//		BufferedReader br = request.getReader();
//		StringBuffer sb = new StringBuffer(128);
//		String json;
//		while ((json = br.readLine()) != null)
//			sb.append(json);
//		json = sb.toString();
//		// System.out.println(json);
//
//		JSONObject err = new JSONObject(); // checking result
//		try {
//			JSONObject obj = new JSONObject(json); // received and parsed JSON
//			AttVO vo = new AttVO();
//
//			try {
//				String attName = obj.getString("attName");
//				if (attName == null || attName.trim().isEmpty() || attName.trim().length() >= 50) {
//					throw new Exception();
//				}
//				vo.setAtt_name(attName);
//			} catch (Exception e) {
//				err.append("attName", "無效的景點名稱。");
////				e.printStackTrace();
//			}
//
//			try {
//				Integer regionId = obj.getInt("regionId");
//				if (regionId == null || regionId < 0) {
//					throw new Exception();
//				}
//				vo.setRegion_id(regionId);
//			} catch (Exception e) {
//				err.append("regionId", "無效的地區代號。");
////				e.printStackTrace();
//			}
//
//			try {
//				String attAddr = obj.getString("attAddr");
//				if (attAddr == null || attAddr.trim().isEmpty()) {
//					throw new Exception();
//				}
//				vo.setAtt_addr(attAddr);
//			} catch (Exception e) {
//				err.append("attAddr", "無效的景點地址。");
////				e.printStackTrace();
//			}
//
//			try {
//				Boolean attEat = obj.getBoolean("attEat");
//				vo.setAtt_eat(attEat);
//			} catch (Exception e) {
//				err.append("attAddr", "無效的吃貨屬性。");
////				e.printStackTrace();
//			}
//
//			try {
//				String attIntro = obj.getString("attIntro");
//				if (attIntro == null || attIntro.trim().isEmpty()) {
//					throw new Exception();
//				}
//				vo.setAtt_intro(attIntro);
//			} catch (Exception e) {
//				err.append("attIntro", "無效的景點介紹。");
////				e.printStackTrace();
//			}
//
//			try {
//				String appOpen = obj.getString("attOpen");
//				if (appOpen == null || appOpen.trim().isEmpty()) {
//					throw new Exception();
//				}
//				vo.setAtt_open(appOpen);
//			} catch (Exception e) {
//				err.append("attOpen", "無效的開放資訊。");
////				e.printStackTrace();
//			}
//
//			try {
//				String attPhone = obj.getString("attPhone");
//				if (attPhone == null || attPhone.trim().isEmpty() || attPhone.trim().length() >= 50) {
//					throw new Exception();
//				}
//				vo.setAtt_phone(attPhone);
//			} catch (Exception e) {
//				err.append("attPhone", "無效的聯絡電話。");
////				e.printStackTrace();
//			}
//
//			try {
//				Double attPrice = obj.getDouble("attPrice");
//				if (attPrice == null || attPrice < 0) {
//					throw new Exception();
//				}
//				vo.setAtt_price(attPrice);
//			} catch (Exception e) {
//				err.append("attPrice", "無效的基本消費。");
////				e.printStackTrace();
//			}
//
//			try {
//				Integer attStaytime = obj.getInt("attStaytime");
//				if (attStaytime == null || attStaytime < -1) {
//					throw new Exception();
//				}
//				vo.setAtt_staytime(attStaytime);
//			} catch (Exception e) {
//				err.append("attStaytime", "無效的滯留時間。");
////				e.printStackTrace();
//			}
//
//			try {
//				String attUrl = obj.getString("attUrl");
//				if (attUrl == null || attUrl.trim().isEmpty()) {
//					throw new Exception();
//				}
//				vo.setAtt_url(attUrl);
//			} catch (Exception e) {
//				err.append("attUrl", "無效的景點網址。");
////				e.printStackTrace();
//			}
//
//			try {
//				Double attLat = obj.getDouble("attLat");
//				if (attLat == null || attLat < -90 || attLat > 90) {
//					throw new Exception();
//				}
//				vo.setAtt_lat(attLat);
//			} catch (Exception e) {
//				err.append("attLat", "無效的緯度。");
////				e.printStackTrace();
//			}
//
//			try {
//				Double attLng = obj.getDouble("attLng");
//				if (attLng == null || attLng < -180 || attLng > 180) {
//					throw new Exception();
//				}
//				vo.setAtt_lng(attLng);
//			} catch (Exception e) {
//				err.append("attLng", "無效的經度。");
////				e.printStackTrace();
//			}
//
//			if (err.length() > 0) {
//				throw new Exception();
//			} else {
//				AttDAO_JDBC dao = new AttDAO_JDBC();
//				dao.insert(vo);
//			}
//		} catch (Exception e) {
//			err.append("result", "新增失敗。");
////			e.printStackTrace();
//		}
//		
//		response.getWriter().println(err.toString());

		// // test output
		// for (String key : JSONObject.getNames(obj)) {
		// System.out.println(key+"\t"+obj.getString(key));
		// for (String key2 : JSONObject.getNames(obj.getJSONObject(key1))) {
		// System.out.println(" " + key2);
		// }
		// }
		//
		// // test output
		// System.out.println("\n" + sb.toString());
		// Enumeration<String> headers = request.getHeaderNames();
		// while (headers.hasMoreElements()) {
		// String header = headers.nextElement();
		// System.out.println(header + "\t: " + request.getHeader(header));
		// }
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		/*************************** 修改Update ****************************************/

		/*********************** 用 id 找 data *************************/

		if ("update_idCheck".equals(action)) {
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			request.setAttribute("ErrorMsgKey", errorMsgMap);

			/*********************** 檢查id *************************/

			Integer idI = null;
			// Integer id=new Integer(request.getParameter("mem_id"));

			/****************** 驗證id ********************/
			try {
				String id = request.getParameter("att_id");
				try {
					idI = new Integer(id);
				} catch (Exception e) {
					if (id == null || id.trim().length() == 0) {
						errorMsgMap.put("id", "please into id ");
					} else {
						errorMsgMap.put("id", "輸入格式錯誤");
					}
				}

				if (!errorMsgMap.isEmpty()) {
					RequestDispatcher rd = request
							.getRequestDispatcher("/att/update_idCheck.jsp");
					rd.forward(request, response);
					return;
				}

				/******************** 利用DAO中的方法 **********************/
				AttService memSvc=new AttService();
				AttVO mb = memSvc.getOneMem(idI); 

				/***************** 跟 DatabBase 比對 id ********************/

				if (mb == null) {
					errorMsgMap.put("id", "查無資料");
				}
				if (!errorMsgMap.isEmpty()) {
					RequestDispatcher rd = request
							.getRequestDispatcher("/att/update_idCheck.jsp");
					rd.forward(request, response);
					return;
				} else {
					request.setAttribute("memVO", mb);
					RequestDispatcher rd = request
							.getRequestDispatcher("/att/update_reNew.jsp");
					rd.forward(request, response);
					return;
				}

			} catch (Exception e) {
				errorMsgMap.put("error", e.getMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("/att/update_idCheck.jsp");
				rd.forward(request, response);
				return;
			}
		}

		/********************** 轉交到更新頁面 **********************/
		if ("update_reNew".equals(action)) {
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			request.setAttribute("ErrorMsgKey", errorMsgMap);
			
			/*********************** 用 id 找 data *************************/

			/************** 驗證update輸入錯誤 ****************/
			Integer id = new Integer(request.getParameter("mem_id"));

			try {
				String name = request.getParameter("mem_name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,50}$";
				if (name == null || (name.trim()).length() == 0) {
					errorMsgMap.put("name", "please into name ");
				} else if (!name.trim().matches(nameReg)) {
					errorMsgMap.put("nameReg", "只能是中,英文;長度在2~50之間");
				}

				java.sql.Date date = null; // Date形式
				String bdate = request.getParameter("mem_bdate");

				if (bdate == null || (bdate.trim()).length() == 0) {
					errorMsgMap.put("bdate", "please into bdate ");
				} else {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						sdf.setLenient(false);
						java.util.Date bdConv = sdf.parse(bdate);
						date = new Date(bdConv.getTime());
					} catch (Exception e) {
						errorMsgMap.put("date", "日期格式錯誤");
					}
				}

				String account = request.getParameter("mem_account");
				String accountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,50}$";
				if (account == null || (account.trim()).length() == 0) {
					errorMsgMap.put("account", "please into account ");
				} else if (!account.trim().matches(accountReg)) {
					errorMsgMap.put("accountReg", "只能是中,英文,數字,_;長度在2~50之間");
				}

				String pwd = request.getParameter("mem_pwd");
				String pwdReg = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
				if (pwd == null || (pwd.trim()).length() == 0) {
					errorMsgMap.put("pwd", "please into pwd ");
				} else if (!pwd.trim().matches(pwdReg)) {
					errorMsgMap.put("pwdReg", "只能是英文,數字,@#$%^&+=;長度在6~50之間");
				}

				String email = request.getParameter("mem_email");
				String emailReg = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

				if (email == null || (email.trim()).length() == 0) {
					errorMsgMap.put("email", "please into email ");
				} else if (!email.trim().matches(emailReg)) {
					errorMsgMap.put("emailReg", "只能是英文,數字,_;長度在2~50之間");
				}

				if (!errorMsgMap.isEmpty()) {
					RequestDispatcher rd = request
							.getRequestDispatcher("update_reNew.jsp");
					rd.forward(request, response);
					return;
				}

				/************** 用VO存取到資料庫中 ****************/
//				MemDAO_interface dao = new MemDAO();
//				MemVO memvo = new MemVO();
//				memvo.setMem_id(id);
//				memvo.setMem_account(account);
//				memvo.setMem_bdate(date);
//				memvo.setMem_email(email);
//				memvo.setMem_name(name);
//				memvo.setMem_pwd(pwd);
//				dao.update(memvo);   //DAO中 update()
				
				/*******************2.開始更新資料*******************/
//				AttService memSvc=new AttService();
//				AttVO memVO=memSvc.updateMem(att_name,att_id,att_staytime,egion_id,att_addr,att_price,att_phone,att_url,att_eat,att_intro,att_open,att_lat,att_lng);
//
//				HttpSession session = request.getSession();
//
//				session.setAttribute("mem_name", name);
//				session.setAttribute("mem_bdate", bdate);
//				session.setAttribute("mem_account", account);
//				session.setAttribute("mem_pwd", pwd);
//				session.setAttribute("mem_email", email);
//
//				// 修改成功;從 update_reNew.jsp 轉往 update.jsp
//				response.sendRedirect("../result/update.jsp");

			} catch (Exception e) {
				errorMsgMap.put("error", e.getMessage());
				RequestDispatcher rd = request
						.getRequestDispatcher("update_reNew.jsp");
				rd.forward(request, response);
				return;
			}
		}
		
		
		
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
