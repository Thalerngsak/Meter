package com.pwa.web.helper;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.pwa.common.DateUtil;
import com.pwa.common.master.Comment;
import com.pwa.common.master.ReportNormalType;
import com.pwa.data.DBHelper;
import com.pwa.web.Paging;
import com.pwa.web.struts.action.ReportBaseAction;
import com.pwa.web.struts.form.ReportNormalForm;
import com.pwa.web.view.ExportDataView;
import com.pwa.web.view.ReportNormalView;
import com.pwa.web.view.ReportSummaryView;

public class ReportHelper {
	public static Logger log = Logger.getLogger(ReportHelper.class);

	public static void main(String[] args) throws SQLException, Exception {
		getReportNormalViewList("select * from input", new TreeMap<String, Object>(), new ArrayList());
	}

	public static void getReportAbNormalViewList(String sql, TreeMap<String, Object> c, ArrayList r) throws Exception, SQLException {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;

		try {
			con = DBHelper.getDBConnection();
			p = con.prepareStatement(sql);
			log.debug(sql);
			DBHelper.setParam(p, c);
			rs = p.executeQuery();

			while (rs.next()) {
				ReportNormalView v = new ReportNormalView();
				v.setMtrrdrname(rs.getString("MTRRDRNAME")); // codeid: MTRRDRNAME
				v.setRte(rs.getString("rte"));
				v.setSeq(rs.getString("seq"));
				v.setCustcode(rs.getString("CUSTCODE"));
				v.setMeterno(rs.getString("meterno"));
				v.setMetersize(rs.getString("metersize"));
				v.setName(rs.getString("CUSTNAME"));
				v.setAddr(rs.getString("ADDR"));
				v.setPrsmtrcnt(rs.getInt("PRSMTRCNT"));
				v.setLstmtrcnt(rs.getInt("LSTMTRCNT"));
				v.setTottrfwt(rs.getDouble("tottrfwt"));
				//v.setPrswtusg(rs.getInt("prswtusg"));
				v.setNewcons(rs.getInt("NEWCONS"));
				v.setAvgwtusg(rs.getInt("avgwtusg"));
				v.setDiff(rs.getString("diff"));
				v.setTrandate(rs.getDate("TRANDATE")); // TRANDATE
				v.setTrandateStr(DateUtil.formatThaiDate(v.getTrandate()));
				v.setLatitude(rs.getString("LATITUDE"));
				v.setLongitude(rs.getString("LONGITUDE"));
				r.add(v);
			}
			log.debug(r.size());
		} finally {
			DBHelper.closeAll(con, p, rs);
		}
	}

	public static void getReportSummaryViewList(String sql, TreeMap<String, Object> c, ArrayList r) throws Exception, SQLException {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;

		try {
			con = DBHelper.getDBConnection();
			p = con.prepareStatement(sql);
			log.debug(sql);
			DBHelper.setParam(p, c);
			rs = p.executeQuery();

			while (rs.next()) {
				ReportSummaryView v = new ReportSummaryView();
				v.setRte(rs.getString("rte"));
				v.setBr(rs.getString("br"));
				v.setAbnormalCount(rs.getInt("abnormalcount"));
				v.setMtrrdrname(rs.getString("codeid")); // codeid: MTRRDRNAME
				v.setTrandate(rs.getDate("TRANDATE")); // TRANDATE
				v.setTrandateStr(DateUtil.formatThaiDate(v.getTrandate()));
				v.setCustCount(rs.getInt("custCount"));
				v.setSaveCount(rs.getInt("saveCount"));
				v.setRemainCount(rs.getInt("remainCount"));
				v.setNoBillCount(rs.getInt("noBillCount"));
				
				v.setComment(rs.getString("comment"));
				v.setCommentdec(getCommentDesc(v.getComment()));

				v.setReportType(rs.getString("type"));
				v.setReportTypeDesc(ReportNormalType.getCommentDesc(v.getReportType()));
				//v.setLatitude(rs.getString("LATITUDE"));
				//v.setLongitude(rs.getString("LONGITUDE"));
				r.add(v);
			}
			log.debug("size=" + r.size());
		} finally {
			DBHelper.closeAll(con, p, rs);
		}
	}

