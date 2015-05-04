package com.pwa.data.impoter;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pwa.common.Env;
import com.pwa.common.FileUtil;
import com.pwa.common.MessageList;
import com.pwa.common.ProgressManager;
import com.pwa.common.exception.CMException;
import com.pwa.data.model.dbst44.Dbst44;
import com.pwa.data.model.dbst44.Dbst44DAO;
import com.pwa.data.model.detail.Detail;
import com.pwa.data.model.detail.DetailDAO;
import com.pwa.data.model.detail.DetailDAOExt;
import com.pwa.data.model.detail.DetailHistory;
import com.pwa.data.model.detail.DetailHistoryDAOExt;
import com.pwa.data.model.detail.DetailId;

public class AssignImporter {
	static Logger log = Logger.getLogger(AssignImporter.class);

	public static long run(String type, String filename, Date tranDate,String codeId, MessageList msgList) throws Exception {
		long r = 0;
		ArrayList inputList = getInputFileDataList(type, filename, tranDate,codeId); // read file and build to list
		//logCodeName(inputList, msgList);
		filterReadList(inputList, msgList); // check read flag for update		
		logRteGrpCount(inputList, msgList); // log rte
		r = insertDetail(inputList, msgList); // insert to db
		return r;
	}

	public static long insertDetail(ArrayList inputList, MessageList msgList) throws Exception {
		log.debug(">>>>>> AssignImporter.insertDetail Start >>>>>>>>>>> ");
		log.debug("insert input =" + inputList.size());
		DetailDAOExt dao = new DetailDAOExt();
		Session session = dao.getSession();
		Transaction tx = null;
		Transaction txHistory = null;
		try {
			tx = session.beginTransaction();
			//txHistory = daoHistorySession.beginTransaction();
			// session.clear(); // re-open
			for (int i = 0; i < inputList.size(); i++) {
				Detail input = (Detail) inputList.get(i);
				//DetailHistory detalHis = new DetailHistory();
				//BeanUtils.copyProperties(detalHis, input);
				//System.out.println("REVYM : "+input.getId().getRevym());
				dao.saveOrUpdate(input); // replace old one
				//daoHistory.saveOrUpdate(detalHis);
				//dao.save(input);
				ProgressManager.setProgress( (int)( (double)(i+1)/inputList.size() *100));
				//Thread.sleep(1000);
				if((i+1)%100 ==0){
					//System.out.println( "mem="+Runtime.getRuntime().totalMemory());
				}
			}
			tx.commit();
			//txHistory.commit();
		} catch (Exception e) {
			tx.rollback();
			//txHistory.rollback();
			throw e;
		} finally {
			session.close();
			//daoHistorySession.close();
		}
		DetailHistoryDAOExt daoHistory = new DetailHistoryDAOExt();
		Session daoHistorySession = daoHistory.getSession();
		try {
			//tx = session.beginTransaction();
			txHistory = daoHistorySession.beginTransaction();
			// session.clear(); // re-open
			for (int i = 0; i < inputList.size(); i++) {
				Detail input = (Detail) inputList.get(i);
				DetailHistory detalHis = new DetailHistory();
				BeanUtils.copyProperties(detalHis, input);
				
				//System.out.println("REVYM : "+input.getId().getRevym());
				
				//dao.saveOrUpdate(input); // replace old one
				daoHistory.saveOrUpdate(detalHis);
				//dao.save(input);
				ProgressManager.setProgress( (int)( (double)(i+1)/inputList.size() *100));
				//Thread.sleep(1000);
				if((i+1)%100 ==0){
					//System.out.println( "mem="+Runtime.getRuntime().totalMemory());
				}
			}
			//tx.commit();
			txHistory.commit();
		} catch (Exception e) {
			//tx.rollback();
			txHistory.rollback();
			throw e;
		} finally {
			//session.close();
			daoHistorySession.close();
		}
		log.debug(">>>>>> AssignImporter.insertDetail END >>>>>>>>>>> ");
		return inputList.size();
	}

