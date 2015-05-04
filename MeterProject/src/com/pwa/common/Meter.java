package com.pwa.common;

public class Meter {
	
	long xstart;
	long start;
	
	public Meter() {
		xstart =System.currentTimeMillis();
		start = System.currentTimeMillis();
		
	}
	
	public String took(){
		long end = System.currentTimeMillis();
		double dif =(end -start)/1000.000;
		start = end;
		return " took "+dif +" s.";
	}
	
	public String elapse(){
		long end = System.currentTimeMillis();
		double dif =(end -xstart)/1000.000;
		return " elapse "+dif +" s.";
	}
	
	public static void main(String[] args) throws InterruptedException {
		Meter m= new Meter();
		//Thread.sleep(100L);
		System.out.println(m.took());
	}
}
