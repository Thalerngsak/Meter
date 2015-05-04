package com.pwa.web.view;

import com.pwa.data.model.detail.AbstractDetail;

public class InvoiceView extends AbstractDetail {
	private String custcode;
	private String zn;
	private String prsmtrrddtStr;
	private String rte;

	private int prsmtrcntcal;
	private int prswtusgcal;

	private String statusUser;  // สถานะผู้ใช้น้ำ
	// ส่วนลด (waiting)

	private String barcode;// barcode
	private String duepaydateStr;// กำหนดชำระภายในวันที่
	private String suspenddateStr;// ถูกตัดถ้าเกินวันที่
	
	private String payBankDateStr; // หักผ่านธนาคารภายในวันที่
	
	private String mtrszmean;
	
	private String lstmtrrddtStr;
	
	private String revym;

	public String getPrsmtrrddtStr() {
		return prsmtrrddtStr;
	}

	public void setPrsmtrrddtStr(String prsmtrrddtStr) {
		this.prsmtrrddtStr = prsmtrrddtStr;
	}

	public String getRte() {
		return rte;
	}

	public void setRte(String rte) {
		this.rte = rte;
	}

	public String getCustcode() {
		return custcode;
	}

	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}

	public String getZn() {
		return zn;
	}

	public void setZn(String zn) {
		this.zn = zn;
	}

	public String getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(String statusUser) {
		this.statusUser = statusUser;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setPrsmtrcntcal(int prsmtrcntcal) {
		this.prsmtrcntcal = prsmtrcntcal;
	}

	public int getPrsmtrcntcal() {
		return prsmtrcntcal;
	}

	public void setPrswtusgcal(int prswtusgcal) {
		this.prswtusgcal = prswtusgcal;
	}

	public int getPrswtusgcal() {
		return prswtusgcal;
	}

	public String getDuepaydateStr() {
		return duepaydateStr;
	}

	public void setDuepaydateStr(String duepaydateStr) {
		this.duepaydateStr = duepaydateStr;
	}

	public String getSuspenddateStr() {
		return suspenddateStr;
	}

	public void setSuspenddateStr(String suspenddateStr) {
		this.suspenddateStr = suspenddateStr;
	}

	public void setPayBankDateStr(String payBankDateStr) {
		this.payBankDateStr = payBankDateStr;
	}

	public String getPayBankDateStr() {
		return payBankDateStr;
	}

	/**
	 * @return the mtrszmean
	 */
	public String getMtrszmean() {
		return mtrszmean;
	}

	/**
	 * @param mtrszmean the mtrszmean to set
	 */
	public void setMtrszmean(String mtrszmean) {
		this.mtrszmean = mtrszmean;
	}

	/**
	 * @return the lstmtrrddtStr
	 */
	public String getLstmtrrddtStr() {
		return lstmtrrddtStr;
	}

	/**
	 * @param lstmtrrddtStr the lstmtrrddtStr to set
	 */
	public void setLstmtrrddtStr(String lstmtrrddtStr) {
		this.lstmtrrddtStr = lstmtrrddtStr;
	}

	/**
	 * @return the revym
	 */
	public String getRevym() {
		return revym;
	}

	/**
	 * @param revym the revym to set
	 */
	public void setRevym(String revym) {
		this.revym = revym;
	}

}
