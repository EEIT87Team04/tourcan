package com.tourcan.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.Task;
import com.google.firebase.tasks.Tasks;
import com.tourcan.mem.model.MemVO;

/**
 * This class is used to assign uid in session only if user provided a valid
 * token.
 */
@WebServlet("/login/continue")
public class MemAuthService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/login.jsp");
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		MemVO vo = (MemVO) session.getAttribute("vo");
		String jwt = (String) session.getAttribute("jwt");

		// TODO workaround for multipart/form-data
		if (req.getParameter("token") != null) {
			jwt = req.getParameter("token");
		}

		if (vo != null) {
			// if vo provided, assume the user signed in successfully.
			// this should not happen.
			// in case it happened, redirect to correct path.
			req.getRequestDispatcher("/mem/edituser").forward(req, resp);
		} else if (jwt != null) {
			// user is signing in.
			// decode token first
			// Task is asynchronous operation
			Task<FirebaseToken> task = FirebaseAuth.getInstance().verifyIdToken(jwt);
			try {
				// token valid
				FirebaseToken token = Tasks.await(task, 5, TimeUnit.SECONDS);
				// retrieve vo
				Response r = new MemService().queryByUid(token.getUid());
				if (r.getStatusInfo().equals(Status.NOT_FOUND)) {
					// vo not exist
					if (req.getParameter("mem_uid") != null && req.getAttribute("data") == null) {
						// form found
						if (!token.getUid().equals(req.getParameter("mem_uid"))) {
							// uid not match
							throw new ExecutionException(
									new FirebaseAuthException("auth/custom", "Provided uid doesn't match with token."));
						} else {
							// uid match
							// token valid, vo not exist, form exist, uid match

							// Jersey need lots setup for x-www-form-urlencoded
							// like @FormParam and @BeanParam
							// so we use org.apache.commons.beanutils
							vo = new MemVO();
							ConvertUtilsBean conv = new ConvertUtilsBean();
							DateConverter dconv = new DateConverter(null);
							dconv.setPattern("yyyy-MM-dd");
							conv.register(dconv, Date.class);
							new BeanUtilsBean(conv).populate(vo, req.getParameterMap());

							Response i = new MemService().insertMem(vo);
							if (i.getStatusInfo().equals(Status.CREATED)) {
								// vo inserted, process login now.
								// jwt still there, just need redirect.
								req.getRequestDispatcher("/login/continue").forward(req, resp);
							} else if (i.getStatusInfo().equals(Status.BAD_REQUEST)) {
								// store vo in request scope for el pre-fill in
								req.setAttribute("data", vo);
								req.setAttribute("msg", i.getEntity());
								req.getRequestDispatcher("/login/continue").forward(req, resp);
							} else if (i.getStatusInfo().equals(Status.CONFLICT)) {
								// this should NOT happen.
								System.out.println("Error occured.");
								for (Entry<String, String> j : ((Map<String, String>) i.getEntity()).entrySet()) {
									System.out.println(j.getKey() + "\t: " + j.getValue());
								}
							} else {
								// TODO handle invalid input.
								System.out.println("Error occured.");
								for (Entry<String, String> j : ((Map<String, String>) i.getEntity()).entrySet()) {
									System.out.println(j.getKey() + "\t: " + j.getValue());
								}
							}
						}
					} else {
						// form not found
						// jwt used to verify the user
						session.setAttribute("jwt", jwt);
						if (req.getAttribute("data") == null) {
							vo = new MemVO();
							// uid to remember which user those data for
							vo.setMem_uid(token.getUid());
							vo.setMem_email(token.getEmail());
							// save vo for fill in default value
							// is has nothing todo with sign in process
							req.setAttribute("data", vo);
						}
						// user is new here, complete member data first.
						// be careful whether those path available without vo
						req.setAttribute("listener", "/login/continue");
						req.getRequestDispatcher("/WEB-INF/mem/edituser.jsp").forward(req, resp);
					}
				} else if (r.getStatusInfo().equals(Status.OK)) {
					// vo exist
					vo = (MemVO) r.getEntity();
					session.removeAttribute("jwt");
					session.setAttribute("vo", vo);
					// sign in successful
					// redirect to previous destination.
					String path = "/";
					if (session.getAttribute("redirect-after-login") != null) {
						path = (String) session.getAttribute("redirect-after-login");
						session.removeAttribute("redirect-after-login");
					}
					resp.sendRedirect(req.getContextPath() + path);
				}
			} catch (InterruptedException | TimeoutException | ExecutionException e) {
				session.invalidate();
				if (e.getCause() instanceof FirebaseAuthException) {
					// token invalid
					// e.printStackTrace();
					resp.sendRedirect(req.getContextPath() + "/login.jsp");
				} else {
					// other exception
					PrintWriter out = resp.getWriter();
					e.printStackTrace();
					e.printStackTrace(out);
					out.flush();
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			// user is neither signed in nor signing in, they shouldn't be here.
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}
}
