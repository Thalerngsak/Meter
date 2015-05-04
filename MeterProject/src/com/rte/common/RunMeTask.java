package com.rte.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.PairValueDesc;
import com.pwa.common.Env;
import com.pwa.common.FileUtil;
import com.pwa.common.MessageList;
import com.pwa.web.helper.WebUtil;

public class RunMeTask {
	static Logger log = Logger.getLogger(RunMeTask.class);
	public static void execute() {
		log.debug(">>>>>> RTE >>>>>>>>>>>");
		MessageList msgList = new MessageList();
		ArrayList<PairValueDesc> branchList = WebUtil.getBranchList();
		for(PairValueDesc branchValues : branchList){
			
			log.debug(">>>>>>"+ branchValues.getValue()+" >>>>>>>>>>>");
			
		try{
			String path =  Env.get(branchValues.getValue()+".RTECTRL");
			String absolutePath = path+"IN/RTECTRL.TXT";
			String desc = path + "RTECTRL.TXT";
			if(new File(absolutePath).exists()){
				//Delete exist file
				if(new File(desc).exists()){
					FileUtil.deleteDir(desc);
				}
				//Create new one file
				File importFile = FileUtil.createBackupFile(absolutePath,desc); 
				msgList.addFirstMessage("I", "Upload File "+importFile.getName()+" สำเร็จ");
			}
		} catch (Exception e) {
			msgList.addMessage(e);
			e.printStackTrace();
		}finally{
		}
		}
	}

public static void main(String[] srg){
	
	RunMeTask.execute();
	
}
}