package com.pwa.web.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class ImportPlanForm extends BaseForm {

	private FormFile importPlanFile;

	/*
	 * Generated Methods
	 */

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	public FormFile getImportPlanFile() {
		return importPlanFile;
	}

	public void setImportPlanFile(FormFile importPlanFile) {
		this.importPlanFile = importPlanFile;
	}


}
