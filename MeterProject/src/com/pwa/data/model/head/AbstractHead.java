package com.pwa.data.model.head;

/**
 * AbstractConfig entity provides the base persistence definition of the Config entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractHead implements java.io.Serializable {

	// Fields
	private String id;
	private String br;
	private String zn;
	private String rte;
	private String totnew;
	private String totrec;
	private String totread;
	private String readerid;
	private String assigndate;
	private String sendflag;

	// Constructors
	
	/**
	 * @param id
	 * @param br
	 * @param zn
	 * @param rte
	 * @param totnew
	 * @param totrec
	 * @param totread
	 * @param readerid
	 * @param assigndate
	 * @param sendflag
	 */
	public AbstractHead(String id, String br, String zn, String rte,
			String totnew, String totrec, String totread, String readerid,
			String assigndate, String sendflag) {
		super();
		this.id = id;
		this.br = br;
		this.zn = zn;
		this.rte = rte;
		this.totnew = totnew;
		this.totrec = totrec;
		this.totread = totread;
		this.readerid = readerid;
		this.assigndate = assigndate;
		this.sendflag = sendflag;
	}

	/**
	 * 
	 */
	public AbstractHead() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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