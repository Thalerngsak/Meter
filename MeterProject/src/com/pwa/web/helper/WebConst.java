package com.pwa.web.helper;

import java.util.TreeMap;

public class WebConst {
	private static WebConst me;
	private  final TreeMap reportTypeMap = new TreeMap ();
	private  final TreeMap assignTypeMap = new TreeMap ();
	private  final TreeMap masterTypeMap = new TreeMap ();
	private  final TreeMap abNormalTypeMap = new TreeMap ();
	
	private WebConst()  {
		// title show in web
		reportTypeMap.put("1", new String[]{"สรุปสถานะการอ่านมาตรปกติ"});
		reportTypeMap.put("2", new String[]{"สรุปสถานะเก็บตกบริษัท"});
		reportTypeMap.put("3", new String[]{"สรุปสถานะเก็บตกประปา"});

		assignTypeMap.put("1", new String[]{"อ่านมาตรปกติ"});
		assignTypeMap.put("2", new String[]{"เก็บตกประปา"});
		
		abNormalTypeMap.put("1", new String[]{"การใช้น้ำสูงผิดปกติ"});
		abNormalTypeMap.put("2", new String[]{"การใช้น้ำต่ำผิดปกติ"});
		abNormalTypeMap.put("3", new String[]{"การใช้น้ำศูนย์หน่วย"});
		abNormalTypeMap.put("4", new String[]{"มาตรตาย หรือตัวเลขไม่หมุน"});
		
		//title,table
		masterTypeMap.put("1", new String[]{"ส่วนลด","dbst06"});
		masterTypeMap.put("2", new String[]{"อัตราค่าบริการ","dbst42"});
		masterTypeMap.put("3", new String[]{"พนักงานอ่านมาตร","dbst44"});
		masterTypeMap.put("4", new String[]{"อัตราค่าน้ำ","dbst51,dbst52,dbst53"});
		//masterTypeMap.put("5", new String[]{"รายชื่อหักเงินผ่านธนาคาร","dbsm70"});

	}
	
	public static WebConst getInstance()  {
		if (me == null)
			me = new WebConst();
		return me;
	}
	
	public static void main(String[] args) {
		String a =(String)WebConst.getInstance().getReportTypeMap().get("1");
		System.out.println(a);
	}

	public  TreeMap getReportTypeMap() {
		return reportTypeMap;
	}

	public TreeMap getAssignTypeMap() {
		return assignTypeMap;
	}

	public TreeMap getMasterTypeMap() {
		return masterTypeMap;
	}

	public TreeMap getAbNormalTypeMap() {
		return abNormalTypeMap;
	}


}
