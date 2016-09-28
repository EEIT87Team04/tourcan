package com.tourcan.trip.controller;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;

public class test {

	public static void main(String[] args) {
		Calendar cal1 = Calendar.getInstance(TimeZone.getDefault());
		long milis1 = cal1.getTimeInMillis();
		Timestamp time1=new Timestamp(milis1);
		System.out.println("time1="+time1);
		Calendar cal2 = Calendar.getInstance(TimeZone.getTimeZone("Asia/Taipei"));
		long milis2 = cal2.getTimeInMillis();
		Timestamp time2=new Timestamp(milis2);
		System.out.println("time2="+time2);
		Calendar cal3 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Copenhagen"));
		long milis3 = cal3.getTimeInMillis();
		Timestamp time3=new Timestamp(milis3);
		System.out.println("time3="+time3);

	}

}
