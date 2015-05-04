package com.pwa.data.impoter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.pwa.common.MessageList;
import com.pwa.data.DBHelper;
import com.pwa.data.model.detail.Detail;
import com.pwa.data.model.detail.DetailId;
import com.pwa.web.view.ExportDataView;

public class ExportDataImporter {
	static Logger log = Logger.getLogger(ExportDataImporter.class);

	public static long run(ArrayList inputList,String fileName,String path, MessageList msgList) throws Exception {
		long r = 0;
		if(inputList.size() > 0) {
			insertExportData(inputList,fileName,path, msgList);
		}
		return r;
	}

	public static void insertExportData(ArrayList inputList,String fileName,String path, MessageList msgList) throws Exception {
		
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
        String sql = " INSERT INTO export values (?,?,?,?,?,?) ";
			Detail detail = (Detail)inputList.get(0);
			try {
				con = DBHelper.getDBConnection();
				//p = con.prepareStatement(sql);
				
				 PreparedStatement pstmInsert = con.prepareStatement (sql);
				 pstmInsert.setString (1, detail.getId().getBr());
				 pstmInsert.setString (2, detail.getId().getRte());
				 pstmInsert.setString (3, detail.getCodeid());
				 pstmInsert.setDate(4, new java.sql.Date(detail.getTrandate().getTime()));
				 pstmInsert.setString (5, fileName);
				 pstmInsert.setString (6, path);
				//log.debug(sql);
				 int row = pstmInsert.executeUpdate();
				 System.out.println("total update "+row);
				//log.debug(rList.size());
			} finally {
				con.commit();
				DBHelper.closeAll(con, p, rs);
			}
	}
public static ExportDataView getExportData(String fileName, MessageList msgList) throws Exception {
		
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
        String sql = " select * from export where FILENAME = '"+fileName+"' ";
        ExportDataView v = new ExportDataView();
        try {
			con = DBHelper.getDBConnection();
			p = con.prepareStatement(sql);
			System.out.println(sql);
			rs = p.executeQuery();
			
			if (rs.next()) {
				v.setPath(rs.getString("PATH"));
			}
		} finally {
			DBHelper.closeAll(con, p, rs);
		}
        return v;
	}
public static void main(String[] args) throws Exception{
	System.out.println(">>>>>>>>>>>> Test Main >>>>>>>>>");
	ArrayList inputList = new ArrayList();
	String fileName ="";
	String path = "";
	Detail detail = new Detail();
	DetailId detailId = new DetailId();
	detail.setId(detailId);
	detailId.setRte("3333");
	detail.setBranch("1111111");
    detail.setCodeid("22222");
    detail.setTrandate(new Date());
	inputList.add(detail);
	MessageList msgList = new MessageList();
	insertExportData(inputList,fileName,path,msgList);
}
}
