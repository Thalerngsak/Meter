package com.pwa.data.impoter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pwa.data.DBHelper;
import com.pwa.data.SqlDBHelper;

public class MasterImporter {
	public static void main(String[] args) throws Exception {
		MasterImporter.run("dbst42");
		
//		String s = "12,23,34";
//		String ss[] =s.split(",");
//		System.out.println(ss);
	}
	// require tx
	public static synchronized long run(String tName) throws Exception {
		long r = 0;
		ArrayList srcRowList = getSrcRowList(tName);
		
		Connection conn = null;
		try {
			conn = DBHelper.getDBConnection();
			r= DBHelper.exeUpdateGetNumber("delete from "+tName, conn);			
			r = copyData(tName, srcRowList, conn);
			System.out.println("["+r+"]");
		} finally {
			DBHelper.close(conn);
		}
		return r;
	}
	private static long copyData(String tName, ArrayList srcRowList, Connection conn) throws Exception, SQLException {
		long r;
		String[] destColTypStr = getDestColTypeStrs(tName, conn);
		String sql = getPrepareInsertStr(tName, destColTypStr.length);
		r = executeSql(srcRowList, destColTypStr, sql, conn);
		return r;
	}

	private static int executeSql(ArrayList srcRowList, String[] destColTypStrs, String sql, Connection conn) throws Exception {
		int r = 0;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < srcRowList.size(); i++) {
				Object[] objs = (Object[]) srcRowList.get(i );
				setSqlParam(objs, destColTypStrs, ps);
				r += ps.executeUpdate();
			}
			conn.commit();
		}catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			DBHelper.close(ps);
		}
		return r;

	}

	private static void setSqlParam(Object[] objs, String[] destColTypStrs, PreparedStatement ps) throws SQLException {
		debugRow(objs, destColTypStrs);
		for (int i = 1; i <= destColTypStrs.length; i++) {
			ps.setObject(i, objs[i - 1]);
		}
	}

	private static void debugRow(Object[] objs, String[] destColTypStrs) {
		for (int i = 1; i <= destColTypStrs.length; i++) {
			Object o = null;
			if (objs[i - 1] instanceof String) {
				o = (String) objs[i - 1];
			}
			if (objs[i - 1] instanceof Number) {
				o = (Number) objs[i - 1];
			}
			if (objs[i - 1] instanceof Date) {
				o = (Date) objs[i - 1];
			}

			System.out.print(o + ",");
		}
		System.out.println();

	}

	// not the correct meaning but ok
	private static String[] getDestColTypeStrs(String tName, Connection conn) throws Exception {
		ArrayList r = new ArrayList();
		String sql = "select * from " + tName;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBHelper.getDBConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int cCnt = meta.getColumnCount();

			for (int i = 0; i < cCnt; i++) {
				r.add(meta.getColumnClassName(i + 1));
			}

		} finally {
			DBHelper.closeAll(conn, ps, rs);
		}
		return (String[]) r.toArray(new String[0]);

	}

	private static ArrayList getSrcRowList(String tName) throws Exception, SQLException {
		ArrayList r = new ArrayList();
		String sql = "select * from " + tName;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = SqlDBHelper.getDBConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			int cCnt = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Object[] objs = new Object[cCnt];
				for (int i = 1; i <= cCnt; i++) {
					Object o = rs.getObject(i);
					objs[i - 1] = o;
				}
				r.add(objs);
			}
		} finally {
			DBHelper.closeAll(conn, ps, rs);
		}
		return r;
	}

	private static String getPrepareInsertStr(String tName, int colCnt) throws Exception {
		String str = "INSERT INTO " + tName + " VALUES(";

		for (int i = 0; i < colCnt; i++) {
			str += "?";
			if (i != colCnt - 1) {
				str += ", ";
			}
		}

		str += ")";
		System.out.println(str);
		return str;
	}

}