	private static void filterReadList(ArrayList inputList, MessageList msgList) {
		DetailDAOExt dao = new DetailDAOExt();
		Session session = dao.getSession();
		ArrayList removeList = new ArrayList();
		try {
			if (inputList.size() != 0) {
				ArrayList l = (ArrayList) dao.findByReadTrandateAndCodeID((Detail) inputList.get(0));

				for (int i = 0; i < l.size(); i++) {
					DetailId readID = ((Detail) l.get(i)).getId();
					for (int j = 0; j < inputList.size(); j++) {
						Detail input = (Detail) inputList.get(j);

						if (input.getId().equals(readID)) { // read leaw
							inputList.remove(input); // remove from insert list
							removeList.add(input);
							continue;
						}
					}
				}
			}
		} finally {
			session.close();
		}
		if (!removeList.isEmpty()) {
			msgList.addMessage("W", "ข้อมูลที่นำเข้ามีพนักงานทำการอ่านมาตรไปแล้ว " + removeList.size() + " รายการ");
		}
	}

	private static void filterReadListOld(ArrayList inputList, MessageList msgList) {
		DetailDAO dao = new DetailDAO();
		Session session = dao.getSession();
		ArrayList removeList = new ArrayList();
		try {
			Iterator it = inputList.iterator();
			for (int i = 0; it.hasNext(); i++) { // preformance impact
				Detail input = (Detail) it.next();
				Detail inputDB = dao.findById(input.getId());
				if (inputDB != null && "1".equals(inputDB.getReadflag())) { // read leaw
					// inputList.remove(input); // remove from insert list
					it.remove();
					removeList.add(input);
				}
			}
		} finally {
			session.close();
		}
		if (!removeList.isEmpty()) {
			msgList.addMessage("W", "ข้อมูลที่นำเข้ามีพนักงานทำการอ่านมาตรไปแล้ว " + removeList.size() + " รายการ");
		}
	}

