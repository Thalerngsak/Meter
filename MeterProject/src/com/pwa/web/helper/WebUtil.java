package com.pwa.web.helper;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.PairValueDesc;
import com.pwa.common.CUtil;
import com.pwa.common.ProgressManager;
import com.pwa.common.SubProgressInfo;
import com.pwa.common.exception.CMException;
import com.pwa.common.master.Comment;
import com.pwa.common.master.ReportNormalType;
import com.pwa.data.DBHelper;
import com.pwa.data.model.config.Config;
import com.pwa.data.model.config.ConfigDAO;

public class WebUtil {
	private static Logger log = Logger.getLogger(WebUtil.class);
	
	public static void setupDefaultProgressManager(HttpSession session) {
		ProgressManager m = ProgressManager.getInstance();
		LinkedHashMap subMap = new LinkedHashMap<String, SubProgressInfo>();
		subMap.put("1", new SubProgressInfo(10, 100)); // starter progress
		subMap.put("2", new SubProgressInfo(90));  // processing progress
		subMap.put("3", new SubProgressInfo(10));  // post progress
		m.setSubProgressInfoMap(subMap);
		m.selectCurrentSubProgressInfo("2");  // default select
		session.setAttribute("progressManager", m);
	}
	public static void removeProgressManager(HttpSession session) {
		ProgressManager.close();
		session.removeAttribute("progressManager");
	}
	
	
	public static void validateFileInfo(FormFile file) throws CMException {
		String fileName = file.getFileName();
		String contentType = file.getContentType();
		int fileSize = file.getFileSize();
		log.debug("File Name: " + fileName + ", contentType: " + contentType + ", File Size: " + fileSize);

		boolean isUpFile = !CUtil.isBlank(fileName);

		if (isUpFile) {
			// check file size
			// if (fileSize > 1000000) {
			// throw new CMException("E004");
			// }
			// check file type image/pjpeg,
			System.out.println(contentType);
			if (!contentType.equals("text/plain")) {
				throw new CMException("E", "กรุณาเลือกไฟล์ให้ถูกต้อง");
			}
		}
		if (!isUpFile) {
			throw new CMException("E", "กรุณาเลือกไฟล์ให้ถูกต้อง" );
		}

	}
	
	public static void validateExcelFileInfo(FormFile file) throws CMException {
		String fileName = file.getFileName();
		String contentType = file.getContentType();
		int fileSize = file.getFileSize();
		log.debug("File Name: " + fileName + ", contentType: " + contentType + ", File Size: " + fileSize);

		boolean isUpFile = !CUtil.isBlank(fileName);

		if (isUpFile) {
			// check file size
			// if (fileSize > 1000000) {
			// throw new CMException("E004");
			// }
			// check file type image/pjpeg,
			System.out.println(contentType);
			if (!contentType.equals("application/vnd.ms-excel")) {
				
				throw new CMException("E", "กรุณาเลือกไฟล์ให้ถูกต้อง");
			}
		}
		if (!isUpFile) {
			throw new CMException("E", "กรุณาเลือกไฟล์ให้ถูกต้อง" );
		}

	}

	public static ArrayList<PairValueDesc> getAbNormalType() throws Exception {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();
		r.add(new PairValueDesc("1", "น้ำสูงผิดปกติ"));
		r.add(new PairValueDesc("2", "น้ำต่ำผิดปกติ"));
		r.add(new PairValueDesc("3", "น้ำศูนย์หน่วย"));
		r.add(new PairValueDesc("4", "มาตรตาย หรือตัวเลขไม่หมุน"));
		return r;
	}

	public static ArrayList<PairValueDesc> getCommentList() throws Exception {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();

		for (int i = 0; i < Comment.comments.length; i++) {
			r.add(new PairValueDesc(Comment.comments[i], Comment.commentDesc[i]));
		}

		return r;
	}

	public static ArrayList<PairValueDesc> getReportNormalTypeList() throws Exception {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();

		for (int i = 0; i < ReportNormalType.types.length; i++) {
			r.add(new PairValueDesc(ReportNormalType.types[i], ReportNormalType.descs[i]));
		}

		return r;
	}

