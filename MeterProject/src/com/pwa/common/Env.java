package com.pwa.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Properties;
import java.util.TreeSet;

public final class Env {

	private static Env me;
	private static Properties p;
	
	public static final Locale LOCALE_TH = new Locale("th","TH");

	private Env() throws IOException {
		init();
	}

	public static String get(String key) throws IOException {
		getInstance();
		String val = p.getProperty(key);
		if (val == null) {
			throw new RuntimeException("Can't Find Property:" + key);
		}
		val = val.trim();
		return val;
	}

	private void init() throws IOException {
		ClassLoader cl = getClass().getClassLoader();
		InputStream in = cl.getResourceAsStream("installation.properties");
		p = new Properties();
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("can't load config file");
			throw e;
			//System.exit(1);
		}
		//set log properties
		//System.setProperty("log_location", (String)p.get("meter.datadir"));
		
		
	}

	private static Env getInstance() throws IOException {
		if (me == null)
			me = new Env();
		return me;
	}
	
	public static ArrayList getPropList(){
		ArrayList r = new ArrayList();
		TreeSet s = new TreeSet(p.keySet());
		Iterator it =s.iterator();
		for (int i = 0; it.hasNext(); i++) {
			String k = (String) it.next();
			String val = p.getProperty(k);
			//System.out.println(k+"="+val);
			r.add(k+"="+val);
		}
		return r;
		
		
	}

	public static void main(String[] args) throws Exception {
		
	}

}
