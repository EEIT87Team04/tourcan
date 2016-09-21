<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<jsp:include page="/frontPageHeader.jsp" />
		<div class="omb_login">
			<h3 class="omb_authTitle">
				<a data-toggle="tab" href="#login_form">登入</a> 或是 <a data-toggle="tab" href="#register_form">立即註冊</a>
			</h3>
			<div class="row">
				<div class="col-xs-12 col-sm-4 col-sm-offset-2">
					<div class="row omb_socialButtons">
						<div class="col-xs-3 col-sm-12">
							<a href="#" class="btn btn-lg btn-block omb_btn-facebook"> <i
								class="fa fa-facebook visible-xs"></i> <span class="hidden-xs">Facebook</span>
							</a>
						</div>
						<div class="col-xs-3 col-sm-12">
							<a href="#" class="btn btn-lg btn-block omb_btn-twitter"> <i
								class="fa fa-twitter visible-xs"></i> <span class="hidden-xs">Twitter</span>
							</a>
						</div>
						<div class="col-xs-3 col-sm-12">
							<a href="#" class="btn btn-lg btn-block omb_btn-google"> <i
								class="fa fa-google visible-xs"></i> <span class="hidden-xs">Google</span>
							</a>
						</div>
						<div class="col-xs-3 col-sm-12">
							<a href="#" class="btn btn-lg btn-block omb_btn-github"> <i
								class="fa fa-github visible-xs"></i> <span class="hidden-xs">Github</span>
							</a>
						</div>
						<div class="col-xs-12">
							<span class="help-block help-oauth"></span>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-4 tab-content">
					<div class="row tab-pane fade in active" id="login_form">
						<form class="omb_loginForm" action="" autocomplete="off"
							method="POST" name="login_form">
							<div class="col-xs-12">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user"></i></span>
									<input type="text" class="form-control" name="username"
										id="username" placeholder="email address">
								</div>
								<span class="help-block help-login-email"></span>

								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
									<input type="password" class="form-control" name="password"
										id="password" placeholder="Password">
								</div>
								<span class="help-block help-login-password"></span>
							</div>
							<div class="col-xs-12">
								<label class=""> <input type="checkbox"
									value="remember-me">Remember Me
								</label>
							</div>
							<div class="col-xs-12">
								<button type="submit" class="btn btn-lg btn-primary btn-block"
									id="signin">
									<i class="fa fa-sign-in"></i> <span class="">Log in</span>
								</button>
							</div>
							<div class="col-xs-12">
								<p>
									<a href="#">Forgot password?</a>
								</p>
							</div>
						</form>
					</div>
					<div class="row tab-pane fade" id="register_form">
						<form class="omb_loginForm" action="" autocomplete="off"
							method="POST" name="register_form">
							<div class="col-xs-12">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user"></i></span>
									<input type="text" class="form-control" name="username"
										id="reg_username" placeholder="email address">
								</div>
								<span class="help-block help-reg-email"></span>

								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
									<input type="password" class="form-control" name="password"
										id="reg_password" placeholder="Password">
								</div>
								<span class="help-block"></span>
								
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock"></i></span>
									<input type="password" class="form-control" name="password"
										id="reg_password_2" placeholder="Password">
								</div>
								<span class="help-block help-reg-password"></span>
							</div>
							<div class="col-xs-12">
								<button type="submit" class="btn btn-lg btn-primary btn-block"
									id="signup">
									<i class="fa fa-sign-in"></i> <span class="">Sign up</span>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	<script src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="https://www.gstatic.com/firebasejs/3.3.0/firebase-app.js"></script>
	<script src="https://www.gstatic.com/firebasejs/3.3.0/firebase-auth.js"></script>
	<script src="${pageContext.request.contextPath}/js/member/login.js"></script>
<jsp:include page="/frontPageFooter.jsp" />