	public static ArrayList<PairValueDesc> getBillFlagList() throws Exception {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();
		r.add(new PairValueDesc("Y", "พิมพ์แล้ว"));
		r.add(new PairValueDesc("N", "ยังไม่พิมพ์"));
		return r;
	}

	public static ArrayList<PairValueDesc> getCodeIDList(String br) throws Exception {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();
		String sql = "select MTRRDRCODE,MTRRDRNAME from dbst44 where br = "+br;
		r = getStrPairValueDescList(sql);
		return r;
	}

	// val=String, desc=String. must have 2 output in sql
	public static ArrayList<PairValueDesc> getStrPairValueDescList(String sql) throws Exception {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();
		Connection conn = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBHelper.getDBConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				r.add(new PairValueDesc(rs.getString(1), rs.getString(2)));
			}
		} finally {
			DBHelper.closeAll(conn, ps, rs);
		}

		return r;
	}

	public static ArrayList<PairValueDesc> getYearList() {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();

		r.add(new PairValueDesc("2557", "2557"));
        r.add(new PairValueDesc("2558", "2558"));
        r.add(new PairValueDesc("2559", "2559"));
        r.add(new PairValueDesc("2560", "2560"));
        r.add(new PairValueDesc("2561", "2561"));
        r.add(new PairValueDesc("2562", "2562"));

		return r;
	}

	public static ArrayList<PairValueDesc> getMonthList() {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();

		r.add(new PairValueDesc("01", "ม.ค."));
		r.add(new PairValueDesc("02", "ก.พ"));
		r.add(new PairValueDesc("03", "มี.ค."));
		r.add(new PairValueDesc("04", "เม.ย."));
		r.add(new PairValueDesc("05", "พ.ค."));
		r.add(new PairValueDesc("06", "มิ.ย."));
		r.add(new PairValueDesc("07", "ก.ค."));
		r.add(new PairValueDesc("08", "ส.ค"));
		r.add(new PairValueDesc("09", "ก.ย."));
		r.add(new PairValueDesc("10", "ต.ค."));
		r.add(new PairValueDesc("11", "พ.ย."));
		r.add(new PairValueDesc("12", "ธ.ค."));

		return r;
	}

	public static ArrayList<PairValueDesc> getDayList() {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();

		r.add(new PairValueDesc("1", "1"));
		r.add(new PairValueDesc("2", "2"));
		r.add(new PairValueDesc("3", "3"));
		r.add(new PairValueDesc("4", "4"));
		r.add(new PairValueDesc("5", "5"));
		r.add(new PairValueDesc("6", "6"));
		r.add(new PairValueDesc("7", "7"));
		r.add(new PairValueDesc("8", "8"));
		r.add(new PairValueDesc("9", "9"));
		r.add(new PairValueDesc("10", "10"));
		r.add(new PairValueDesc("11", "11"));
		r.add(new PairValueDesc("12", "12"));
		r.add(new PairValueDesc("13", "13"));
		r.add(new PairValueDesc("14", "14"));
		r.add(new PairValueDesc("15", "15"));
		r.add(new PairValueDesc("16", "16"));
		r.add(new PairValueDesc("17", "17"));
		r.add(new PairValueDesc("18", "18"));
		r.add(new PairValueDesc("19", "19"));
		r.add(new PairValueDesc("20", "20"));
		r.add(new PairValueDesc("21", "21"));
		r.add(new PairValueDesc("22", "22"));
		r.add(new PairValueDesc("23", "23"));
		r.add(new PairValueDesc("24", "24"));
		r.add(new PairValueDesc("25", "25"));
		r.add(new PairValueDesc("26", "26"));
		r.add(new PairValueDesc("27", "27"));
		r.add(new PairValueDesc("28", "28"));
		r.add(new PairValueDesc("29", "29"));
		r.add(new PairValueDesc("30", "30"));
		r.add(new PairValueDesc("31", "31"));

		return r;
	}
	public static ArrayList<PairValueDesc> getDateList() {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();

		r.add(new PairValueDesc("01", "01"));
		r.add(new PairValueDesc("02", "02"));
		r.add(new PairValueDesc("03", "03"));
		r.add(new PairValueDesc("04", "04"));
		r.add(new PairValueDesc("05", "05"));
		r.add(new PairValueDesc("06", "06"));
		r.add(new PairValueDesc("07", "07"));
		r.add(new PairValueDesc("08", "08"));
		r.add(new PairValueDesc("09", "09"));
		r.add(new PairValueDesc("10", "10"));
		r.add(new PairValueDesc("11", "11"));
		r.add(new PairValueDesc("12", "12"));
		r.add(new PairValueDesc("13", "13"));
		r.add(new PairValueDesc("14", "14"));
		r.add(new PairValueDesc("15", "15"));
		r.add(new PairValueDesc("16", "16"));
		r.add(new PairValueDesc("17", "17"));
		r.add(new PairValueDesc("18", "18"));
		r.add(new PairValueDesc("19", "19"));
		r.add(new PairValueDesc("20", "20"));
		r.add(new PairValueDesc("21", "21"));
		r.add(new PairValueDesc("22", "22"));
		r.add(new PairValueDesc("23", "23"));
		r.add(new PairValueDesc("24", "24"));
		r.add(new PairValueDesc("25", "25"));
		r.add(new PairValueDesc("26", "26"));
		r.add(new PairValueDesc("27", "27"));
		r.add(new PairValueDesc("28", "28"));
		r.add(new PairValueDesc("29", "29"));
		r.add(new PairValueDesc("30", "30"));
		r.add(new PairValueDesc("31", "31"));

		return r;
	}
	public static ArrayList<PairValueDesc> getZoneList() {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();
		// call dbhelper to get list
		// String sql = "select zone, desc from ...";
		// return pairvaluedesc

		// temp
		r.add(new PairValueDesc("00", "00"));
		r.add(new PairValueDesc("01", "01"));

		return r;
	}
	public static String getBranchName(String id) {
		ConfigDAO dao = new ConfigDAO();
		Config c = dao.findById(id);
		String r = c.getHead();
		
		return r;
	}
	public static ArrayList<PairValueDesc> getBranchList() {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();
		// temp
		r.add(new PairValueDesc("5531019", "บางคล้า"));
		r.add(new PairValueDesc("5531020", "พนมสารคราม"));
		r.add(new PairValueDesc("5531023", "ปากน้ำประแสร์"));
		r.add(new PairValueDesc("5531025", "ขลุง"));
		r.add(new PairValueDesc("5531026", "ตราด"));
		r.add(new PairValueDesc("5531027", "คลองใหญ่"));
		r.add(new PairValueDesc("5531028", "สระแก้ว"));
		r.add(new PairValueDesc("5531029", "วัฒนานคร"));
		r.add(new PairValueDesc("5531030", "อรัญประเทศ"));
		r.add(new PairValueDesc("5531031", "ปราจีนบุรี"));
		r.add(new PairValueDesc("5531032", "กบินทร์บุรี"));
		return r;
	}
	public static ArrayList<PairValueDesc> getConfigList() {
		ArrayList<PairValueDesc> r = new ArrayList<PairValueDesc>();
		// temp
		r.add(new PairValueDesc("5530325", "5530325"));
		r.add(new PairValueDesc("5530332", "5530332"));

		return r;
	}
	// no need config uriencode at server.xml
	public static String getURIParam(HttpServletRequest request, String name) throws UnsupportedEncodingException {
		String r = request.getParameter(name);
		r = decodeISOStr(r);
		return r;
	}

	public static String decodeISOStr(String r) throws UnsupportedEncodingException {
		if (r != null) {
			r = new String(r.getBytes("iso8859-1"), "tis-620"); // to tis-620, defaut encode to sys if not spec
		}
		return r;
	}
}
