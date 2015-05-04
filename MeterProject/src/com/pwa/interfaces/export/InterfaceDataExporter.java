package com.pwa.interfaces.export;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.TreeMap;

import com.pwa.data.DBHelper;
import com.pwa.interfaces.ExportProcess;

public class InterfaceDataExporter extends ExportProcess {
	private String absFileName = "";
	private TreeMap c = null;

	public static void run(String destAbsFileName,TreeMap c) throws Exception {
		new InterfaceDataExporter(destAbsFileName,c).export("Data01");
	}

	public InterfaceDataExporter(String localFileName, TreeMap c) {
		this.absFileName = localFileName;
		this.c = c;
	}

	@Override
	protected String getModifyConfigFileName(String fileName) {
		return this.absFileName;
	}

	@Override
	protected String getBodyQuery() {
		String sql = "select * from detail " + DBHelper.getConStr(c);
		System.out.println(sql);
		
		return sql;
	}
	
	@Override
	protected void setPSParam(PreparedStatement ps) throws SQLException {
		DBHelper.setParam(ps, c);
	}

}
