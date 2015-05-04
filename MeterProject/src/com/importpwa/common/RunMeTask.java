package com.importpwa.common;

import java.io.BufferedReader;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.PairValueDesc;
import com.pwa.common.Env;
import com.pwa.common.FileUtil;
import com.pwa.common.MessageList;
import com.pwa.data.impoter.DataImporter;
import com.pwa.data.impoter.ExportDataImporter;
import com.pwa.data.model.head.Head;
import com.pwa.web.helper.WebUtil;

public class RunMeTask {
	static Logger log = Logger.getLogger(RunMeTask.class);
	public static void execute() {
		//System.out.println(">>>>>> Import >>>>>>>>>>>");
		log.debug(">>>>>> Import >>>>>>>>>>>");
		try {
			String path = "";
			String fileName = "";
			BufferedReader br = null;
			String date = new SimpleDateFormat("ddMMyy",Env.LOCALE_TH).format(new Date());
			String month = date.substring(2,4);
			String year = date.substring(4,6);
			List<Head> fileList = DataImporter.getInputFileList();
			ArrayList importDataInputList = new ArrayList();
			MessageList msgList = new MessageList();
			for(Head file : fileList){
				//log.debug(">>>>>> Head >>>>>>>>>>>");
				List<PairValueDesc> dateList = WebUtil.getDateList();
				for(int i = 0;i < dateList.size();i++){
						fileName = "T"+file.getReaderid()+file.getRte()+year+month+dateList.get(i).getValue()+".TXT";
						//System.out.println("file name : "+fileName);
						//System.out.println("BR : "+file.getBr());
						String tranDateFile = "";
						if(fileName != null){
							tranDateFile = fileName.substring(11, 17);
						}
						try{
								//path =  Env.get("localhost.OUT");
								path =  Env.get(file.getBr()+".OUT");
								String absolutePath = path+fileName;
								//System.out.println("absolutePath = "+absolutePath);
								//log.debug(">>>>>> absolutePath >>>>>>>>>>> "+absolutePath);
								File importFile = new File(absolutePath);
								// check file and log (codename, rte count, trandate)
								//if(importFile.exists()){
									importDataInputList = DataImporter.logFileInfoAndGetInputList(importFile.getAbsolutePath(),tranDateFile, msgList);
									long rowEffect =DataImporter.run(importDataInputList, msgList);
									 if(rowEffect > 0 ){
										 log.debug(">>>>>> rowEffect >>>>>>>>>>> "+rowEffect);
								        try {
									    fileName = FileUtil.changeFileName(fileName);
										importFile = FileUtil.createBackupFile(absolutePath,path + "/Backup/" + fileName); // change fiel dynamic na
										FileUtil.deleteDir(absolutePath);
										if(importDataInputList.size() > 0 && importFile != null){
										   ExportDataImporter.run(importDataInputList, fileName, importFile.getAbsolutePath(), msgList);
										}
								        }catch(Exception e){
								        	e.printStackTrace();
								        }
									 }
							//}
						}catch(Exception e){
							e.printStackTrace();
						}
					 }
					}
					
				} catch (Exception e) {
					//msgList.addMessage(e);
					e.printStackTrace();
				}finally{
					//log.debug(m.took());
					//WebUtil.removeProgressManager(session);
		}
	}

public static void main(String[] srg){
	
	RunMeTask.execute();
	
}
}