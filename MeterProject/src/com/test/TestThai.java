package com.test;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class TestThai {

	static Logger log = Logger.getLogger(TestThai.class);

	public static void main(String[] args) {
		Connection conn = null;

		try {
			String userName = "root";
			String password = "123";
			String acc = "user=" + userName + "&password=" + password;
			String encode = "&characterEncoding=UTF-8";
			//String encode="";
			String url = "jdbc:mysql://localhost/billutf8?" + acc+encode;

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url);
			System.out.println("Database connection established");

			select(conn);
			//String r =ReadWriteFile.readfile();
			
			//insert(conn,r);
			
			
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("Database connection terminated");
				} catch (Exception e) { /* ignore close errors */
				}
			}
		}

	}

	private static void insert(Connection conn, String r) {
		String str = "insert into config (head) value('"+r+"')";
		Statement s =null;
		try {
			s= conn.prepareStatement(str);
			s.execute(str);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(s);
		}
		
		
		
	}

	private static void select(Connection conn) throws UnsupportedEncodingException {
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM config");

			if (rs.next()) {
				String a = rs.getString(1);
				a = new String(a.getBytes());
				System.out.println(a);

			}

			// Now do something with the ResultSet ....
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeRsS(stmt, rs);
		}
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
}
