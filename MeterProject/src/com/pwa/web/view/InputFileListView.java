package com.pwa.web.view;

import com.pwa.data.model.detail.AbstractDetail;

public class InputFileListView extends AbstractDetail {
	private String trandateStr;	
	private String prsmtrrddtStr;
	private String br;	
	private String rte;
	private String custcode;
	private String mtrrdrname;
	private String diff; // abnormal
	
	public void setMtrrdrname(String mtrrdrname) {
		this.mtrrdrname = mtrrdrname;
	}

	public String getMtrrdrname() {
		return mtrrdrname;
	}

	public void setDiff(String diff) {
		this.diff = diff;
	}

	public String getDiff() {
		return diff;
	}

	public String getCustcode() {
		return custcode;
	}

	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}

	public String getRte() {
		return rte;
	}

	public void setRte(String rte) {
		this.rte = rte;
	}

	public void setTrandateStr(String trandateStr) {
		this.trandateStr = trandateStr;
	}

	public String getTrandateStr() {
		return trandateStr;
	}

	public void setPrsmtrrddtStr(String prsmtrrddtStr) {
		this.prsmtrrddtStr = prsmtrrddtStr;
	}

	public String getPrsmtrrddtStr() {
		return prsmtrrddtStr;
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
	
}