	public static void logRteGrpCount(ArrayList inputList, MessageList msgList) {
		// find rte group,count
		TreeMap m = new TreeMap();
		for (int i = 0; i < inputList.size(); i++) {
			Detail input = (Detail) inputList.get(i);
			String rte = input.getId().getRte();
			BigDecimal n = (BigDecimal) m.get(rte);
			if (n == null) {
				n = new BigDecimal(1);
			} else {
				n = n.add(new BigDecimal(1));
			}
			m.put(rte, n);
		}
		String rteStr = "เส้นทาง:รายการ ";
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String rte = (String) it.next();
			BigDecimal n = (BigDecimal) m.get(rte);
			rteStr += "[" + rte + ":" + n + "] ";
		}
		if (inputList.size() != 0) {
			msgList.addMessage("I", rteStr);
		}
	}

	public static void logCodeName(ArrayList inputList, MessageList msgList) throws CMException {
		Detail d = (Detail) inputList.get(0);
		Dbst44DAO dao = new Dbst44DAO();
		try {
			Dbst44 dbst44 = dao.findById(d.getCodeid());
			if (dbst44 == null) {
				throw new CMException("E", "ไม่พบพนักงาน CODEID=" + d.getCodeid());
			}
			msgList.addMessage("I", "พนักงานชื่อ " + dbst44.getMtrrdrname());
			dbst44.getMtrrdrname();
			// dao.getSession().evict(dbst44);
		} finally {

			dao.getSession().close();
		}
	}

	public static ArrayList getInputFileDataList(String type, String filename, Date tranDate,String codeId) throws Exception {
		ArrayList r = new ArrayList();
		log.debug("getData from file=" + filename);

		BufferedReader br = null;
		try {
			br = FileUtil.getBufferReader(filename);
			String strLine;
			if(br != null){
				while ((strLine = br.readLine()) != null) {
					String[] parts = ImportUtil.splitByComma(strLine);
					//if (parts.length != 40) { // validate format
						//log.debug("parts lenght="+parts.length);
	//					for(int i= 0;i< parts.length;i++){
	//						
	//						System.out.println("["+i+"] "+ parts[i]);
	//					}
						//throw new CMException("E", "เลือกไฟล์ไม่ถูกต้อง กรุณาเลือกไฟล์มอบหมายเส้นทาง");
					//}
					if(parts.length == 40){
						Detail input = getDetail(type, parts, tranDate,codeId);
						r.add(input);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			FileUtil.close(br);
		}
		log.debug("number input in file =" + r.size());
		return r;
	}

	private static Detail getDetail(String type, String[] parts, Date tranDate,String codeId) throws Exception {
		DetailId id = new DetailId();
		Detail r = new Detail(id);
		int i = 0;
		id.setBr(parts[i++]);//1
		id.setRte(parts[i++]); //2
		
		//id.setZn(parts[i++]);
		r.setSeq(parts[i++]);//3
		id.setCustcode(parts[i++]);//4
		r.setType(type); // type come with user selected
		r.setUsetype(parts[i++]);//5
		r.setOldtype(parts[i++]);//6
		r.setCuststat(parts[i++]);//7
		String bgncustdt = parts[i++];//8
		if(bgncustdt != null && !"".equals(bgncustdt)){
		   r.setBgncustdt(ImportUtil.toDate(bgncustdt,"yyMMdd",Env.LOCALE_TH));
		}
		String location = parts[i++];
		//System.out.println("Location >>>>>>>>>>>>> " +location );
		r.setMlocation(location == null ? "" : location);//9
		r.setName(parts[i++]);//10
		r.setAddr(parts[i++]);//11
		r.setAccode(parts[i++]);//12
		r.setMtrmkcode(parts[i++]);//13
		r.setMetersize(parts[i++]);//14
		r.setMeterno(parts[i++]);//15
		r.setControlmtr(parts[i++]);//16
		r.setMtrstat(parts[i++]);//17
		String lstmtrddt = parts[i++];//18
		if(lstmtrddt != null && !"".equals(lstmtrddt)){
		   r.setLstmtrddt(ImportUtil.toDate(lstmtrddt,"yyMMdd",Env.LOCALE_TH));
		}
		r.setLstmtrcnt(ImportUtil.toInt(parts[i++]));//19
		id.setRevym(parts[i++]);//20
		r.setNovat(parts[i++]);//21
		r.setAvgwtusg(ImportUtil.toInt(parts[i++]));//22
		r.setDiscntcode(parts[i++]);//23
		r.setInvoicecnt(parts[i++]);//24
		r.setInvflag(parts[i++]);//25
		r.setNomonth(ImportUtil.toInt(parts[i++]));//26
		r.setBillamt(ImportUtil.toDouble(parts[i++]));//27
		r.setOldmtrusg(ImportUtil.toInt(parts[i++]));//28
		r.setNoofhouse(ImportUtil.toInt(parts[i++]));//29
		r.setPwa_flag(parts[i++]);//30
		r.setEst(ImportUtil.toInt(parts[i++]));//31
		r.setWaterest(ImportUtil.toInt(parts[i++]));//32
		r.setDiscntsold(parts[i++]);//33
		r.setMincharge(parts[i++]);//34
		r.setBigmtrno(parts[i++]);//35
		r.setSmcnt(ImportUtil.toDouble(parts[i++]));//36
		r.setAvgwtusg(ImportUtil.toInt(parts[i++]));//37
		r.setSubdiscn(ImportUtil.toDouble(parts[i++]));//38
		r.setTaxno(parts[i++]);//39
		r.setBranch(parts[i++]);//40
		
		
		//i = ImportUtil.setInputDetail(parts, r, i,true); // true FLAG here (ddMMyy date format)

		// re-assign by input logic
		r.setReadflag("0");
		r.setBillflag("N");
		r.setCodeid(codeId);
		r.setComment("999");
		String dateImport = String.valueOf(tranDate.getDate()) ;
		if(dateImport.length() == 1){
			dateImport = "0"+dateImport;
		}
		r.setPrsmtrrddtd(dateImport);
		//r.setPrswtusg(0);
		//r.setPrsmtrcnt(0);

		//String date = new SimpleDateFormat("ddMMyy").format(new Date());
		//log.debug("date =" + date);
		//r.setTrandate(ImportUtil.toDate(date,"ddMMyy",Env.LOCALE_TH));
		r.setTrandate(tranDate);
		
//		if (tranDate.compareTo(r.getTrandate()) != 0) {
//			throw new CMException("E", "กรุณาเลือกวันที่มอบหมายงานให้ตรงกับวันที่ในไฟล์ซึ่งเป็น " + DateUtil.formatThaiDate(r.getTrandate()));
//		}

		return r;
	}

	public static void main(String[] args) throws Exception {
		// Date tranDate = new SimpleDateFormat("ddMMyyyy").parse("01012551"); // local
		// AssignImporter.run("1", "c:/detail.txt", tranDate, new MessageList());
		// System.out.println(tranDate);

		BigDecimal d = new BigDecimal(1);
		System.out.println(d);
		d.subtract(new BigDecimal(1));
		System.out.println(d);
	}
}
