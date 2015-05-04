package com.pwa.data.model.config;

/**
 * AbstractConfig entity provides the base persistence definition of the Config entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractConfig implements java.io.Serializable {

	// Fields

	private String br;
	private String head;
	private String telno;
	private String lowtype1;
	private String lowtype2;
	private String lowtype3;
	private String bankdate;
	private String paydate;

	// Constructors

	/** default constructor */
	public AbstractConfig() {
	}

	/** minimal constructor */
	public AbstractConfig(String br) {
		this.br = br;
	}

	/** full constructor */
	public AbstractConfig(String br, String head, String telno, String lowtype1, String lowtype2, String lowtype3, String bankdate, String paydate) {
		this.br = br;
		this.head = head;
		this.telno = telno;
		this.lowtype1 = lowtype1;
		this.lowtype2 = lowtype2;
		this.lowtype3 = lowtype3;
		this.bankdate = bankdate;
		this.paydate = paydate;
	}

	// Property accessors

	public String getBr() {
		return this.br;
	}

	public void setBr(String br) {
		this.br = br;
	}

	public String getHead() {
		return this.head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getTelno() {
		return this.telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getLowtype1() {
		return this.lowtype1;
	}

	public void setLowtype1(String lowtype1) {
		this.lowtype1 = lowtype1;
	}

	public String getLowtype2() {
		return this.lowtype2;
	}

	public void setLowtype2(String lowtype2) {
		this.lowtype2 = lowtype2;
	}

	public String getLowtype3() {
		return this.lowtype3;
	}

	public void setLowtype3(String lowtype3) {
		this.lowtype3 = lowtype3;
	}

	public String getBankdate() {
		return this.bankdate;
	}

	public void setBankdate(String bankdate) {
		this.bankdate = bankdate;
	}

	public String getPaydate() {
		return this.paydate;
	}

	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

}