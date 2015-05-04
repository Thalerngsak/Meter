package com.pwa.interfaces;

import org.apache.log4j.Logger;

public class BatchUtil {
	public static Logger log = Logger.getLogger(BatchUtil.class);

	

	public static String padZeroLeft(String fixStr, int len) {
		String padWith = "0";
		String conCt = getPadStr(fixStr, len, padWith);
		fixStr = conCt + fixStr.trim();
		return fixStr;
	}

	public static String padSpaceLeft(String fixStr, int len) {
		String padWith = " ";
		String conCt = getPadStr(fixStr, len, padWith);
		fixStr = fixStr.trim() + conCt;
		return fixStr;
	}

	public static String padSpaceRight(String fixStr, int len) {
		String padWith = " ";
		String conCt = getPadStr(fixStr, len, padWith);
		fixStr = conCt + fixStr.trim();
		return fixStr;
	}

	private static String getPadStr(String fixStr, int len, String padWith) {
		int size = len - fixStr.trim().length();
		String conCt = "";
		for (int j = 0; j < size; j++) {
			conCt = conCt + padWith;
		}
		return conCt;
	}
}
