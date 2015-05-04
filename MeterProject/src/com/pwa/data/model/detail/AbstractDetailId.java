package com.pwa.data.model.detail;

/**
 * AbstractDetailId entity provides the base persistence definition of the DetailId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDetailId implements java.io.Serializable {

	// Fields

	private String br;
	//private String zn;
	private String custcode;
	private String rte;
	private String revym;

	// Constructors

	/** default constructor */
	public AbstractDetailId() {
	}

	/** full constructor */
	public AbstractDetailId(String br ,String custcode, String rte,String revym) {
		this.br = br;
		//this.zn = zn;
		this.custcode = custcode;
		this.rte = rte;
		this.revym = revym;
	}

	// Property accessors

	public String getBr() {
		return this.br;
	}

	public void setBr(String br) {
		this.br = br;
	}

//	public String getZn() {
//		return this.zn;
//	}
//
//	public void setZn(String zn) {
//		this.zn = zn;
//	}

	public String getCustcode() {
		return this.custcode;
	}

	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}

	public String getRte() {
		return this.rte;
	}

	public void setRte(String rte) {
		this.rte = rte;
	}

//	public String getBillmonth() {
//		return this.billmonth;
//	}
//
//	public void setBillmonth(String billmonth) {
//		this.billmonth = billmonth;
//	}

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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractDetailId))
			return false;
		AbstractDetailId castOther = (AbstractDetailId) other;

		return ((this.getBr() == castOther.getBr()) || (this.getBr() != null && castOther.getBr() != null && this.getBr().equals(castOther.getBr())))
				&& ((this.getCustcode() == castOther.getCustcode()) || (this.getCustcode() != null && castOther.getCustcode() != null && this
						.getCustcode().equals(castOther.getCustcode())))
				&& ((this.getRte() == castOther.getRte()) || (this.getRte() != null && castOther.getRte() != null && this.getRte().equals(
						castOther.getRte())))
				&& ((this.getRevym() == castOther.getRevym()) || (this.getRevym() != null && castOther.getRevym() != null && this.getRevym().equals(
						castOther.getRevym())))
						
				 ;
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getBr() == null ? 0 : this.getBr().hashCode());
		//result = 37 * result + (getZn() == null ? 0 : this.getZn().hashCode());
		result = 37 * result + (getCustcode() == null ? 0 : this.getCustcode().hashCode());
		result = 37 * result + (getRte() == null ? 0 : this.getRte().hashCode());
		result = 37 * result + (getRevym() == null ? 0 : this.getRevym().hashCode());
		return result;
	}

}