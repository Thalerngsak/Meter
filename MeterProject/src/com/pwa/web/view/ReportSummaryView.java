package com.pwa.web.view;


public class ReportSummaryView extends ReportNormalView {
	private int custCount;
	private int saveCount;
	private int remainCount;
	private int noBillCount;
	private int abnormalCount;
	private String br;
	private String reportType;
	private String reportTypeDesc;
	
	public int getSaveCount() {
		return saveCount;
	}
	public void setSaveCount(int saveCount) {
		this.saveCount = saveCount;
	}
	public int getRemainCount() {
		return remainCount;
	}
	public void setRemainCount(int remainCount) {
		this.remainCount = remainCount;
	}
	public int getCustCount() {
		return custCount;
	}
	public void setCustCount(int custCount) {
		this.custCount = custCount;
	}
	public int getNoBillCount() {
		return noBillCount;
	}
	public void setNoBillCount(int noBillCount) {
		this.noBillCount = noBillCount;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportTypeDesc(String reportTypeDesc) {
		this.reportTypeDesc = reportTypeDesc;
	}
	public String getReportTypeDesc() {
		return reportTypeDesc;
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
	 * @return the abnormalCount
	 */
	public int getAbnormalCount() {
		return abnormalCount;
	}
	/**
	 * @param abnormalCount the abnormalCount to set
	 */
	public void setAbnormalCount(int abnormalCount) {
		this.abnormalCount = abnormalCount;
	}

}
