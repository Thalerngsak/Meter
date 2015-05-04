package com.pwa.data;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.pwa.common.Env;

public class SqlDBHelper extends DBHelper {
	private static Logger log = Logger.getLogger(SqlDBHelper.class);

	public static Connection getDBConnection() throws Exception {
		Connection conn = null;		
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setServerName(Env.get("sqldb.host"));
		ds.setPortNumber(Integer.valueOf(Env.get("sqldb.port")));
		ds.setDatabaseName(Env.get("sqldb.dbname"));
		ds.setUser(Env.get("sqldb.username"));
		ds.setPassword(Env.get("sqldb.password"));
		log.debug("url="+ds.getServerName()+":"+ds.getPortNumber()+","+ds.getDatabaseName()+","+ds.getUser());		
		conn = ds.getConnection();
		
//		Driver d = (Driver)Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
//		conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433", "sa", "123");

		
		return conn;
	}

	public static void main(String[] args)  {
		Connection conn = null;
		try {
			conn = SqlDBHelper.getDBConnection();

			boolean b = DBHelper.exeQueryGetIsFound("select * from dbst38", conn);
			System.out.println(b);

		}catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			DBHelper.close(conn);
		}
	}
}
