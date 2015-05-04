package com.pwa.data.model.detail;

/**
 * DetailId entity. @author MyEclipse Persistence Tools
 */
public class DetailId extends AbstractDetailId implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public DetailId() {
	}

	/** full constructor */
	public DetailId(String br, String custcode, String rte,String revym) {
		super(br, custcode, rte,revym);
	}
}
