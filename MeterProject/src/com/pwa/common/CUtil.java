package com.pwa.common;


import org.apache.log4j.Logger;

public class CUtil {
	public static Logger log = Logger.getLogger(CUtil.class);

	public static boolean isBlank(String str) {
		boolean r = false;
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return r;
	}

	public static String getNullIfEmpty(String str) {
		if (isBlank(str)) {
			return null;
		}
		return str;
	}
}
