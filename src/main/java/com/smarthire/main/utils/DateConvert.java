package com.smarthire.main.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvert {
	
	public String epochToISO(String epoch){
		long epochLong = Long.parseLong(epoch);
		epochLong = epochLong * 1000;
		String dateISO="";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS+0000");
		Date date = new Date(epochLong);
		dateISO = df.format(date);
		return dateISO;
	}
	
	public String isoToEpoch(String iso){
		long epoch=0;

		iso = iso.replace("T"," ");
		System.out.println("\t\t+++3Convert time from ISO: " + iso);
		try {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS+0000");
	    Date date;
		
			date = df.parse(iso);
			epoch = date.getTime()/1000;
		} catch (ParseException e) {
			System.out.println("isoToEpoch catch: " + e);
//			e.printStackTrace();
		}
	    
		
	    String dateEpoch=epoch+"";
	    System.out.println("\t\t+++Converted time to Epoch: " + dateEpoch);
		return dateEpoch;
	}

}
