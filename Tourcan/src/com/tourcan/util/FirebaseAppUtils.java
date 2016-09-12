package com.tourcan.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseAppUtils implements ServletContextAware {
	private ServletContext context;
	private String firebaseCfg = "/WEB-INF/FirebaseServiceAccount.json";
	private FirebaseApp firebaseApp;

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public FirebaseApp getApp() {
		if (firebaseCfg == null) {
			throw new NullPointerException("firebase configuration not found.");
		}
		if (firebaseApp == null) {
			FirebaseApp.initializeApp(
					new FirebaseOptions.Builder().setServiceAccount(context.getResourceAsStream(firebaseCfg)).build());
			// System.out.println("FirebaseApp init...");
		}
		return firebaseApp;
	}

}
