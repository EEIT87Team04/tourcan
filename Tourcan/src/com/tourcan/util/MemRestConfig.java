package com.tourcan.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("members")
public class MemRestConfig extends ResourceConfig {
	public MemRestConfig() {
		this.packages("com.tourcan.mem");
	}
}
