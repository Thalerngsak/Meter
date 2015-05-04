package com.pwa.web.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import com.pwa.common.CUtil;
import com.pwa.common.DateUtil;
import com.pwa.data.DBHelper;
import com.pwa.web.view.InvoiceView;

public class InvoiceHelper {

	public static InvoiceView getInvoiceView(TreeMap c,String br) throws Exception, SQLException {
		InvoiceView v = null;
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;

		String sql = "";
		sql += "SELECT if(comment = '01', est, prsmtrcnt) AS prsmtrcntcal, ";
		sql += "       NEWCONS  AS prswtusgcal, ";
		sql += "       LATITUDE, ";
		sql += "       LONGITUDE, ";
		sql += "       concat('T', d.usetype, '(', date_format(prsmtrrddt, '%m'), '/', substring(date_format(prsmtrrddt, '%Y') + 543, 3, 2), ')') AS statususer, ";
		sql += "       d.*, ";
		sql += "       (SELECT bankdate FROM config where br = "+br+" ) AS bankdate, ";
		sql += "       (SELECT paydate FROM config where br = "+br+" ) AS paydate ";
		sql += "FROM detail_his d ";
		sql += "WHERE readflag = '1' and br = "+br+" ";
//		sql += "      AND trandate = (SELECT max(trandate) ";
//		sql += "                      FROM detail d1 ";
//		sql += "                      WHERE d1.custcode ='" + (String) c.get("custcode") + "') ";
		sql += DBHelper.getAndIfCond(DBHelper.getConStr(c, false));

		try {
			con = DBHelper.getDBConnection();
			p = con.prepareStatement(sql);
			System.out.println(sql);
			DBHelper.setParam(p, c);
			rs = p.executeQuery();

			if (rs.next()) {
				v = new InvoiceView();
				v.setInvoicecnt(rs.getString("INVOICENO"));
				v.setCustcode(rs.getString("custcode"));
				v.setLatitude(rs.getString("LATITUDE"));
				v.setLongitude(rs.getString("LONGITUDE"));
				v.setPrsmtrrddt(rs.getDate("prsmtrrddt"));
				v.setPrsmtrrddtStr(DateUtil.formatInvDate(v.getPrsmtrrddt()));
				v.setRte(rs.getString("rte"));
				v.setSeq(rs.getString("seq"));
				String time = rs.getString("time");
				if(!"".equals(time)){
				    String hour = time.substring(0, 2);
				    String minutes = time.substring(2, 4);
				   v.setTime(hour+":"+minutes);
				}
				v.setName(rs.getString("CUSTNAME"));
				v.setAddr(rs.getString("addr"));

				v.setPrsmtrcntcal(rs.getInt("prsmtrcntcal"));  // เลขในมาตร:
				v.setPrswtusgcal(rs.getInt("prswtusgcal")); // จำนวนที่ใช้

				v.setUsetype(rs.getString("usetype"));
				v.setStatusUser(rs.getString("statususer"));

				v.setNortrfwt(rs.getDouble("nortrfwt"));  // ค่าน้ำ
				v.setDiscntamt(rs.getDouble("discntamt"));  // ส่วนลด
				//v.setDiscntcode(rs.getString("DISCNTCODE"));

				v.setSrvfee(rs.getDouble("srvfee"));  // ค่าบริการทั่วไป
				v.setVat(rs.getDouble("vat")); // ภาษีมูลค่าเพิ่ม
				v.setTottrfwt(rs.getDouble("tottrfwt"));  // รวมเงินค่าน้ำประจำเดือน

				//v.setNomonth(rs.getInt("nomonth"));  // ค้างชำระ เดือน
				v.setBillamt(rs.getDouble("billamt")); // ค้างชำระ
				v.setAlltoprice(rs.getDouble("ALLTOTPRICE"));  // รวมเงินที่ต้องชำระทั้งสิ้น
				v.setCodeid(rs.getString("codeid"));
				v.setComment(rs.getString("comment"));

				//v.setPrintflag(rs.getString("printflag"));
				v.setMetersize(rs.getString("metersize"));

				modifyView(v);
				setDueSuspendAndBankDateStr(v, rs);

			}
		} finally {
			DBHelper.closeAll(con, p, rs);
		}
		return v;
	}
	public static InvoiceView getMeterCardView(TreeMap c,String br,ArrayList r) throws Exception, SQLException {
		InvoiceView v = null;
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;

		String sql = "";
		sql += "SELECT detail_his.CUSTCODE, detail_his.PRSMTRRDDTM + '/' + detail_his.PRSMTRRDDTY AS REVYM, detail_his.METERNO, ";
		sql += " dbst43.MTRSZMEAN, detail_his.PRSMTRRDDT, ";
		sql += " detail_his.PRSMTRCNT, detail_his.NEWCONS AS PRSWTUSG, detail_his.MTRMKCODE, detail_his.PRSMTRRDDTY, ";
		sql += " detail_his.PRSMTRRDDTM, detail_his.PRSMTRRDDTD, detail_his.RTE, detail_his.SEQ, detail_his.ADDR, detail_his.USETYPE,  ";
		sql += " detail_his.METERSIZE, detail_his.TOTTRFWT, detail_his.COMMENT, detail_his.COMMENTDEC, detail_his.MTRSTAT, ";
		sql += " detail_his.INVOICENO, detail_his.AVGWTUSG, detail_his.Custname, detail_his.LSTMTRRDDT, detail_his.MLOCATION ";
		sql += " FROM dbst43 ";
		sql += " INNER JOIN ";
		sql += " detail_his ON dbst43.METERSIZE = detail_his.METERSIZE ";
		sql += "WHERE  br = "+br+" ";
		sql += DBHelper.getAndIfCond(DBHelper.getConStr(c, false));

		try {
			con = DBHelper.getDBConnection();
			p = con.prepareStatement(sql);
			System.out.println(sql);
			DBHelper.setParam(p, c);
			rs = p.executeQuery();

			while (rs.next()) {
				v = new InvoiceView();
				v.setCustcode(rs.getString("CUSTCODE"));
				v.setRevym(rs.getString("REVYM"));
				v.setMeterno(rs.getString("METERNO"));
				v.setMtrszmean(rs.getString("MTRSZMEAN"));
				//v.setLongitude(rs.getString("LONGITUDE"));
				Date prsmtrrddt = rs.getDate("PRSMTRRDDT");
				if(prsmtrrddt != null){
					v.setPrsmtrrddt(prsmtrrddt);
					v.setPrsmtrrddtStr(DateUtil.formatInvDate(v.getPrsmtrrddt()));
				}
				v.setPrsmtrcnt(rs.getInt("PRSMTRCNT"));
				v.setNewcons(rs.getInt("PRSWTUSG"));
				v.setMtrmkcode(rs.getString("MTRMKCODE"));
				v.setPrsmtrrddty(rs.getString("PRSMTRRDDTY"));
				v.setPrsmtrrddtm(rs.getString("PRSMTRRDDTM"));
				v.setPrsmtrrddtd(rs.getString("PRSMTRRDDTD"));
				v.setRte(rs.getString("RTE"));
				v.setSeq(rs.getString("SEQ"));
				v.setAddr(rs.getString("ADDR"));
				v.setUsetype(rs.getString("USETYPE"));
				v.setMetersize(rs.getString("METERSIZE"));
				v.setTottrfwt(rs.getDouble("TOTTRFWT"));
				v.setComment(rs.getString("COMMENT"));
				v.setCommentdec(rs.getString("COMMENTDEC"));
				v.setMtrstat(rs.getString("MTRSTAT"));
				v.setInvoicecnt(rs.getString("INVOICENO"));
				v.setAvgwtusg(rs.getInt("AVGWTUSG"));
				v.setName(rs.getString("Custname"));
				v.setLstmtrddt(rs.getDate("LSTMTRRDDT"));
				v.setLstmtrrddtStr(DateUtil.formatInvDate(v.getLstmtrddt()));
				v.setMlocation(rs.getString("MLOCATION"));
				modifyView(v);
				//setDueSuspendAndBankDateStr(v, rs);
				r.add(v);

			}
		} finally {
			DBHelper.closeAll(con, p, rs);
		}
		return v;
	}
	private static void modifyView(InvoiceView v) {
//		if ("2".equals(v.getDiscntcode())) {
//			double cost = v.getNortrfwt() - v.getDiscntamt();
//			v.setNortrfwt(cost);
//		}
	}

