/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.pwa.web.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class ImportDataForm extends BaseForm {

	private String assignDateStr;
	private FormFile assignFile;

	private String filename;

	/*
	 * Generated Methods
	 */

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	public String getAssignDateStr() {
		return assignDateStr;
	}

	public void setAssignDateStr(String assignDateStr) {
		this.assignDateStr = assignDateStr;
	}

	public FormFile getAssignFile() {
		return assignFile;
	}

	public void setAssignFile(FormFile assignFile) {
		this.assignFile = assignFile;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

}