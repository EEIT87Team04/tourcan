firebase.initializeApp({
	apiKey : "AIzaSyCBQ5sPydJ0xmpC9Evp8bWZu6O8LmJyuHw",
	authDomain : "tourcan-1373.firebaseapp.com",
	databaseURL : "https://tourcan-1373.firebaseio.com",
	storageBucket : "tourcan-1373.appspot.com",
});

$(function() {
	var doneHandler = function(result) {
		var credential, user;
		if (typeof (result.user) === "undefined") {
			user = result;
		} else {
			credential = result.credential;
			user = result.user;
		}
		$(".help-block").text("");
		if (typeof (user) === "undefined") {
			$(".help-login-email").text("Signed out.");
		} else {
			$(".help-login-email").text("Welcome back, " + user.displayName);
			if (typeof (credential) !== "undefined") {
				console.log("Login with " + credential.provider);
			}
		}
		firebase.auth().currentUser.getToken().then(
				function(d) {
					$("<form action='login/continue' method='POST'>").append(
							$("<input name='token'>").val(d)).submit();
				});
	}
	var errorHandler = function(error) {
		console.log(error.email + ": " + error.code);
		$(".help-block").text("");

		console.log(error);
		console.log((error.email !== "undefined" ? error.email + ": "
				+ error.code : error.code));
		if (typeof (error.credential) !== "undefined")
			console.log(error.credential.provider);

		if (error.code === 'auth/invalid-email')
			$(".help-login-email").text("無效的電子郵件地址");

		if (error.code === 'auth/user-disabled')
			$(".help-login-email").text("帳號已停用，請聯絡管理員");

		if (error.code === 'auth/user-not-found')
			$(".help-login-email").text("帳號不存在");

		if (error.code === 'auth/wrong-password')
			$(".help-login-password").text("您輸入的帳號與密碼不符");

		if (error.code === 'auth/operation-not-allowed')
			$(".help-oauth").text("該登入方式尚未開放");

		if (error.code === 'auth/account-exists-with-different-credential') {
			firebase.auth().fetchProvidersForEmail(error.email).then(
					function(providers) {
						if (providers[0] === 'password') {
							$("#username").val(error.email);
							console.log("該email已由其他提供者註冊。如果這是您，請使用先前使用的方式登入");
							return;
						}
					})
		}
	}
	var popupLogin = function(provider) {
		firebase.auth().signInWithPopup(provider).then(doneHandler,
				errorHandler);
	}
	$("#signin").click(
			function(e) {
				e.preventDefault();
				firebase.auth().signInWithEmailAndPassword(
						$("#username").val(), $("#password").val()).then(
						doneHandler, errorHandler);
			});
	$(".omb_login .omb_socialButtons .omb_btn-google").click(function(e) {
		provider = new firebase.auth.GoogleAuthProvider();
		provider.addScope('email');
		provider.addScope('profile');
		popupLogin(provider);
	});
	$(".omb_login .omb_socialButtons .omb_btn-facebook").click(function(e) {
		provider = new firebase.auth.FacebookAuthProvider();
		provider.addScope('email');
		provider.addScope('user_friends');
		popupLogin(provider);
	});
	$(".omb_login .omb_socialButtons .omb_btn-github").click(function(e) {
		provider = new firebase.auth.GithubAuthProvider();
		provider.addScope('user:email');
		popupLogin(provider);
	});
	$(".omb_login .omb_socialButtons .omb_btn-twitter").click(function(e) {
		provider = new firebase.auth.TwitterAuthProvider();
		popupLogin(provider);
	});

});

