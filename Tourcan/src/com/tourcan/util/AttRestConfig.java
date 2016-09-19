package com.tourcan.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("attractions")
public class AttRestConfig extends ResourceConfig {
	public AttRestConfig() {
		this.packages("com.tourcan.att");
	}
}
