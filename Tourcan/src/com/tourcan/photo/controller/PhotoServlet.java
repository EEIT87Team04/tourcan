package com.tourcan.photo.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.tourcan.att.model.AttService;
import com.tourcan.photo.model.PhotoService;

@WebServlet("/PhotoServlet")
@MultipartConfig
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhotoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		JSONObject result = new JSONObject();
		String photoId = request.getParameter("photo_id");
		// String attId = request.getParameter("att_id");
		// String hotelId = request.getParameter("hotel_id");
		String method = request.getParameter("method");
		Integer photo_id = null;
		PhotoService pSrc = new PhotoService();
		if ("getOne".equals(method)) {
			try {

				// -------------Check photo_id-------------
				if (photoId == null || photoId.trim().length() <= 0) {
					result.append("photoId", "請輸入圖片編號");
					throw new Exception();
				}

				try {
					photo_id = new Integer(photoId);
				} catch (Exception e) {
					result.append("photoId", "編號格式錯誤");
					throw e;
				}

				String photo_file = pSrc.getImg(photo_id);

				if (photo_file == null) {
					result.append("result", "查無資料");
					throw new Exception();
				}

				result.append("photo_file", photo_file);
				response.getWriter().println(result.toString());
			} catch (Exception e) {
				result.append("result", "查詢失敗");
				response.getWriter().println(result.toString());
				e.printStackTrace();
			}
		} else if ("getOne".equals(method)) {
			// try {
			// // -------------Check photo_id-------------
			// if (photoId == null || photoId.trim().length() <= 0) {
			// result.append("photoId", "請輸入圖片編號");
			// throw new Exception();
			// }
			//
			// try {
			// photo_id = new Integer(photoId);
			// } catch (Exception e) {
			// result.append("photoId", "編號格式錯誤");
			// throw e;
			// }
			//
			// PhotoService pSrc = new PhotoService();
			// String photo_file = pSrc.getImg(photo_id);
			//
			// if (photo_file == null) {
			// result.append("result", "查無資料");
			// throw new Exception();
			// }
			//
			// result.append("photo_file", photo_file);
			// response.getWriter().println(result.toString());
			// } catch (Exception e) {
			// result.append("result", "查詢失敗");
			// response.getWriter().println(result.toString());
			// e.printStackTrace();
			// }
		} else if ("getAll".equals(method)) {

			try {
				JSONObject imgList = pSrc.getAll();
				response.getWriter().println(imgList.toString());
			} catch (Exception e) {
				result.append("result", "查無資料");
				response.getWriter().println(result.toString());
				e.printStackTrace();
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String method = request.getParameter("action");

		if ("post".equals(method)) {
			session.removeAttribute("attid");
			session.removeAttribute("photo");
			session.removeAttribute("result");
			String uri = request.getParameter("uri");
			Integer att_id = null;
			// Integer hotel_id = null;
			PhotoService src = new PhotoService();

			try {
				String attId = request.getParameter("att_id");
				if (attId.trim().length() > 0) {
					try {
						att_id = new Integer(attId);
						AttService attSrc = new AttService();
						if (attSrc.getOne(att_id) == null) {
							throw new Exception();
						}
					} catch (Exception e) {
						session.setAttribute("attid", "無效景點編號");
						throw e;
					}
				}

				// try {
				// hotel_id = new Integer(request.getParameter("hotel_id"));
				// } catch (Exception e) {
				// e.printStackTrace();
				// }

				Part photoFile = request.getPart("photo");
				if (photoFile.getSize() <= 0) {
					session.setAttribute("photo", "請載入圖片");
					throw new Exception();
				}
				byte[] photo_file = new byte[(int) photoFile.getSize()];
				InputStream is = photoFile.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				bis.read(photo_file);
				if (att_id != null) {
					src.insertByAttId(photo_file, att_id);
				}
				// else if(hotel_id!=null){}
				else {
					src.insert(photo_file);
					// src.insert(photo_file, att_id, hotel_id);
				}
				session.setAttribute("result", "新增成功");
				response.sendRedirect(uri);
			} catch (Exception e) {
				session.setAttribute("result", "新增失敗");
				response.sendRedirect(uri);
				 e.printStackTrace();
			}
		} else if ("put".equals(method)) {
			session.removeAttribute("attid");
			session.removeAttribute("photo");
			session.removeAttribute("result");
			String uri = request.getParameter("uri");
			Integer att_id = null;
			// Integer hotel_id = null;
			PhotoService src = new PhotoService();

			try {
				String attId = request.getParameter("att_id");
				if (attId.trim().length() > 0) {
					try {
						att_id = new Integer(attId);
						AttService attSrc = new AttService();
						if (attSrc.getOne(att_id) == null) {
							throw new Exception();
						}
					} catch (Exception e) {
						session.setAttribute("attid", "無效景點編號");
						throw e;
					}
				}

				// try {
				// hotel_id = new Integer(request.getParameter("hotel_id"));
				// } catch (Exception e) {
				// e.printStackTrace();
				// }

				Part photoFile = request.getPart("photo");
				if (photoFile.getSize() <= 0) {
					session.setAttribute("photo", "請載入圖片");
					throw new Exception();
				}
				byte[] photo_file = new byte[(int) photoFile.getSize()];
				InputStream is = photoFile.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				bis.read(photo_file);
				if (att_id != null) {
					src.insertByAttId(photo_file, att_id);
				}
				// else if(hotel_id!=null){}
				else {
					src.insert(photo_file);
					// src.insert(photo_file, att_id, hotel_id);
				}
				session.setAttribute("result", "新增成功");
				response.sendRedirect(uri);
			} catch (Exception e) {
				session.setAttribute("result", "新增失敗");
				response.sendRedirect(uri);
//				e.printStackTrace();
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
