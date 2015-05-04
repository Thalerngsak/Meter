package com.pwa.data.model.dbst44;

/**
 * AbstractDbst44 entity provides the base persistence definition of the Dbst44 entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDbst44 implements java.io.Serializable {

	// Fields

	private String mtrrdrcode;
	private String mtrrdrname;

	// Constructors

	/** default constructor */
	public AbstractDbst44() {
	}

	/** minimal constructor */
	public AbstractDbst44(String mtrrdrcode) {
		this.mtrrdrcode = mtrrdrcode;
	}

	/** full constructor */
	public AbstractDbst44(String mtrrdrcode, String mtrrdrname) {
		this.mtrrdrcode = mtrrdrcode;
		this.mtrrdrname = mtrrdrname;
	}

	// Property accessors

	public String getMtrrdrcode() {
		return this.mtrrdrcode;
	}

	public void setMtrrdrcode(String mtrrdrcode) {
		this.mtrrdrcode = mtrrdrcode;
	}

	public String getMtrrdrname() {
		return this.mtrrdrname;
	}

	public void setMtrrdrname(String mtrrdrname) {
		this.mtrrdrname = mtrrdrname;
	}

}