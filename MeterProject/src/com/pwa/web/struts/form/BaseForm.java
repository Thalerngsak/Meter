package com.pwa.web.struts.form;

import org.apache.struts.action.ActionForm;

public class BaseForm extends ActionForm {

	private String day;
	private String month;
	private String year;
	
	private String dateStr;
	
	private String codeid;
	private String rte;
	private String comment;
	private String billflag;
	private String readflag;	
	private String custcode;
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCodeid() {
		return codeid;
	}
	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
	public String getRte() {
		return rte;
	}
	public void setRte(String rte) {
		this.rte = rte;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getBillflag() {
		return billflag;
	}
	public void setBillflag(String billflag) {
		this.billflag = billflag;
	}
	public String getReadflag() {
		return readflag;
	}
	public void setReadflag(String readflag) {
		this.readflag = readflag;
	}
	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}
	public String getCustcode() {
		return custcode;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getDateStr() {
		return dateStr;
	}
}