	public static void getReportNormalViewList(String sql, TreeMap<String, Object> c, ArrayList r) throws Exception, SQLException {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;

		try {
			con = DBHelper.getDBConnection();
			p = con.prepareStatement(sql);
			log.debug(sql);
			DBHelper.setParam(p, c);
			rs = p.executeQuery();

			while (rs.next()) {
				ReportNormalView v = new ReportNormalView();
				v.setRte(rs.getString("rte"));
				v.setSeq(rs.getString("seq"));
				v.setMtrrdrname(rs.getString("MTRRDRNAME")); // codeid: MTRRDRNAME

				v.setTrandate(rs.getDate("TRANDATE")); // TRANDATE
				v.setTrandateStr(DateUtil.formatThaiDate(v.getTrandate()));
				v.setCustcode(rs.getString("CUSTCODE"));
				v.setName(rs.getString("CUSTNAME")); // name
				v.setAddr(rs.getString("ADDR")); // addr
				v.setPrsmtrrddt(rs.getDate("prsmtrrddt"));
				v.setPrsmtrrddtStr(DateUtil.formatThaiDate(v.getPrsmtrrddt()));
				String time = rs.getString("time");
				if(time != null){
					String newTime1 = time.substring(0,2);
					String newTime2 = time.substring(2,4);
					v.setTime(newTime1+":"+newTime2);
				}
				v.setPrsmtrcnt(rs.getInt("Prsmtrcnt"));
				v.setLstmtrcnt(rs.getInt("lstmtrcnt"));
				v.setNewcons(rs.getInt("NEWCONS"));
				//v.setPrswtusg(rs.getInt("prswtusg"));
				v.setComment(rs.getString("comment"));
				v.setCommentdec(getCommentDesc(v.getComment()));
				v.setTottrfwt(rs.getDouble("tottrfwt"));
				v.setBillamt(rs.getDouble("billamt"));

				//v.setPrintflag(rs.getString("printflag"));
				v.setNotimes(rs.getInt("notimes"));

				v.setReadflag(rs.getString("Readflag")); // Readflag
				v.setLatitude(rs.getString("LATITUDE"));
				v.setLongitude(rs.getString("LONGITUDE"));
				v.setAvgwtusg(rs.getInt("AVGWTUSG"));
				r.add(v);
			}
			log.debug(r.size());
		} finally {
			DBHelper.closeAll(con, p, rs);
		}
	}
	public static void getExportDataViewList(String sql, TreeMap<String, Object> c, ArrayList r) throws Exception, SQLException {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;

		try {
			con = DBHelper.getDBConnection();
			p = con.prepareStatement(sql);
			log.debug(sql);
			DBHelper.setParam(p, c);
			rs = p.executeQuery();

			while (rs.next()) {
				ExportDataView v = new ExportDataView();
				v.setFileName(rs.getString("FILENAME"));
				v.setPath(rs.getString("PATH"));
				v.setBranch(rs.getString("BR"));
				v.setRte(rs.getString("RTE"));
				v.setTrandate(rs.getDate("TRANDATE"));
				v.setCodeid(rs.getString("CODEID"));
				r.add(v);
			}
			log.debug(r.size());
		} finally {
			DBHelper.closeAll(con, p, rs);
		}
	}
	private static String getCommentDesc(String comment) {
		return Comment.getCommentDesc(comment);
	}

	// incomplete+bug date
	public static void populate(Object bean, ResultSet resultSet) throws SQLException {
		// Build a list of relevant column properties from this resultSet
		HashMap properties = new HashMap();
		// Acquire resultSet MetaData
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols = metaData.getColumnCount();
		// Scroll to next record and pump into hashmap

		for (int i = 1; i <= cols; i++) {
			Object o = null;
			String name = metaData.getColumnClassName(i);

			if ("java.lang.String".equals(name)) {
				o = resultSet.getObject(i);
			} else if ("java.lang.Double".equals(name)) {
				o = resultSet.getDouble(i);
			} else if ("java.lang.Integer".equals(name)) {
				o = resultSet.getInt(i);
			} else if ("java.sql.Date".equals(name)) {
				o = resultSet.getDate(i);
			}
			log.debug(metaData.getColumnName(i) + ": " + o);
			properties.put(metaData.getColumnName(i).toLowerCase(), o);
		}
		// Set the corresponding properties of our bean
		try {
			BeanUtils.populate(bean, properties);
		} catch (Exception e) {
			throw new SQLException("BeanUtils.populate threw " + e.toString());
		}
	}



	public static void decodeISOForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		ReportNormalForm f = (ReportNormalForm) form;
		// workaround: do encoding for GET method (urlencode alway iso8859)
		if (request.getMethod().equalsIgnoreCase("GET")) {
			// workaround(link from summary report, all report submit page)
			f.setDateStr(WebUtil.decodeISOStr(f.getDateStr()));
			f.setDatetoStr(WebUtil.decodeISOStr(f.getDatetoStr()));
		}
	}

}
