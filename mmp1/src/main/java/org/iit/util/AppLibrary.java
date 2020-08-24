package org.iit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public  class AppLibrary {
	//to get a new date
	public static String  getFutureDate(int days) {
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		Date d=cal.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/YYYY");
		String date=sdf.format(d);
		return  date.toString();
		
	}
	//to get random year
	public static String getFutureYear(int n,String format) {
		
	Calendar cal=Calendar.getInstance();
	cal.add(Calendar.YEAR,n );
	Date d=cal.getTime();
	SimpleDateFormat sdf=new SimpleDateFormat(format);
		
	String date=sdf.format(d);
	return date.toString();		
	}
		
	//to read from excel  -- .xls file
	
	public static String[][] readXls(String filePath) {
		
		String[][] str=new String[10][10];
		
	return 	str;
	}
	//to read from excel  -- .xlsx file
	
	public static String[][] readXlsx(String filePath) {
		
		String[][] str=new String[10][10];
		//write logic here
		return 	str;
		
	}
	//generating random characters
	
	public static String getRandomChars(int count) {
		Random rnd=new Random();
		String s="";
		for(int i=0;i<count;i++)
		s=s+(char)(65+rnd.nextInt(26));
		return s;
		
	}
	//generating random number
	
	public static long getRandomDigits(int count) {
		
		int j=10;
		for(int i=0;i<count;i++) {
			j=j*10;
		}
		return Calendar.getInstance().getTimeInMillis()%j;
	}
	
	//state array
	public static String[] stateArr() {
		String[] stateArray= {"Texas","Missouri","Kentucky"};
		return stateArray;
	}
	//city array
	public static String[] cityArr() {
		String[] cityArray= {"Austin","St.Louis","Lexington"};
		return cityArray;
	}
}






