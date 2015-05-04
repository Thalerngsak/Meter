package com.pwa.data.exporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import com.pwa.common.Env;
import com.pwa.data.DBHelper;
import com.pwa.data.impoter.AssignImporter;
import com.pwa.data.model.detail.Detail;
// hibernate can't get contain null record
public class DataExporter {
	static Logger log = Logger.getLogger(AssignImporter.class);
	// c= criteria
	public static void run(TreeMap<String, Object> c) throws Exception {

		// read db to build list
		//ArrayList<Detail> detailList = getDataList(cri);
		//System.out.println(detailList.size());
		// write file
		//writeDataFile(detailList);
		
		//temo solution
		writeFromDB(c);
		
	}
//
//	private static void writeDataFile(ArrayList<Detail> detailList) throws IOException {
//		File file = new File(Env.get("exportdata.filedir") + "/"+Env.get("exportdata.filename"));
//		BufferedWriter out = null;
//		try {
//			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "MS-874"));
//			String line = "";
//			String d = ";";
//			for (int i = 0; i < detailList.size(); i++) {
//				Detail detail = detailList.get(i);
//				DetailId v = detail.getId();
//				line += v.getBr() + d + v.getChkdigit() + d + v.getCustcode() + d + v.getRte() + d + v.getSeq() + d
//						+ v.getAddr() + d + v.getUsetype() + d + v.getMtrmkcode() + d + v.getMetersize() + d
//						+ v.getMeterno() + d + v.getNortrfwt() + d + v.getDiscntamt() + d + v.getSrvfee() + d
//						+ v.getVat() + d + v.getTottrfwt() + d + v.getBillamt() + d + v.getAlltoprice() + d
//						+ v.getPrsmtrrddt()/* YMD */+ d + v.getPrsmtrcnt() + d + v.getNewread()/* newcon */+ d
//						+ v.getComment() + d + v.getCommentdec() + d + v.getTime() + d + v.getReadflag() + d
//						+ v.getMlocation() + d + v.getMincharge() + d + v.getBillflag() + d + v.getBillsend() + d
//						+ v.getMtrstat() + d + v.getNewread() + d + v.getCustcode() /* codeid */+ d + v.getNotimes()
//						+ d + v.getUnitdiscnt() + d + v.getHln() + d + v.getOkread() + d + v.getInvoicecnt()/* invoiceno */
//						+ d + v.getCommentdec() /* trandate */;
//			}
//
//			out.write(line);
//		} finally {
//			out.close();
//		}
//
//	}

	public static ArrayList<Detail> getDataList(TreeMap<String, Object> c) throws Exception {
		ArrayList<Detail> r = new ArrayList<Detail>();

		// make query string
		String sqlStr = "SELECT *  FROM detail" + getHConStr(c);
		// SQLQuery q = HibernateSessionFactory.getSession().createSQLQuery(sqlStr).addEntity(Detail.class);
		// setHParam(q, c);
		// query
		// r = (ArrayList<Detail>) q.list();

		return r;
	}

	// temporary
	public static void writeFromDB(TreeMap<String, Object> c) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT concat_ws(';', detail.BR,detail.ZN,detail.CUSTCODE,detail.RTE,detail.SEQ,detail.NAME,detail.ADDR,detail.CUSTSTAT,detail.MTRMKCODE,detail.METERSIZE,detail.METERNO,detail.NORTRFWT,detail.DISCNTAMT,detail.SRVFEE,detail.VAT,detail.TOTTRFWT,detail.NOMONTH,detail.BILLAMT,detail.NOVAT,detail.OLDTYPE,detail.USETYPE,detail.LSTMTRDDT,detail.LSTMTRCNT,detail.AVGWTUSG,detail.PRSMTRRDDT,detail.PRSMTRCNT,detail.PRSWTUSG,detail.`COMMENT`,detail.COMMENTDEC,detail.`TIME`,detail.DISCNTCODE,detail.READFLAG,detail.MLOCATION,detail.MINCHARGE,detail.NOOFHOUSE,detail.PRINTFLAG,detail.BILLFLAG,detail.BILLSEND,detail.INVOICECNT,detail.MTRSTAT,detail.NEWREAD,detail.EST,detail.WATEREST,detail.BILLMONTH,detail.DISCNTBATH,detail.UNITDISCNT,detail.OLDMTRUSG,detail.CHKDIGIT,detail.ALLTOPRICE,detail.NOTIMES,detail.HLN,detail.OKREAD) FROM detail"
				+ DBHelper.getConStr(c);

		try {
			con = DBHelper.getDBConnection();
			ps = con.prepareStatement(sql);
			DBHelper.setParam(ps, c);
			rs = ps.executeQuery();
			
			// create file
			File file = new File(Env.get("exportdata.filedir") + "/"+Env.get("exportdata.filename"));
			BufferedWriter out = null;
			try {
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "MS-874"));
				for (int i = 1; rs.next(); i++) {
					String line = rs.getString(1);
					System.out.println(line);
					out.write(line);
					out.newLine();
					out.flush();
				}
			} finally {				
				out.close();
			}

		} finally {
			DBHelper.closeAll(con, ps, rs);
		}

	}

	public static void setHParam(SQLQuery q, TreeMap<String, Object> c) {
		Iterator<String> it = c.keySet().iterator();
		for (int i = 1; it.hasNext(); i++) {
			String k = it.next();

			Object o = c.get(k);
			if (o instanceof String) {
				q.setString(k, (String) o);
			}

			// add other later
		}
	}

	public static String getHConStr(TreeMap<String, Object> c) {
		String r = "";
		if (c.size() != 0) {
			r += " Where ";

			Iterator<String> it = c.keySet().iterator();
			for (int i = 1; it.hasNext(); i++) {
				String k = it.next();
				r += k + " =:" + k;
				if (i != c.size()) {
					r += " and ";
				}
			}
		}

		return r;
	}

	public static void main(String[] args) throws Exception {
		TreeMap<String, Object> c = new TreeMap<String, Object>();
		c.put("zn", "00");
		 c.put("br", "5530325");
		 c.put("codeid","1111" );
		// c.put("trandate","" );
		DataExporter.run(c);

	}
}
