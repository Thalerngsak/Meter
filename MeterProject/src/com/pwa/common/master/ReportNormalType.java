package com.pwa.common.master;

public class ReportNormalType {
	public static final String[] types = { "1", "2", "3" };
	public static final String[] descs = { "อ่านมาตรปกติ", "เก็บตกบริษัท", "เก็บตกประปา" };

	public static String getCommentDesc(String type) {
		String r =  "อื่นๆ";
		for (int i = 0; i < types.length; i++) {
			if (types[i].equals(type)) {
				r = descs[i];
				break;
			}
		}
		return r;
	}
}
