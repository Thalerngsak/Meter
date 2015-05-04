package com.pwa.web.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class CheckPlanForm extends BaseForm {

	private String br;
	private String zn;
	private String rte;
	private String totnew;
	private String totrec;
	private String totread;
	private String readerid;
	private String assigndate;
	private String sendflag;

	/*
	 * Generated Methods
	 */

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	/**
	 * @return the br
	 */
	public String getBr() {
		return br;
	}

	/**
	 * @param br the br to set
	 */
	public void setBr(String br) {
		this.br = br;
	}

	/**
	 * @return the zn
	 */
	public String getZn() {
		return zn;
	}

	/**
	 * @param zn the zn to set
	 */
	public void setZn(String zn) {
		this.zn = zn;
	}

	/**
	 * @return the rte
	 */
	public String getRte() {
		return rte;
	}

	/**
	 * @param rte the rte to set
	 */
	public void setRte(String rte) {
		this.rte = rte;
	}

	/**
	 * @return the totnew
	 */
	public String getTotnew() {
		return totnew;
	}

	/**
	 * @param totnew the totnew to set
	 */
	public void setTotnew(String totnew) {
		this.totnew = totnew;
	}

	/**
	 * @return the totrec
	 */
	public String getTotrec() {
		return totrec;
	}

	/**
	 * @param totrec the totrec to set
	 */
	public void setTotrec(String totrec) {
		this.totrec = totrec;
	}

	/**
	 * @return the totread
	 */
	public String getTotread() {
		return totread;
	}

	/**
	 * @param totread the totread to set
	 */
	public void setTotread(String totread) {
		this.totread = totread;
	}

	/**
	 * @return the readerid
	 */
	public String getReaderid() {
		return readerid;
	}

	/**
	 * @param readerid the readerid to set
	 */
	public void setReaderid(String readerid) {
		this.readerid = readerid;
	}

	/**
	 * @return the assigndate
	 */
	public String getAssigndate() {
		return assigndate;
	}

	/**
	 * @param assigndate the assigndate to set
	 */
	public void setAssigndate(String assigndate) {
		this.assigndate = assigndate;
	}

	/**
	 * @return the sendflag
	 */
	public String getSendflag() {
		return sendflag;
	}

	/**
	 * @param sendflag the sendflag to set
	 */
	public void setSendflag(String sendflag) {
		this.sendflag = sendflag;
	}


}
