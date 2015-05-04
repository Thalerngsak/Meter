package com.pwa.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.pwa.common.CUtil;
import com.pwa.common.Env;

public class DBHelper {
	private static Logger log = Logger.getLogger(DBHelper.class);

	/*
	 * =================================================================== DB Helper Section
	 * ==================================================================
	 */
	public static String getConnectionURL() throws Exception {
		String r = null;
		String host = Env.get("db.host");
		String dbname = Env.get("db.dbname");
		String encode = "characterEncoding=" + Env.get("db.characterEncoding");
		String url = "jdbc:mysql://" + host + ":3306/" + dbname + "?" + encode;
		log.debug("url=" + url);
		r=url;
		return r;
	}

	public static Connection getDBConnection() throws Exception {
		Connection conn = null;
		String host = Env.get("db.host");
		String dbname = Env.get("db.dbname");
		String userName = Env.get("db.username");
		String password = Env.get("db.password");
		String encode = "characterEncoding=" + Env.get("db.characterEncoding");

		String acc = "user=" + userName + "&password=" + password;
		String url = "jdbc:mysql://" + host + "/" + dbname + "?" + acc + "&" + encode;
		//log.debug("url=" + url);
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(url);

		conn.setAutoCommit(false);
		return conn;
	}

	public static int truncateTable(String tName, Connection conn) throws Exception {
		int cRow = 0;
		String sql = "truncate table " + tName;
		log.debug(sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			cRow = ps.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			closeRsS(ps, rs);
		}

		return cRow;
	}

	public static long deleteByRecIDList(String table, ArrayList al, Connection conn) throws Exception {
		long cRow = 0;

		StringBuffer sb = new StringBuffer(" delete from " + table + " where recid in (");
		for (int i = 1; i <= al.size(); i++) {
			Number recidNumber = (Number) al.get(i - 1);
			long recid = recidNumber.longValue();
			sb.append(recid);
			if ((i % 1000 == 0) || (i == al.size())) {
				sb.append(")");
				cRow += exeUpdateGetNumber(sb.toString(), conn);
				log.debug(sb);
				sb = new StringBuffer(" delete from " + table + " where recid in (");
			} else {
				sb.append(",");
			}

		}
		return cRow;
	}

	public static long deleteByRecIDMap(String table, TreeMap map, Connection conn) throws Exception {
		long cRow = 0;

		Iterator it = map.keySet().iterator();

		StringBuffer sb = new StringBuffer(" delete from  " + table + " where recid in (");
		for (int i = 1; it.hasNext(); i++) {
			Integer recidInt = (Integer) it.next();
			long recid = recidInt.longValue();
			sb.append(recid);
			if ((i % 1000 == 0) || (i == map.size())) {
				sb.append(")");
				cRow += exeUpdateGetNumber(sb.toString(), conn);
				// log.debug(sb);
				sb = new StringBuffer(" delete from " + table + " where recid in (");
			} else {
				sb.append(",");
			}

		}
		return cRow;
	}

	public static long deleteByStrList(String tName, String cName, ArrayList al, Connection conn) throws Exception {
		long cRow = 0;

		String nullSql = "  delete from   " + tName + " where " + cName + "  is null";

		StringBuffer sb = new StringBuffer(" delete from  " + tName + " where " + cName + " in (");
		for (int i = 1; i <= al.size(); i++) {
			String strVal = (String) al.get(i - 1);

			if (strVal == null || "".equals(strVal)) {
				cRow += DBHelper.exeUpdateGetNumber(nullSql, conn);
				sb.append("null"); // (null) -> no effect
			} else {
				sb.append("'" + strVal + "'");
			}

			if ((i % 1000 == 0) || (i == al.size())) {
				sb.append(")");
				cRow += DBHelper.exeUpdateGetNumber(sb.toString(), conn);
				log.debug(sb);
				sb = new StringBuffer(" delete from " + tName + " where " + cName + " in (");
			} else {
				sb.append(",");
			}
		}
		return cRow;
	}

	/*
	 * =================================================================== Utility Section
	 * ==================================================================
	 */
	/***
	 * to sql condition
	 * 
	 * @param in
	 * @return IS NULL if in==null, else return "='in'"
	 */
	public static String toStrCon(String in) {
		String out = "";
		if (in == null) {
			out = " IS NULL ";
		} else {
			out = "= '" + in + "' ";
		}
		return out;
	}

	/***
	 * to sql condition
	 * 
	 * @param in
	 * @return IS NULL if in==null, else return "= to_date('" + in + "','DD-MM-YYYY')"
	 */
	public static String toDateCon(Date in) {
		String out = "";
		if (in == null) {
			out = " IS NULL ";
		} else {
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			String dStr = df.format(in);
			out = "= to_date('" + dStr + "','DD-MM-YYYY') ";
		}
		return out;
	}

	public static String toDateCon(String op, Date in) {
		String out = op;
		if (in == null) {
			out += " NULL ";
		} else {
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			String dStr = df.format(in);
			out += " to_date('" + dStr + "','DD-MM-YYYY') ";
		}
		return out;
	}

