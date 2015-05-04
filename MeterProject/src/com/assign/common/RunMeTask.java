package com.assign.common;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.pwa.common.DateUtil;
import com.pwa.common.Env;
import com.pwa.common.MessageList;
import com.pwa.common.exception.CMException;
import com.pwa.data.impoter.AssignAllImporter;
import com.pwa.data.impoter.AssignImporter;
import com.pwa.data.model.head.Head;

public class RunMeTask {
	static Logger log = Logger.getLogger(RunMeTask.class);
	public static void execute() {
		//System.out.println(">>>>>> Assign >>>>>>>>>>>");
		log.debug(">>>>>> Assign >>>>>>>>>>>");
		MessageList msgList = new MessageList();
		Date tranDate = new Date();
		int date = tranDate.getDate();
		
		try {
		List<Head> headList = AssignAllImporter.getHeadByDateAll(String.valueOf(date),msgList);
		//String path = "d://Meter//wattana//";
		//String path = "/home/jbspro/pwa1/watthana/IN/DATA/";
		//String path = "/home/jbspro/domains/jbsprosmart.com/public_html/pwa1/watthana/IN/DATA/";
		
		//System.out.println("path = "+path);
		for(Head head : headList){
			System.out.println("BR Head : "+head.getBr());
			System.out.println("RTE Head : "+head.getRte());
			System.out.println("READID Head : "+head.getReaderid());
			System.out.println("AssignDate Head : "+head.getAssigndate());
			String branch = head.getBr();
			//String path = Env.get("localhost.IN");
			String path = Env.get(branch+".IN");
			String absolutePath = path+head.getRte()+".TXT";
			String type = "1";
			long rowEffect = AssignImporter.run(type, absolutePath, tranDate,head.getReaderid(), msgList);
			msgList.addFirstMessage("I", "มอบหมายเส้นทางวันที่" + DateUtil.formatThaiDate(tranDate) + " สำเร็จทั้งหมด" + rowEffect + " รายการ");
		}
		} catch (CMException e) {
			msgList.addMessage(e);
		} catch (Exception e) {
			msgList.addMessage(e);
			e.printStackTrace();
		}finally{
		}
	}

public static void main(String[] srg){
	
	RunMeTask.execute();
	
}
}