	// copy logic from mobile code
	private static void setDueSuspendAndBankDateStr(InvoiceView v, ResultSet rs) throws NumberFormatException, SQLException {
		// default value of DueDate
		int dueDateNum = 7;
		// default value of BankDate
		int bankDateNum = 20;
		//String printFlag = v.getPrintflag();
		if (!CUtil.isBlank(rs.getString("paydate"))) {
			dueDateNum = Integer.parseInt(rs.getString("paydate"));
		}
		if (!CUtil.isBlank(rs.getString("bankdate"))) {
			bankDateNum = Integer.parseInt(rs.getString("bankdate"));
		}

		// print date for payment
		//int noMonth = v.getNomonth();
		String useType = v.getUsetype();
		int meterSize = Integer.parseInt(v.getMetersize());

		String payDateStr = "";
		String suspendDateStr = "";

//		if (noMonth == 0) {
//			if (useType.equals("393") || meterSize > 3) {
//				payDateStr = DateUtil.formatInvDate(DateUtil.getAddDate(v.getPrsmtrrddt(), dueDateNum));
//				suspendDateStr = DateUtil.formatInvDate(DateUtil.getAddDate(v.getPrsmtrrddt(), dueDateNum + 7));
//			} else {
//				payDateStr = DateUtil.formatInvDate(DateUtil.getAddDate(v.getPrsmtrrddt(), dueDateNum));
//				suspendDateStr = "-";
//			}
//		} else if (noMonth == 1) {
//			if (useType.equals("393") || meterSize > 3) {
//				payDateStr = "-";
//				suspendDateStr = DateUtil.formatInvDate(DateUtil.getAddDate(v.getPrsmtrrddt(), dueDateNum));
//			} else {
//				payDateStr = DateUtil.formatInvDate(DateUtil.getAddDate(v.getPrsmtrrddt(), dueDateNum));
//				suspendDateStr = DateUtil.formatInvDate(DateUtil.getAddDate(v.getPrsmtrrddt(), dueDateNum + 7));
//			}
//		} else if (noMonth > 1) {
//			payDateStr = "-";
//			suspendDateStr = DateUtil.formatInvDate(DateUtil.getAddDate(v.getPrsmtrrddt(), 0));
//		}
//
//		if ((printFlag.equals("2")) && (noMonth == 0)) {
//			payDateStr = "-";
//			suspendDateStr = "-";
//		}

		v.setDuepaydateStr(payDateStr);
		v.setSuspenddateStr(suspendDateStr);

		// bankdate
		Date payBankDate = DateUtil.getReplaceDayDate(v.getPrsmtrrddt(), bankDateNum);
		v.setPayBankDateStr(DateUtil.formatInvDate(payBankDate));
	}
}
