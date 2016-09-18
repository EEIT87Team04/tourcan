package com.tourcan.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("hotels")
public class HotelRestConfig extends ResourceConfig {
	public HotelRestConfig() {
		this.packages("com.tourcan.hotel");
	}
}