	public static ArrayList getStringListFromQuery(String sql, Connection conn) throws Exception {
		ArrayList al = new ArrayList();
		// Connection conn = getDBConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				al.add(rs.getString(1));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			closeRsS(pstm, rs);
		}
		return al;
	}

	public static long exeUpdateGetNumber(String sql, Connection conn) throws Exception {
		long num = -1;

		// Connection conn = getDBConnection();
		PreparedStatement ps = null;
		// ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			num = ps.executeUpdate();

		} catch (Exception ex) {
			throw ex;
		} finally {
			close(ps);

		}

		return num;
	}

	public static long exeQueryGetNumber(String sql, Connection conn) throws Exception {
		long r = -1;

		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				r = rs.getLong(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			closeRsS(pstm, rs);
		}
		return r;

	}

	public static String exeQueryGetString(String sql, Connection conn) throws Exception {
		String r = null;

		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			if (rs.next()) {
				r = rs.getString(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			closeRsS(pstm, rs);
		}
		return r;

	}

	/* clean resource */
	public static void closeAll(Connection c, Statement s, ResultSet rs) {
		close(rs);
		close(s);
		close(c);
	}

	public static void closeRsS(Statement s, ResultSet rs) {
		close(rs);
		close(s);

	}

	public static void close(Connection c) {

		// log.debug("connection close [" + c + "]");

		if (c != null) {
			try {
				c.close();
			} catch (Exception ex) {
				log.error(ex, ex);
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				log.error(ex, ex);
			}
		}

	}

	public static void close(Statement s) {
		if (s != null) {
			try {
				s.close();
			} catch (Exception ex) {
				log.error(ex, ex);
			}
		}
	}

	public static boolean exeQueryGetIsFound(String sql, Connection conn) throws Exception {
		boolean r = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				r = true;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			DBHelper.closeRsS(ps, rs);
		}
		return r;
	}

	// key=Integer,val=String. must have 2 output in sql
	public static TreeMap exeQueryGetIntegerMap(String sql, Connection conn) throws SQLException {
		TreeMap map = new TreeMap();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(new Integer(rs.getString(1)), rs.getString(2));
			}
		} finally {
			DBHelper.closeRsS(ps, rs);
		}

		return map;
	}

	// key=String, val=String. must have 2 output in sql
	public static TreeMap exeQueryGetStringMap(String sql, Connection conn) throws SQLException {
		TreeMap map = new TreeMap();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1), rs.getString(2));
			}
		} finally {
			DBHelper.closeRsS(ps, rs);
		}

		return map;
	}

	// key=String,val= ArrayList(allow key dup). must have 2 output in sql.
	public static TreeMap exeQueryGetStringMapVL(String sql, Connection conn) throws SQLException {
		TreeMap map = new TreeMap();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String str1 = rs.getString(1);
				String str2 = rs.getString(2);

				String key = str1;
				ArrayList al = (ArrayList) map.get(str1);
				key = key == null ? "" : key; // map can't get with null;
				if (al == null) {
					al = new ArrayList();
					map.put(key, al);
				}
				al.add(str2);

			}
		} finally {
			DBHelper.closeRsS(ps, rs);
		}
		return map;

	}

	// key=1'st String,val= ArrayList of String[] . must have 3 output in sql.
	public static TreeMap exeQueryGetStringMapVL2(String sql, Connection conn) throws SQLException {
		TreeMap map = new TreeMap();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String str1 = rs.getString(1);
				String str2 = rs.getString(2);
				String str3 = rs.getString(3);

				String key = str1;
				String[] vals = new String[] { str2, str3 };

				ArrayList al = (ArrayList) map.get(str1);
				key = key == null ? "" : key; // map can't get with null;
				if (al == null) {
					al = new ArrayList();
					map.put(key, al);
				}
				al.add(vals);

			}
		} finally {
			DBHelper.closeRsS(ps, rs);
		}
		return map;

	}

	public static void main(String[] args) throws Exception {
		Connection conn = DBHelper.getDBConnection();

		TreeMap map = new TreeMap();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement("select * from detail where trandate=?");
			java.util.Date tranDate = new SimpleDateFormat("ddMMyyyy").parse("01012551"); // local
			Date q = new Date(tranDate.getTime());
			ps.setDate(1, q);
			rs = ps.executeQuery();
			while (rs.next()) {
				String str1 = rs.getString(1);
				System.out.println(str1);

			}
		} finally {
			DBHelper.closeRsS(ps, rs);
		}

	}

	public static String getConStr(TreeMap<String, Object> c, boolean includeWhereStr) {
		String r = "";
		if (c.size() != 0) {
			if (includeWhereStr) {
				r += " Where ";
			}
			Iterator<String> it = c.keySet().iterator();
			for (int i = 1; it.hasNext(); i++) {
				String k = it.next();

				if (c.get(k) instanceof java.util.Date[]) {
					r += " (" + k + ">= ? and " + k + "<=?) ";
				} else {
					r += k + " =? ";
				}
				if (i != c.size()) {
					r += " and ";
				}
			}
		}
		return r;
	}

	public static String getConStr(TreeMap<String, Object> c) {
		return getConStr(c, true);
	}

	public static String getAndIfCond(String con) {
		if (!CUtil.isBlank(con)) {
			return " and " + con;
		} else {
			return con;
		}
	}

	public static void setParam(PreparedStatement p, TreeMap c) throws SQLException {
		Iterator<String> it = c.keySet().iterator();
		for (int i = 1; it.hasNext(); i++) {
			String k = it.next();

			Object o = c.get(k);
			if (o instanceof String) {
				p.setString(i, (String) o);
			}
			if (o instanceof java.util.Date) {
				p.setDate(i, new java.sql.Date(((java.util.Date) o).getTime()));
			}
			if (o instanceof java.util.Date[]) {
				p.setDate(i, new java.sql.Date((((java.util.Date[]) o)[0]).getTime())); // from
				p.setDate(++i, new java.sql.Date((((java.util.Date[]) o)[1]).getTime())); // to
			}
		}
	}

	public static void putToCriIfNotNull(TreeMap c, String key, Object val) {
		if (val != null) {
			c.put(key, val);
		}

	}

}