$(function() {
	var doneHandler = function(result) {
		var credential, user;
		if (typeof (result.user) === "undefined") {
			user = result;
		} else {
			credential = result.credential;
			user = result.user;
		}
		$(".help-block").text("");
		if (typeof (user) === "undefined") {
			$(".help-reg-email").text("Signed out.");
		} else {
			$(".help-login-email").text("Welcome join us, " + user.email);
			if (typeof (credential) !== "undefined") {
				//console.log("Login with " + credential.provider);
			}
		}
		$("#username").val(user.email);
		$("a[href='#login_form']").click();
	}
	var errorHandler = function(error) {
		console.log(error.email + ": " + error.code);
		$(".help-block").text("");

		console.log(error);
		console.log((error.email !== "undefined" ? error.email + ": "
				+ error.code : error.code));
		if (typeof (error.credential) !== "undefined")
			console.log(error.credential.provider);

		if (error.code === 'auth/invalid-email'){
			$("#reg_username").parent()
			.removeClass("has-success has-error").addClass("has-error");
			$(".help-reg-email").text("無效的電子郵件地址");}
		
		if (error.code === 'auth/email-already-in-use'){
			$("#reg_username").parent()
			.removeClass("has-success has-error").addClass("has-error");
			$(".help-reg-email").text("該電子郵件地址已被使用");}
		
		if (error.code === 'auth/operation-not-allowed'){
			$("#reg_username").parent()
			.removeClass("has-success has-error").addClass("has-error");
			$(".help-reg-email").text("不合法的操作，請洽詢管理員");}
		
		if (error.code === 'auth/weak-password'){
			$("#reg_password").parent()
			.removeClass("has-success has-error").addClass("has-error");
			$("#reg_password_2").parent()
			.removeClass("has-success has-error").addClass("has-error");
			$(".help-reg-password").text("密碼安全性不足，請使用強式密碼");}
	}
	var timer;
	$("#reg_username").keyup(
			function() {
				var email = this.value;
				$(".help-reg-email").text("");
				clearTimeout(timer);
				timer = setTimeout(function() {
					firebase.auth().fetchProvidersForEmail(email).then(
							function(providers) {
								if (providers.length > 0) {
									$("#reg_username").parent()
									.removeClass("has-success has-error").addClass("has-error");
									$(".help-reg-email").text("該帳號已被使用");
								}else{
									$("#reg_username").parent()
									.removeClass("has-success has-error").addClass("has-success");
									$(".help-reg-email").text("");
								}
							}, errorHandler)
				}, 200);
			});
	$("#reg_password").keyup(
			function() {
				$(".help-reg-password").text("");
				if (this.value.length >= 8) {
					// No illegal character allowed
					if (!/[^A-Za-z0-9!@#$%^&*]/g.test(this.value)) {
						var point = 1;
						var msg = "必須包含";
						if (/[A-Z]/g.exec(this.value) == null) {
							point--;
							msg = msg + "大寫字母、";
						}
						;
						if (/[a-z]/g.exec(this.value) == null) {
							point--;
							msg = msg + "小寫字母、";
						}
						;
						if (/[0-9]/g.exec(this.value) == null) {
							point--;
							msg = msg + "數字、";
						}
						;
						if (/[!@#$%^&*]/g.exec(this.value) == null) {
							point--;
							msg = msg + "特殊符號(!@#$%^&*)、";
						}
						;
						if (point < 0) {
							$("#reg_password").parent().removeClass(
									"has-success has-error").addClass(
									"has-error");
							$(".help-reg-password").text(
									msg.substr(0, msg.length - 1));
						} else {
							$("#reg_password").parent().removeClass(
									"has-success has-error").addClass(
									"has-success");
							$(".help-reg-password").text("");
						}
					} else {
						$("#reg_password").parent().removeClass(
								"has-success has-error").addClass("has-error");
						$(".help-reg-password").text(
								"密碼限使用大小寫字母、數字、特殊符號(!@#$%^&*)");
					}
				} else {
					$("#reg_password").parent().removeClass(
							"has-success has-error").addClass("has-error");
					$(".help-reg-password").text("密碼長度不得低於8位");
				}

			});
	$("#reg_password_2").keyup(
			function() {
				if ($("#reg_password_2").val() != $("#reg_password").val()) {
					$("#reg_password_2").parent().removeClass(
							"has-success has-error").addClass("has-error");
					$(".help-reg-password").text("兩次輸入的密碼不一致");
				} else {
					$("#reg_password_2").parent().removeClass(
							"has-success has-error").addClass("has-success");
					$(".help-reg-password").text("");
				}
			});
	$("#signup").click(
			function(e) {
				e.preventDefault();
				firebase.auth().createUserWithEmailAndPassword(
						$("#reg_username").val(), $("#reg_password").val()).then(
						doneHandler, errorHandler);
			});
});