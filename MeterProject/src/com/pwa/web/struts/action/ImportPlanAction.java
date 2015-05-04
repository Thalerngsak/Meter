package com.pwa.web.struts.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.pwa.common.MessageList;
import com.pwa.common.Meter;
import com.pwa.common.exception.CMException;
import com.pwa.data.impoter.PlanImporter;
import com.pwa.web.ScreenMsgUtil;
import com.pwa.web.helper.WebUtil;
import com.pwa.web.struts.form.ImportPlanForm;

public class ImportPlanAction extends BaseAction {
	public static Logger log = Logger.getLogger(AssignAction.class);

	public ActionForward main(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		ImportPlanForm f = (ImportPlanForm) form;
//		String type = WebUtil.getURIParam(request, "type");
//		log.debug("enter " + type);
//		session.setAttribute("type", type);
//
//		if (CUtil.isBlank(f.getDateStr())) {
//			f.setDateStr(DateUtil.formatLongThaiDate(new Date()));
//		}
		return mapping.findForward("main");
	}

	public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ImportPlanForm f = (ImportPlanForm) form;
		MessageList msgList = new MessageList();
		Meter m = new Meter();
		String br = (String)session.getAttribute("br");
		WebUtil.setupDefaultProgressManager(session); // setup progress
		// Process the FormFile save and import
		FormFile formFile = f.getImportPlanFile();
		
		try {
			
			WebUtil.validateExcelFileInfo(formFile);
			String fileName = formFile.getFileName();
			byte[] fileData = formFile.getFileData();
			// log.debug(new String(fileData));
			System.out.println (formFile.getFileName()); 
			System.out.println (formFile.getContentType()); 
			System.out.println (formFile.getFileSize());
			log.debug("br "+br);
			long rowEffect = PlanImporter.run(br,formFile.getInputStream(), msgList);
			//fileName = FileUtil.changeFileName(fileName);
			//File importFile = FileUtil.createFile(Env.get("meter.datadir") + "/input/" + fileName, fileData);

			//long rowEffect = AssignImporter.run(type, importFile.getAbsolutePath(), tranDate, msgList);
			msgList.addFirstMessage("I", "นำเข้าแผนสำเร็จทั้งหมด "+ rowEffect + " รายการ");
		
		} catch (CMException e) {
			msgList.addMessage(e);
		} catch (Exception e) {
			msgList.addMessage(e);
			e.printStackTrace();
		}finally{
			log.debug(m.took());
			WebUtil.removeProgressManager(session);
		}
		ActionForward forward = null;
		if (!msgList.isContainError()) {
			forward = mapping.findForward("success");
		} else {
			forward = mapping.findForward("main");
		}
		saveMessages(request, ScreenMsgUtil.getMsgsToScreen(msgList));
		return forward;
	}
}
