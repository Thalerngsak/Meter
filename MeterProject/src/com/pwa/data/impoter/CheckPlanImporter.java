package com.pwa.data.impoter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.pwa.common.MessageList;
import com.pwa.data.DBHelper;
import com.pwa.web.view.CheckPlanView;

public class CheckPlanImporter {
	
	static Logger log = Logger.getLogger(CheckPlanImporter.class);
	
	public static List<CheckPlanView> run(String id,MessageList msgList) throws Exception {
		List<CheckPlanView> r = null;
		
		r = getInputFileDataList(id,msgList); // read file and build to list
		
		return r;
	}
	public static List<CheckPlanView> getInputFileDataList(String id,MessageList msgList) throws Exception {
		List<CheckPlanView> r = null;
		try {
			    r = GetData(id,msgList);
		} catch (Exception e) {
			throw e;
		} finally {
			
		}
		return r;
	}
	
	private static List<CheckPlanView> GetData (String id,MessageList msgList) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
        String sql = "select * from head where br = "+id+" order by ASSIGNDATE,READERID ";
        List<CheckPlanView> r = new ArrayList<CheckPlanView>();
		try {
			con = DBHelper.getDBConnection();
			p = con.prepareStatement(sql);
			log.debug(sql);
			rs = p.executeQuery();

			while (rs.next()) {
				CheckPlanView v = new CheckPlanView();
				v.setBr(rs.getString("BR"));
				v.setRte(rs.getString("RTE"));
				v.setZn(rs.getString("ZN"));
				v.setTotnew(rs.getString("TOTNEW"));
				v.setTotrec(rs.getString("TOTREC"));
				v.setTotread(rs.getString("TOTREAD"));
				v.setReaderid(rs.getString("READERID"));
				v.setAssigndate(rs.getString("ASSIGNDATE"));
				v.setSendflag(rs.getString("SENDFLAG"));
				r.add(v);
			}
			log.debug(r.size());
		} finally {
			DBHelper.closeAll(con, p, rs);
		}

		return r;

	}
}
