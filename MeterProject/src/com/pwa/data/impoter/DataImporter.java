package com.pwa.data.impoter;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.pwa.common.DateUtil;
import com.pwa.common.Env;
import com.pwa.common.FileUtil;
import com.pwa.common.MessageList;
import com.pwa.common.exception.CMException;
import com.pwa.data.DBHelper;
import com.pwa.data.model.detail.Detail;
import com.pwa.data.model.detail.DetailId;
import com.pwa.data.model.head.Head;
import com.pwa.web.view.InputFileListView;

public class DataImporter {
	static Logger log = Logger.getLogger(DataImporter.class);

	public static long run(ArrayList inputList, MessageList msgList) throws Exception {
		long r = 0;
		if(inputList.size() > 0) {
			log.debug(">>>>>> DataImporter.run >>>>>>>>>>> ");
			logCodeName(inputList, msgList);
			logTrandate(inputList, msgList);
			logRteGrpCount(inputList, msgList);
			r = insertDetail(inputList, msgList);
		}
		return r;
	}

	public static long insertDetail(ArrayList inputList, MessageList msgList) throws Exception {
		return AssignImporter.insertDetail(inputList, msgList);
	}

	public static ArrayList logFileInfoAndGetInputList(String filename,String tranDate, MessageList msgList) throws Exception {
		ArrayList inputList = getInputFileDataList(filename,tranDate);
		//log.debug(">>>>>> logFileInfoAndGetInputList >>>>>>>>>>> ");
		if(inputList.size() > 0){
			logCodeName(inputList, msgList);
			logTrandate(inputList, msgList);
			logRteGrpCount(inputList, msgList);
		}
		
		return inputList;
	}

	public static void logTrandate(ArrayList inputList, MessageList msgList) {
		if (inputList.size() != 0) {
			Date date = ((Detail) inputList.get(0)).getTrandate();
			msgList.addMessage("I", "วันที่มอบหมายงาน " + DateUtil.formatThaiDate(date) + " จำนวน " + inputList.size() + " รายการ");
		}
		msgList.addMessage("I",  " จำนวน " + inputList.size() + " รายการ");
	}

	public static void logRteGrpCount(ArrayList inputList, MessageList msgList) {
		AssignImporter.logRteGrpCount(inputList, msgList);
	}

	public static void logCodeName(ArrayList inputList, MessageList msgList) throws CMException {
		AssignImporter.logCodeName(inputList, msgList);
	}

	public static ArrayList getInputFileDataList(String filename,String tranDate) throws Exception {
		ArrayList r = new ArrayList();
		//log.debug("getData from file=" + filename);

		BufferedReader br = null;
		try {
			br = FileUtil.getBufferReader(filename);
			String strLine;
			if(br != null){
				while ((strLine = br.readLine()) != null) {
					// System.out.println(strLine);
					String[] parts = ImportUtil.splitBySC(strLine);
					if (parts.length != 50) { // validate format
						log.debug("parts lenght=" + parts.length);
						throw new CMException("E", "เลือกไฟล์ไม่ถูกต้อง กรุณาเลือกไฟล์ข้อมูลสถานะอ่านมาตรที่ส่งไม่สำเร็จ");
					}
					Detail input = getDetail(parts,tranDate);
					if(input.getPrsmtrrddty() != null && input.getPrsmtrrddtm() != null){
						r.add(input);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			FileUtil.close(br);
		}
		//log.debug("number input in file =" + r.size());
		return r;
	}

	@SuppressWarnings("deprecation")
	private static Detail getDetail(String[] parts,String tranDate) throws Exception {
		DetailId id = new DetailId();
		Detail r = new Detail(id);
		int i = 0;
		id.setBr(parts[i++]); //1
		//id.setZn(parts[i++]);
		r.setChkdigit(parts[i++]); //2
		id.setCustcode(parts[i++]); //3
		id.setRte(parts[i++]); //.4

		i = ImportUtil.setInputDetail(parts, r, i);

		// differnet from input column
		//String date = new SimpleDateFormat("ddMMyy").format(new Date());
		//log.debug("date =" + date);
		r.setTrandate(ImportUtil.toDate(tranDate,"yyMMdd",Env.LOCALE_TH));
		r.setType("1"); // from interface file
		if(r.getPrsmtrrddty() != null && r.getPrsmtrrddtm() != null){
			id.setRevym(r.getPrsmtrrddty()+r.getPrsmtrrddtm());
		}
		
		return r;
	}
	public static List<Head> getInputFileList() throws Exception {
		
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
        String sql = "select * from head order by BR,ASSIGNDATE,READERID,RTE ";
        List<Head> rList = new ArrayList<Head>();
		try {
			con = DBHelper.getDBConnection();
			p = con.prepareStatement(sql);
			//log.debug(sql);
			rs = p.executeQuery();

			while (rs.next()) {
				Head v = new Head();
				
				v.setBr(rs.getString("BR"));
				v.setRte(rs.getString("RTE"));
				v.setZn(rs.getString("ZN"));
				v.setTotnew(rs.getString("TOTNEW"));
				v.setTotrec(rs.getString("TOTREC"));
				v.setTotread(rs.getString("TOTREAD"));
				v.setReaderid(rs.getString("READERID"));
				v.setAssigndate(rs.getString("ASSIGNDATE"));
				v.setSendflag(rs.getString("SENDFLAG"));
				rList.add(v);
			}
			//log.debug(rList.size());
		} finally {
			DBHelper.closeAll(con, p, rs);
		}
		return rList;
	}

}
