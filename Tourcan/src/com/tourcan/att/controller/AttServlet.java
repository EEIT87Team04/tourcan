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

import com.tourcan.att.model.AttService;
import com.tourcan.att.model.AttVO;
import com.tourcan.region.model.RegionVO;

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
//			Integer id=new Integer(request.getParameter("att_id"));

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
					request.setAttribute("attVO", mb);
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
				System.out.println(e.getMessage());
				return;
			}
		}

		/********************** 轉交到更新頁面 **********************/
		if ("update_reNew".equals(action)) {
			Map<String, String> errorMsgMap = new HashMap<String, String>();
			request.setAttribute("ErrorMsgKey", errorMsgMap);
			
			/*********************** 用 id 找 data *************************/

			/************** 驗證update輸入錯誤 ****************/
			Integer id=null;
			try {
				id = new Integer(request.getParameter("att_id"));
			} catch (NumberFormatException e1) {
				System.out.println(e1.getMessage());
			}
			Integer staytimeI = new Integer(request.getParameter("att_staytime"));
			Integer regionIdI = new Integer(request.getParameter("region_id"));
			Double priceD=new Double(request.getParameter("att_price"));
			Boolean eatB=new Boolean(request.getParameter("att_eat"));
			Double latD=new Double(request.getParameter("att_lat"));
			Double lngD=new Double(request.getParameter("att_lng"));
			
			

			try {
				String name = request.getParameter("att_name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,50}$";
				if (name == null || (name.trim()).length() == 0) {
					errorMsgMap.put("name", "please into name ");
				} else if (!name.trim().matches(nameReg)) {
					errorMsgMap.put("nameReg", "只能是中,英文;長度在1~50之間");
				}

				
				String staytime = request.getParameter("att_staytime");
				if (staytime == null || (staytime.trim()).length() == 0) {
					errorMsgMap.put("staytime", "please into staytime ");
				} 
				

				String regionId = request.getParameter("region_id");
				String regionIdReg = "^[(0-9)]{1,2}$";
				if (regionId == null || (regionId.trim()).length() == 0) {
					errorMsgMap.put("regionId", "please into regionId ");
				} else if (!regionId.trim().matches(regionIdReg)) {
					errorMsgMap.put("regionIdReg", "只能是數字,_;0~20");
				}

				String addr = request.getParameter("att_addr");
				if (addr == null || (addr.trim()).length() == 0) {
					errorMsgMap.put("addr", "please into addr ");
				} 

				String price = request.getParameter("att_price");
				
				String priceReg = "^[(0-9)]{1,10}$";
				if (price == null || (price.trim()).length() == 0) {
					errorMsgMap.put("price", "please into price ");
				} else if (!price.trim().matches(priceReg)) {
					errorMsgMap.put("priceReg", "數字;長度在1~10之間");
				}
				
				String phone = request.getParameter("att_phone");
				String phoneReg = "^[(0-9)]{1,20}$";
				if (phone == null || (phone.trim()).length() == 0) {
					errorMsgMap.put("phone", "please into phone ");
				} else if (!phone.trim().matches(phoneReg)) {
					errorMsgMap.put("phoneReg", "數字;長度在1~20之間");
				}
				
				String url = request.getParameter("att_url");
				if (url == null || (url.trim()).length() == 0) {
					errorMsgMap.put("url", "please into url ");
				} 
				
				String eat = request.getParameter("att_eat");
				String eatReg = "^[(0-1)]{1}$";
				if (eat == null || (eat.trim()).length() == 0) {
					errorMsgMap.put("eat", "please into eat ");
				} else if (!eat.trim().matches(eatReg)) {
					errorMsgMap.put("eatReg", "0 or 1;長度在1之間");
				}
				
				String intro = request.getParameter("att_intro");
				if (intro == null || (intro.trim()).length() == 0) {
					errorMsgMap.put("intro", "please into intro ");
				} 
				
				String open = request.getParameter("att_open");
				if (open == null || (open.trim()).length() == 0) {
					errorMsgMap.put("open", "please into open ");
				} 
				
				String lat = request.getParameter("att_lat");
				if (lat == null || (lat.trim()).length() == 0) {
					errorMsgMap.put("lat", "please into lat ");
				} else if (!lat.trim().matches(latReg)) {
					errorMsgMap.put("latReg", "number;長度在1~3之間");
				}
				
				String lng = request.getParameter("att_lng");
				if (lng == null || (lng.trim()).length() == 0) {
					errorMsgMap.put("lng", "please into lng ");
				} else if (!lng.trim().matches(lngReg)) {
					errorMsgMap.put("lngReg",  "number;長度在1~3之間");
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
				AttService memSvc=new AttService();
				AttVO attVO=memSvc.updateMem(name,id,staytimeI,regionIdI,addr,priceD,phone,url,eatB,intro,open,latD,lngD);

				HttpSession session = request.getSession();

				session.setAttribute("att_name", name);
				session.setAttribute("att_id", id);
				session.setAttribute("att_staytime", staytimeI);
				session.setAttribute("region_id", regionIdI);
				session.setAttribute("att_addr", addr);
				session.setAttribute("att_price", priceD);
				session.setAttribute("att_phone", phone);
				session.setAttribute("att_url", url);
				session.setAttribute("att_eat", eatB);
				session.setAttribute("att_intro", intro);
				session.setAttribute("att_open", open);
				session.setAttribute("att_lat", latD);
				session.setAttribute("att_lng", lngD);


				// 修改成功;從 update_reNew.jsp 轉往 update.jsp
				response.sendRedirect("../result/update.jsp");

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
