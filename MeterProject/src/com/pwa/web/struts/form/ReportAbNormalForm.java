/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.pwa.web.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class ReportAbNormalForm extends ReportNormalForm {

	private String abnormaltype;
	

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	public void setAbnormaltype(String abnormaltype) {
		this.abnormaltype = abnormaltype;
	}

	public String getAbnormaltype() {
		return abnormaltype;
	}

	


}