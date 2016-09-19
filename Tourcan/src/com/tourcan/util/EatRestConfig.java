package com.tourcan.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("restaurants")
public class EatRestConfig extends ResourceConfig {
	public EatRestConfig() {
		this.packages("com.tourcan.eat");
	}
}
